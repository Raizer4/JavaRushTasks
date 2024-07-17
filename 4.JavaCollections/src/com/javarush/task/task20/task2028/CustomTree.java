package com.javarush.task.task20.task2028;

import java.io.Closeable;
import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable,Serializable {

    Entry<String> root;
    int size;
    List<Entry<String>> list = new LinkedList<>();

    public CustomTree() {
        this.root = new Entry<>("Nikita");
        root.parent = root;
        list.add(root.parent);
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }


    }


    @Override
    public boolean add(String element) {
        Entry<String> entry = new Entry<>(element);

        for (Entry<String> temp : list){
            if (temp.isAvailableToAddChildren()){
                entry.parent = temp;
                list.add(entry);
                size++;

                if (temp.availableToAddLeftChildren) {
                    temp.leftChild = entry;
                    temp.availableToAddLeftChildren = false;
                    return true;
                }

                if (temp.availableToAddRightChildren) {
                    temp.rightChild = entry;
                    temp.availableToAddRightChildren = false;
                    return true;
                }
            }
        }

       return false;

    }

    public String getParent(String elementName) {
        for (Entry<String> temp : list) {
            if (temp.leftChild != null && temp.leftChild.elementName.equals(elementName)) {
                return temp.elementName;
            }
            if (temp.rightChild != null && temp.rightChild.elementName.equals(elementName)) {
                return temp.elementName;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }

        String elementName = (String) o;

        Queue<Entry<String>> nodes = new LinkedList<>(Collections.singletonList(root));

        while (!nodes.isEmpty()) {
            Entry<String> node = nodes.poll();

            if (node.elementName.equals(elementName)) {
                if (node.parent.leftChild == node) {
                    node.parent.leftChild = null;
                    node.parent.availableToAddLeftChildren = true;
                } else if (node.parent.rightChild == node) {
                    node.parent.rightChild = null;
                    node.parent.availableToAddRightChildren = true;
                }

                recursiveRemove(node);
                return true;
            }

            if (node.leftChild != null) nodes.offer(node.leftChild);
            if (node.rightChild != null) nodes.offer(node.rightChild);
        }

        return false;
    }

    private void recursiveRemove(Entry<String> node) {
        if (node.leftChild != null) {
            recursiveRemove(node.leftChild);
            node.leftChild = null;
        }

        if (node.rightChild != null) {
            recursiveRemove(node.rightChild);
            node.rightChild = null;
        }

        list.remove(node);
        size--;
    }




    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }
    @Override
    public void add(int index, String element){
        throw new UnsupportedOperationException();
    }
    @Override
    public String set(int index, String element){
        throw new UnsupportedOperationException();
    }
    @Override
    public String remove(int index){
        throw new UnsupportedOperationException();
    }
    @Override
    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }
    @Override
    protected void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }


}
