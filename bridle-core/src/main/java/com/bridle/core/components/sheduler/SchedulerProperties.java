package com.bridle.core.components.sheduler;

import javax.validation.constraints.Positive;

public class SchedulerProperties {
    @Positive
    private int delayMillis;
    @Positive
    private int threadCount;

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public int getDelayMillis() {
        return delayMillis;
    }

    public void setDelayMillis(int delayMillis) {
        this.delayMillis = delayMillis;
    }
}
