package com.yage.springenv.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yage.springenv.entity.User;

import java.util.Map;

public interface UserService extends IService<User> {
    Map<String, String> batchAddUser(int userSize);
    boolean saves(User user);
}
