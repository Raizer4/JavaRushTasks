package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{

    LRUCache cache = new LRUCache(100);
    OriginalRetriever retriever;

    public CachingProxyRetriever(Storage retriever) {
        this.retriever = new OriginalRetriever(retriever);
    }

    @Override
    public Object retrieve(long id) {
        Object o = cache.find(id);

        if (o == null){
            Object retrieve = retriever.retrieve(id);
            cache.set(id,retrieve);
            return retrieve;
        }else {
            return o;
        }


    }


}
