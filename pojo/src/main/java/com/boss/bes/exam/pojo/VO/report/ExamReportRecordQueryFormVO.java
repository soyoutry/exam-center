
package com.boss.bes.exam.pojo.VO.report;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ExamReportRecordQueryFormVO {

    private String examSession;
    private List<Date> examTimeRange;
    private String publisher;
    private String title;

}
