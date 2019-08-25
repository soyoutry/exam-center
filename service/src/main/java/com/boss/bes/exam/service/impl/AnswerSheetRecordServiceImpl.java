package com.boss.bes.exam.service.impl;

import com.boss.bes.exam.dao.ExamPublishRecordMapper;
import com.boss.bes.exam.dao.ExamRecordMapper;
import com.boss.bes.exam.entity.ExamPublishRecord;
import com.boss.bes.exam.entity.ExamRecord;
import com.boss.bes.exam.pojo.DTO.answersheet.ExamAnswerSheetRecordTableDataDTO;
import com.boss.bes.exam.service.AnswerSheetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Converter;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerSheetRecordServiceImpl implements AnswerSheetRecordService {
    @Autowired
    ExamPublishRecordMapper examPublishRecordMapper;

    @Override
    public List<ExamAnswerSheetRecordTableDataDTO> queryAnswerSheet(){
        List<ExamPublishRecord> examPublishRecords = examPublishRecordMapper.query();
        List<ExamAnswerSheetRecordTableDataDTO> examAnswerSheetRecordTableDataDTOS= new ArrayList<>();
        for (ExamPublishRecord examPublishRecord : examPublishRecords) {
            ExamAnswerSheetRecordTableDataDTO examAnswerSheetRecordTableDataDTO = new ExamAnswerSheetRecordTableDataDTO();
            Converter.copyProperties(examPublishRecord,examAnswerSheetRecordTableDataDTO);
            for (ExamRecord examRecord : examPublishRecord.getExamRecords()) {
                Converter.copyProperties(examRecord,examAnswerSheetRecordTableDataDTO);
            }
            examAnswerSheetRecordTableDataDTOS.add(examAnswerSheetRecordTableDataDTO);
        }
        return examAnswerSheetRecordTableDataDTOS;
    }
}
