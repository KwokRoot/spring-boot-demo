package org.kwok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 自动扫描配置 Controller 接口及参数
 * @author: Kwok
 * @date: 2025/2/22
 */
@RequestMapping("swagger")
@Controller
public class SwaggerController {

    @RequestMapping(value = "now", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, String> date() {
        Map map = new HashMap();
        map.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        return map;
    }

}
