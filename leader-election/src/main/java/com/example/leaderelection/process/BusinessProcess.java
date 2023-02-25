package com.example.leaderelection.process;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BusinessProcess implements SmartLifecycle {

    private boolean state = false;


    @Override
    public void start() {
        log.info("started");
        state = true;
    }

    @Override
    public void stop() {
        log.info("stopped");
        state = false;
    }

    @Override
    public boolean isRunning() {
        return state;
    }

    @Override
    public boolean isAutoStartup() {
        return false;
    }
}
