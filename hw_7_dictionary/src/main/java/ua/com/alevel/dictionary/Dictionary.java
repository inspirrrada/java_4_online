package ua.com.alevel.dictionary;

import java.util.*;

public class Dictionary<K, V> {

    private ArrayList<Pair<K, V>> pairsList = new ArrayList<>();
    private int size = 0;

    private static class Pair<K, V> {

        private final K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(K key) {
        boolean containsKey = false;
        for (Pair<K, V> currentPair : this.pairsList) {
            if (currentPair.key.equals(key)) {
                containsKey = true;
                break;
            }
        }
        return containsKey;
    }

    public boolean containsValue(V value) {
        boolean containsValue = false;
        for (Pair<K, V> currentPair : this.pairsList) {
            if (currentPair.value.equals(value)) {
                containsValue = true;
                break;
            }
        }
        return containsValue;
    }

    public V get(K key) {
        V value = null;
        for (Pair<K, V> currentPair : this.pairsList) {
            K currentKey = currentPair.key;
            if (currentKey.equals(key)) {
                value = currentPair.value;
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
            Pair<K, V> newPair = new Pair<>(key, value);
            pairsList.add(newPair);
            size++;
        } else {
            for (Pair<K, V> currentPair : this.pairsList) {
                if (currentPair.key.equals(key)) {
                    currentPair.value = value;            //update value
                }
            }
        }
        return true;
    }

    public boolean remove(K key) {
        boolean hasKey = false;
        for (Pair<K, V> currentPair : this.pairsList) {
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

    public ArrayList<Pair<K, V>> getPairsList() {
        return pairsList;
    }

    public void setPairsList(ArrayList<Pair<K, V>> pairsList) {
        this.pairsList = pairsList;
    }

    public void putAll(Dictionary<K, V> dictionary) {
        for (Pair<K, V> currentPair : dictionary.getPairsList()) {
            K currentKey = currentPair.key;
            V currentValue = currentPair.value;
            this.put(currentKey, currentValue);
        }
    }

    public void clear() {
        Iterator<Pair<K, V>> iterator = this.pairsList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        this.size = 0;
    }

    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet<>();
        for (Pair<K, V> currentPair : this.pairsList) {
            keySet.add(currentPair.key);
        }
        return keySet;
    }

    public Collection<V> values() {
        Collection<V> valueCollection = new ArrayList<>();
        for (Pair<K, V> currentPair : this.pairsList) {
            valueCollection.add(currentPair.value);
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
                Pair<K, V> currentPair = pairsList.get(i);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dictionary<?, ?> that = (Dictionary<?, ?>) o;
        return size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
