package com.cjhxfund.autocode.disruptor.wrapper;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class MonitorThreadFactory implements ThreadFactory {

    private final AtomicLong NUMBER_OF_THREAD = new AtomicLong(1);
    private final ThreadGroup THREAD_GROUP = new ThreadGroup("monitor");

    private final boolean daemon;
    private final String prefix;

    private MonitorThreadFactory(final String prefix, final boolean daemon) {
        this.prefix = prefix;
        this.daemon = daemon;
    }

    public static ThreadFactory create(final String prefix, final boolean daemon) {
        return new MonitorThreadFactory(prefix, daemon);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(THREAD_GROUP, r,
                THREAD_GROUP.getName() + "-"
                        + prefix + "-"
                        + NUMBER_OF_THREAD.getAndIncrement());
        thread.setDaemon(daemon);
        if (thread.getPriority() != Thread.NORM_PRIORITY)
            thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }
}
