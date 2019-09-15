package com.boss.bes.exam.pojo.VO.dopaper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class UserInfoFormVO {
    private String tel;
    private String examiner;
    private Byte sex;
//    TODO 发布记录id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long examPublishRecordId;
}
