package ua.com.alevel;

public class Main {
    public static void main(String[] args) {

        MatList<Number> matList = new MatList<>();
        MatList<Number> matListInside = new MatList<>();
        matListInside.add(1);
        matListInside.add(2);
        matListInside.add(3);
        matListInside.add(4);
        matListInside.add(5);
        matList.addAll(2, new MatList<>());

        System.out.println(matList);
    }
}