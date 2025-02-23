package org.kwok.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description:
 * @author: Kwok
 * @date: 2025/2/18
 */
@Entity
@Table(name = "t_permission")
public class Permission {
    @Id
    private Long id;

    private String name;

    public Permission() {}

    public Permission(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
