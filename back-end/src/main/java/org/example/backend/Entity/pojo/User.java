package org.example.backend.Entity.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username,password,mail,avator;

    public User() {
    }
}
