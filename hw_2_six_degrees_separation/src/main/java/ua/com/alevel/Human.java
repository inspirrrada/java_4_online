package ua.com.alevel;

public class Human {
    private String name;
    private boolean isUnfriendly;

    public Human(String name, boolean isUnfriendly) {
        this.name = name;
        this.isUnfriendly = isUnfriendly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUnfriendly() {
        return isUnfriendly;
    }

    public void setUnfriendly(boolean unfriendly) {
        isUnfriendly = unfriendly;
    }

    public void sendMessage(String message) {
        System.out.println(message);
    }
}
