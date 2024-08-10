package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileBucket {

    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(Integer.toHexString(hashCode()), ".tmp");
            path.toFile().deleteOnExit();

            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {

        }
    }

    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(entry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Entry getEntry(){
        if (getFileSize() > 0){
            try(ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
                return (Entry) in.readObject();
            } catch (Exception e) {

            }
        }
        return null;

    }

    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
