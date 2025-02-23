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
4. spring-boot-web-security-dev02 使用 `spring-boot-starter-security` 实现认证授权。
   1. 自定义登录页面。
   2. 实现数据库用户名密码认证。
   3. 实现 RBAC(Role Based Access Control)基于角色的访问控制。
   4. 通过注解实现方法或请求的角色控制(hasRole、hasAnyRole)或者属性控制(hasAuthority、hasAnyAuthority)。
   5. 通过自定义实现 `PermissionEvaluator` 接口，使用 hasPermission 注解，实现更细粒度的权限控制。
5. 
