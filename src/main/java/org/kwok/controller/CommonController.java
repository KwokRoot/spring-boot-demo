package org.kwok.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONObject;
import org.kwok.config.CustomSystemProperties;
import org.kwok.util.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonController {

    public static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    CustomSystemProperties customSystemProperties;

    @Value("${sys.author:Kwok}")
    String author;

    @RequestMapping({"", "index"})
    @ResponseBody
    public String index() {
        return "ServerStart...";
    }

    @RequestMapping({"test"})
    @ResponseBody
    public CommonResult<Long> test(HttpServletRequest request2,
                       @RequestParam(defaultValue = "0", required = false) Long id) {
        logger.info(">>> remoteAddr: {}, path: {}, param: {}", ServletUtil.getClientIP(request2), request2.getServletPath(), id);
        return CommonResult.ok("test", id);
    }

    @RequestMapping({"hello"})
    @ResponseBody
    public String hello() {
        logger.info(">>> remoteAddr: {}, path: {}", ServletUtil.getClientIP(request), request.getServletPath());
        return "hello";
    }

    @RequestMapping("open/sysinfo")
    @ResponseBody
    public JSONObject sysInfo() {
        JSONObject sysInfo = new JSONObject();
        sysInfo.set("id", customSystemProperties.getId());
        sysInfo.set("name", customSystemProperties.getName());
        sysInfo.set("ver", customSystemProperties.getVer());
        sysInfo.set("uptime", customSystemProperties.getUptime());
        sysInfo.set("author", author);
        return sysInfo;
    }

    @RequestMapping("open/ping")
    @ResponseBody
    public CommonResult<String> OpenMonitor(HttpServletRequest request) {

        CommonResult<String> result = CommonResult.CreateInstance();
        result.setStatus(CommonResult.ResultCode.ok);
        result.setMessage("pong");
        result.setData(DateUtil.now());
        return result;

    }

}
