package ru.job4j.storage;

import junit.framework.TestCase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserStorageTest extends TestCase {

    public void testAdd() {
        UserStorage storage = new UserStorage();
        Map<Integer, User> expected = new ConcurrentHashMap<>();
        expected.put(1, new User(1, 1000));
        expected.put(2, new User(2, 1000));
        storage.add(new User(1, 1000));
        storage.add(new User(2, 1000));
    }

    public void testUpdate() {
        UserStorage storage = new UserStorage();
        Map<Integer, User> expected = new ConcurrentHashMap<>();
        User user = new User(1, 1000);
        expected.put(1, new User(1, 2000));
        storage.add(user);
        user = new User(1, 2000);
        storage.update(user);
        assertEquals(user.getAmount(), expected.get(1).getAmount());
    }

    public void testDelete() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 1000);
        storage.add(user);
        storage.delete(user);
    }

    public void testFindById() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 1000);
        storage.add(user);
    }

    public void testTransfer() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 1000);
        User user2 = new User(2, 10000);
        storage.add(user1);
        storage.add(user2);
        storage.transfer(2, 1, 5000);
    }
}