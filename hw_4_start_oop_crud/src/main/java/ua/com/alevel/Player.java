package ua.com.alevel;

public class Player {

    private int age;
    private String email;
    private String nickname;
    private int level;
    private String guild;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
//        if (age < 11) {
//            System.out.println("Grow up first and come later");
//        } else {
//            this.age = age;
//        }
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        boolean correctEmail;

        if (email.matches("^(.+)@(.+)$")) {
            this.email = email;
            correctEmail = true;
        } else {
            System.out.println("Invalid value!");
            correctEmail = false;
        }

        return correctEmail;

    }

    public String getNickname() {
        return nickname;
    }

    public boolean setNickname(String nickname) {
        boolean correctNickname;
        //nickname can't have only digits
       if (nickname.matches("\\d+")) {
            System.out.println("Nickname can't contain only digits!");
            correctNickname = false;
        } else {
            this.nickname = nickname;
            correctNickname = true;
        }

        return correctNickname;
    }

    public int getLevel() {
        return level;
    }

    public boolean setLevel(int level) {
        boolean correctLevel;

        if (level >= 0 && level <= 100) {
            this.level = level;
            correctLevel = true;
        } else {
            correctLevel = false;
            System.out.println("Level can't be less than 0 and more than 100");
        }

        return correctLevel;
    }


    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }


    @Override
    public String toString() { //TODO add id
        return "Player{" +
                "nickname='" + nickname + "', " +
                "level='" + level + "', " +
                "guild='" + guild + "', " +
                "email='" + email + "', " +
                "age='" + age + "'}";
    }
}
