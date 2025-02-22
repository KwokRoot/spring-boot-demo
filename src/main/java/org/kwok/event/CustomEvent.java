package org.kwok.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 定义事件
 */
public class CustomEvent extends ApplicationEvent {

    public CustomEvent(Object source) {
        super(source);
    }

    public CustomEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
