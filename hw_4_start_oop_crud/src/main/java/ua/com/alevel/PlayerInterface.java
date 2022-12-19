package ua.com.alevel;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerInterface {

    public void start() throws IOException {
        System.out.println("Welcome to the World of JAVA!");
        System.out.println("Please select your options:");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String selectedOption;

        menu();

        while( (selectedOption = reader.readLine()) != null) {
                        crud(reader, selectedOption);
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

//    private void subMenuOfUpdate() {
//        System.out.println("\n<><><><><><><><><><><><><><><><><><><><><><><><><>");
//        System.out.println("If you want to update age of player, please enter 7");
//        System.out.println("If you want to update nickname of player, please enter 8");
//        System.out.println("If you want to update level of player, please enter 9");
//        System.out.println("If you want to update guild of player, please enter 10");
//        System.out.println("If you want to return to the main menu, please enter 0");
//        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
//    }

    private void crud(BufferedReader reader, String selectedOption) throws IOException {
        switch(selectedOption) {
            case "1": create(reader);
            break;

            case "2": findByEmail(reader);
            break;

            case "3": updateByEmail(reader);
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

            menu();

    }


    private void create(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 1. CREATE PLAYER");

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
                System.out.println("Invalid value! Please enter again, but number NOT string!");
                correctAge = false;
            }
        }

        if (age < 18) {
                System.out.println("Grow up first and come later.");
                stop();
            } else if (age > 100) {
            System.out.println("Your age is fantastic! You're too good for this game");
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
        System.out.println("\nMenu 2. FIND PLAYER BY EMAIL");
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
        System.out.println("\nMenu 3. UPDATE PLAYER BY EMAIL");

        System.out.println("Please enter your email");
        String email = reader.readLine();
        Player player = PlayerStorage.getPlayer(email);

        if (player == null) {
            System.out.println("We don't have player with such email!");
        } else {
            System.out.println("Great," + player.getNickname() + "! Please update your details below.");
            System.out.println("If you don't want to update some detail, press 0 and you go to the next.");

            //update age
            System.out.println("\nPlease enter new age:");
            String ageS = reader.readLine();

            if (!ageS.equals("0")) {
                int age = Integer.parseInt(ageS);
                    PlayerStorage.updatePlayerAge(email, age);
                    System.out.println("Dear " + player.getNickname() + ", age was updated successfully.");
            } else {
                System.out.println("Your age remains the same.");
            }

            //update nickname
            System.out.println("\nPlease enter new nickname:");
            String nickname = reader.readLine();

            if (!nickname.equals("0")) {
                boolean correctNickname = player.setNickname(nickname);

                if (!correctNickname) {
                    System.out.println("Your changes of nickname weren't saved.");
                } else {
                    PlayerStorage.updatePlayerNickName(email, nickname);
                    System.out.println("Dear player, nickname was updated successfully to the '" + nickname + "'.");
                }
            } else {
                System.out.println("Your nickname remains the same.");
            }


            //update level
            System.out.println("\nPlease enter new level:");
            String levelS = reader.readLine();

            if (!levelS.equals("0")) {
                int level = Integer.parseInt(levelS);
                boolean correctLevel = player.setLevel(level);

                if (!correctLevel) {
                    System.out.println("Your changes of level weren't saved.");
                } else {
                    PlayerStorage.updatePlayerLevel(email, level);
                    System.out.println("Dear " + player.getNickname() +", level was updated successfully.");
                }
            } else {
                System.out.println("Your level remains the same.");
            }



            //update guild
            System.out.println("\nPlease enter new guild:");
            String guild = reader.readLine();

            if (!guild.equals("0")) {
                PlayerStorage.updatePlayerGuild(email, guild);
                    System.out.println("Dear " + player.getNickname() +", guild was updated successfully.");

            } else {
                System.out.println("Your guild remains the same.");
            }

        }
    }

    private void deleteByEmail(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 4. DELETE PLAYER BY EMAIL");
        String email = reader.readLine();

        boolean wasDeleted = PlayerStorage.deletePlayer(email);
        if (wasDeleted) {
            System.out.println("Your player was successfully deleted");
        } else {
            System.out.println("We can't find player with such email!");
        }
    }

    private void findAllPlayers() {
        System.out.println("\nMenu 5. FIND ALL PLAYERS");
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
