package org.kwok.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @description: SpringBoot MockMvc 测试 http 接口。
 * @author: Kwok
 * @date: 2025/2/22
 */
@WebMvcTest
public class SpringBootMockMvcTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void testTest() throws Exception {
        this.mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("test"));
    }

    @Test
    public void helloTest() throws Exception {
        this.mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(result -> result.getResponse());
    }

}
