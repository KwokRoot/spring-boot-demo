package org.kwok.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: Kwok
 * @date: 2021/5/30
 */
@SpringBootTest
public class CustomSystemPropertiesTest {

    @Autowired
    CustomSystemProperties customSystemProperties;

    @Value("${sys.author:Kwok}")
    String author;

    @Test
    public void Test(){

        System.out.println(customSystemProperties.getId());
        System.out.println(customSystemProperties.getName());
        System.out.println(customSystemProperties.getVer());
        System.out.println(customSystemProperties.getUptime());
        System.out.println(author);

    }

}
