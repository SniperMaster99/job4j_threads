package ru.job4j.concurrent;

import java.util.Random;

public class Producer implements Runnable {
    private final SimpleBlockingQueue queue;

    public Producer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.offer(producer());
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer producer() {
        return new Random().nextInt(100);
    }
}
