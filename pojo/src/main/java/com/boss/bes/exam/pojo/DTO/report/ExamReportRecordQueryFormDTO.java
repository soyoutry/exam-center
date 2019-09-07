
package com.boss.bes.exam.pojo.DTO.report;

import lombok.Data;

import java.util.Date;

@Data
@SuppressWarnings("unused")
public class ExamReportRecordQueryFormDTO {

    private String examSession;
    private Date startTime;
    private Date endTime;
    private String publisher;
    private String title;

}
