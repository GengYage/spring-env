package com.yage.springenv.controller;

import com.yage.springenv.entity.User;
import com.yage.springenv.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.yage.springenv.common.Constant.SIZE;
import static com.yage.springenv.utils.common.ResponseUtils.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/info/v1")
    public User getUser(String id) {
        log.info("get user info by id: {}", id);
        return userService.getById(id);
    }

    @PostMapping("/user/add/v1")
    public Map<String, String> addUser(@RequestBody User user) {
        log.info("add user: {}", user);
        userService.save(user);

        return ok();
    }

    @PostMapping("/user/add/batch/v1")
    public Map<String, String> addUserBatch(@RequestBody Map<String, Integer> params) {
        log.info("batch add user, size: {}", params.get(SIZE));
        return userService.batchAddUser(params.get(SIZE));
    }
}
