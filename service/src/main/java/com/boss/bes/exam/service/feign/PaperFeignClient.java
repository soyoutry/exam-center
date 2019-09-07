package com.boss.bes.exam.service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import protocol.CommonRequest;
import protocol.CommonResponse;
@FeignClient(name = "boss-bes-paper")
public interface PaperFeignClient {
    @RequestMapping("boss/bes/paper/externalPaperService/publishPaper")
    CommonResponse publishPaper(@RequestBody CommonRequest<Long> paperId);

    @RequestMapping("boss/bes/paper/externalPaperService/paperInfo")
    CommonResponse getPaperInfo(@RequestBody CommonRequest commonRequest);
}

