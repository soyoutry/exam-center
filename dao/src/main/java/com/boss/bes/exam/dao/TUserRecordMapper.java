package com.boss.bes.exam.dao;

import com.boss.bes.exam.pojo.entity.TUserRecord;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.MyMapper;
import tk.mybatis.spring.annotation.MapperScan;

public interface TUserRecordMapper extends MyMapper<TUserRecord> {
}