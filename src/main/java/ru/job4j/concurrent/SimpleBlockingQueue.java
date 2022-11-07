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
        queue.offer(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        T result = queue.poll();
        notifyAll();
        return result;
    }

    public boolean isEmpty() {
        return queue.poll() == null;
    }
}
