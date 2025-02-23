package org.kwok.security.dao;

import org.kwok.security.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: Kwok
 * @date: 2021/4/29
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);

}
