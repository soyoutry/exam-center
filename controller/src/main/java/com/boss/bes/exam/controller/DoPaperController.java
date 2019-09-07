package com.boss.bes.exam.controller;

import com.boss.bes.exam.pojo.DTO.dopaper.UserInfoFormDTO;
import com.boss.bes.exam.pojo.VO.dopaper.UserInfoFormVO;
import com.boss.bes.exam.service.DoPaperService;
import com.bosssoft.hr.train.bossbescommonlogging.annotation.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import protocol.CommonResponse;

@CrossOrigin
@RestController
@RequestMapping("dopaper")
public class DoPaperController {
    @Autowired
    DoPaperService doPaperService;
    @Log
    @RequestMapping(value = "saveexaminer")
    public CommonResponse saveExaminer(@RequestBody UserInfoFormVO userInfoFormVO){
        System.out.println("///////////////////////////////");
        System.out.println(userInfoFormVO);
        System.out.println("///////////////////////////////");
        UserInfoFormDTO userInfoFormDTO = new UserInfoFormDTO();
        BeanUtils.copyProperties(userInfoFormVO,userInfoFormDTO);
        Long publishId = doPaperService.getPublishId(userInfoFormDTO);
        return new CommonResponse("","200","成功",false,null);
    }
}
