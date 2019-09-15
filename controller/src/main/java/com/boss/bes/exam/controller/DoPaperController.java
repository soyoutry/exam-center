package com.boss.bes.exam.controller;

import bosssoft.hr.train.common.utils.Converter;
import com.boss.bes.exam.pojo.DTO.dopaper.DoPaperFormDTO;
import com.boss.bes.exam.pojo.DTO.dopaper.UserInfoFormDTO;
import com.boss.bes.exam.pojo.VO.dopaper.DoPaperFormVO;
import com.boss.bes.exam.pojo.VO.dopaper.UserInfoFormVO;
import com.boss.bes.exam.service.DoPaperService;
import com.boss.bes.exam.service.feign.PaperFeignClient;
import com.bosssoft.hr.train.bossbescommonlogging.annotation.MethodEnhancer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import protocol.CommonRequest;
import protocol.CommonResponse;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("dopaper")
public class DoPaperController {
    @Autowired
    Converter converter;
    @Autowired
    DoPaperService doPaperService;
    @Autowired
    PaperFeignClient paperFeignClient;

    @MethodEnhancer
    @RequestMapping(value = "saveexaminer")
    public CommonResponse saveExaminer(@RequestBody UserInfoFormVO userInfoFormVO){
        UserInfoFormDTO userInfoFormDTO = new UserInfoFormDTO();
        BeanUtils.copyProperties(userInfoFormVO,userInfoFormDTO);
        Long examId = doPaperService.getExamId(userInfoFormDTO);
        if (null != examId){
            return new CommonResponse("200","成功",false,examId.toString());
        }
        return new CommonResponse("200","失败",false,null);
    }

    @MethodEnhancer
    @RequestMapping(value = "addanswer")
    public CommonResponse addAnswer(@RequestBody List<DoPaperFormVO> doPaperFormVOS){
        List<DoPaperFormDTO> doPaperFormDTOS = converter.convertList(doPaperFormVOS,DoPaperFormDTO.class);
        if (doPaperService.addMyAnswer(doPaperFormDTOS)){
            return new CommonResponse("200","填写答案成功",false,null);
        }
        return new CommonResponse("200","填写答案失败",false,null);
    }

    @MethodEnhancer
    @RequestMapping(value = "getPaper")
    public CommonResponse getPaper(@RequestBody CommonRequest<Long> commonRequest){
        return paperFeignClient.getPaperDetail(commonRequest);
    }
}
