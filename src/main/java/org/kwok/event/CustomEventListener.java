package org.kwok.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 定义事件监听器
 */
@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {

        System.out.println(String.format("event: %s", event.toString()));

    }


    //http://127.0.0.1:8080/event/test?s=hello
    //http://127.0.0.1:8080/event/test?s=hello2
    @EventListener(condition="event.source == 'hello'")
    @Async
    public void listenEvent(CustomEvent event) {
        System.out.println(String.format("event2: %s", event.toString()));
    }

}
