package org.kwok.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: Kwok
 * @date: 2025/2/13
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public String user() {
        return "user";
    }

    // http://127.0.0.1:8080/test01?id=1
    @GetMapping("/test01")
    // @PreAuthorize("hasPermission(#id, 'doc', 'add')")
    @PreAuthorize("hasPermission(#id, 'add')")
    @ResponseBody
    public String test01(String id) {
        return "id" + id;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", defaultValue = "false") boolean error, Model model) {
        if (error) {
            model.addAttribute("errorMessage", "无效的用户名或密码");
        }
        return "login";
    }


}
