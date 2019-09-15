package com.boss.bes.exam.controller;

import bosssoft.hr.train.common.utils.Converter;
import com.boss.bes.exam.pojo.DTO.publish.*;
import com.boss.bes.exam.pojo.VO.publish.*;
import com.boss.bes.exam.service.ExamPublishRecordService;
import com.boss.bes.exam.service.feign.CategoryFeignClient;
import com.boss.bes.exam.service.feign.PaperFeignClient;
import com.boss.bes.exam.service.feign.UserFeignClient;
import com.boss.bes.exam.utils.DateFormatUtil;
import com.boss.bes.exam.utils.DateToString;
import com.bosssoft.hr.train.bossbescommonlogging.annotation.MethodEnhancer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import protocol.CommonRequest;
import protocol.CommonResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("exampublishrecord")
public class ExamPublishRecordController {
    private final static Integer PAGE_SIZE = 8;

    @Autowired
    private Converter converter;
    @Autowired
    private ExamPublishRecordService examPublishRecordService;
    @Autowired
    private PaperFeignClient paperFeignClient;
    @Autowired
    private CategoryFeignClient categoryFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 列出考试发布记录
     * @param commonRequest
     * @return
     */
    @MethodEnhancer
    @RequestMapping(value = "query")
    public CommonResponse query(@RequestBody CommonRequest<ExamPublishRecordQueryFormVO> commonRequest){
        try {
            ExamPublishRecordQueryFormVO examPublishRecordQueryFormVO = commonRequest.getBody();
            ExamPublishRecordQueryFormDTO examPublishRecordQueryFormDTO = new ExamPublishRecordQueryFormDTO();
            BeanUtils.copyProperties(examPublishRecordQueryFormVO, examPublishRecordQueryFormDTO);
            if(examPublishRecordQueryFormVO.getExamTimeRange()!=null && examPublishRecordQueryFormVO.getExamTimeRange().size()!=0)
            {
                List<String> examTimeRange = DateToString.convert(examPublishRecordQueryFormVO.getExamTimeRange());
                examPublishRecordQueryFormDTO.setExamTimeRange(examTimeRange);
            }
            if(examPublishRecordQueryFormVO.getPublishTimeRange()!=null && examPublishRecordQueryFormVO.getPublishTimeRange().size()!=0)
            {
                List<String> publishTimeRange = DateToString.convert(examPublishRecordQueryFormVO.getPublishTimeRange());
                examPublishRecordQueryFormDTO.setPublishTimeRange(publishTimeRange);
            }
            Page p = PageHelper.startPage(examPublishRecordQueryFormVO.getCurrentPage(),PAGE_SIZE);
            List<ExamPublishRecordTableDataDTO> tableDataDTOS = examPublishRecordService.queryExamPublishRecord(examPublishRecordQueryFormDTO);
            List<ExamPublishRecordTableDataVO> tableDataVOS = new ArrayList<>();
            for (ExamPublishRecordTableDataDTO tableDataDTO : tableDataDTOS) {
                ExamPublishRecordTableDataVO tableDataVO = new ExamPublishRecordTableDataVO();
                BeanUtils.copyProperties(tableDataDTO,tableDataVO);
                //TODO id读出姓名
                if(tableDataDTO.getPublisher()!=null)
                    tableDataVO.setPublisher(tableDataDTO.getPublisher().toString());
                //TODO 状态转为文本
                if(tableDataDTO.getStatus()!=null)
                    tableDataVO.setStatus(tableDataDTO.getStatus().toString());
                // get publishTimes
                if (tableDataDTO.getPaperId()!=null){
                    CommonRequest<Long> request = new CommonRequest<>();
                    request.setHead(commonRequest.getHead());
                    request.setBody(tableDataDTO.getPaperId());
                    tableDataVO.setPublishTimes(converter.parseCommonResponse(paperFeignClient.getPublishedTimes(request),Integer.class));
                }
               // get examinersName
                tableDataVO.setExaminers(getExaminersName(tableDataDTO.getExaminers(),commonRequest));
                tableDataVO.setCreatedTime(DateFormatUtil.format(tableDataDTO.getCreatedTime()));
                tableDataVO.setEndTime(DateFormatUtil.format(tableDataDTO.getEndTime()));
                tableDataVOS.add(tableDataVO);
            }
            PageInfo<ExamPublishRecordTableDataVO> pageInfo = new PageInfo<>(tableDataVOS);
            Map<String,Object> map = new HashMap<>();
            map.put("total",p.getTotal());
            map.put("pageInfo",pageInfo);
            return new CommonResponse("200","页面加载成功",false,map);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new CommonResponse("200","页面加载失败",false,null);
    }

    /**
     * 发布一条考试发布记录
     * @param commonRequest
     * @return
     */
    @MethodEnhancer
    @RequestMapping(value = "save")
    public CommonResponse save(@RequestBody CommonRequest<ExamPublishRecordPublishFormVO> commonRequest){
        ExamPublishRecordPublishFormVO examPublishRecordPublishFormVO = commonRequest.getBody();
        ExamPublishRecordPublishFormDTO examPublishRecordPublishFormDTO = new ExamPublishRecordPublishFormDTO();
        BeanUtils.copyProperties(examPublishRecordPublishFormVO,examPublishRecordPublishFormDTO);
        if (examPublishRecordService.addExamPublishRecord(examPublishRecordPublishFormDTO)) {
            CommonRequest<Long> paperRequest = new CommonRequest<>();
            paperRequest.setHead(commonRequest.getHead());
            paperRequest.setBody(examPublishRecordPublishFormDTO.getPaperId());
            if (paperFeignClient.publishPaper(paperRequest).getHead().getCode().equals("200")){
                System.out.println("***************************************");
                System.out.println(paperFeignClient.publishPaper(paperRequest));
                return new CommonResponse( "200", "添加成功", false, "success");
            }
        }
        return new CommonResponse( "200", "添加失败", false, "failed");
    }

    /**
     * 删除考试发布记录
     * @param commonRequest
     * @return
     */
    @MethodEnhancer
    @RequestMapping(value = "del")
    public CommonResponse del(@RequestBody CommonRequest<List<ExamPublishRecordDeleteFormVO>> commonRequest){
        List<ExamPublishRecordDeleteFormVO> list = commonRequest.getBody();
        try {
            List<ExamPublishRecordDeleteFormDTO> dtoList = converter.convertList(list,ExamPublishRecordDeleteFormDTO.class);
            if(examPublishRecordService.deleteExamPublishRecord(dtoList))
                return new CommonResponse("200","删除成功",false,"success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResponse("200","删除失败",false,"failed");
    }


    /**
     * 更新考试发布记录
     * @param commonRequest
     * @return
     */
    @MethodEnhancer
    @RequestMapping(value = "update")
    public CommonResponse update(@RequestBody CommonRequest<ExamPublishRecordEditFormVO> commonRequest){
        ExamPublishRecordEditFormVO examPublishRecordEditFormVO = commonRequest.getBody();
        ExamPublishRecordEditFormDTO dto = new ExamPublishRecordEditFormDTO();
        BeanUtils.copyProperties(examPublishRecordEditFormVO,dto);
        if(examPublishRecordService.updateExamPublishRecord(dto))
            return new CommonResponse("200","修改成功",false,null);
        return new CommonResponse("200","修改失败",false,null);
    }

    @MethodEnhancer
    @RequestMapping(value = "getpaperinfo",method = RequestMethod.POST)
    public CommonResponse getPaperInfo(@RequestBody CommonRequest<String> commonRequest){
        return paperFeignClient.getPaperInfoByName(commonRequest);
    }

    @MethodEnhancer
    @RequestMapping(value = "getuserinfo",method = RequestMethod.POST)
    public CommonResponse getUserInfo(@RequestBody CommonRequest<String> commonRequest){
        return userFeignClient.getUserInfo(commonRequest);
    }

    @MethodEnhancer
    @RequestMapping(value = "getCategoryInfo",method = RequestMethod.GET)
    public CommonResponse getCategory(){
        return categoryFeignClient.getCategoryInfo();
    }

    private String getExaminersName(List<Long> ids,CommonRequest commonRequest){
        if (ids!=null){
            List<String> names = new ArrayList<>();
            StringBuffer examiners = new StringBuffer();
            //去用户服务获取姓名
            for (Long id : ids) {
                CommonRequest<Long> request = new CommonRequest<>();
                request.setHead(commonRequest.getHead());
                request.setBody(id);
                String name = converter.parseCommonResponse(userFeignClient.getName(request), String.class);
                names.add(name);
            }
            for (String name : names) {
                examiners.append(name+";");
            }
            return examiners.toString();
        }
        return null;
    }
}
