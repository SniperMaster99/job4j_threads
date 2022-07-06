package ru.job4j.concurrent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}
