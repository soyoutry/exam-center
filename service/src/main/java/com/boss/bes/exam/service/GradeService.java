package com.boss.bes.exam.service;

import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordTableDataDTO;
import com.boss.bes.exam.pojo.DTO.grade.MarkingPaperDTO;

import java.util.List;

public interface GradeService {
    List<ExamGradeRecordTableDataDTO> query(ExamGradeRecordQueryFormDTO examGradeRecordQueryFormDTO) throws InstantiationException, IllegalAccessException;
    Boolean markingPaper(MarkingPaperDTO markingPaperDTO);
}
