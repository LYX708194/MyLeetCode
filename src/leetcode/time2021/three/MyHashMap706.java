package leetcode.time2021.three;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 706 设计哈希映射
 *
 * @author lyx
 * @date 2021/3/14 11:48
 */
public class MyHashMap706 {


    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashMap706() {
        data = new LinkedList[BASE];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        if (null == data[h]){
            data[h] = new LinkedList();
        }
        Iterator<Entry> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Entry entry = iterator.next();
            if (entry.getKey() == key){
                entry.setValue(value);
                return;
            }
        }
        data[h].addLast(new Entry(key,value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h = hash(key);
        if (null == data[h]){
            return -1;
        }
        Iterator<Entry> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Entry entry = iterator.next();
            if (entry.getKey() == key){
                return entry.getValue();
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        if (null == data[h]){
            return ;
        }
        Iterator<Entry> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Entry entry = iterator.next();
            if (entry.getKey() == key){
                iterator.remove();
                return;
            }
        }
    }

    private int hash(int key){
        return key % BASE;
    }


    private class Entry{
        private int key;
        private int value;
        public Entry(int key,int value){
            this.key = key;
            this.value = value;
        }
        public int getKey(){
            return key;
        }
        public int getValue(){
            return value;
        }
        public void setValue(int value){
            this.value = value;
        }
    }

}
