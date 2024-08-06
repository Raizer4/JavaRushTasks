package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    @Override
    public boolean containsKey(Long key) {
        Entry entry = getEntry(key);

        if (entry != null){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean containsValue(String value) {
        Long key = getKey(value);

        if (key != null){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        addEntry(hash,key,value,index);
    }

    @Override
    public Long getKey(String value) {

        for (Entry temp : table) {
            if (temp != null) {

                if (temp.getValue().equals(value)) {
                    return temp.getKey();
                }

                Entry next = temp.next;

                while (next != null) {
                    if (next.getValue().equals(value)) {
                        return next.getKey();
                    }
                    next = next.next;
                }

            }
        }

        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);

        if (entry != null){
            return entry.getValue();
        }else {
            return null;
        }

    }

    int hash(Long k) {
      return k.hashCode();
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getEntry(Long key) {
        int hash = hash(key);
        int bucketIndex = indexFor(hash, table.length);

        Entry entry = table[bucketIndex];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry;
            }
            entry = entry.next;
        }

        return null;
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {

        if (table[bucketIndex] == null){
            createEntry(hash, key, value, bucketIndex);
        }else {
            Entry entry = table[bucketIndex];

            while (entry != null){
                if (entry.next == null){
                    entry.next = new Entry(hash,key,value,null);
                    return;
                }else {
                    entry = entry.next;
                }
            }

        }



        size++;
        if (size >= threshold) {
            resize(2 * table.length);
        }

    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        table[bucketIndex] = new Entry(hash, key, value, null);
    }

    void resize(int newCapacity) {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    void transfer(Entry[] newTable) {
        for (int i = 0; i < table.length; i++) {
            Entry entry = table[i];
            while (entry != null) {
                Entry next = entry.next;

                int indexInNewTable = indexFor(entry.hash, newTable.length);

                entry.next = newTable[indexInNewTable];
                newTable[indexInNewTable] = entry;

                entry = next;
            }
        }
    }

}
