package com.want.custom.event.enumeration;

import lombok.ToString;

/**
 * @author WangZhiJian
 * @since 2021/2/4
 */
@ToString
public enum EventEnum {

    ONE("one")
    ,TWO("two");

    private String name;

    EventEnum(String name) {
        this.name = name;
    }
}