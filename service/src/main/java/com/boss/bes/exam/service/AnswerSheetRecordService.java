package com.boss.bes.exam.service;

import com.boss.bes.exam.pojo.DTO.answersheet.ExamAnswerSheetRecordQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.answersheet.ExamAnswerSheetRecordTableDataDTO;

import java.util.List;
public interface AnswerSheetRecordService {
    List<ExamAnswerSheetRecordTableDataDTO> queryAnswerSheet(ExamAnswerSheetRecordQueryFormDTO examAnswerSheetRecordQueryFormDTO);
}
