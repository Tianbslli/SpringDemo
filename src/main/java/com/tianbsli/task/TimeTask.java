package com.tianbsli.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/23 5:36 下午
 *
 * 定时任务，每5s调用一次timeTelling接口获取时间，触发日志打印
 */
//@Component
public class TimeTask {

    @Scheduled(fixedRate = 5000)
    public void askTime(){
        RestTemplate restTemplate = new RestTemplate();
        String time = restTemplate.getForObject("http://localhost:8080/timeTelling"
                , String.class);
        System.out.println("定时任务获取时间：" + time);
    }

}
