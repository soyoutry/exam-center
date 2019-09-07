
package com.boss.bes.exam.pojo.VO.grade;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ExamGradeRecordTableDataVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String actualEndTime;
    private String examSession;
    private String examiner;
    private String markingStopTime;
    private Double objectiveSubjectScore;
    private Long paperId;
    private String createdTime;
    private String status;
    private Double subjectvieSubjectScore;
    private String systemEvaluate;
    private String tel;
}
