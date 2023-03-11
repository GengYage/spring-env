package com.yage.springenv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yage.springenv.entity.User;
import com.yage.springenv.mapper.UserMapper;
import com.yage.springenv.service.UserService;
import com.yage.springenv.utils.StringGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.ResourceTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.yage.springenv.utils.common.ResponseUtils.ok;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final AtomicInteger count = new AtomicInteger(0);
    @Resource
    private UserService userService;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Override
    @Transactional
    public Map<String, String> batchAddUser(int userSize) {
        Object resourceFactory;
        Object resource;
        if (platformTransactionManager instanceof ResourceTransactionManager) {
            resourceFactory = ((ResourceTransactionManager) platformTransactionManager).getResourceFactory();
            resource = TransactionSynchronizationManager.getResource(resourceFactory);
        } else {
            resourceFactory = null;
            resource = null;
        }

        @SuppressWarnings("unchecked")
        CompletableFuture<Void>[] tasks = Stream.generate(new StringGenerator(0, 1))
                .limit(userSize)
                .map(User::new)
                .map(user -> CompletableFuture.supplyAsync(()
                                -> {
                            if (resourceFactory != null && resource != null) {
                                TransactionSynchronizationManager.bindResource(resourceFactory, resource);
                            }
                            return userService.saves(user);
                        }, threadPoolTaskExecutor)
                        .exceptionally(ex -> {
                            log.error("add user error.", ex);
                            return null;
                        })).collect(Collectors.toList())
                .toArray(new CompletableFuture[userSize]);

        CompletableFuture.allOf(tasks).join();

        return ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saves(User user) {
        int i = count.addAndGet(1);
        if (i == 4) {
            throw new RuntimeException("手动抛出异常");
        }
        return this.save(user);
    }
}
