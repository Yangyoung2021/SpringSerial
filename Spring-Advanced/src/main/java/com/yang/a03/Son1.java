package com.yang.a03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Son1 implements IInterface {
    @Override
    public void run() {
        log.info("执行run方法");
    }
}
