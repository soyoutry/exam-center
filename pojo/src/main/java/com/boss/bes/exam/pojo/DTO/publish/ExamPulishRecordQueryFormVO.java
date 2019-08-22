
package com.boss.bes.exam.pojo.DTO.publish;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamPulishRecordQueryFormVO {

    private List<Date> examTimeRange;
    private List<Date> publishTimeRange;
    private String publisher;
    private String title;

}
