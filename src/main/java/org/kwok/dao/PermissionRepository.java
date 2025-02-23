package org.kwok.dao;

import org.kwok.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: guohao
 * @date: 2025/2/18
 */@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    

}
