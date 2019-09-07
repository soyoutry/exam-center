
package com.boss.bes.exam.pojo.VO.answersheet;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ExamAnswerSheetRecordQueryFormVO {

    private String examSession;
    private List<Date> examTimeRange;
    private String publisher;
    private String title;
    private Integer currentPage;
}
