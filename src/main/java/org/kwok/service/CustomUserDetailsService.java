package org.kwok.service;

import org.kwok.dao.UserRepository;
import org.kwok.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Kwok
 * @date: 2025/2/14
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        return user; // 根据你的实体类调整字段和方法名，确保匹配正确的属性名和方法名。例如，如果你的实体类中有isAccountNonExpired()等其他方法，你也需要实现它们。
    }
}