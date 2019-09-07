
package com.boss.bes.exam.pojo.VO.report;

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
    private Integer currentPage;
}
