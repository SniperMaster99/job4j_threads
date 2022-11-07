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
        return list.replace(user.getId(), user) == null;
    }

    synchronized boolean delete(User user) {
        return list.remove(user.getId(), user);
    }

    synchronized boolean transfer(int fromID, int toId, int amount) {
        if (list.get(fromID) != null && list.get(toId) != null) {
            if (list.get(fromID).getAmount() >= amount) {
                list.get(fromID).setAmount(list.get(fromID).getAmount() - amount);
                list.get(toId).setAmount(list.get(toId).getAmount() + amount);
                return true;
            }
        }
        return false;
    }
}
