package ru.job4j.concurrent;

public class ConverterOutput {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        thread.start();
        Thread second = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        second.start();
    }
}
