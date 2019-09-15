package com.boss.bes.exam.service.impl;

import bosssoft.hr.train.common.utils.Converter;
import bosssoft.hr.train.common.utils.UserDataFromRequest;
import bosssoft.hr.train.common.utils.jwt.UserPermission;
import bosssoft.hr.train.service.aop.annotations.FullCommonField;
import com.boss.bes.exam.dao.ExamPublishRecordMapper;
import com.boss.bes.exam.dao.UserRecordMapper;
import com.boss.bes.exam.entity.ExamPublishRecord;
import com.boss.bes.exam.entity.UserRecord;
import com.boss.bes.exam.pojo.DTO.publish.*;
import com.boss.bes.exam.service.ExamPublishRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamPublishRecordServiceImpl implements ExamPublishRecordService {
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Autowired
    Converter converter;
    @Autowired
    private ExamPublishRecordMapper examPublishRecordMapper;
    @Autowired
    private UserRecordMapper userRecordMapper;
    @Override
    public List<ExamPublishRecordTableDataDTO> queryExamPublishRecord(ExamPublishRecordQueryFormDTO examPublishRecordQueryFormDTO) {
        List<ExamPublishRecord> examPublishRecords = examPublishRecordMapper.queryExamPubulishRecord(examPublishRecordQueryFormDTO);
        List<ExamPublishRecordTableDataDTO> examPublishRecordTableDataDTOS = converter.convertList(examPublishRecords,ExamPublishRecordTableDataDTO.class);
        examPublishRecordTableDataDTOS.forEach(dto -> {
            UserRecord userRecord = new UserRecord();
            userRecord.setExamPublishRecordId(dto.getId());
            List<UserRecord> selected = userRecordMapper.select(userRecord);
            List<Long> examiners = selected.stream().map(UserRecord::getId).collect(Collectors.toList());
            dto.setExaminers(examiners);
        });
        return examPublishRecordTableDataDTOS;
    }

    @FullCommonField(dataCenterId = 1,machineId = 1)
    @Override
    public boolean addExamPublishRecord(ExamPublishRecordPublishFormDTO examPublishRecordPublishFormDTO) {
        ExamPublishRecord examPublishRecord = new ExamPublishRecord();
        UserRecord userRecord = new UserRecord();
        UserPermission userPermission = UserDataFromRequest.getUserPermission();
        examPublishRecordPublishFormDTO.setPublisher(userPermission.getId());
        BeanUtils.copyProperties(examPublishRecordPublishFormDTO,examPublishRecord);
        userRecord.setExamPublishRecordId(examPublishRecordPublishFormDTO.getId());
        List<Long> idList = examPublishRecordPublishFormDTO.getExaminersId();
        if (idList!=null){
            idList.forEach(id -> {
                userRecord.setId(id);
                userRecordMapper.insert(userRecord);
            });
        }
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

