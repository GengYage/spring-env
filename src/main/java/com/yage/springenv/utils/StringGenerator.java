package com.yage.springenv.utils;

import cn.hutool.core.util.RandomUtil;

import java.util.function.Supplier;

import static com.yage.springenv.common.Constant.DEFAULT;

public class StringGenerator implements Supplier<String> {
    private int start;
    private final int step;

    public StringGenerator(int start, int step) {
        this.start = start;
        this.step = step;
    }

    @Override
    public String get() {
        String result = String.valueOf(start);
        start += step;
        return RandomUtil.randomString(DEFAULT, 5) + result;
    }
}
