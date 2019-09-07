package com.boss.bes.exam.service.impl;

import com.boss.bes.exam.dao.ExamPublishRecordMapper;
import com.boss.bes.exam.entity.ExamPublishRecord;
import com.boss.bes.exam.entity.ExamRecord;
import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordTableDataDTO;
import com.boss.bes.exam.service.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    ExamPublishRecordMapper examPublishRecordMapper;
    @Override
    public List<ExamGradeRecordTableDataDTO> query(ExamGradeRecordQueryFormDTO examGradeRecordQueryFormDTO) throws InstantiationException, IllegalAccessException {
        List<ExamPublishRecord> examPublishRecords = examPublishRecordMapper.queryGrade(examGradeRecordQueryFormDTO);
        return convert(examPublishRecords);
    }

    private List<ExamGradeRecordTableDataDTO> convert(List<ExamPublishRecord> examPublishRecords){
        List<ExamGradeRecordTableDataDTO> tableDataDTOS = new ArrayList<>();
        for (ExamPublishRecord examPublishRecord : examPublishRecords) {
            ExamGradeRecordTableDataDTO tableDataDTO = new ExamGradeRecordTableDataDTO();
            for (ExamRecord examRecord : examPublishRecord.getExamRecords()) {
                BeanUtils.copyProperties(examRecord,tableDataDTO);
            }
            BeanUtils.copyProperties(examPublishRecord,tableDataDTO);
            tableDataDTOS.add(tableDataDTO);
        }
        return tableDataDTOS;
    }
}


