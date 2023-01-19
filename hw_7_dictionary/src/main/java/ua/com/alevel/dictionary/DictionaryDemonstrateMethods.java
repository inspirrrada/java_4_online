package ua.com.alevel.dictionary;

import com.diogonunes.jcolor.AnsiFormat;

import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Attribute.UNDERLINE;

public class DictionaryDemonstrateMethods {

    private static AnsiFormat blueText = new AnsiFormat(BRIGHT_BLUE_TEXT());
    private static AnsiFormat yellowText = new AnsiFormat(YELLOW_TEXT());
    private static AnsiFormat reverse = new AnsiFormat(REVERSE());
    private static AnsiFormat underlinedText = new AnsiFormat(UNDERLINE());

    public void start() {
        System.out.println();
        System.out.println(reverse.format("WELCOME TO THE DEMOSTRATION OF DICTIONARY METHODS "));
        System.out.println("(on examples of random integers)");
        System.out.println();
//        createDemonstration();
//        addMethodsDemonstration();
//        joinMethodsDemonstration();
//        sortingMethodsDemonstration();
//        getMaxMinDemonstration();
//        averageMedianDemonstration();
//        toArrayDemonstration();
//        cutAndClearDemonstration();
    }

    public void

    int size()
    boolean isEmpty()
    boolean containsKey(K key)
    boolean containsValue(V value)
    V get(K key)
    boolean put(K key, V value)
    boolean remove(K key)
    boolean putAll(Dictionary<K, V> dictionary)
    boolean clear()
    Set<K> keySet()
    Collection<V> values()

}
