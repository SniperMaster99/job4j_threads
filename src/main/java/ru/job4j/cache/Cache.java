package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memoryCache = new ConcurrentHashMap<>();

    public boolean add(Base base) {
        return  memoryCache.putIfAbsent(base.getId(), base) == null;
    }

    public boolean update(Base base) {
        memoryCache.computeIfPresent(base.getId(), ((key, value) -> {
            if (value.getVersion() != base.getVersion()) {
                throw new OptimisticException("mess");
            }
            return new Base(base.getId(), value.getVersion() + 1);
        }));
        return true;
    }

    public boolean delete(Base base) {
        return memoryCache.remove(base.getId(), base);
    }
}
