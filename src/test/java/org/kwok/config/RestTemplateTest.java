package org.kwok.config;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: Kwok
 * @date: 2025/2/22
 */
@SpringBootTest
public class RestTemplateTest {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void restTemplateGetTest(){
        Object result = restTemplate.getForObject("https://ipinfo.io/json", Object.class);
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }

}
