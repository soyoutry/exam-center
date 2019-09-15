
package com.boss.bes.exam.pojo.VO.grade;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamGradeRecordQueryFormVO {
    private List<Date> endTimeRange;
    private Byte status;
    private String examSession;
    private Integer currentPage;
}
