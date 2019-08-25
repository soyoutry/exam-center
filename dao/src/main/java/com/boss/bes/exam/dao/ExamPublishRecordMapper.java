package com.boss.bes.exam.dao;

import com.boss.bes.exam.entity.ExamPublishRecord;
import tk.mybatis.MyMapper;

import java.util.List;

public interface ExamPublishRecordMapper extends MyMapper<ExamPublishRecord> {
    List<ExamPublishRecord> query();
}