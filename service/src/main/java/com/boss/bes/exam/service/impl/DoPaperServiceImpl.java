package com.boss.bes.exam.service.impl;

import bosssoft.hr.train.common.utils.SnowFlake;
import com.boss.bes.exam.dao.ExamRecordMapper;
import com.boss.bes.exam.entity.ExamRecord;
import com.boss.bes.exam.pojo.DTO.dopaper.UserInfoFormDTO;
import com.boss.bes.exam.service.DoPaperService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class DoPaperServiceImpl implements DoPaperService {
    @Autowired
    ExamRecordMapper examRecordMapper;

    @Override
    public Long getPublishId(UserInfoFormDTO userInfoFormDTO) {
        Example example = new Example(ExamRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("tel",userInfoFormDTO.getTel());
        ExamRecord record = examRecordMapper.selectOneByExample(example);
        if (record==null) {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            ExamRecord examRecord = new ExamRecord();
            examRecord.setId(snowFlake.nextId());
            // TODO 二维码获取
            examRecord.setExamPublishRecordId(618087691157901312L);
            BeanUtils.copyProperties(userInfoFormDTO, examRecord);
            if (examRecordMapper.insert(examRecord) == 1)
                return examRecordMapper.selectOneByExample(example).getExamPublishRecordId();
        }
        else {
            return record.getExamPublishRecordId();
        }
        return null;
    }
}
