package ru.job4j.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService executorService = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                send(String.format(" Notification %s to email %s",
                                user.getUsername(),
                                user.getEmail()),
                        String.format("Add a new event to %s",
                                user.getUsername()),
                                user.getEmail());
            }
        });
    }

    public void send(String subject, String body, String email) {
    }

    public void close() {
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
