package com.boss.bes.exam.dao;

import com.boss.bes.exam.pojo.entity.TExamRecord;
import tk.mybatis.MyMapper;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

public interface TExamRecordMapper extends MyMapper<TExamRecord> {
    List<TExamRecord> query();
}