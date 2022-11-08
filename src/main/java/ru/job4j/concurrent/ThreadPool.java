package ru.job4j.concurrent;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(5);
    private int size;

    public ThreadPool() {
        size = Runtime.getRuntime().availableProcessors();
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
        tasks.notify();
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
