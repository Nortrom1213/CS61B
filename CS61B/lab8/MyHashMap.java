import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private double loadFactor = 0.75;
    private int size = 0;
    private int capacity = 16;
    private HashSet<K> keySet = new HashSet<K>();
    private Entry<K, V>[] table;
    private int threshhold;
    private static int maximum = 1 << 30;

    private static class Entry<K, V> {
        private K key;
        private V val;
        private Entry<K, V> next;
        private boolean deleted;

        public Entry(K k, V v, Entry<K, V> n) {
            key = k;
            val = v;
            next = n;
            deleted = false;
        }

        public Entry(K k, V v, Entry<K, V> n, boolean deleted) {
            key = k;
            val = v;
            next = n;
            this.deleted = deleted;
        }
    }

    private class MyHashMapIterator implements Iterator<K> {

        /**
         * Create a new ULLMapIter by setting cur to the first node in the
         * linked list that stores the key-value pairs.
         */
        MyHashMapIterator() {
            List<K> iter = new ArrayList<>();
            for (int i = 0; i < capacity; i++) {
                for (Entry<K, V> e = table[i]; e != null; e = e.next) {
                    if (e.deleted) {
                        continue;
                    }
                    iter.add(e.key);
                }
            }
            cur = iter.iterator();
        }

        @Override
        public boolean hasNext() {
            return cur.hasNext();
        }

        @Override
        public K next() {
            return cur.next();
        }

        private Iterator<K> cur;

    }

    public MyHashMap(){
        threshhold=(int)(loadFactor*capacity);
        table = new Entry[capacity]; // Entry<K,V> Wrong???
    }

    public MyHashMap(int initialCapacity){
        capacity = initialCapacity;
        threshhold=(int)(loadFactor*capacity);
        table = new Entry[capacity];
    }

    public MyHashMap(int initialCapacity, double loadFactor){
        capacity = initialCapacity;
        this.loadFactor =  loadFactor;
        threshhold=(int)(loadFactor*capacity);
        table = (Entry<K,V>[])new Object[capacity];
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        threshhold = (int) (capacity * loadFactor);
        table = new Entry[capacity];
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        int index = (hash(key.hashCode()) + maximum) % maximum % capacity;
        if (table[index] == null) {
            return null;
        }
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.key.equals(key) && !e.deleted) {
                return e.val;
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int index = (hash(key.hashCode()) +maximum) % maximum % capacity;
        if (table[index] != null) {
          for (Entry<K, V> e = table[index]; e != null; e = e.next) {
              if (e.key.equals(key) && !e.deleted) {
                  e.val = value;
                  return;
              }
          }
        }

        table[index] = new Entry<K, V>(key, value, table[index]);
        size++;
        if (size > threshhold) {
            int oldCapacity = capacity;
            capacity = capacity * 2 + 1;
            Entry[] oldTable = table;
            clear();
            for (int i = 0; i < oldCapacity; i++) {
                for (Entry<K, V> e = oldTable[i]; e != null; e = e.next) {
                    if (e.deleted) {
                        continue;
                    }
                    int indexx = ((hash(e.key.hashCode()) + maximum) % maximum) % capacity;
                    table[indexx] = new Entry<K, V>(e.key, e.val, table[indexx]);
                    size++;
                }
            }

        }
    }

    @Override
    public V remove(K key) {
        int index = hash(key.hashCode()) % capacity;
        Entry<K, V> f = table[index];
        if (f == null) {
            return null;
        }
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.key.equals(key) && !e.deleted) {
                e.deleted = true;
                size--;
                return e.val;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        int index = hash(key.hashCode()) % capacity;
        Entry<K, V> f = table[index];
        if (f == null) {
            return null;
        }
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.key.equals(key) && e.val == value && !e.deleted) {
                e.deleted = true;
                size--;
                return e.val;
            }
        }
        return null;
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();

        for (int i = 0; i < capacity; i++){
            if (table[i] != null){
                for (Entry<K, V> e = table[i]; e != null; e = e.next) {
                    set.add(e.key);
                }
            }
        }
        return set;
    }

    private int hash(int h) {
        return (hashhelper(h) + capacity) % capacity;
    }


    private int hashhelper(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return (h ^ (h >>> 7) ^ (h >>> 4));
    }
}
