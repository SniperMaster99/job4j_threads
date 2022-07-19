package ru.job4j.concurrent;

public class Consumer implements Runnable {
    private final ru.job4j.concurrent.SimpleBlockingQueue queue;

    public Consumer(ru.job4j.concurrent.SimpleBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consumer();
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumer() throws InterruptedException {
        Integer integer = (Integer) queue.poll();
    }
}
