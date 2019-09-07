package com.boss.bes.exam.controller;

import com.boss.bes.exam.pojo.DTO.report.ExamDetailQueryFormDTO;
import com.boss.bes.exam.pojo.DTO.report.ExamReportRecordDetailTableDataDTO;
import com.boss.bes.exam.pojo.DTO.report.ExamReportRecordExamTableDataDTO;
import com.boss.bes.exam.pojo.DTO.report.ExamReportRecordQueryFormDTO;
import com.boss.bes.exam.pojo.VO.report.ExamDetailQueryFormVO;
import com.boss.bes.exam.pojo.VO.report.ExamReportRecordDetailTableDataVO;
import com.boss.bes.exam.pojo.VO.report.ExamReportRecordExamTableDataVO;
import com.boss.bes.exam.pojo.VO.report.ExamReportRecordQueryFormVO;
import com.boss.bes.exam.service.ReportService;
import com.boss.bes.exam.utils.DateFormatUtil;
import com.bosssoft.hr.train.bossbescommonlogging.annotation.Log;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import protocol.CommonRequest;
import protocol.CommonResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("report")
public class ReportController {
    private final static Integer PAGE_SIZE = 8;
    @Autowired
    ReportService reportService;
    @Log
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public CommonResponse query(@RequestBody CommonRequest<ExamReportRecordQueryFormVO> commonRequest){
        try {
            ExamReportRecordQueryFormVO queryFormVO = commonRequest.getBody();
            ExamReportRecordQueryFormDTO queryFormDTO = new ExamReportRecordQueryFormDTO();
            BeanUtils.copyProperties(queryFormVO,queryFormDTO);
            if (queryFormVO.getExamTimeRange()!=null && queryFormVO.getExamTimeRange().size()!=0) {
                queryFormDTO.setStartTime(queryFormVO.getExamTimeRange().get(0));
                queryFormDTO.setEndTime(queryFormVO.getExamTimeRange().get(1));
            }
            Page p = PageHelper.startPage(commonRequest.getBody().getCurrentPage(),PAGE_SIZE);
            List<ExamReportRecordExamTableDataDTO> tableDataDTOS = reportService.query(queryFormDTO);
            List<ExamReportRecordExamTableDataVO> tableDataVOS = new ArrayList<>();
            for (ExamReportRecordExamTableDataDTO tableDataDTO : tableDataDTOS) {
                ExamReportRecordExamTableDataVO vo = new ExamReportRecordExamTableDataVO();
                BeanUtils.copyProperties(tableDataDTO,vo);
                vo.setEndTime(DateFormatUtil.format(tableDataDTO.getEndTime()));
                tableDataVOS.add(vo);
            }
            PageInfo<ExamReportRecordExamTableDataVO> pageInfo = new PageInfo<>(tableDataVOS);
            Map<String,Object> map = new HashMap<>();
            map.put("total",p.getTotal());
            map.put("pageInfo",pageInfo);
            return new CommonResponse("","200","页面加载成功",false,map);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new CommonResponse("","200","页面加载失败",false,null);
    }

    @Log
    @RequestMapping(value = "querydetail",method = RequestMethod.POST)
    public CommonResponse queryDetail(@RequestBody CommonRequest<ExamDetailQueryFormVO> commonRequest){
        try {
            ExamDetailQueryFormVO  queryFormVO = commonRequest.getBody();
            ExamDetailQueryFormDTO  queryFormDTO = new ExamDetailQueryFormDTO();
            BeanUtils.copyProperties(queryFormVO,queryFormDTO);
            Page p = PageHelper.startPage(commonRequest.getBody().getCurrentPage(),PAGE_SIZE);
            List<ExamReportRecordDetailTableDataDTO> tableDataDTOS = reportService.queryDetail(queryFormDTO);
            List<ExamReportRecordDetailTableDataVO> tableDataVOS = new ArrayList<>();
            if (tableDataDTOS!=null && tableDataDTOS.size()!=0){
                for (ExamReportRecordDetailTableDataDTO tableDataDTO : tableDataDTOS) {
                    ExamReportRecordDetailTableDataVO vo = new ExamReportRecordDetailTableDataVO();
                    BeanUtils.copyProperties(tableDataDTO,vo);
                    vo.setAbilityLabel(getLabel(tableDataDTO));
                    vo.setExamCostTime(getCostTime(tableDataDTO));
                    tableDataVOS.add(vo);
                }
            }
            PageInfo<ExamReportRecordDetailTableDataVO> pageInfo = new PageInfo<>(tableDataVOS);
            Map<String,Object> map = new HashMap<>();
            map.put("total",p.getTotal());
            map.put("pageInfo",pageInfo);
            return new CommonResponse("","200","页面加载成功",false,map);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new CommonResponse("","200","页面加载失败",false,null);
    }

    private String getLabel(ExamReportRecordDetailTableDataDTO dto){
        if (dto.getScore()!=null){
            if (dto.getScore()>=90)
                return "A";
            else if (dto.getScore()>=80)
                return "B";
            else if (dto.getScore()>=70)
                return "C";
            else
                return "D";
        }
        return null;
    }

    private String getCostTime(ExamReportRecordDetailTableDataDTO dto){
        if (dto.getActualStartTime()!=null && dto.getActualEndTime()!=null){
            Long costTime = (dto.getActualEndTime().getTime() - dto.getActualStartTime().getTime()) / (1000 * 60);
            return costTime.toString()+"分钟";
        }
        return null;
    }
}
