package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.junit.Assert;
import org.junit.Test;


public class FunctionalTest {

    @Test
    public void testStorage(Shortener shortener){
        String str1 = "hello";
        String str2 = "bay";
        String str3 = "hello";

        Long id1 = shortener.getId(str1);
        Long id2 = shortener.getId(str2);
        Long id3 = shortener.getId(str3);

        Assert.assertNotEquals(id2 , id1);
        Assert.assertNotEquals(id2 , id3);
        Assert.assertEquals(id1, id3);

        String string1 = shortener.getString(id1);
        String string2 = shortener.getString(id2);
        String string3 = shortener.getString(id3);

        Assert.assertEquals(string1, str1);
        Assert.assertEquals(string2, str2);
        Assert.assertEquals(string3, str3);

    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy temp = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(temp);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy temp = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(temp);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        FileStorageStrategy temp = new FileStorageStrategy();
        Shortener shortener = new Shortener(temp);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        HashBiMapStorageStrategy temp = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(temp);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy temp = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(temp);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy temp = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(temp);
        testStorage(shortener);
    }

}
