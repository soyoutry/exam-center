
package com.boss.bes.exam.pojo.VO.report;

import lombok.Data;

import java.math.BigDecimal;

@Data
@SuppressWarnings("unused")
public class ExamReportRecordDetailTableDataVO {

    private String abilityLabel;
    private String examCostTime;
    private String examiner;
    private BigDecimal objectiveSubjectScore;
    private Integer ranking;
    private BigDecimal score;
    private String sex;
    private BigDecimal subjectvieSubjectScore;
    private String title;

}
