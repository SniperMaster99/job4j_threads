package ru.job4j.cache;

import java.util.concurrent.atomic.AtomicReference;

public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();
    private volatile int value = 0;

    public int increment() {
        int number;
        int next;
        do {
            number = get();
            next = number + 1;
        } while (!count.compareAndSet(number, next));
        return number;
    }

    public int get() {
        return value;
    }
}
