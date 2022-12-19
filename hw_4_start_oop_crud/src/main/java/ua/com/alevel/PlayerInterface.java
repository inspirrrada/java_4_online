package ua.com.alevel;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerInterface {

    public void start() throws IOException {
        System.out.println("Welcome to the World of JAVA!");
        System.out.println("\nPlease select your options:");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String selectedOption;

        menu();

        while( (selectedOption = reader.readLine()) != null) {
                    if (selectedOption.equals("3")) {
                        updateCrud(reader, selectedOption);
                    } else {
                        crud(reader, selectedOption);
                    }


        }

    }

    private void menu() {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("If you want to create new player, please enter 1");
        System.out.println("If you want to find player, please enter 2");
        System.out.println("If you want to update player, please enter 3");
        System.out.println("If you want to delete player, please enter 4");
        System.out.println("If you want to find all players, please enter 5");
        System.out.println("If you want to close application, please enter 6");
        System.out.println("-----------------------------------------------------");
    }

    private void subMenuOfUpdate() {
        System.out.println("\n<><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("If you want to update age of player, please enter 7");
        System.out.println("If you want to update nickname of player, please enter 8");
        System.out.println("If you want to update level of player, please enter 9");
        System.out.println("If you want to update guild of player, please enter 10");
        System.out.println("If you want to return to the main menu, please enter 0");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    private void crud(BufferedReader reader, String selectedOption) throws IOException {
        switch(selectedOption) {
            case "1": create(reader);
            break;

            case "2": findByEmail(reader);
            break;

            case "3": updateCrud(reader, selectedOption);
            break;

            case "4": deleteByEmail(reader);
            break;

            case "5": findAllPlayers();
            break;

            case "6": stop();
            break;

            default:
                System.out.println("Wrong value! Select menu again.");
        }
        if (!selectedOption.equals("3")) {
            menu();
        }


    }

    private void updateCrud(BufferedReader reader, String selectedOption) throws IOException {
        switch(selectedOption) {
            case "7": updateAge(reader);
                break;

            case "8": updateNickname(reader);
                break;

            case "9": updateLevel(reader);
                break;

            case "10": updateGuild(reader);
                break;

            case "0": menu();
                break;

            default:
                System.out.println("Wrong value! Select update option again.");
        }

        subMenuOfUpdate();

    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 1. Create player");

        System.out.println("Please enter your age. \n***You must be of legal age to play this game.");
        String ageS;
        int age = 0;
        boolean correctAge = false;
        while(!correctAge) {
            ageS = reader.readLine();
            if (ageS.matches("\\d+")) {
                correctAge = true;
                age = Integer.parseInt(ageS);
            } else {
                System.out.println("Invalid value! Please enter number, not string!");
                correctAge = false;
            }
        }

        if (age < 18) {
                System.out.println("Grow up first and come later");
                stop();
            } else {
            Player player = new Player();
            player.setAge(age);

            System.out.println("\nPlease enter your email. \n***Pay attention, you can't change your email in the future.");
            String email = reader.readLine();
            boolean correctEmail = player.setEmail(email);
            while(!correctEmail) {
                System.out.println("Please check and try to enter your email again");
                email = reader.readLine();
                correctEmail = player.setEmail(email);
            }


            System.out.println("\nPlease enter your nickname. \n***Pay attention, nickname can't have only digits.");
            String nickname = reader.readLine();
            boolean correctNickname = player.setNickname(nickname);
            while(!correctNickname) {
                System.out.println("Please check and try to enter your nickname again");
                nickname = reader.readLine();
                correctNickname = player.setNickname(nickname);
            }

            System.out.println("\nPlease enter your level (from 0 to 100)");
            int level = Integer.parseInt(reader.readLine());
            boolean correctLevel = player.setLevel(level);
            while(!correctLevel) {
                System.out.println("Please check and try to enter your level again");
                level = Integer.parseInt(reader.readLine());
                correctLevel = player.setLevel(level);
            }

            System.out.println("\nPlease enter your guild");
            String guild = reader.readLine();
            player.setGuild(guild);

            PlayerStorage.addPlayer(player);
            System.out.println("\nCongratulations! Your player was created.");

        }


    }

    private void findByEmail(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 2. Find player by email");
        String email = reader.readLine();

        Player player = PlayerStorage.getPlayer(email);
        if (player == null) {
            System.out.println("We don't have player with such email!");
        } else {
            System.out.println("Player = " + player);
        }
        System.out.println();

    }


    private void updateByEmail(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 3. Update player by email");

        System.out.println("Please enter your email");
        String email = reader.readLine();
        Player player = PlayerStorage.getPlayer(email);

        if (player == null) {
            System.out.println("We don't have player with such email!");
        } else {
            System.out.println("Great," + player.getGuild() + "! Please select update options below:");
            subMenuOfUpdate();
        }
    }

    private void updateAge(BufferedReader reader) throws IOException {
        System.out.println("\nSubmenu 3.1. Update age of player");

        System.out.println("Please enter your email");
        String email = reader.readLine();
        Player player = PlayerStorage.getPlayer(email);

        if (player == null) {
            System.out.println("We don't have player with such email!");
        } else {
            System.out.println("Please enter new age:");
            int age = Integer.parseInt(reader.readLine());

            if (age < 11) {
                System.out.println("Age is too young for this game. Your changes weren't saved.");
            } else {
                player.setAge(age);
                System.out.println("Dear" + player + ", age was updated successfully.");
            }
        }

    }

    private void updateNickname(BufferedReader reader) throws IOException {
        System.out.println("\nSubmenu 3.2. Update nickname of player");

        System.out.println("Please enter your email");
        String email = reader.readLine();
        Player player = PlayerStorage.getPlayer(email);

        if (player == null) {
            System.out.println("We don't have player with such email!");
        } else {
            System.out.println("Please enter new nickname:");
            String nickname = reader.readLine();
            boolean correctNickname = player.setNickname(nickname);

            if (!correctNickname) {
                System.out.println("Something went wrong. Your changes weren't saved.");
            } else {
                player.setNickname(nickname);
                System.out.println("Dear player, nickname was updated successfully.");
            }
        }
    }

    private void updateLevel(BufferedReader reader) throws IOException {
        System.out.println("\nSubmenu 3.3. Update level of player");

        System.out.println("Please enter your email");
        String email = reader.readLine();
        Player player = PlayerStorage.getPlayer(email);

        if (player == null) {
            System.out.println("We don't have player with such email!");
        } else {
            System.out.println("Please enter new level:");
            int level = Integer.parseInt(reader.readLine());
            boolean correctLevel = player.setLevel(level);

            if (!correctLevel) {
                System.out.println("Something went wrong. Your changes weren't saved.");
            } else {
                player.setLevel(level);
                System.out.println("Dear " + player.getNickname() +", level was updated successfully.");
            }

        }
    }

    private void updateGuild(BufferedReader reader) throws IOException {
        System.out.println("\nSubmenu 3.4. Update guild of player");

        System.out.println("Please enter your email");
        String email = reader.readLine();
        Player player = PlayerStorage.getPlayer(email);

        if (player == null) {
            System.out.println("We don't have player with such email!");
        } else {
            System.out.println("Please enter new guild:");
            String guild = reader.readLine();

            if (guild != null && !guild.equals("")) {
                player.setGuild(guild);
                System.out.println("Dear " + player.getNickname() +", guild was updated successfully.");
            }

        }
    }

    private void deleteByEmail(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 4. Delete player by email");
        String email = reader.readLine();

        boolean wasDeleted = PlayerStorage.deletePlayer(email);
        if (wasDeleted) {
            System.out.println("Your player was successfully deleted\n");
        } else {
            System.out.println("We can't find player with such email!\n");
        }
    }

    private void findAllPlayers() {
        System.out.println("\nMenu 5. Find all players");
        Player[] players = PlayerStorage.getPlayers();
        boolean isArrayEmpty = true;

        for (Player player: players) {
            if(player != null) {
                isArrayEmpty = false;
                break;
            }
        }

        if (isArrayEmpty) {
            System.out.println("There are no players.");
        } else {
            System.out.println("Players:");

            for (int i = 0; i < players.length; i++) {
                if (players[i] != null) {
                    System.out.println((i+1) + ". " + players[i]);
                }
            }
        }

    }

    private void stop() {
        System.out.println("\nThe application is finished.");
        System.exit(0);
    }
}
