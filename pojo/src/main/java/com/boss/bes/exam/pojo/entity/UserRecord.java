package com.boss.bes.exam.pojo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_user_record")
public class UserRecord implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 考试发布记录
     */
    @Column(name = "exam_publish_record_id")
    private Long examPublishRecordId;

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取考试发布记录
     *
     * @return exam_publish_record_id - 考试发布记录
     */
    public Long getExamPublishRecordId() {
        return examPublishRecordId;
    }

    /**
     * 设置考试发布记录
     *
     * @param examPublishRecordId 考试发布记录
     */
    public void setExamPublishRecordId(Long examPublishRecordId) {
        this.examPublishRecordId = examPublishRecordId;
    }
}