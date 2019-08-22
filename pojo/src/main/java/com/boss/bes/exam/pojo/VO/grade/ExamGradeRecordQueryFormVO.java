
package com.boss.bes.exam.pojo.VO.grade;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ExamGradeRecordQueryFormVO {

    private List<Date> endTimeRange;
    private List<Option> options;
    private String title;

}
