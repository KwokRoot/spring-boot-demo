package org.kwok.dao;

import org.kwok.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: Kwok
 * @date: 2025/2/18
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


}
