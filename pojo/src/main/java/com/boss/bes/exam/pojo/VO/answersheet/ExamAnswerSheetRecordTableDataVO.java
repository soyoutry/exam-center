
package com.boss.bes.exam.pojo.VO.answersheet;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ExamAnswerSheetRecordTableDataVO {
    /**
     * 考试纪录id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 考试结束时间
     */
    private String actualEndTime;
    /**
     * 考试开始时间
     */
    private String actualStartTime;
    /**
     * 考试截止时间
     */
    private String endTime;
    /**
     * 考试发布人
     */
    //TODO 改为string显示姓名
    private Long publisher;
    /**
     * 考试场次
     */
    private String examSession;
    /**
     * 答卷人姓名
     */
    private String examiner;
    /**
     * 客观主观题分数
     */
    private Double objectiveSubjectScore;
    private Double subjectvieSubjectScore;
    /**
     * 答卷人手机号
     */
    private String tel;
    /**
     * 考试名
     */
    private String title;

}
