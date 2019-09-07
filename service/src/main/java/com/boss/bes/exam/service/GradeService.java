package com.boss.bes.exam.service;

import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordTableDataDTO;
import com.boss.bes.exam.service.impl.GradeServiceImpl;

/**
 * @see GradeServiceImpl
 */
import java.util.List;
public interface GradeService {
    List<ExamGradeRecordTableDataDTO> query(ExamGradeRecordQueryFormDTO examGradeRecordQueryFormDTO) throws InstantiationException, IllegalAccessException;
}
