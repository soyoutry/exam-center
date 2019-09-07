
package com.boss.bes.exam.pojo.VO.report;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ExamReportRecordDetailTableDataVO {
    private String examiner;
    private Byte sex;
    private String title;
    private Double objectiveSubjectScore;
    private Double subjectvieSubjectScore;
    private Double score;
    /**
     * 排名 TODO 待生成
     */
    private Integer ranking;
    /**
     * 考试耗时
     */
    private String examCostTime;
    /**
     * TODO 根据成绩生成
     */
    private String abilityLabel;


}
