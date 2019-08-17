package com.boss.bes.exam.controller;

import com.boss.bes.exam.dao.TUserRecordMapper;
import com.boss.bes.exam.pojo.entity.TUserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    TUserRecordMapper tUserRecordMapper;

    @RequestMapping("getuser")
    public List<TUserRecord> getUser(){
        List<TUserRecord> tUserRecords = tUserRecordMapper.selectAll();
        return tUserRecords;
    }


    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
