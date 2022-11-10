package ru.job4j.cache;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class CASCountTest {

    @Test
    public void addIncrement() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        CASCount counter = new CASCount();
        for (int i = 0; i < 50; i++) {
            service.submit(counter::increment);
        }
        int expected = 100;
        assertEquals(expected, counter.get());
    }
}