package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;

@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = {"com.boss.bes.exam.controller","com.boss.bes.exam.service","com.boss.bes.exam.dao","com.bosssoft.hr.train","utils","com.bosssoft.hr.train.bossbescommonlogging.annotation","annotations"})
@MapperScan(basePackages = {"com.boss.bes.exam.dao"})
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class,args);
    }
}
