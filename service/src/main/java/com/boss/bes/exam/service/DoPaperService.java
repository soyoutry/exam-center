package com.boss.bes.exam.service;

import com.boss.bes.exam.pojo.DTO.dopaper.DoPaperFormDTO;
import com.boss.bes.exam.pojo.DTO.dopaper.UserInfoFormDTO;

import java.util.List;

public interface DoPaperService {
    Long getExamId(UserInfoFormDTO userInfoFormDTO);
    Boolean addMyAnswer(List<DoPaperFormDTO> doPaperFormDTOList);
}
