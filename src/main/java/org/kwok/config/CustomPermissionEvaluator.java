package org.kwok.config;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @description:
 * @author: Kwok
 * @date: 2025/2/20
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println(authentication.getName());
        System.out.println(targetDomainObject);
        System.out.println(permission);
        return false;
    }
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        System.out.println(authentication.getName());
        System.out.println(targetId);
        System.out.println(targetType);
        System.out.println(permission);
        return false;
    }
}
