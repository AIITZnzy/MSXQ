package org.example.backend.Service;

import org.example.backend.Entity.pojo.User;

public interface UserService {
    User login(String username, String password);
}
