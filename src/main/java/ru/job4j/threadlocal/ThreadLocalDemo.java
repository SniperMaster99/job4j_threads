package ru.job4j.threadlocal;

public class ThreadLocalDemo {
    public static ThreadLocal<String> t1 = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        Thread first = new FirstThread();
        Thread second = new SecondThread();
        t1.set("Main");
        System.out.println(t1.get());
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
