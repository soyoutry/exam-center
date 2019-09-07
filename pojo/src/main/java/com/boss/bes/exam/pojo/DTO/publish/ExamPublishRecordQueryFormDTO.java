
package com.boss.bes.exam.pojo.DTO.publish;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamPublishRecordQueryFormDTO {

    private List<String> examTimeRange;
    private List<String> publishTimeRange;
    private String publisher;
    private String title;

}
