package ua.com.alevel.controller;

import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;
import ua.com.alevel.service.GamePlayService;
import ua.com.alevel.service.GamePlayServiceImpl;
import ua.com.alevel.utils.ColorUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GamePlayController {

    private String playersFile = "players.json";
    private String gamesFile = "games.json";

    private GamePlayService service = new GamePlayServiceImpl(playersFile, gamesFile);

    public void start() {
        System.out.println();
        System.out.println("WELCOME TO THE GAMEPLAY MENU!");
        System.out.println();
        System.out.println("Please select your options:");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String selectedOption;

        menu();

        try {
            while ((selectedOption = reader.readLine()) != null) {
                crud(reader, selectedOption);
            }
        } catch (IOException e) {
            System.out.println("Oops...Something went wrong:( Please try again later.");
            e.printStackTrace();
        }

    }

    private void menu() {
        System.out.println();
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

        System.out.println("If you want to attach game to player, please enter 11");
        System.out.println("If you want to find all players by game, please enter 12");
        System.out.println("If you want to find all games by player, please enter 13");
        System.out.println("If you want to delete game from player, please enter 14");
        System.out.println("--------------------------------------------------------");

        System.out.println("If you want to close application, please enter 15");
        System.out.println("========================================================");
        System.out.println();
    }

    private void crud(BufferedReader reader, String selectedOption) throws IOException {
        switch (selectedOption) {
            case "1" -> createPlayer(reader);
            case "2" -> findPlayerById(reader);
            case "3" -> updatePlayer(reader);
            case "4" -> deletePlayer(reader);
            case "5" -> findAllPlayers();
            case "6" -> createGame(reader);
            case "7" -> findGameById(reader);
            case "8" -> updateGame(reader);
            case "9" -> deleteGame(reader);
            case "10" -> findAllGames();
            case "11" -> attachGameToPlayer(reader);
            case "12" -> findAllPlayersByGame(reader);
            case "13" -> findAllGamesByPlayer(reader);
            case "14" -> deleteGameFromPlayer(reader);
            case "15" -> stop();
            default -> System.out.println(ColorUtils.getRedText().format("Wrong value! Select menu again."));
        }
        menu();
    }

    private void createPlayer(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 1. CREATE PLAYER"));
        System.out.println();
        System.out.print(ColorUtils.getUnderlinedText().format("Please enter your age."));
        System.out.println(" ***You must be of legal age to play this game.");
        String ageValue = "";
        boolean isCorrectAgeFormat = false;
        while (!isCorrectAgeFormat) {
            ageValue = reader.readLine();
            isCorrectAgeFormat = service.isCorrectAgeFormat(ageValue);
        }
        int age = Integer.parseInt(ageValue);
        boolean isAgePermissible = service.isAgePermissible(age);
        if (!isAgePermissible) {
            stop();
        } else {
            System.out.println(ColorUtils.getUnderlinedText().format("\nPlease enter your email."));
            String email = "";
            boolean correctEmail = false;
            boolean hasTheSameEmail = false;
            while (true) {
                email = reader.readLine();
                correctEmail = service.isCorrectEmail(email);
                if (correctEmail) {
                    hasTheSameEmail = service.hasTheSameEmail(email);
                    if (!hasTheSameEmail) {
                        break;
                    } else {
                        System.out.println(ColorUtils.getRedText().format("This email is already registered!"));
                        System.out.println("Please check and try to enter your email again");
                    }
                } else {
                    System.out.println("Please check and try to enter your email again");
                }
            }
            System.out.print(ColorUtils.getUnderlinedText().format("\nPlease enter your nickname."));
            System.out.println(" ***Pay attention, nickname can't have only digits.");
            String nickname = "";
            boolean correctNickname = false;
            boolean hasTheSameNickname = false;
            while (true) {
                nickname = reader.readLine();
                correctNickname = service.isCorrectNickname(nickname);
                if (correctNickname) {
                    hasTheSameNickname = service.hasTheSameNickname(nickname);
                    if (!hasTheSameNickname) {
                        break;
                    } else {
                        System.out.println(ColorUtils.getRedText().format("This nickname is already registered!"));
                        System.out.println("Please check and try to enter your nickname again");
                    }
                } else {
                    System.out.println("Please check and try to enter your nickname again");
                }
            }
            Player player = new Player();
            player.setAge(age);
            player.setEmail(email);
            player.setNickname(nickname);
            service.addPlayer(player);
            System.out.println(ColorUtils.getBlueText().format("\nCongratulations! Your player was created."));
        }
    }

    private void findPlayerById(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 2. FIND PLAYER BY ID"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter player ID:"));
        String playerId = reader.readLine();
        Player player = service.getPlayerByIdOrNull(playerId);
        if (player != null) {
            System.out.println(ColorUtils.getYellowText().format(player.toString()));
        } else {
            System.out.println("Please check and try this menu again.");
        }
    }

    private void updatePlayer(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 3. UPDATE PLAYER BY ID"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter player ID"));
        String playerId = reader.readLine();
        Player playerNew = service.getPlayerByIdOrNull(playerId);

        if (playerNew != null) {
            int ageOld = playerNew.getAge();
            System.out.println("Great, please update details of player '" + playerNew.getNickname() + "' below.");
            System.out.println("If you don't want to update some detail, press 0 and you go to the next. Updates for old values will not be saved.");

            //update age
            System.out.println(ColorUtils.getUnderlinedText().format("\nPlease enter new age:"));
            String ageValue = reader.readLine();

            //"0" means without changes
            if (!ageValue.equals("0")) {
                boolean isCorrectAgeFormat = service.isCorrectAgeFormat(ageValue);
                if (isCorrectAgeFormat) {
                    int age = Integer.parseInt(ageValue);
                    boolean isAgePermissible = service.isAgePermissible(age);
                    if (isAgePermissible) {
                        if (age != ageOld) {
                            playerNew.setAge(age);
                            service.updatePlayerAge(playerId, age);
                            System.out.println(ColorUtils.getBlueText().format("Age for player '" + playerNew.getNickname() + "' was updated successfully."));
                        } else {
                            System.out.println(ColorUtils.getRedText().format("You entered old value, your changes weren't saved."));
                        }
                    } else {
                        System.out.println(ColorUtils.getRedText().format("New age is unacceptable, your changes weren't saved."));
                    }
                } else {
                    System.out.println("Your changes weren't saved.");
                }
            } else {
                System.out.println("Age of player remains the same.");
            }

            //update nickname
            System.out.println(ColorUtils.getUnderlinedText().format("\nPlease enter new nickname:"));
            String nickname = reader.readLine();

            //"0" means without changes
            if (!nickname.equals("0")) {
                boolean correctNickname = service.isCorrectNickname(nickname);
                if (!correctNickname) {
                    System.out.println("Your changes of nickname weren't saved.");
                } else {
                    boolean hasTheSameNickname = service.hasTheSameNickname(nickname);
                    if (!hasTheSameNickname) {
                        playerNew.setNickname(nickname);
                        service.updatePlayerNickname(playerId, nickname);
                        System.out.println(ColorUtils.getBlueText().format("Nickname for player was updated successfully to the '" + nickname + "'."));
                    } else {
                        System.out.println(ColorUtils.getRedText().format("This nickname is already registered. Your changes weren't saved."));
                    }
                }
            } else {
                System.out.println("Nickname of player remains the same.");
            }

            //update email
            System.out.println(ColorUtils.getUnderlinedText().format("\nPlease enter new email:"));
            String email = reader.readLine();

            //"0" means without changes
            if (!email.equals("0")) {
                boolean correctEmail = service.isCorrectEmail(email);
                if (!correctEmail) {
                    System.out.println("Your changes of email weren't saved.");
                } else {
                    boolean hasTheSameEmail = service.hasTheSameEmail(email);
                    if (!hasTheSameEmail) {
                        playerNew.setEmail(email);
                        service.updatePlayerEmail(playerId, email);
                        System.out.println(ColorUtils.getBlueText().format("Email for player '" + playerNew.getNickname() + "' was updated successfully."));
                    } else {
                        System.out.println(ColorUtils.getRedText().format("This email is already registered. Your changes weren't saved."));
                    }
                }
            } else {
                System.out.println("Email of player remains the same.");
            }
        } else {
            System.out.println("Please check and try this menu again.");
        }
    }

    private void deletePlayer(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 4. DELETE PLAYER BY ID"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter player ID:"));
        String playerId = reader.readLine();
        boolean existSuchPlayerId = service.existPlayerId(playerId);
        if (existSuchPlayerId) {
            boolean wasDeletedEverywhere = service.deletePlayer(playerId);
            if (wasDeletedEverywhere) {
                System.out.println(ColorUtils.getBlueText().format("\nYour player was successfully deleted."));
            } else {
                System.out.println(ColorUtils.getRedText().format("\nYour player can't be deleted in automatic mode. Please contact with support service."));
            }
        } else {
            System.out.println("Please check and try this menu again.");
        }
    }

    private void findAllPlayers() {
        System.out.println(ColorUtils.getReverse().format("\nMenu 5. FIND ALL PLAYERS"));
        System.out.println();
        List<Player> allPlayers = service.getAllPlayers();
        if (allPlayers.isEmpty()) {
            System.out.println("There are no players.");
        } else {
            System.out.println("Players:");
            int count = 1;
            for (Player player : allPlayers) {
                System.out.println(ColorUtils.getYellowText().format(count + ". " + player.toString()));
                count++;
            }
        }
    }

    private void createGame(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 6. ENTER GAME"));
        System.out.println();
        System.out.print(ColorUtils.getUnderlinedText().format("Please enter name of game."));
        System.out.println(" ***Pay attention, name of game can't have only digits.");
        String gameName = "";
        boolean correctGameName = false;
        boolean hasTheSameGameName = false;
        while (true) {
            gameName = reader.readLine();
            correctGameName = service.isCorrectGameName(gameName);
            if (correctGameName) {
                hasTheSameGameName = service.hasTheSameGameName(gameName);
                if (!hasTheSameGameName) {
                    break;
                } else {
                    System.out.println(ColorUtils.getRedText().format("This name of game is already registered!"));
                    System.out.println("Please check and try to enter name of game again");
                }
            } else {
                System.out.println("Please check and try to enter name of game again");
            }
        }
        System.out.print(ColorUtils.getUnderlinedText().format("\nPlease choose type of game: command or single."));
        System.out.println(" ***For this enter below 'y' for command game or 'n' for single.");
        String gameType = "";
        boolean isCommandGame = false;
        while (true) {
            gameType = reader.readLine();
            if (gameType.equalsIgnoreCase("Y")) {
                isCommandGame = true;
                break;
            } else if (gameType.equalsIgnoreCase("N")) {
                isCommandGame = false;
                break;
            } else {
                System.out.println(ColorUtils.getRedText().format("You entered wrong value! Please write your choice again: 'y' for command game or 'n' for single."));
            }
        }
        Game game = new Game();
        game.setName(gameName);
        game.setCommandGame(isCommandGame);
        service.addGame(game);
        System.out.println(ColorUtils.getBlueText().format("\nCongratulations! Your game was recorded."));

    }

    private void findGameById(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 7. FIND GAME BY ID"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter game ID:"));
        String gameId = reader.readLine();
        Game game = service.getGameByIdOrNull(gameId);
        if (game != null) {
            System.out.println(ColorUtils.getYellowText().format(game.toString()));
        } else {
            System.out.println("Please check and try this menu again.");
        }
    }

    private void updateGame(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 8. UPDATE GAME BY ID"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter game ID"));
        String gameId = reader.readLine();
        Game game = service.getGameByIdOrNull(gameId);
        if (game != null) {
            System.out.println("Great, please update details of game '" + game.getName() + "' below.");
            System.out.println("If you don't want to update some detail, press 0 and you go to the next. Updates for old values will not be saved.");
            //update name
            System.out.println(ColorUtils.getUnderlinedText().format("\nPlease enter new name:"));
            String gameName = reader.readLine();
            //"0" means without changes
            if (!gameName.equals("0")) {
                boolean isCorrectGameName = service.isCorrectGameName(gameName);
                if (isCorrectGameName) {
                    boolean hasTheSameGameName = service.hasTheSameGameName(gameName);
                    if (!hasTheSameGameName) {
                        game.setName(gameName);
                        service.updateGameName(gameId, gameName);
                        System.out.println(ColorUtils.getBlueText().format("Name for game was updated to '" + game.getName() + "' successfully."));
                    } else {
                        System.out.println(ColorUtils.getRedText().format("This game name is already registered. Your changes weren't saved."));
                    }
                }
            } else {
                System.out.println("Name of game remains the same.");
            }

            //update gameType
            System.out.print(ColorUtils.getUnderlinedText().format("\nPlease enter new command type:"));
            System.out.println(" enter below 'y' for command game or 'n' for single.");
            String gameType = reader.readLine();
            boolean isCommandGame = game.isCommandGame();
            String gameTypeValue = "";
            //"0" means without changes
            if (!gameType.equals("0")) {
                if (gameType.equalsIgnoreCase("Y") && !isCommandGame) {
                    isCommandGame = true;
                    game.setCommandGame(isCommandGame);
                    if (game.isCommandGame()) {
                        gameTypeValue = "command game";
                    } else {
                        gameTypeValue = "single game";
                    }
                    service.updateGameType(gameId, isCommandGame);
                    System.out.println(ColorUtils.getBlueText().format("Type of game was updated successfully to the '" + gameTypeValue + "'."));
                } else if (gameType.equalsIgnoreCase("N") && isCommandGame) {
                    isCommandGame = false;
                    game.setCommandGame(isCommandGame);
                    if (game.isCommandGame()) {
                        gameTypeValue = "command game";
                    } else {
                        gameTypeValue = "single game";
                    }
                    service.updateGameType(gameId, isCommandGame);
                    System.out.println(ColorUtils.getBlueText().format("Type of game was updated successfully to the '" + gameTypeValue + "'."));
                } else {
                    System.out.println(ColorUtils.getRedText().format("You entered wrong or old value! Your changes weren't saved."));
                }
            } else {
                System.out.println("Type of game remains the same: " + gameTypeValue + ".");
            }
        } else {
            System.out.println("Please check and try this menu again.");
        }
    }

    private void deleteGame(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 9. DELETE GAME BY ID"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter game ID:"));
        String gameId = reader.readLine();
        boolean existSuchGameId = service.existGameId(gameId);
        if (existSuchGameId) {
            boolean wasDeletedEverywhere = service.deleteGame(gameId);
            if (wasDeletedEverywhere) {
                System.out.println(ColorUtils.getBlueText().format("\nYour game was successfully deleted."));
            } else {
                System.out.println(ColorUtils.getRedText().format("\nYour game can't be deleted in automatic mode. Please contact with support service."));
            }
        } else {
            System.out.println("Please check and try this menu again.");
        }
    }

    private void findAllGames() {
        System.out.println(ColorUtils.getReverse().format("\nMenu 10. FIND ALL GAMES"));
        System.out.println();
        List<Game> allGames = service.getAllGames();
        if (allGames.isEmpty()) {
            System.out.println("There are no games.");
        } else {
            System.out.println("Games:");
            int count = 1;
            for (Game game : allGames) {
                System.out.println(ColorUtils.getYellowText().format(count + ". " + game.toString()));
                count++;
            }
        }
    }

    private void attachGameToPlayer(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 11. ATTACH GAME TO PLAYER"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter game ID"));
        String gameId = reader.readLine();
        boolean existSuchGameId = service.existGameId(gameId);
        if (existSuchGameId) {
            System.out.println(ColorUtils.getUnderlinedText().format("\nPlease enter player ID"));
            String playerId = reader.readLine();
            boolean existSuchPlayerId = service.existPlayerId(playerId);
            if (existSuchPlayerId) {
                service.addGameToPlayerInAllDb(gameId, playerId);
            }
        } else {
            System.out.println("Please check and try this menu again.");
        }
    }

    private void findAllPlayersByGame(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 12. FIND ALL PLAYERS BY GAME ID"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter game ID"));
        String gameId = reader.readLine();
        boolean existSuchGameId = service.existGameId(gameId);
        if (existSuchGameId) {
            List<Player> playersList = service.getPlayersByGame(gameId);
            if (playersList.isEmpty()) {
                System.out.println("There are no players in this game");
            } else {
                System.out.println("Players:");
                int count = 1;
                for (Player player : playersList) {
                    System.out.println(ColorUtils.getYellowText().format(count + ". " + player.toString()));
                    count++;
                }
            }
        } else {
            System.out.println("Please check and try this menu again.");
        }
    }

    private void findAllGamesByPlayer(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 13. FIND ALL GAMES BY PLAYER ID"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter player ID"));
        String playerId = reader.readLine();
        boolean existSuchPlayerId = service.existPlayerId(playerId);
        if (existSuchPlayerId) {
            List<Game> gamesList = service.getGamesByPlayer(playerId);
            if (gamesList.isEmpty()) {
                System.out.println("There are no games for this player");
            } else {
                int count = 1;
                System.out.println("Games:");
                for (Game game : gamesList) {
                    System.out.println(ColorUtils.getYellowText().format(count + ". " + game.toString()));
                    count++;
                }
            }
        } else {
            System.out.println("Please check and try this menu again.");
        }
    }

    private void deleteGameFromPlayer(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 14. DELETE GAME FROM PLAYER BY ID"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter game ID"));
        String gameId = reader.readLine();
        boolean existSuchGameId = service.existGameId(gameId);
        if (existSuchGameId) {
            System.out.println(ColorUtils.getUnderlinedText().format("\nPlease enter player ID"));
            String playerId = reader.readLine();
            boolean existSuchPlayerId = service.existPlayerId(playerId);
            if (existSuchPlayerId) {
                service.deleteGameFromPlayerInAllDb(gameId, playerId);
            } else {
                System.out.println("Please check and try this menu again.");
            }
        } else {
            System.out.println("Please check and try this menu again.");
        }
    }

    private void stop() {
        System.out.println("\nThe application is finished.\n");
        System.exit(0);
    }
}
