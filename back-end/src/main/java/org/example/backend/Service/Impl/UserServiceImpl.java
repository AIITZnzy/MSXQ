package org.example.backend.Service.Impl;

import jakarta.annotation.Resource;
import org.example.backend.Entity.pojo.User;
import org.example.backend.Mapper.UserMapper;
import org.example.backend.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public User login(String username, String password) {
        return userMapper.getUserByUsernameAndPassword(username,password);
    }
}
