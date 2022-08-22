package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private Map<Integer, User> list = new ConcurrentHashMap<>();

    synchronized void add(User user) {
        list.putIfAbsent(user.getId(), user);
    }

    synchronized void update(User user) {
        if (list.containsValue(user)) {
            list.put(user.getId(), user);
        }
    }

    synchronized void delete(User user) {
        if (list.containsValue(user)) {
            list.remove(user.getId(), user);
        }
    }

    synchronized User findById(int idUser) {
        User user = null;
        for (User user1 : list.values()) {
            if (user1.getId() == idUser) {
                user = user1;
            }
        }
        return user;
    }

    synchronized boolean transfer(int fromID, int toId, int amount) {
        User user1 = findById(fromID);
        User user2 = findById(toId);
        user1.setAmount(user1.getAmount() - amount);
        user2.setAmount(user2.getAmount() + amount);
        return true;
    }
}
