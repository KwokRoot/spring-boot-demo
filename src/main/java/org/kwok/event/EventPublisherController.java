package org.kwok.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 事件发送
 */
@Controller
public class EventPublisherController {

    @Autowired
    public ApplicationEventPublisher publisher;

    @RequestMapping("event/test")
    @ResponseBody
    public String testEvent(String s){
        publisher.publishEvent(new CustomEvent(s));
        return "ok";
    }

}
