package com.boss.bes.exam.controller;

import com.boss.bes.exam.dao.TUserRecordMapper;
import com.boss.bes.exam.pojo.entity.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    TUserRecordMapper tUserRecordMapper;

    @RequestMapping("getuser")
    public List<UserRecord> getUser(){
        List<UserRecord> userRecords = tUserRecordMapper.selectAll();
        return userRecords;
    }


    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
