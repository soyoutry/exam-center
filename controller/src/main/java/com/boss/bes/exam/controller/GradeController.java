package com.boss.bes.exam.controller;

import bosssoft.hr.train.common.utils.Converter;
import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordTableDataDTO;
import com.boss.bes.exam.pojo.DTO.grade.MarkingAnswerDTO;
import com.boss.bes.exam.pojo.DTO.grade.MarkingPaperDTO;
import com.boss.bes.exam.pojo.VO.grade.ExamGradeRecordQueryFormVO;
import com.boss.bes.exam.pojo.VO.grade.ExamGradeRecordTableDataVO;
import com.boss.bes.exam.pojo.VO.grade.MarkingPaperVO;
import com.boss.bes.exam.service.GradeService;
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
@RequestMapping("grade")
public class GradeController {
    private final static Integer PAGE_SIZE = 8;
    @Autowired
    GradeService gradeService;
    @Autowired
    Converter converter;
    @MethodEnhancer
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public CommonResponse query(@RequestBody CommonRequest<ExamGradeRecordQueryFormVO> commonRequest){
        try {
            ExamGradeRecordQueryFormVO queryFormVO = commonRequest.getBody();
            ExamGradeRecordQueryFormDTO queryFormDTO = new ExamGradeRecordQueryFormDTO();
            BeanUtils.copyProperties(queryFormVO,queryFormDTO);
            if(queryFormVO.getEndTimeRange()!=null && queryFormVO.getEndTimeRange().size()!=0){
                List<String> endTimeRange = DateToString.convert(queryFormVO.getEndTimeRange());
                queryFormDTO.setEndTimeRange(endTimeRange);
            }
            Page p = PageHelper.startPage(queryFormVO.getCurrentPage(),PAGE_SIZE);
            List<ExamGradeRecordTableDataDTO> tableDataDTOS = gradeService.query(queryFormDTO);
            List<ExamGradeRecordTableDataVO> tableDataVOS = new ArrayList<>();
            for (ExamGradeRecordTableDataDTO tableDataDTO : tableDataDTOS) {
                ExamGradeRecordTableDataVO tableDataVO = new ExamGradeRecordTableDataVO();
                BeanUtils.copyProperties(tableDataDTO,tableDataVO);
                tableDataVO.setCreatedTime(DateFormatUtil.format(tableDataDTO.getCreatedTime()));
                tableDataVO.setActualEndTime(DateFormatUtil.format(tableDataDTO.getActualEndTime()));
                tableDataVO.setMarkingStopTime(DateFormatUtil.format(tableDataDTO.getMarkingStopTime()));
                setVoStatus(tableDataVO,tableDataDTO);
                tableDataVOS.add(tableDataVO);
            }
            PageInfo<ExamGradeRecordTableDataVO> pageInfo = new PageInfo<>(tableDataVOS);
            Map<String,Object> map = new HashMap<>();
            map.put("total",p.getTotal());
            map.put("pageInfo",pageInfo);
            return new CommonResponse("200","页面加载成功",false,map);

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new CommonResponse("200","页面加载失败",false,null);
    }


    @MethodEnhancer
    @RequestMapping(value = "markingpaper",method = RequestMethod.POST)
    public CommonResponse markingPaper(@RequestBody CommonRequest<MarkingPaperVO> commonRequest){
        MarkingPaperVO markingPaperVO = commonRequest.getBody();
        MarkingPaperDTO markingPaperDTO = new MarkingPaperDTO();
        BeanUtils.copyProperties(markingPaperVO,markingPaperDTO);
        List<MarkingAnswerDTO> markingAnswerDTOS = converter.convertList(markingPaperVO.getMarkingAnswerVOList(), MarkingAnswerDTO.class);
        markingPaperDTO.setMarkingAnswerDTOList(markingAnswerDTOS);
        //todo 添加总分和评价
        if (gradeService.markingPaper(markingPaperDTO)){
            return new CommonResponse("200","试卷评分成功",false,null);
        }
        return new CommonResponse("200","试卷评分失败",false,null);

    }

    private void setVoStatus(ExamGradeRecordTableDataVO tableDataVO,ExamGradeRecordTableDataDTO tableDataDTO){
       if (tableDataDTO.getStatus()!=null){
           if (tableDataDTO.getStatus()==1)
               tableDataVO.setStatus("已批阅");
           else
               tableDataVO.setStatus("未批阅");
       }
    }
}
