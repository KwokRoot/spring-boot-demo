package org.kwok.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @description: `application.properties` 配置文件的自定义配置项使用 `@ConfigurationProperties(prefix = "xxx")` 直接注入到实体类属性
 * 注：需要 Set 方法。
 * @author: Kwok
 * @date: 2021/4/27
 */

@Configuration
@ConfigurationProperties(prefix = "sys")
public class CustomSystemProperties {

    // 默认值，配置中有该配置项时会覆盖默认值。
    private String id = UUID.randomUUID().toString();
    private String name;
    private String ver;

    // 系统启动时间，不需要注入，不提供 Set 方法。
    private String uptime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getUptime() {
        return uptime;
    }

}

