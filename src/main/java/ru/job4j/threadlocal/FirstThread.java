package ru.job4j.threadlocal;

public class FirstThread extends Thread {
    @Override
    public void run() {
        ThreadLocalDemo.getT1().set("Thread 1");
        System.out.println(ThreadLocalDemo.getT1().get());
    }
}
