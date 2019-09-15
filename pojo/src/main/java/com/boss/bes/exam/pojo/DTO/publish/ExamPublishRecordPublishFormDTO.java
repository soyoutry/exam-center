
package com.boss.bes.exam.pojo.DTO.publish;

import common.BaseDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamPublishRecordPublishFormDTO extends BaseDTO {
    private String descript;
    private Date startTime;
    private Date endTime;
    private String examSession;
    private Integer limitTime;
    private Long markingMode;
    private Date markingStopTime;
    private Long paperId;
    private Integer planPepoleNum;
    private String title;
    private List<Long> examinersId;
    private Long publisher;
}
