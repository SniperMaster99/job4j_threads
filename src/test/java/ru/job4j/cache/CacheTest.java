package ru.job4j.cache;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CacheTest {

    @Test
    public void addBase() {
        Cache cacheBase = new Cache();
        cacheBase.add(new Base(1, 1, "Ivan"));
        Base expect = new Base(1, 1, "Ivan");
        assertEquals(cacheBase.get(1), expect);
    }

    @Test
    public void updateBase() {
        Cache cacheBase = new Cache();
        Base base = new Base(1, 1, "Ivan");
        cacheBase.add(base);
        cacheBase.update(new Base(1,1,"Petr"));
        Base expect = new Base(1, 2, "Petr");
        assertEquals(cacheBase.get(1), expect);
    }

    @Test
    public void deleteBase() {
        Cache cacheBase = new Cache();
        Base base = new Base(1, 1, "Ivan");
        cacheBase.add(base);
        cacheBase.delete(base);
        assertNull(cacheBase.get(1));
    }
}