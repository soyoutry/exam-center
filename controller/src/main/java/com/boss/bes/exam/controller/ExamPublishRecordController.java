package com.boss.bes.exam.controller;

import bosssoft.hr.train.common.utils.Converter;
import com.boss.bes.exam.pojo.DTO.publish.*;
import com.boss.bes.exam.pojo.VO.publish.*;
import com.boss.bes.exam.service.ExamPublishRecordService;
import com.boss.bes.exam.service.feign.CategoryFeignClient;
import com.boss.bes.exam.service.feign.PaperFeignClient;
import com.boss.bes.exam.utils.DateFormatUtil;
import com.boss.bes.exam.utils.DateToString;
import com.bosssoft.hr.train.bossbescommonlogging.annotation.Log;
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
    @Log
    @RequestMapping(value = "query")
    public CommonResponse query(@RequestBody CommonRequest<ExamPublishRecordQueryFormVO> commonRequest){
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
            tableDataVO.setCreatedTime(DateFormatUtil.format(tableDataDTO.getCreatedTime()));
            tableDataVO.setEndTime(DateFormatUtil.format(tableDataDTO.getEndTime()));
            tableDataVOS.add(tableDataVO);
        }
        PageInfo<ExamPublishRecordTableDataVO> pageInfo = new PageInfo<>(tableDataVOS);
        Map<String,Object> map = new HashMap<>();
        map.put("total",p.getTotal());
        map.put("pageInfo",pageInfo);
        return new CommonResponse("","200","页面加载成功",false,map);
    }

    @Log
    @RequestMapping(value = "save")
    public CommonResponse save(@RequestBody CommonRequest<ExamPublishRecordPublishFormVO> commonRequest){
        ExamPublishRecordPublishFormVO examPublishRecordPublishFormVO = commonRequest.getBody();
        ExamPublishRecordPublishFormDTO examPublishRecordPublishFormDTO = new ExamPublishRecordPublishFormDTO();
        BeanUtils.copyProperties(examPublishRecordPublishFormVO,examPublishRecordPublishFormDTO);
        if (examPublishRecordService.addExamPublishRecord(examPublishRecordPublishFormDTO)==true) {
            return new CommonResponse("1", "200", "添加成功", false, "success");
        }
        return new CommonResponse("1", "200", "添加失败", false, "failed");
    }

    @Log
    @RequestMapping(value = "del")
    public CommonResponse del(@RequestBody CommonRequest<List<ExamPublishRecordDeleteFormVO>> commonRequest){
        List<ExamPublishRecordDeleteFormVO> list = commonRequest.getBody();
        try {
            List<ExamPublishRecordDeleteFormDTO> dtoList = converter.convertList(list,ExamPublishRecordDeleteFormDTO.class);
            if(examPublishRecordService.deleteExamPublishRecord(dtoList))
                return new CommonResponse("","200","删除成功",false,"success");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return new CommonResponse("","200","删除失败",false,"failed");
    }

    @Log
    @RequestMapping(value = "update")
    public CommonResponse update(@RequestBody CommonRequest<ExamPublishRecordEditFormVO> commonRequest){
        ExamPublishRecordEditFormVO examPublishRecordEditFormVO = commonRequest.getBody();
        ExamPublishRecordEditFormDTO dto = new ExamPublishRecordEditFormDTO();
        BeanUtils.copyProperties(examPublishRecordEditFormVO,dto);
        if(examPublishRecordService.updateExamPublishRecord(dto))
            return new CommonResponse("","200","修改成功",false,null);
        return new CommonResponse("","200","修改失败",false,null);
    }

    @Log
    @RequestMapping(value = "getpaperinfo",method = RequestMethod.POST)
    public CommonResponse getPaperInfo(@RequestBody CommonRequest<Object> commonRequest){
        CommonResponse commonResponse = paperFeignClient.getPaperInfo(commonRequest);
        return commonResponse;
    }
    @Log
    @RequestMapping(value = "getCategoryInfo",method = RequestMethod.GET)
    public CommonResponse getCategory(){
        return categoryFeignClient.getCategoryInfo();
    }
}
