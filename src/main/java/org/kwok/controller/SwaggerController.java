package org.kwok.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Kwok
 * @date: 2025/2/22
 */
@Api(tags = "Swagger控制器")
@RequestMapping("swagger")
@Controller
public class SwaggerController {

    @ApiOperation(value = "获取当前时间", httpMethod = "GET")
    @RequestMapping("now")
    @ResponseBody
    public Map<String, String> date() {
        Map map = new HashMap();
        map.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        return map;
    }

}
