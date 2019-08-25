package com.boss.bes.exam.controller;

import com.boss.bes.exam.pojo.DTO.answersheet.ExamAnswerSheetRecordTableDataDTO;
import com.boss.bes.exam.pojo.VO.answersheet.ExamAnswerSheetRecordTableDataVO;
import com.boss.bes.exam.service.AnswerSheetRecordService;
import com.bosssoft.hr.train.bossbescommonlogging.annotation.Log;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import protocol.CommonResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("answersheet")
public class AnswerSheetRecordController {
    @Autowired
    private AnswerSheetRecordService answerSheetRecordService;

    @Log
    @RequestMapping(value = "get",method = RequestMethod.GET)
    public CommonResponse queryAll(){
        PageHelper.startPage(1,10);
        List<ExamAnswerSheetRecordTableDataDTO> tableDataDTOS = answerSheetRecordService.queryAnswerSheet();
        List<ExamAnswerSheetRecordTableDataVO> tableDataVOS = new ArrayList<ExamAnswerSheetRecordTableDataVO>();
        for (ExamAnswerSheetRecordTableDataDTO tableDataDTO : tableDataDTOS) {
            ExamAnswerSheetRecordTableDataVO tableDataVO = new ExamAnswerSheetRecordTableDataVO();
            BeanUtils.copyProperties(tableDataDTO,tableDataVO);
            tableDataVOS.add(tableDataVO);
        }
        PageInfo<ExamAnswerSheetRecordTableDataVO> tableDataVOPageInfo = new PageInfo<ExamAnswerSheetRecordTableDataVO>(tableDataVOS);
        return CommonResponse.create("","","",false,tableDataVOPageInfo);
    }
}
