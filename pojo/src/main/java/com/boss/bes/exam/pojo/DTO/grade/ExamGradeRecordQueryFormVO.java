
package com.boss.bes.exam.pojo.DTO.grade;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamGradeRecordQueryFormVO {

    private List<Date> endTimeRange;
    private List<Option> options;
    private String title;

}
