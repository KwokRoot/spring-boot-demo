package org.kwok.dao;

import org.kwok.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: Kwok
 * @date: 2025/2/14
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // 根据用户名查找用户

}
