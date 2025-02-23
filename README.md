# Spring-Boot-Demo
###### Spring-Boot 常用功能 Demo 脚手架。

### 分支：
1. master: 空 maven 项目
```shell
mvn archetype:generate -DgroupId=org.kwok -DartifactId=spring-boot-demo -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
2. base: 空 spring-boot 项目
>> https://start.aliyun.com
>> https://start.spring.io
3. spring-boot-web
4. spring-boot-web-actuator 
   1. 使用 `spring-boot-starter-actuator` 模块，提供的 HTTP 端点（Endpoints），用于获取应用的运行时信息。
   2. 使用 `spring-boot-admin-starter-server` 模块，集中管理 client actuator 的 Endpoints 信息。
5. 
