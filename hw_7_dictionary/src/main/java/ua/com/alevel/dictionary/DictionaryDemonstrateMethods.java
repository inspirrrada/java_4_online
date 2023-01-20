package ua.com.alevel.dictionary;

import com.diogonunes.jcolor.AnsiFormat;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Attribute.UNDERLINE;
import java.util.Collection;
import java.util.Set;

public class DictionaryDemonstrateMethods {
    Dictionary zipCodesUa;
    private static AnsiFormat blueText = new AnsiFormat(BRIGHT_BLUE_TEXT());
    private static AnsiFormat yellowText = new AnsiFormat(YELLOW_TEXT());
    private static AnsiFormat reverse = new AnsiFormat(REVERSE());
    private static AnsiFormat underlinedText = new AnsiFormat(UNDERLINE());

    public void start() {
        System.out.println();
        System.out.println(reverse.format("WELCOME TO THE DEMOSTRATION OF DICTIONARY METHODS "));
        System.out.println("(on examples of zip codes)");
        System.out.println();
        addMethodsDemonstration();
        emptyAndSizeMethodsDemonstration();
        getMethodsDemonstration();
        containsMethodsDemonstration();
        deleteMethodsDemonstration();
    }

    public void putValuesToTheNewDictionary() {
        zipCodesUa = new Dictionary();
        System.out.println(underlinedText.format("Put values to the new Dictionary"));
        System.out.println("Value 1: zipCode - 58000, city - Chernivtsi.");
        System.out.println("Value 2: zipCode - 79000, city - Lviv.");
        zipCodesUa.put("58000", "Chernivtsi");
        zipCodesUa.put("79000", "Lviv");
        System.out.println(blueText.format("Dictionary result: " + zipCodesUa.toString()));
    }

    public void addValuesFromOtherDictionary() {
        System.out.println(underlinedText.format("Put values from other Dictionary"));
        Dictionary dictionary = new Dictionary();
        dictionary.put("61000", "Kharkiv");
        dictionary.put("61140", "Kharkiv");
        dictionary.put("62480", "Kharkiv");
        System.out.println("Other Dictionary: " + dictionary);
        zipCodesUa.putAll(dictionary);
        System.out.println(blueText.format("Dictionary result: " + zipCodesUa.toString()));
    }

    public void addMethodsDemonstration() {
        System.out.println(yellowText.format("CREATE DICTIONARY AND ADD VALUES"));
        putValuesToTheNewDictionary();
        addValuesFromOtherDictionary();
        System.out.println();
    }

    public void checkIsEmpty() {
        System.out.println(underlinedText.format("Check or Dictionary is empty"));
        Dictionary newDictionary = new Dictionary();
        System.out.println("Dictionary 1: " + zipCodesUa);
        System.out.println("Dictionary 2: " + newDictionary);
        System.out.println(blueText.format("Dictionary 1, is empty? " + zipCodesUa.isEmpty() + ". Dictionary 2, is empty? " + newDictionary.isEmpty()));
    }

    public void getSize() {
        System.out.println(underlinedText.format("Get size of Dictionary"));
        Dictionary newDictionary = new Dictionary();
        System.out.println("Dictionary 1: " + zipCodesUa);
        System.out.println("Dictionary 2: " + newDictionary);
        System.out.println(blueText.format("Dictionary 1 size - " + zipCodesUa.size() + ". Dictionary 2 size - " + newDictionary.size()));
    }

    public void emptyAndSizeMethodsDemonstration() {
        System.out.println(yellowText.format("CHECK EMPTY AND SIZE OF DICTIONARY "));
        checkIsEmpty();
        getSize();
        System.out.println();
    }

    public void getAllKeys() {
        System.out.println(underlinedText.format("Get all keys from Dictionary"));
        System.out.println("Dictionary: " + zipCodesUa);
        Set keySet = zipCodesUa.keySet();
        System.out.println(blueText.format("All keys: " + keySet.toString()));
    }

    public void getAllValues() {
        System.out.println(underlinedText.format("Get all values from Dictionary"));
        System.out.println("Dictionary: " + zipCodesUa);
        Collection valuesList = zipCodesUa.values();
        System.out.println(blueText.format("All values: " + valuesList.toString()));
    }

    public void getValue() {
        System.out.println(underlinedText.format("Get value by a key"));
        System.out.println("Dictionary: " + zipCodesUa);
        String key = "58000";
        System.out.println("Get value by key " + key);
        String city = (String) zipCodesUa.get(key);
        System.out.println(blueText.format("Value: " + city));
    }

    public void getMethodsDemonstration() {
        System.out.println(yellowText.format("GET SIZE AND VALUE OF DICTIONARY"));
        getValue();
        getAllKeys();
        getAllValues();
        System.out.println();
    }

    public void containsKeyInDictionary() {
        System.out.println(underlinedText.format("Check if Dictionary contains a key"));
        System.out.println("Dictionary: " + zipCodesUa);
        String key1 = "58000";
        String key2 = "11111";
        System.out.println("Key 1: " + key1 + ". Key 2: " + key2);
        System.out.println(blueText.format("Dictionary contains key1? " + zipCodesUa.containsKey(key1) + ". Dictionary contains key2? " + zipCodesUa.containsKey(key2)));
    }

    public void containsValueInDictionary() {
        System.out.println(underlinedText.format("Check if Dictionary contains a value"));
        System.out.println("Dictionary: " + zipCodesUa);
        String value1 = "Chernivtsi";
        String value2 = "Odesa";
        System.out.println("Value 1: " + value1 + ". Value 2: " + value2);
        System.out.println(blueText.format("Dictionary contains value1? " + zipCodesUa.containsValue(value1) + ". Dictionary contains value2? " + zipCodesUa.containsValue(value2)));
    }

    public void containsMethodsDemonstration() {
        System.out.println(yellowText.format("CHECK CONTAINING VALUES IN DICTIONARY"));
        containsKeyInDictionary();
        containsValueInDictionary();
        System.out.println();
    }

    public void removeValueByKey() {
        System.out.println(underlinedText.format("Remove value by a key"));
        System.out.println("Dictionary before: " + zipCodesUa);
        String key = "79000";
        System.out.println("Delete by key: " + key);
        zipCodesUa.remove(key);
        System.out.println(blueText.format("Dictionary after: " + zipCodesUa));
    }

    public void clearDictionary() {
        System.out.println(underlinedText.format("Clear Dictionary"));
        System.out.println("Dictionary before: " + zipCodesUa);
        zipCodesUa.clear();
        System.out.println(blueText.format("Dictionary after: " + zipCodesUa));
    }

    public void deleteMethodsDemonstration() {
        System.out.println(yellowText.format("DELETING IN DICTIONARY"));
        removeValueByKey();
        clearDictionary();
        System.out.println();
    }
}
