package ru.job4j.concurrent;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(5);
    private int size = Runtime.getRuntime().availableProcessors();

    public ThreadPool() throws InterruptedException {
        for (int i = 0; i < size; i++) {
            while (!Thread.currentThread().isInterrupted()) {
                tasks.poll().run();
                Thread thread = new Thread();
                thread.start();
                threads.add(thread);
            }
        }
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
