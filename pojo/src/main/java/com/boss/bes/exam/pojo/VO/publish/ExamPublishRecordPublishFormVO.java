
package com.boss.bes.exam.pojo.VO.publish;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
@SuppressWarnings("unused")
public class ExamPublishRecordPublishFormVO {
    private String descript;
    private Date startTime;
    private Date endTime;
    private String examSession;
    private Integer limitTime;
    private Long markingMode;
    private Date markingStopTime;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long paperId;
    private Integer planPepoleNum;
    private String title;
}
