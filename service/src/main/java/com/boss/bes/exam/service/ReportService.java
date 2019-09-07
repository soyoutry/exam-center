package com.boss.bes.exam.service;

import com.boss.bes.exam.pojo.DTO.report.ExamDetailQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.report.ExamReportRecordDetailTableDataDTO;
import com.boss.bes.exam.pojo.DTO.report.ExamReportRecordExamTableDataDTO;
import com.boss.bes.exam.pojo.DTO.report.ExamReportRecordQueryFormDTO;

import java.util.List;

public interface ReportService {
    List<ExamReportRecordExamTableDataDTO> query(ExamReportRecordQueryFormDTO examReportRecordQueryFormDTO) throws InstantiationException, IllegalAccessException;
    List<ExamReportRecordDetailTableDataDTO> queryDetail(ExamDetailQueryFormDTO examDetailQueryFormDTO) throws InstantiationException, IllegalAccessException;
}
