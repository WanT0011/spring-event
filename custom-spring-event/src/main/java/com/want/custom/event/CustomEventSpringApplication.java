package com.want.custom.event;

import com.want.custom.event.enumeration.EventEnum;
import com.want.custom.event.event.RandomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author WangZhiJian
 * @since 2021/2/4
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
public class CustomEventSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomEventSpringApplication.class,args);
    }

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Scheduled(fixedDelay = 1500)
    public void scheduled1500(){
        Random random = new Random();
        int anInt = random.nextInt(3);
        EventEnum eventEnum;
        if(anInt < 2){
            eventEnum = EventEnum.ONE;
        }else {
            eventEnum = EventEnum.TWO;
        }
        RandomEvent randomEvent = new RandomEvent("scheduler-1500",eventEnum);
        applicationEventPublisher.publishEvent(randomEvent);
    }
    @Scheduled(fixedDelay = 1000)
    public void scheduled1000(){
        Random random = new Random();
        int anInt = random.nextInt(3);
        EventEnum eventEnum;
        if(anInt < 2){
            eventEnum = EventEnum.THREE;
        }else {
            eventEnum = EventEnum.FOUR;
        }
        RandomEvent randomEvent = new RandomEvent("scheduler-1000",eventEnum);
        applicationEventPublisher.publishEvent(randomEvent);
    }


    @EventListener
    @Order(15)
    public void listener(RandomEvent randomEvent){
        EventEnum eventEnum = randomEvent.getEventEnum();
        log.info("发生事件： {},事件来源为： {}",eventEnum,randomEvent.getSource());
    }
}
