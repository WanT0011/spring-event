package com.want.custom.event.event;

import com.want.custom.event.enumeration.EventEnum;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @author WangZhiJian
 * @since 2021/2/4
 */
@Data
public class RandomEvent extends ApplicationEvent {

   private EventEnum eventEnum;


    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param eventEnum the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public RandomEvent(String source,EventEnum eventEnum) {
        super(source);
        this.eventEnum = eventEnum;
    }
}
