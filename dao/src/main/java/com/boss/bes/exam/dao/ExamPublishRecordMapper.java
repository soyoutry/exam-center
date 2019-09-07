package com.boss.bes.exam.dao;

import com.boss.bes.exam.entity.ExamPublishRecord;
import com.boss.bes.exam.pojo.DTO.answersheet.ExamAnswerSheetRecordQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.publish.ExamPublishRecordDeleteFormDTO;
import com.boss.bes.exam.pojo.DTO.publish.ExamPublishRecordQueryFormDTO;
import tk.mybatis.MyMapper;

import java.util.List;

public interface ExamPublishRecordMapper extends MyMapper<ExamPublishRecord> {
    List<ExamPublishRecord> queryAnswerRecord(ExamAnswerSheetRecordQueryFormDTO examAnswerSheetRecordQueryFormDTO);
    List<ExamPublishRecord> queryExamPubulishRecord(ExamPublishRecordQueryFormDTO examPublishRecordQueryFormDTO);
    List<ExamPublishRecord> queryGrade(ExamGradeRecordQueryFormDTO examGradeRecordQueryFormDTO);
    int deleteExamPublishRecord(ExamPublishRecordDeleteFormDTO examPublishRecordDeleteFormDTO);
}