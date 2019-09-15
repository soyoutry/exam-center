package com.boss.bes.exam.service.impl;

import bosssoft.hr.train.service.aop.annotations.FullCommonField;
import com.boss.bes.exam.dao.AnswerRecordMapper;
import com.boss.bes.exam.dao.ExamPublishRecordMapper;
import com.boss.bes.exam.dao.ExamRecordMapper;
import com.boss.bes.exam.entity.AnswerRecord;
import com.boss.bes.exam.entity.ExamPublishRecord;
import com.boss.bes.exam.entity.ExamRecord;
import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.grade.ExamGradeRecordTableDataDTO;
import com.boss.bes.exam.pojo.DTO.grade.MarkingAnswerDTO;
import com.boss.bes.exam.pojo.DTO.grade.MarkingPaperDTO;
import com.boss.bes.exam.service.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    ExamPublishRecordMapper examPublishRecordMapper;
    @Autowired
    AnswerRecordMapper answerRecordMapper;
    @Autowired
    ExamRecordMapper examRecordMapper;
    @Override
    public List<ExamGradeRecordTableDataDTO> query(ExamGradeRecordQueryFormDTO examGradeRecordQueryFormDTO) throws InstantiationException, IllegalAccessException {
        List<ExamPublishRecord> examPublishRecords = examPublishRecordMapper.queryGrade(examGradeRecordQueryFormDTO);
        return convert(examPublishRecords);
    }

    /**
     * 添加试卷评分信息
     * @param markingPaperDTO
     * @return
     */
    @FullCommonField(dataCenterId = 1,machineId = 1)
    @Override
    public Boolean markingPaper(MarkingPaperDTO markingPaperDTO) {
        Example example = new Example(ExamRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",markingPaperDTO.getExamRecordId());
        ExamRecord examRecord = examRecordMapper.selectOneByExample(example);
        BeanUtils.copyProperties(markingPaperDTO,examRecord);
        examRecord.setId(markingPaperDTO.getExamRecordId());
        examRecord.setActualEndTime(new Date());
        examRecord.setStatus((byte) 1);
        if (examRecordMapper.updateByPrimaryKey(examRecord)==1 && markingAnswer(markingPaperDTO)){
            return true;
        }
        return false;
    }

    /**
     * 给每道题添加评分
     * @param markingPaperDTO
     * @return true or false
     */
    private Boolean markingAnswer(MarkingPaperDTO markingPaperDTO) {
        Boolean flag = false;
        for (MarkingAnswerDTO markingAnswerDTO : markingPaperDTO.getMarkingAnswerDTOList()) {
            Example example = new Example(AnswerRecord.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("examRecordId",markingPaperDTO.getExamRecordId());
            criteria.andEqualTo("paperSubjectId",markingAnswerDTO.getPaperSubjectId());
            AnswerRecord answerRecord = answerRecordMapper.selectOneByExample(example);
            if (answerRecord != null){
                answerRecord.setScore(markingAnswerDTO.getScore());
                answerRecord.setEvaluate(markingAnswerDTO.getEvaluate());
                if (answerRecordMapper.updateByPrimaryKey(answerRecord)==1)
                    flag = true;
                else
                    return false;
            }
        }
        return flag;
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


