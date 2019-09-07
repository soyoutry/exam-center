package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.boss.bes.exam.service")
@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = {"com.boss.bes.exam.controller","com.boss.bes.exam.service","com.boss.bes.exam.dao","com.bosssoft.hr.train","com.bosssoft.hr.train.bossbescommonlogging.exception","bosssoft.hr.train.common.utils","bosssoft.hr.train.service.aop.annotations","com.bosssoft.hr.train.bossbescommonlogging.*"})
@MapperScan(basePackages = {"com.boss.bes.exam.dao"})
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class,args);
    }
}
