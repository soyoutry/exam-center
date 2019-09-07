
package com.boss.bes.exam.pojo.DTO.report;

import lombok.Data;

import java.util.Date;

@Data
@SuppressWarnings("unused")
public class ExamReportRecordExamTableDataDTO {
    private Long id;
    private Integer actualPepoleNum;
    private Date endTime;
    private String examSession;
    private Integer planPepoleNum;
    private String title;

}
