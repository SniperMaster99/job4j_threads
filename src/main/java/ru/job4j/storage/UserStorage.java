package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> list = new ConcurrentHashMap<>();

    synchronized boolean add(User user) {
        return list.putIfAbsent(user.getId(), user) == null;
    }

    synchronized boolean update(User user) {
        return list.replace(user.getId(), user) != null;
    }

    synchronized boolean delete(User user) {
        return list.remove(user.getId(), user);
    }

    synchronized boolean transfer(int fromID, int toId, int amount) {
        User fromUser = list.get(fromID);
        User toUser = list.get(toId);
        if (fromUser != null && toUser != null) {
            if (fromUser.getAmount() >= amount) {
                fromUser.setAmount(fromUser.getAmount() - amount);
                toUser.setAmount(toUser.getAmount() + amount);
                return true;
            }
        }
        return false;
    }
}
