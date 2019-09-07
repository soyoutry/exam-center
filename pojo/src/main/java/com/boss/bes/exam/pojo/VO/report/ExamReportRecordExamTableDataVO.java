
package com.boss.bes.exam.pojo.VO.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ExamReportRecordExamTableDataVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Integer actualPepoleNum;
    private String endTime;
    private String examSession;
    private Integer planPepoleNum;
    private String title;

}
