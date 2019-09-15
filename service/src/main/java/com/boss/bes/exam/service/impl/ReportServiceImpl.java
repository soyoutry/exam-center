package com.boss.bes.exam.service.impl;

import bosssoft.hr.train.common.utils.Converter;
import com.boss.bes.exam.dao.ExamPublishRecordMapper;
import com.boss.bes.exam.dao.ExamRecordMapper;
import com.boss.bes.exam.entity.ExamPublishRecord;
import com.boss.bes.exam.entity.ExamRecord;
import com.boss.bes.exam.pojo.DTO.report.ExamDetailQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.report.ExamReportRecordDetailTableDataDTO;
import com.boss.bes.exam.pojo.DTO.report.ExamReportRecordExamTableDataDTO;
import com.boss.bes.exam.pojo.DTO.report.ExamReportRecordQueryFormDTO;
import com.boss.bes.exam.service.ReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    Converter converter;
    @Autowired
    ExamPublishRecordMapper examPublishRecordMapper;
    @Autowired
    ExamRecordMapper examRecordMapper;

    //TODO 搜索试卷发布人
    @Override
    public List<ExamReportRecordExamTableDataDTO> query(ExamReportRecordQueryFormDTO examReportRecordQueryFormDTO) throws InstantiationException, IllegalAccessException {
        Example example = new Example(ExamPublishRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("title",examReportRecordQueryFormDTO.getTitle()+"%");
        criteria.andLike("examSession",examReportRecordQueryFormDTO.getExamSession()+"%");
        criteria.andBetween("endTime",examReportRecordQueryFormDTO.getStartTime(),examReportRecordQueryFormDTO.getEndTime());
        List<ExamPublishRecord> recordList = examPublishRecordMapper.selectByExample(example);
        List<ExamReportRecordExamTableDataDTO> tableDataDTOS = new ArrayList<>();
        for (ExamPublishRecord examPublishRecord : recordList) {
            ExamReportRecordExamTableDataDTO tableDataDTO = new ExamReportRecordExamTableDataDTO();
            BeanUtils.copyProperties(examPublishRecord,tableDataDTO);
            Example examExample = new Example(ExamRecord.class);
            Example.Criteria examCriteria = examExample.createCriteria();
            examCriteria.andEqualTo("examPublishRecordId",examPublishRecord.getId());
            List<ExamRecord> examRecords = examRecordMapper.selectByExample(examExample);
            tableDataDTO.setActualPepoleNum(examRecords.size());
            tableDataDTOS.add(tableDataDTO);
        }
        return tableDataDTOS;
    }

    @Override
    public List<ExamReportRecordDetailTableDataDTO> queryDetail(ExamDetailQueryFormDTO examDetailQueryFormDTO) throws InstantiationException, IllegalAccessException {
        Example example = new Example(ExamRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("examPublishRecordId",examDetailQueryFormDTO.getId());
        List<ExamRecord> examRecords = examRecordMapper.selectByExample(example);
        ExamPublishRecord examPublishRecord = examPublishRecordMapper.selectByPrimaryKey(examDetailQueryFormDTO.getId());
        if (examRecords!=null && examRecords.size()!=0){
            List<ExamReportRecordDetailTableDataDTO> tableDataDTOS = converter.convertList(examRecords,ExamReportRecordDetailTableDataDTO.class);
            for (ExamReportRecordDetailTableDataDTO tableDataDTO : tableDataDTOS) {
                tableDataDTO.setTitle(examPublishRecord.getTitle());
            }
            return tableDataDTOS;
        }
        return null;
    }
}
