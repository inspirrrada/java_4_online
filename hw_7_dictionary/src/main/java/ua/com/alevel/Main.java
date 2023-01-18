package ua.com.alevel;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Dictionary dictionary = new Dictionary<>();
        dictionary.put(1, "A");
        dictionary.put(2, "A");
        dictionary.put(3, "B");
        dictionary.put(4, "C");
        dictionary.put(5, "D");
        dictionary.printDictionary();
    }
}