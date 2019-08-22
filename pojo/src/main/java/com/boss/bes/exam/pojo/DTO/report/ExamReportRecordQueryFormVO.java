
package com.boss.bes.exam.pojo.DTO.report;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamReportRecordQueryFormVO {

    private String examSession;
    private List<Date> examTimeRange;
    private String publisher;
    private String title;

}
