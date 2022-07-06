package ru.job4j.Storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    List<User> list = new ArrayList<>();

    synchronized boolean add (User user) {
        if(!list.contains(user)) {
            list.add(user);
            return true;
        }
        return false;
    }

    synchronized boolean update(User user) {
        if(list.contains(user)) {
            list.set(list.indexOf(user), user);
            return true;
        }
        return false;
    }

    synchronized boolean delete(User user) {
        if(list.contains(user)) {
            list.remove(user);
            return true;
        }
        return false;
    }

    synchronized User findById(int idUser) {
        User user = null;
        for (User user1 : list) {
            if(user1.getId() == idUser) {
                user = user1;
            }
        }
        return user;
    }

    synchronized boolean transfer(int fromID, int told, int amount) {
        User user1 = findById(fromID);
        User user2 = findById(told);
        user1.setAmount(user1.getAmount() - amount);
        user2.setAmount(user2.getAmount() + amount);
        return true;
    }
}
