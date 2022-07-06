package ru.job4j.concurrent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(final File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Integer> predicate) {
        StringBuilder output = new StringBuilder();
        try {
            InputStream i = new FileInputStream(file);
            int data = 0;
            while (predicate.test(data)) {
                output.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public synchronized String getContentWithoutUnicode(Predicate<Integer> predicate) {
        return getContent(predicate);
    }


}
