package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {

    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int size = 0;

        Collection<List<V>> values = map.values();

        for (List<V> temp : values){
           size += temp.size();
        }

        return size;
    }

    @Override
    public V put(K key, V value) {

        if (map.containsKey(key)){
            List<V> vs = map.get(key);
            int index = 0;

            if (vs.size() < repeatCount && !vs.contains(value)){
                vs.add(value);
                index = vs.indexOf(value);
            }else if(vs.size() == repeatCount){
                vs.remove(0);
                vs.add(value);
                index = vs.indexOf(value);
            }

            return vs.get(index-1);

        }
        else {
            ArrayList<V> objects = new ArrayList<>();
            objects.add(value);
            map.put(key,objects);
            return null;
        }



    }

    @Override
    public V remove(Object key) {

        if (!map.containsKey(key)){
            return null;
        }

        List<V> vs = map.get(key);

        if (vs.size() == 1){
            map.remove(key);
        }

        return vs.remove(0);

    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> result = new ArrayList<>();

        Collection<List<V>> values = map.values();

        for (List<V> temp : values){
            result.addAll(temp);
        }

        return result;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        Collection<V> values = values();
        return values.contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }

        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }

}