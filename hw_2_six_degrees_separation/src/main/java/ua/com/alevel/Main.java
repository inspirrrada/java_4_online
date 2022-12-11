public class Main {
    public static void main(String[] args) {
        Human human1 = new Human();
        human1.name = "Alex";

        Human human2 = new Human();
        human2.name = "Boris";

        Human human3 = new Human();
        human3.name = "Vladislava";

        Human human4 = new Human();
        human4.name = "Gloria";

        Human human5 = new Human();
        human5.name = "Denys";

        Human human6 = new Human();
        human6.name = "Elly";

        human1.sendMessage(human2);
        human2.sendMessage(human3);
        human3.sendMessage(human4);
        human4.sendMessage(human5);
        human5.sendMessage(human6);


    }
}