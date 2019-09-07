package com.boss.bes.exam.service;

import com.boss.bes.exam.pojo.DTO.publish.*;

import java.util.List;

/**
 * @see com.boss.bes.exam.service.impl.ExamPublishRecordServiceImpl
 */
public interface ExamPublishRecordService {
    List<ExamPublishRecordTableDataDTO> queryExamPublishRecord(ExamPublishRecordQueryFormDTO examPublishRecordQueryFormDTO);
    boolean addExamPublishRecord(ExamPublishRecordPublishFormDTO examPublishRecordPublishFormDTO);
    boolean deleteExamPublishRecord(List<ExamPublishRecordDeleteFormDTO> dtoList);
    boolean updateExamPublishRecord(ExamPublishRecordEditFormDTO examPublishRecordEditFormDTO);
}
