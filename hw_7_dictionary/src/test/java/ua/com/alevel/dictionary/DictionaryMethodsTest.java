package ua.com.alevel.dictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Set;

public class DictionaryMethodsTest {
    public static Dictionary dictionary = new Dictionary();

    @BeforeAll
    public static void createDictionary() {
        dictionary.put("58000", "Vinnutsya");
        dictionary.put("79000", "Lviv");
        dictionary.put("61000", "Kharkiv");
        dictionary.put("61140", "Kharkiv");
        dictionary.put("62480", "Kharkiv");
    }

    @Test
    @Order(1)
    public void checkPutValuesToDictionary() {
        int sizeBefore = dictionary.size();
        String key1 = "73000";
        String value1 = "Kherson";
        dictionary.put(key1, value1);

        Assertions.assertEquals(dictionary.size(), sizeBefore + 1);
    }

    @Test
    @Order(2)
    public void checkPutValueWithTheSameKey() {
        int sizeBefore = dictionary.size();
        String key = "58000";
        String value = "Chernivtsi";
        dictionary.put(key, value);

        Assertions.assertEquals(dictionary.size(), sizeBefore);
        Assertions.assertEquals(dictionary.get(key), value);
    }

    @Test
    @Order(3)
    public void checkPutValuesFromOtherDictionaryWhereAllKeysNotDuplicate() {
        Dictionary dictionaryOther = new Dictionary();
        dictionaryOther.put("01001", "Kyiv");
        dictionaryOther.put("14000", "Chernigiv");
        dictionaryOther.put("43000", "Lutsk");
        int dictionarySizeBefore = dictionary.size();
        int dictionaryOtherSize = dictionaryOther.size();
        dictionary.putAll(dictionaryOther);

        Assertions.assertEquals(dictionary.size(), dictionarySizeBefore + dictionaryOtherSize);
    }

    @Test
    @Order(4)
    public void checkIsEmpty() {
        Assertions.assertFalse(dictionary.isEmpty());
    }

    @Test
    @Order(5)
    public void checkGetSize() {
        Dictionary newDictionary = new Dictionary();

        Assertions.assertEquals(newDictionary.size(), 0);
        Assertions.assertEquals(dictionary.size(), 5);
    }

    @Test
    @Order(6)
    public void checkGetAllKeys() {
        Set keySet = dictionary.keySet();
        String key = "58000";

        Assertions.assertEquals(keySet.size(), dictionary.size());
        Assertions.assertTrue(keySet.contains(key));
    }

    @Test
    @Order(7)
    public void checkGetAllValues() {
        Collection valuesList = dictionary.values();
        String value = "Lviv";

        Assertions.assertEquals(valuesList.size(), dictionary.size());
        Assertions.assertTrue(valuesList.contains(value));
    }

    @Test
    @Order(8)
    public void checkGetValue() {
        String key = "61000";
        String value = "Kharkiv";
        String valueReceived = (String) dictionary.get(key);

        Assertions.assertEquals(valueReceived, value);
    }

    @Test
    @Order(9)
    public void checkContainsKeyInDictionary() {
        String key1 = "58000";
        String key2 = "11111";

        Assertions.assertTrue(dictionary.containsKey(key1));
        Assertions.assertFalse(dictionary.containsKey(key2));
    }

    @Test
    @Order(10)
    public void checkContainsValueInDictionary() {
        String value1 = "Lviv";
        String value2 = "Odesa";

        Assertions.assertTrue(dictionary.containsValue(value1));
        Assertions.assertFalse(dictionary.containsValue(value2));
    }

    @Test
    @Order(11)
    public void checkRemoveValueByKey() {
        String key = "79000";
        int sizeBefore = dictionary.size();
        dictionary.remove(key);

        Assertions.assertEquals(dictionary.size(), sizeBefore - 1);
    }

    @Test
    @Order(12)
    public void checkClearDictionary() {
        Dictionary newDictionary = new Dictionary();
        newDictionary.putAll(dictionary);
        newDictionary.clear();

        Assertions.assertEquals(newDictionary.size(), 0);
    }
}
