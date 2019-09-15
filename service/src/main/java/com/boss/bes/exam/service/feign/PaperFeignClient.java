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

    @RequestMapping("boss/bes/paper/externalPaperService/getPublishedTimes")
    CommonResponse getPublishedTimes(@RequestBody CommonRequest<Long> commonRequest);

    @RequestMapping("boss/bes/paper/externalPaperService/paperInfoByName")
    CommonResponse getPaperInfoByName(@RequestBody CommonRequest<String> commonRequest);

    @RequestMapping("boss/bes/paper/externalPaperService/paperDetail")
    CommonResponse getPaperDetail(@RequestBody CommonRequest<Long> commonRequest);
}

