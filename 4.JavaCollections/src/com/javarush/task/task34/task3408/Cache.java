package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>(); //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V v = cache.get(key);
        if (v == null) {
            v = clazz.getConstructor(key.getClass()).newInstance(key); // Используем конструктор с одним параметром типа K
            put(v);
        }
        return v;
    }

    public boolean put(V obj) {

        try {
            Method getKey = obj.getClass().getDeclaredMethod("getKey");
            getKey.setAccessible(true);
            K key = (K) getKey.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
        } catch (IllegalAccessException e) {
        }
        return false;

    }

    public int size() {
        return cache.size();
    }

}
