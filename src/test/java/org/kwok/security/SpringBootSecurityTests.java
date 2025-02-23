package org.kwok.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description:
 * @author: Kwok
 * @date: 2025/2/23
 */
public class SpringBootSecurityTests {

    @Test
    void BCryptPasswordEncoderTest(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
        System.out.println(encoder.matches("admin","$2a$10$begH4hLJD3O/99WKyUuw7eI1f/Xg2l2RFY7m53GgIb/eJoS1jZ6ea"));
    }

}
