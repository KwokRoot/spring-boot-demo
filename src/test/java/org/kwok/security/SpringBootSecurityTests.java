package org.kwok.security;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.kwok.dao.PermissionRepository;
import org.kwok.dao.RoleRepository;
import org.kwok.dao.UserRepository;
import org.kwok.entity.Permission;
import org.kwok.entity.Role;
import org.kwok.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringBootSecurityTests {

    @Autowired
    UserRepository userRepository;

    @Resource
    RoleRepository roleRepository;

    @Resource
    PermissionRepository permissionRepository;

    @Test
    void addUserTest(){

        Permission perm_Add = new Permission(1L, "add");
        Permission perm_Delete = new Permission(2L, "delete");
        Permission perm_Update = new Permission(3L, "update");
        Permission perm_Query = new Permission(4L, "query");

        permissionRepository.saveAll(CollUtil.newHashSet(
                perm_Add,
                perm_Delete,
                perm_Update,
                perm_Query
        ));

        Role role_Admin = new Role(1L, "ADMIN");
        role_Admin.setPermissions(CollUtil.newHashSet(
                perm_Add,
                perm_Delete,
                perm_Update,
                perm_Query
        ));
        roleRepository.save(role_Admin);

        Role role_User = new Role(2L, "USER");
        role_User.setPermissions(CollUtil.newHashSet(
                perm_Query
        ));
        roleRepository.save(role_User);

        User user_Admin = new User("admin", SecureUtil.md5("admin"));
        user_Admin.setId(1L);
        user_Admin.setStatus(1);
        user_Admin.setRoles(CollUtil.newHashSet(role_Admin));
        userRepository.save(user_Admin);

        User user_user = new User("user", SecureUtil.md5("admin"));

        user_user.setId(2L);
        user_user.setStatus(1);
        user_user.setRoles(CollUtil.newHashSet(role_User));

        userRepository.save(user_user);

    }

    @Test
    void queryUserTest(){;
        List<User> userList = userRepository.findAll();
        System.out.println(JSONUtil.toJsonPrettyStr(userList));
    }

    @Test
    void deleteUserTest(){;
        userRepository.deleteAll();
    }

}
