package com.boss.bes.exam.pojo.VO.publish;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class ExamPublishRecordTableDataVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String publisher;
    private String createdTime;
    private String endTime;
    private Integer planPepoleNum;
    private Integer limitTime;
    private String title;
    private String descript;
    private String status;
    private String examSession;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long version;
    /**
     * 试卷发布次数
     */
    //TODO 试卷服务获取
    private Integer publishTimes;
    private String examiners;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long paperId;
}
