package ua.com.alevel.dictionary;

import java.util.*;

public class Dictionary<K, V> {
    private ArrayList<Pair> pairsList = new ArrayList<>();

    private static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    int size = 0;


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
        for (Pair currentPair : this.pairsList) {
            if (currentPair.key.equals(key)) {
                containsKey = true;
                break;
            }
        }
        return containsKey;
    }

    public boolean containsValue(V value) {
        boolean containsValue = false;
        for (Pair currentPair : this.pairsList) {
            if (currentPair.value.equals(value)) {
                containsValue = true;
                break;
            }
        }
        return containsValue;
    }

    public V get(K key) {
        V value = null;
        for (Pair currentPair : this.pairsList) {
            K currentKey = (K) currentPair.key;
            if (currentKey.equals(key)) {
                value = (V) currentPair.value;
                break;
            }
        }
        if (value == null) {
            System.out.println("We can't find such key in this dictionary!");
        }
        return value;
    }

    public boolean put(K key, V value) {
        if (!containsKey(key)) {
            Pair newPair = new Pair(key, value);
            pairsList.add(newPair);
            size++;
        } else {
            for (Pair currentPair : this.pairsList) {
                if (currentPair.key.equals(key)) {
                    currentPair.value = value;            //update value
                }
            }
        }
        return true;
    }

    public boolean remove(K key) {
        boolean hasKey = false;
        for (Pair currentPair : this.pairsList) {
            if (currentPair.key.equals(key)) {
                this.pairsList.remove(currentPair);
                size--;
                hasKey = true;
                break;
            }
        }
        if (!hasKey) {
            System.out.println("We can't find such key in this dictionary!");
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Pair> getPairsList() {
        return pairsList;
    }

    public void setPairsList(ArrayList<Pair> pairsList) {
        this.pairsList = pairsList;
    }

    public boolean putAll(Dictionary<K, V> dictionary) {
        for (Pair currentPair : dictionary.getPairsList()) {
            K currentKey = (K) currentPair.key;
            V currentValue = (V) currentPair.value;
            this.put(currentKey, currentValue);
        }
        return true;
    }

    public boolean clear() {
        Iterator iterator = this.pairsList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        this.size = 0;
        return true;
    }

    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet<>();
        for (Pair currentPair : this.pairsList) {
            keySet.add((K) currentPair.key);
        }
        return keySet;
    }

    public Collection<V> values() {
        Collection<V> valueCollection = new ArrayList<>();
        for (Pair currentPair : this.pairsList) {
            valueCollection.add((V) currentPair.value);
        }
        return valueCollection;
    }

    @Override
    public String toString() {
        String value = "";
        if (pairsList.size() == 0) {
            value = "[]";
        } else {
            for (int i = 0; i < pairsList.size(); i++) {
                Pair currentPair = pairsList.get(i);
                if (i == 0) {
                    value += "[ ";
                }
                value += "{key: " + currentPair.key + ", value: " + currentPair.value + "} ";
                if (i == pairsList.size() - 1) {
                    value += "]";
                }
            }
        }
        return value;
    }
}
