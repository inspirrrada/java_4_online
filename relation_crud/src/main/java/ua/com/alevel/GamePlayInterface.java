package ua.com.alevel;

import ua.com.alevel.db.DbStorage;
import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GamePlayInterface {

    public void start() throws IOException {
        System.out.println("WELCOME TO THE GAMEPLAY MENU!");
        System.out.println();
        System.out.println("Please select your options:");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String selectedOption;

        menu();

        while ((selectedOption = reader.readLine()) != null) {
            crud(reader, selectedOption);
        }

    }

    private void menu() {
        System.out.println("========================================================");
        System.out.println("If you want to create new player, please enter 1");
        System.out.println("If you want to find player, please enter 2");
        System.out.println("If you want to update player, please enter 3");
        System.out.println("If you want to delete player, please enter 4");
        System.out.println("If you want to find all players, please enter 5");
        System.out.println("--------------------------------------------------------");

        System.out.println("If you want to enter new game, please enter 6");
        System.out.println("If you want to find game, please enter 7");
        System.out.println("If you want to update game, please enter 8");
        System.out.println("If you want to delete game, please enter 9");
        System.out.println("If you want to find all games, please enter 10");
        System.out.println("--------------------------------------------------------");

        System.out.println("If you want to attach player to game, please enter 11");
        System.out.println("If you want to attach game to player, please enter 12");
        System.out.println("If you want to find all players by game, please enter 13");
        System.out.println("If you want to find all games by player, please enter 14");
        System.out.println("If you want to delete player from game, please enter 15");
        System.out.println("--------------------------------------------------------");

        System.out.println("If you want to close application, please enter 16");
        System.out.println("========================================================");
        System.out.println();
    }

    private void crud(BufferedReader reader, String selectedOption) throws IOException {
        switch (selectedOption) {
            case "1":
                createPlayer(reader);
                break;

            case "2":
                findPlayerById(reader);
                break;

            case "3":
                updatePlayer(reader);
                break;

            case "4":
                deletePlayer(reader);
                break;

            case "5":
                findAllPlayers();
                break;

            case "6":
                createGame(reader);
                break;

            case "7":
                findGameById(reader);
                break;

            case "8":
                updateGame(reader);
                break;

            case "9":
                deleteGame(reader);
                break;

            case "10":
                findAllGames();
                break;

            case "11":
                attachPlayerToGame(reader);
                break;

            case "12":
                attachGameToPlayer(reader);
                break;

            case "13":
                findAllPlayersByGame(reader);
                break;

            case "14":
                findAllGamesByPlayer(reader);
                break;

            case "15":
                deletePlayerFromGame(reader);
                break;

            case "16":
                deleteGameFromPlayer(reader);
                break;

            case "17":
                stop();
                break;

            default:
                System.out.println("Wrong value! Select menu again.");
        }

        menu();

    }


    private void createPlayer(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 1. CREATE PLAYER");

        System.out.println("Please enter your age. \n***You must be of legal age to play this game.");

        String ageS;
        int age = 0;
        boolean correctAge = false;

        while (!correctAge) {
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

            System.out.println("\nPlease enter your email.");
            String email = reader.readLine();
            boolean correctEmail = player.setEmail(email);
            while (!correctEmail) {
                System.out.println("Please check and try to enter your email again");
                email = reader.readLine();
                correctEmail = player.setEmail(email);
            }


            System.out.println("\nPlease enter your nickname. \n***Pay attention, nickname can't have only digits.");
            String nickname = reader.readLine();
            boolean correctNickname = player.setNickname(nickname);
            while (!correctNickname) {
                System.out.println("Please check and try to enter your nickname again");
                nickname = reader.readLine();
                correctNickname = player.setNickname(nickname);
            }

            DbStorage.addPlayer(player);
            System.out.println("\nCongratulations! Your player was created.");

        }


    }

    private void findPlayerById(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 2. FIND PLAYER BY ID");
        String playerId = reader.readLine();

        Player player = DbStorage.getPlayer(playerId);
        if (player == null) {
            System.out.println("We don't have player with such id!");
        } else {
            System.out.println("Player = " + player);
        }
        System.out.println();

    }

    private void updatePlayer(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 3. UPDATE PLAYER BY ID");

        System.out.println("Please enter player ID");
        String playerId = reader.readLine();
        Player player = DbStorage.getPlayer(playerId);

        if (player == null) {
            System.out.println("We don't have player with such id!");
        } else {
            System.out.println("Great, please update details of player '" + player.getNickname() + "' below.");
            System.out.println("If you don't want to update some detail, press 0 and you go to the next.");

            //update age
            System.out.println("\nPlease enter new age:");
            String ageS = reader.readLine();

            if (!ageS.equals("0")) {
                int age = Integer.parseInt(ageS);
                DbStorage.updatePlayerAge(playerId, age);
                System.out.println("Age for player '" + player.getNickname() + "' was updated successfully.");
            } else {
                System.out.println("Age of player remains the same.");
            }

            //update nickname
            System.out.println("\nPlease enter new nickname:");
            String nickname = reader.readLine();

            if (!nickname.equals("0")) {
                boolean correctNickname = player.setNickname(nickname);

                if (!correctNickname) {
                    System.out.println("Your changes of nickname weren't saved.");
                } else {
                    DbStorage.updatePlayerNickname(playerId, nickname);
                    System.out.println("Nickname for player was updated successfully to the '" + nickname + "'.");
                }
            } else {
                System.out.println("Nickname of player remains the same.");
            }


            //update email
            System.out.println("\nPlease enter new email:");
            String email = reader.readLine();

            if (!email.equals("0")) {
                boolean correctEmail = player.setEmail(email);

                if (!correctEmail) {
                    System.out.println("Your changes of email weren't saved.");
                } else {
                    DbStorage.updatePlayerEmail(playerId, email);
                    System.out.println("Email for player '" + player.getNickname() + "' was updated successfully.");
                }
            } else {
                System.out.println("Email of player remains the same.");
            }

        }
    }

    private void deletePlayer(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 4. DELETE PLAYER BY ID");
        String playerId = reader.readLine();

        boolean wasDeletedEverywhere = DbStorage.deletePlayer(playerId);
        if (wasDeletedEverywhere) {
            System.out.println("Your player was successfully deleted.");
        } else {
            System.out.println("Your player can't be deleted in automatic mode. Please contact with support service.");
        }
    }

    private void findAllPlayers() {
        System.out.println("\nMenu 5. FIND ALL PLAYERS");
        List<Player> allPlayers = DbStorage.getAllPlayers();
        boolean isListEmpty = true;

        for (Player player: allPlayers) {
            if (player != null) {
                isListEmpty = false;
                break;
            }
        }

        if (isListEmpty) {
            System.out.println("There are no players.");
        } else {
            System.out.println("Players:");

            int count = 1;
            for (Player player : allPlayers) {
                System.out.println(count + ". " + player.toString());
                count++;
            }

        }

    }



    private void createGame(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 6. ENTER GAME");
        Game game = null;

        System.out.println("\nPlease enter name of game. \n***Pay attention, name of game can't have only digits.");
        String gameName = reader.readLine();
        boolean correctGameName = game.setName(gameName);
        while (!correctGameName) {
            System.out.println("Please check and try to enter name of game again");
            gameName = reader.readLine();
            correctGameName = game.setName(gameName);
        }

        System.out.println("\nPlease choose type of game: command or single. \n***For this enter below 'y' for command game or 'n' for single.");
        String gameType = reader.readLine();
        boolean isCommandGame = false;

        while (true) {
            if (gameType.equalsIgnoreCase("Y")) {
                isCommandGame = true;
                break;
            } else if (gameType.equalsIgnoreCase("N")) {
                isCommandGame = false;
                break;
            } else {
                System.out.println("You entered wrong value! Please write your choice again: 'y' for command game or 'n' for single.");
            }
        }

        game.setCommandGame(isCommandGame);

        DbStorage.addGame(game);
        System.out.println("\nCongratulations! Your game was entered.");

    }

    private void findGameById(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 7. FIND GAME BY ID");
        String gameId = reader.readLine();

        Game game = DbStorage.getGame(gameId);
        if (game == null) {
            System.out.println("We don't have game with such id!");
        } else {
            System.out.println("Game = " + game);
        }
        System.out.println();

    }

    private void updateGame(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 8. UPDATE GAME BY ID");

        System.out.println("Please enter game ID");
        String gameId = reader.readLine();
        Game game = DbStorage.getGame(gameId);

        if (game == null) {
            System.out.println("We don't have game with such id!");
        } else {
            System.out.println("Great, please update details of game '" + game.getName() + "' below.");
            System.out.println("If you don't want to update some detail, press 0 and you go to the next.");

            //update name
            System.out.println("\nPlease enter new name:");
            String gameName = reader.readLine();

            if (!gameName.equals("0")) {
                DbStorage.updateGameName(gameId, gameName);
                System.out.println("Name for game was updated to '" + game.getName() + "' successfully.");
            } else {
                System.out.println("Name of game remains the same.");
            }

            //update gameType
            System.out.println("\nPlease enter new command type: enter below 'y' for command game or 'n' for single.");
            String gameType = reader.readLine();

            boolean isCommandGame = game.isCommandGame();
            String gameTypeValue = "";

            if (!gameType.equals("0")) {
                if (gameType.equalsIgnoreCase("Y") && !isCommandGame) {
                    isCommandGame = true;
                    DbStorage.updateGameType(gameId, isCommandGame);

                    if (game.isCommandGame()) {
                        gameTypeValue = "command game";
                    } else {
                        gameTypeValue = "single game";
                    }
                    System.out.println("Type of game was updated successfully to the '" + gameTypeValue + "'.");
                } else if (gameType.equalsIgnoreCase("N") && isCommandGame) {
                    isCommandGame = false;
                    DbStorage.updateGameType(gameId, isCommandGame);

                    if (game.isCommandGame()) {
                        gameTypeValue = "command game";
                    } else {
                        gameTypeValue = "single game";
                    }
                    System.out.println("Type of game was updated successfully to the '" + gameTypeValue + "'.");
                } else {
                    System.out.println("You entered wrong value! Your changes weren't saved.");
                }
            } else {
                System.out.println("Type of game remains the same: " + gameTypeValue + ".");
            }

        }
    }

    private void deleteGame(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 9. DELETE GAME BY ID");
        String gameId = reader.readLine();

        boolean wasDeletedEverywhere = DbStorage.deleteGame(gameId);
        if (wasDeletedEverywhere) {
            System.out.println("Your game was successfully deleted.");
        } else {
            System.out.println("Your game can't be deleted in automatic mode. Please contact with support service.");
        }
    }

    private void findAllGames() {
        System.out.println("\nMenu 10. FIND ALL GAMES");
        List<Game> allGames = DbStorage.getAllGames();
        boolean isListEmpty = true;

        for (Game game: allGames) {
            if (game != null) {
                isListEmpty = false;
                break;
            }
        }

        if (isListEmpty) {
            System.out.println("There are no games.");
        } else {
            System.out.println("Games:");

            int count = 1;
            for (Game game : allGames) {
                System.out.println(count + ". " + game.toString());
                count++;
            }

        }

    }



    private void attachPlayerToGame(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 11. ATTACH PLAYER TO GAME\n");

        System.out.println("Please enter player ID");
        String playerId = reader.readLine();

        Player player = DbStorage.getPlayer(playerId);
        if (player != null) {

            System.out.println("Please enter game ID");
            String gameId = reader.readLine();

            Game game = DbStorage.getGame(gameId);
            if (game != null) {

                DbStorage.addPlayerToGame(playerId, gameId);

            } else {
                System.out.println("We can't find game with such id! Please check and try this menu again.");
            }

        } else {
            System.out.println("We can't find player with such id! Please check and try this menu again.");
        }

    }

    private void attachGameToPlayer(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 12. ATTACH GAME TO PLAYER\n");

        System.out.println("Please enter game ID");
        String gameId = reader.readLine();

        Game game = DbStorage.getGame(gameId);
        if (game != null) {

            System.out.println("Please enter player ID");
            String playerId = reader.readLine();

            Player player = DbStorage.getPlayer(playerId);
            if (player != null) {

                DbStorage.addGameToPlayer(gameId, playerId);

            } else {
                System.out.println("We can't find player with such id! Please check and try this menu again.");
            }

        } else {
            System.out.println("We can't find game with such id! Please check and try this menu again.");
        }

    }

    private void findAllPlayersByGame(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 13. FIND ALL PLAYERS BY GAME ID\n");
        System.out.println("Please enter game ID");
        String gameId = reader.readLine();

        Game game = DbStorage.getGame(gameId);
        if (game != null) {
            DbStorage.findPlayersByGame(gameId);
        } else {
            System.out.println("We can't find game with such id! Please check and try this menu again.");
        }
    }

    private void findAllGamesByPlayer(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 14. FIND ALL GAMES BY PLAYER ID\n");
        System.out.println("Please enter player ID");
        String playerId = reader.readLine();

        Player player = DbStorage.getPlayer(playerId);
        if (player != null) {
            DbStorage.findGamesByPlayer(playerId);
        } else {
            System.out.println("We can't find player with such id! Please check and try this menu again.");
        }
    }

    private void deletePlayerFromGame(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 15. DELETE PLAYER FROM GAME BY ID\n");

        System.out.println("Please enter player ID");
        String playerId = reader.readLine();

        Player player = DbStorage.getPlayer(playerId);
        if (player != null) {

            System.out.println("Please enter game ID");
            String gameId = reader.readLine();

            Game game = DbStorage.getGame(gameId);
            if (game != null) {

                DbStorage.deletePlayerFromGame(playerId, gameId);

            } else {
                System.out.println("We can't find game with such id! Please check and try this menu again.");
            }

        } else {
            System.out.println("We can't find player with such id! Please check and try this menu again.");
        }
    }

    private void deleteGameFromPlayer(BufferedReader reader) throws IOException {
        System.out.println("\nMenu 16. DELETE GAME FROM PLAYER BY ID\n");

        System.out.println("Please enter game ID");
        String gameId = reader.readLine();

        Game game = DbStorage.getGame(gameId);
        if (game != null) {

            System.out.println("Please enter player ID");
            String playerId = reader.readLine();

            Player player = DbStorage.getPlayer(playerId);
            if (player != null) {

                DbStorage.deleteGameFromPlayer(gameId, playerId);

            } else {
                System.out.println("We can't find player with such id! Please check and try this menu again.");
            }

        } else {
            System.out.println("We can't find game with such id! Please check and try this menu again.");
        }
    }


    private void stop() {
        System.out.println("\nThe application is finished.");
        System.exit(0);
    }

}
