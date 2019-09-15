package com.boss.bes.exam.pojo.DTO.dopaper;

import lombok.Data;

@Data
public class UserInfoFormDTO {
    private String tel;
    private String examiner;
    private Byte sex;
    //TODO 发布记录id
    private Long examPublishRecordId;
}
