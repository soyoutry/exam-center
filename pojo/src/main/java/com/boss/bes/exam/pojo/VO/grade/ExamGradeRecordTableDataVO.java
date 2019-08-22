
package com.boss.bes.exam.pojo.VO.grade;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@SuppressWarnings("unused")
public class ExamGradeRecordTableDataVO {

    private Date actualEndTime;
    private String examSession;
    private String examiner;
    private Date markingStopTime;
    private BigDecimal objectiveSubjectScore;
    private String paper;
    private Date publishDate;
    private String status;
    private BigDecimal subjectvieSubjectScore;
    private String systemEvaluate;
    private String tel;

}
