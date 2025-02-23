package org.kwok.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import org.kwok.util.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        String userName = "guest";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //
        // Authentication authenticationNotNull = Optional.ofNullable(authentication).orElse(new UsernamePasswordAuthenticationToken(User.builder().username("guest").password("").authorities("ROLE_GUEST").build(), ""));
        // if(authenticationNotNull.getPrincipal() instanceof UserDetails){
        //     UserDetails principal = (UserDetails) authenticationNotNull.getPrincipal();
        //     userName = principal.getUsername();
        // }else if(authenticationNotNull.getPrincipal() instanceof String) {
        //     userName = (String)authenticationNotNull.getPrincipal();
        // }
        //

        if (authentication!=null){
            userName = authentication.getName();
        }

        return CommonResult.ok(StrUtil.format("登录用户: {}", userName), id);
    }

    @RequestMapping({"hello"})
    @ResponseBody
    public String hello() {
        logger.info(">>> remoteAddr: {}, path: {}", ServletUtil.getClientIP(request), request.getServletPath());
        return "hello";
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
