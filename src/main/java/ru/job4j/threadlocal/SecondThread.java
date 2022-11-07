package ru.job4j.threadlocal;

public class SecondThread extends Thread {
    @Override
    public void run() {
        ThreadLocalDemo.getT1().set("Thread 2");
        System.out.println(ThreadLocalDemo.getT1().get());
    }
}
