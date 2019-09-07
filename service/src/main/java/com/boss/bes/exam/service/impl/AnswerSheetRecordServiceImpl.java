package com.boss.bes.exam.service.impl;

import com.boss.bes.exam.dao.ExamPublishRecordMapper;
import com.boss.bes.exam.entity.ExamPublishRecord;
import com.boss.bes.exam.entity.ExamRecord;
import com.boss.bes.exam.pojo.DTO.answersheet.ExamAnswerSheetRecordQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.answersheet.ExamAnswerSheetRecordTableDataDTO;
import com.boss.bes.exam.service.AnswerSheetRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerSheetRecordServiceImpl implements AnswerSheetRecordService {
    @Autowired
    ExamPublishRecordMapper examPublishRecordMapper;

    @Override
    public List<ExamAnswerSheetRecordTableDataDTO> queryAnswerSheet(ExamAnswerSheetRecordQueryFormDTO examAnswerSheetRecordQueryFormDTO) {
        List<ExamPublishRecord> examPublishRecords = examPublishRecordMapper.queryAnswerRecord(examAnswerSheetRecordQueryFormDTO);
        return convert(examPublishRecords);
    }

    private List<ExamAnswerSheetRecordTableDataDTO> convert(List<ExamPublishRecord> examPublishRecords){
        List<ExamAnswerSheetRecordTableDataDTO> examAnswerSheetRecordTableDataDTOS= new ArrayList<>();
        for (ExamPublishRecord examPublishRecord : examPublishRecords) {
            ExamAnswerSheetRecordTableDataDTO examAnswerSheetRecordTableDataDTO = new ExamAnswerSheetRecordTableDataDTO();
            for (ExamRecord examRecord : examPublishRecord.getExamRecords()) {
                BeanUtils.copyProperties(examRecord,examAnswerSheetRecordTableDataDTO);
            }
            BeanUtils.copyProperties(examPublishRecord,examAnswerSheetRecordTableDataDTO);
            examAnswerSheetRecordTableDataDTOS.add(examAnswerSheetRecordTableDataDTO);
        }
        return examAnswerSheetRecordTableDataDTOS;
    }
}
