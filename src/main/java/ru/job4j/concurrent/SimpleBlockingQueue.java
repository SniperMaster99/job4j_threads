package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void offer(T value) throws InterruptedException {
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        queue.remove();
        notifyAll();
        return queue.remove();
    }

    public boolean isEmpty() {
        return true;
    }
}
