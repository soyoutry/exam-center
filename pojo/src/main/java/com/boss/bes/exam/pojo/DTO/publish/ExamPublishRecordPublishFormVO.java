
package com.boss.bes.exam.pojo.DTO.publish;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamPublishRecordPublishFormVO {

    private String discript;
    private Date endDate;
    private Date endTime;
    private String examSession;
    private List<String> examiners;
    private Integer limitTime;
    private List<MarkingMode> markingModes;
    private Date markingStopTime;
    private List<String> paper;
    private Integer planPepoleNum;
    private String title;

}
