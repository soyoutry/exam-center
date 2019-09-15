package com.boss.bes.exam.service.impl;

import bosssoft.hr.train.common.utils.SnowFlake;
import com.boss.bes.exam.dao.AnswerRecordMapper;
import com.boss.bes.exam.dao.ExamPublishRecordMapper;
import com.boss.bes.exam.dao.ExamRecordMapper;
import com.boss.bes.exam.entity.AnswerRecord;
import com.boss.bes.exam.entity.ExamPublishRecord;
import com.boss.bes.exam.entity.ExamRecord;
import com.boss.bes.exam.pojo.DTO.dopaper.DoPaperFormDTO;
import com.boss.bes.exam.pojo.DTO.dopaper.UserInfoFormDTO;
import com.boss.bes.exam.service.DoPaperService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class DoPaperServiceImpl implements DoPaperService {
    @Autowired
    ExamRecordMapper examRecordMapper;
    @Autowired
    AnswerRecordMapper answerRecordMapper;
    @Autowired
    ExamPublishRecordMapper examPublishRecordMapper;
    /**
     * 扫码后用户不存在添加
     * @param userInfoFormDTO
     * @return 考试发布记录id
     */
    @Override
    public Long getExamId(UserInfoFormDTO userInfoFormDTO) {
        Example example = new Example(ExamRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("tel",userInfoFormDTO.getTel());
        ExamRecord record = examRecordMapper.selectOneByExample(example);
        if (record==null) {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            ExamRecord examRecord = new ExamRecord();
            examRecord.setId(snowFlake.nextId());
            // TODO 发布记录id从网页获取
            examRecord.setActualStartTime(new Date());
            examRecord.setStatus((byte) 0);
            BeanUtils.copyProperties(userInfoFormDTO, examRecord);
            examRecord.setExamPublishRecordId(618087691157901312L);
            //id从dto拿 找到publish的开始结束时间
            ExamPublishRecord examPublishRecord = examPublishRecordMapper.selectByPrimaryKey(618087691157901312L);
            examRecord.setPlanStartTime(examPublishRecord.getStartTime());
            examRecord.setPlanEndTime(examPublishRecord.getEndTime());
            if (examRecordMapper.insert(examRecord) == 1)
                return examRecord.getId();
        }
        else {
            return record.getId();
        }
        return null;
    }

    /**
     * 填写答案 存入答案
     * @param
     * @return
     */

    @Override
    public Boolean addMyAnswer(List<DoPaperFormDTO> doPaperFormDTOList) {
        Boolean flag = false;
//        查询是否存在答案记录
        for (DoPaperFormDTO doPaperFormDTO : doPaperFormDTOList) {
            Example example = new Example(AnswerRecord.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("myAnswer",doPaperFormDTO.getMyAnswer());
            criteria.andEqualTo("paperSubjectId",doPaperFormDTO.getPaperSubjectId());
            criteria.andEqualTo("examRecordId",doPaperFormDTO.getExamRecordId());
            AnswerRecord answerRecord = answerRecordMapper.selectOneByExample(example);
            if (answerRecord==null) {
                SnowFlake snowFlake = new SnowFlake(1, 1);
                AnswerRecord newAnswer = new AnswerRecord();
                newAnswer.setId(snowFlake.nextId());
                BeanUtils.copyProperties(doPaperFormDTO,newAnswer);
                if (answerRecordMapper.insert(newAnswer)==1) {
                    flag=true;
                } else {
                    flag=false;
                }
            }
        }
        return flag;
    }
}
