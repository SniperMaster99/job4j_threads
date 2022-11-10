package ru.job4j.buffer;

import ru.job4j.concurrent.SimpleBlockingQueue;

public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);
        final int poisonPill = Integer.MAX_VALUE;
        final Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    int temp = queue.poll();
                    if (temp == poisonPill) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println(temp);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.start();
        new Thread(() -> {
            try {
                for (int index = 0; index < 5; index++) {
                    queue.offer(index);
                }
                queue.offer(poisonPill);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
