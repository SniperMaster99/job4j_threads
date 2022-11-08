package ru.job4j.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int number;
        int next;
        do {
            number = get();
            next = number + 1;
        } while (!count.compareAndSet(number, next));
    }

    public int get() {
        if (count.get() != null) {
            return count.get();
        }
        return 0;
    }
}
