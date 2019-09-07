
package com.boss.bes.exam.pojo.DTO.answersheet;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamAnswerSheetRecordQueryFormDTO {

    private String examSession;
    private List<String> examTimeRange;
    private String publisher;
    private String title;

}
