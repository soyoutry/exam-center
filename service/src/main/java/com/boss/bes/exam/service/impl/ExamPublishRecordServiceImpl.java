package com.boss.bes.exam.service.impl;

import bosssoft.hr.train.service.aop.annotations.FullCommonField;
import com.boss.bes.exam.dao.ExamPublishRecordMapper;
import com.boss.bes.exam.entity.ExamPublishRecord;
import com.boss.bes.exam.pojo.DTO.publish.*;
import com.boss.bes.exam.service.ExamPublishRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamPublishRecordServiceImpl implements ExamPublishRecordService {
    @Autowired
    private ExamPublishRecordMapper examPublishRecordMapper;

    @Override
    public List<ExamPublishRecordTableDataDTO> queryExamPublishRecord(ExamPublishRecordQueryFormDTO examPublishRecordQueryFormDTO) {
        List<ExamPublishRecord> examPublishRecords = examPublishRecordMapper.queryExamPubulishRecord(examPublishRecordQueryFormDTO);
        List<ExamPublishRecordTableDataDTO> examPublishRecordTableDataDTOS = new ArrayList<>();
        for (ExamPublishRecord examPublishRecord : examPublishRecords) {
            ExamPublishRecordTableDataDTO examPublishRecordTableDataDTO = new ExamPublishRecordTableDataDTO();
            BeanUtils.copyProperties(examPublishRecord,examPublishRecordTableDataDTO);
            examPublishRecordTableDataDTOS.add(examPublishRecordTableDataDTO);
        }
        return examPublishRecordTableDataDTOS;
    }

    @FullCommonField(dataCenterId = 1,machineId = 1)
    @Override
    public boolean addExamPublishRecord(ExamPublishRecordPublishFormDTO examPublishRecordPublishFormDTO) {
        ExamPublishRecord examPublishRecord = new ExamPublishRecord();
        BeanUtils.copyProperties(examPublishRecordPublishFormDTO,examPublishRecord);
        if (examPublishRecordMapper.insert(examPublishRecord)==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteExamPublishRecord(List<ExamPublishRecordDeleteFormDTO> dtoList) {
        for (ExamPublishRecordDeleteFormDTO dto : dtoList) {
            examPublishRecordMapper.deleteExamPublishRecord(dto);
        }
        return true;
    }

    @Override
    public boolean updateExamPublishRecord(ExamPublishRecordEditFormDTO examPublishRecordEditFormDTO) {
        ExamPublishRecord examPublishRecord = new ExamPublishRecord();
        ExamPublishRecord oldPo = examPublishRecordMapper.selectByPrimaryKey(examPublishRecordEditFormDTO.getId());
        BeanUtils.copyProperties(oldPo,examPublishRecord);
        BeanUtils.copyProperties(examPublishRecordEditFormDTO,examPublishRecord);
        if(examPublishRecordMapper.updateByPrimaryKey(examPublishRecord)==1)
            return true;
        return false;
    }
}

