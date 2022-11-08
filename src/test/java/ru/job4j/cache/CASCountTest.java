package ru.job4j.cache;

import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class CASCountTest {

    @Test
    public void addIncrement() {
        CASCount counter = new CASCount();
        Thread thread1 = new Thread(() ->
        {
            for (int i = 0; i < 50; i++) {
                counter.increment();
            }
        });
        Thread thread2 = new Thread(() ->
        {
            for (int i = 0; i < 50; i++) {
                counter.increment();
            }
        });
        thread1.start();
        thread2.start();
        int expected = 100;
        assertEquals(counter.get(), is(expected));
    }
}