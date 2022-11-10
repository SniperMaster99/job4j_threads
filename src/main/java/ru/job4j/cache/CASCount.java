package ru.job4j.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();
    private int number = 1;

    public void increment() {
        int next;
        do {
            number = get();
            next = number + 1;
        } while (!count.compareAndSet(number, next));
    }

    public int get() {
        return count.get();
    }
}
