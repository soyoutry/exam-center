package com.boss.bes.exam.service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import protocol.CommonResponse;

@FeignClient(value  = "boss-bes-basedata")
public interface CategoryFeignClient {
    @RequestMapping(value = "boss/bes/basedata/category/getCategoryInfo" , method = RequestMethod.GET)
    CommonResponse getCategoryInfo();
}

