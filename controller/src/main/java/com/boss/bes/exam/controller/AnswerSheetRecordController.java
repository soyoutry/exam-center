package com.boss.bes.exam.controller;

import com.boss.bes.exam.pojo.DTO.answersheet.ExamAnswerSheetRecordQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.answersheet.ExamAnswerSheetRecordTableDataDTO;
import com.boss.bes.exam.pojo.VO.answersheet.ExamAnswerSheetRecordQueryFormVO;
import com.boss.bes.exam.pojo.VO.answersheet.ExamAnswerSheetRecordTableDataVO;
import com.boss.bes.exam.service.AnswerSheetRecordService;
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
@RequestMapping("answersheet")
public class AnswerSheetRecordController {
    @Autowired
    private AnswerSheetRecordService answerSheetRecordService;
    private final static Integer PAGE_SIZE = 8;

    @Log
    @RequestMapping(value = "getanswersheet",method = RequestMethod.POST)
    public CommonResponse queryAll(@RequestBody CommonRequest<ExamAnswerSheetRecordQueryFormVO> commonRequest){
        ExamAnswerSheetRecordQueryFormVO examAnswerSheetRecordQueryFormVO = commonRequest.getBody();
        ExamAnswerSheetRecordQueryFormDTO examAnswerSheetRecordQueryFormDTO = new ExamAnswerSheetRecordQueryFormDTO();
        BeanUtils.copyProperties(examAnswerSheetRecordQueryFormVO,examAnswerSheetRecordQueryFormDTO);
        if(examAnswerSheetRecordQueryFormVO.getExamTimeRange()!=null&&examAnswerSheetRecordQueryFormVO.getExamTimeRange().size()!=0)
        {
            List<String> examTimeRange = DateToString.convert(examAnswerSheetRecordQueryFormVO.getExamTimeRange());
            examAnswerSheetRecordQueryFormDTO.setExamTimeRange(examTimeRange);
        }
        Page p = PageHelper.startPage(examAnswerSheetRecordQueryFormVO.getCurrentPage(),PAGE_SIZE);
        List<ExamAnswerSheetRecordTableDataDTO> tableDataDTOS = answerSheetRecordService.queryAnswerSheet(examAnswerSheetRecordQueryFormDTO);
        List<ExamAnswerSheetRecordTableDataVO> tableDataVOS = new ArrayList<ExamAnswerSheetRecordTableDataVO>();
        for (ExamAnswerSheetRecordTableDataDTO tableDataDTO : tableDataDTOS) {
            ExamAnswerSheetRecordTableDataVO tableDataVO = new ExamAnswerSheetRecordTableDataVO();
            BeanUtils.copyProperties(tableDataDTO,tableDataVO);
            tableDataVO.setEndTime(DateFormatUtil.format(tableDataDTO.getEndTime()));
            tableDataVO.setActualEndTime(DateFormatUtil.format(tableDataDTO.getActualEndTime()));
            tableDataVO.setActualStartTime(DateFormatUtil.format(tableDataDTO.getActualStartTime()));
            tableDataVOS.add(tableDataVO);
        }
        PageInfo<ExamAnswerSheetRecordTableDataVO> pageInfo = new PageInfo<>(tableDataVOS);
        Map<String,Object> map = new HashMap<>();
        map.put("total",p.getTotal());
        map.put("pageInfo",pageInfo);
        return new CommonResponse("","200","页面加载成功",false,map);
    }
}
