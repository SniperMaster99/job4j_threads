package ru.job4j.buffer;

import ru.job4j.concurrent.SimpleBlockingQueue;

public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);
        final Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted() && !queue.isEmpty()) {
                try {
                    System.out.println(queue.poll());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.start();
        new Thread(() -> {
            try {
                for (int index = 0; index < 3; index++) {
                    queue.offer(index);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            consumer.interrupt();
        }).start();
    }
}
