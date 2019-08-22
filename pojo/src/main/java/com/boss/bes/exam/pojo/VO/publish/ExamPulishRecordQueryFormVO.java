
package com.boss.bes.exam.pojo.VO.publish;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ExamPulishRecordQueryFormVO {

    private List<Date> examTimeRange;
    private List<Date> publishTimeRange;
    private String publisher;
    private String title;

}
