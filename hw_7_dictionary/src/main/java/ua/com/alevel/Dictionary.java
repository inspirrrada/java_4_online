package ua.com.alevel;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Dictionary<K, V> {
    private ArrayList[] array = new ArrayList[2];
    private ArrayList<Object> keyList = new ArrayList<>();
    private ArrayList<Object> valueList = new ArrayList<>();
    private int size = 0;


    public Dictionary() {
        array[0] = keyList;
        array[1] = valueList;
    }

    public int size() {
        return this.size;
    }
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }
    public boolean containsKey(K key) {
        boolean containsKey = false;
        for (Object currentKey : this.keyList) {
            if (currentKey.equals(key)) {
                containsKey = true;
                break;
            }
        }
            return containsKey;
    }
    public boolean containsValue(V value) {
        boolean containsValue = false;
        for (Object currentValue : this.valueList) {
            if (currentValue.equals(value)) {
                containsValue = true;
                break;
            }
        }
        return containsValue;
    }
    public V get(K key) {
        int keyIndex = -1;
        for (int i = 0; i < this.keyList.size(); i++) {
            K currentKey = (K) this.keyList.get(i);
            if (currentKey.equals(key)) {
                keyIndex = i;
                break;
            }
        }
        if (keyIndex != -1) {
            return (V) this.valueList.get(keyIndex);
        } else {
            System.out.println("We can't find such key in this dictionary!");
            return null;
        }
    }
    public boolean put(K key, V value) {
        if (!containsKey(key)) {
            this.keyList.add(key);
            this.valueList.add(value);
            size++;
        } else {
            int keyIndex = this.keyList.indexOf(key);
            this.valueList.set(keyIndex, value);
        }
        return true;
    }
    public boolean remove(K key) {
        int keyIndex = -1;
        for (int i = 0; i < this.keyList.size(); i++) {
            K currentKey = (K) this.keyList.get(i);
            if (currentKey.equals(key)) {
                keyIndex = i;
                break;
            }
        }
        if (keyIndex != -1) {
            this.keyList.remove(key);
            this.valueList.remove(get(key));
            return true;
        } else {
            System.out.println("We can't find such key in this dictionary!");
            return false;
        }
    }
    public boolean putAll(Dictionary<K, V> dictionary) {
        for (int i = 0; i < dictionary.size(); i++) {
            K key = (K) dictionary.getKeyList().get(i);
            V value = (V) dictionary.getValueList().get(i);
            put(key, value);
        }
        return true;
    }
    public boolean clear() {
        this.keyList = new ArrayList<>();
        this.valueList = new ArrayList<>();
        this.size = 0;
        return true;
    }
    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet<>();
        for (Object currentKey : this.keyList) {
            keySet.add((K) currentKey);
        }
        return keySet;
    }
    public Collection<V> values() {
        Collection<V> valueCollection = new ArrayList<>();
        for (Object currentValue : this.valueList) {
            valueCollection.add((V) currentValue);
        }
        return valueCollection;
    }

    public void printDictionary() {
        for (int i = 0; i < this.size(); i++) {
            ArrayList keyList = this.getKeyList();
            ArrayList valueList = this.getValueList();
            System.out.println("key: " + keyList.get(i) + ", value: " + valueList.get(i));
        }
    }


    public ArrayList[] getArray() {
        return array;
    }

    public void setArray(ArrayList[] array) {
        this.array = array;
    }

    public ArrayList<Object> getKeyList() {
        return keyList;
    }

    public void setKeyList(ArrayList<Object> keyList) {
        this.keyList = keyList;
    }

    public ArrayList<Object> getValueList() {
        return valueList;
    }

    public void setValueList(ArrayList<Object> valueList) {
        this.valueList = valueList;
    }
}
