public class Human {
    String name;
    Boolean getMessage;

    public void sendMessage(Human human) {
        System.out.println("" + this.name + " sent message to " + human.name);
        human.getMessage = true;
    }
}