package com.boss.bes.exam.pojo.VO.publish;

import lombok.Data;

import java.util.Date;

@Data
public class ExamPublishRecordTableDataVO {
    private String publisher;
    private String publishTimeRange;
    private Date endTime;
    private Integer planPepoleNum;
    private Integer limitTime;
    private String examiner;
    private String title;
    private String descript;
    private String status;
    private Integer publishTimes;
}
