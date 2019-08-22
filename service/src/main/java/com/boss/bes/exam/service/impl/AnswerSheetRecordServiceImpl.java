package com.boss.bes.exam.service.impl;

import com.boss.bes.exam.dao.TExamRecordMapper;
import com.boss.bes.exam.pojo.DTO.answersheet.ExamAnswerSheetRecordTableDataDTO;
import com.boss.bes.exam.pojo.entity.TExamRecord;
import com.boss.bes.exam.service.AnswerSheetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Converter;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerSheetRecordServiceImpl implements AnswerSheetRecordService {
    @Autowired
    TExamRecordMapper examRecordMapper;

    @Override
    public List<ExamAnswerSheetRecordTableDataDTO> queryAnswerSheet(){
        List<TExamRecord> examRecords = examRecordMapper.query();
        List<ExamAnswerSheetRecordTableDataDTO> examAnswerSheetRecordTableDataDTOS= new ArrayList<>();
        for (TExamRecord examRecord : examRecords) {
            ExamAnswerSheetRecordTableDataDTO examAnswerSheetRecordTableDataDTO = new ExamAnswerSheetRecordTableDataDTO();
            Converter.copyProperties(examRecord,examAnswerSheetRecordTableDataDTO);
            examAnswerSheetRecordTableDataDTOS.add(examAnswerSheetRecordTableDataDTO);
        }
        return examAnswerSheetRecordTableDataDTOS;
    }
}
