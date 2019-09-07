
package com.boss.bes.exam.pojo.VO.publish;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamPublishRecordDeleteFormVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long version;
}
