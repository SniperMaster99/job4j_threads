package ru.job4j.executor;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindIndexLikeFork<T> extends RecursiveTask<Integer> {
    private T[] array;
    private int index;
    private int from;
    private int to;

    public FindIndexLikeFork(T[] array, int index, int from, int to) {
        this.array = array;
        this.index = index;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        Integer result = 0;
        if (to - from >= 10) {
            FindIndexLikeFork arrayFirstHalf = new FindIndexLikeFork(array, index, from, to / 2);
            FindIndexLikeFork arraySecondHalf = new FindIndexLikeFork(array, index, (to / 2) + 1, to);
            arrayFirstHalf.fork();
            arraySecondHalf.fork();
        }
        for (int indexCompute = 0; indexCompute < array.length; indexCompute++) {
            if (array[indexCompute] == index){
                return result = indexCompute;
            }
        }
        return result;
    }

    public Integer findByIndex(T[] array) {
        ForkJoinPool fork = new ForkJoinPool();
        return fork.invoke(new FindIndexLikeFork<T>(array, index, from, to));
    }
}
