package org.kwok.security.service;

import org.kwok.security.dao.SysUserRepository;
import org.kwok.security.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @description:
 * @author: Kwok
 * @date: 2021/4/29
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository.findByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("username: "+username);
        System.out.println("username: " + sysUser.getUsername() + ";password:" + sysUser.getPassword());
        return sysUser;
    }

}