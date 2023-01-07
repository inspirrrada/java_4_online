package ua.com.alevel.service;

import com.diogonunes.jcolor.AnsiFormat;
import ua.com.alevel.dao.GamePlayDao;
import ua.com.alevel.dao.GamePlayDao1;
import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

import java.util.List;

import static com.diogonunes.jcolor.Attribute.*;

public class GamePlayService {
    private GamePlayDao gamePlayDao = new GamePlayDao1();

    private static AnsiFormat redText = new AnsiFormat(BRIGHT_RED_TEXT());
    private static AnsiFormat blueText = new AnsiFormat(BRIGHT_BLUE_TEXT());
    private static AnsiFormat yellowText = new AnsiFormat(YELLOW_TEXT());
    private static AnsiFormat reverse = new AnsiFormat(REVERSE());
    private static AnsiFormat underlinedText = new AnsiFormat(UNDERLINE());



    //FROM STORAGE
    //------------------------------------
    //operations create from CRUD
    public void addPlayer(Player player) {
        if (isAgePermissible(player.getAge()) &&
                isCorrectEmail(player.getEmail()) && !hasTheSameEmail(player.getEmail()) &&
                isCorrectNickname(player.getNickname()) && !hasTheSameNickname(player.getNickname())) {
            gamePlayDao.addPlayer(player);
        }
    }

    public void addGame(Game game) {
        if (!hasTheSameGameName(game.getName()) && isCorrectGameName(game.getName())) {
            gamePlayDao.addGame(game);
        }
    }


    //------------------------------------
    //operations read from CRUD
    public Player getPlayerByIdOrNull(String id) {
        boolean existSuchPlayerId = existPlayerId(id);
        Player player = null;

        if (existSuchPlayerId) {
            player = gamePlayDao.getPlayerByIdOrNull(id);
        }

        return player;

    }

    public List<Player> getAllPlayers() {
        return gamePlayDao.getAllPlayers();
    }

    public Game getGameByIdOrNull(String id) {
        boolean existSuchGameId = existGameId(id);
        Game game = null;

        if (existSuchGameId) {
            game = gamePlayDao.getGameByIdOrNull(id);
        }

        return game;
    }

    public List<Game> getAllGames() {

        return gamePlayDao.getAllGames();
    }


    //------------------------------------
    //operations update from CRUD
//    public void updatePlayer(Player playerNew) {
//        Player player = getPlayerByIdOrNull(playerNew.getId());
//        Player playerCopy = new Player();
//        playerCopy.setAge(player.getAge());
//        playerCopy.setEmail(player.getEmail());
//        playerCopy.setNickname(player.getNickname());
//
//        if (isAgePermissible(playerNew.getAge()) &&
//                isCorrectEmail(playerNew.getEmail()) && !hasTheSameEmail(playerNew.getEmail()) &&
//                isCorrectNickname(playerNew.getNickname()) && !hasTheSameNickname(playerNew.getNickname())) {
//            gamePlayDao.updatePlayer(playerNew);
//        } else {
//            playerNew.setAge(playerCopy.getAge());
//            playerNew.setEmail(playerCopy.getEmail());
//            playerNew.setNickname(playerCopy.getNickname());
//        }
//
//
//
////            if (!hasTheSameEmail(playerNew.getEmail()) && !hasTheSameNickname(playerNew.getNickname())) {
////                gamePlayDao.updatePlayer(playerNew);
////                updateSuccessfull = true;
////            } else if ( hasTheSameEmail(playerNew.getEmail()) ) {
////                System.out.println("This email is already registered! Please enter new one.");
////            } else if ( hasTheSameNickname(playerNew.getNickname()) ) {
////                System.out.println("This nickname is already registered! Please enter new one.");
////            }
//
//
//        //return updateSuccessfull;
//    }

    public void updatePlayerAge(String id, int age) {
        Player player = getPlayerByIdOrNull(id);

        if (player != null) {
            if (isAgePermissible(age)) {
                gamePlayDao.updatePlayerAge(id, age);
            }
        }

    }

    public void updatePlayerEmail(String id, String email) {
        Player player = getPlayerByIdOrNull(id);

        if (player != null) {
            if (isCorrectEmail(email) && !hasTheSameEmail(email)) {
                gamePlayDao.updatePlayerEmail(id, email);
            }
        }

    }

    public void updatePlayerNickname(String id, String nickname) {
        Player player = getPlayerByIdOrNull(id);

        if (player != null) {
            if ( isCorrectNickname(nickname) && !hasTheSameNickname(nickname) ) {
                gamePlayDao.updatePlayerNickname(id, nickname);
            }
        }

    }

//    public boolean updateGame(Game gameNew) {
//        boolean updateSuccessfull = false;
//
//        if (!hasTheSameGameName(gameNew.getName())) {
//            gamePlayDao.updateGame(gameNew);
//        } else {
//            //System.out.println("This name of game is already registered!");
//        }
//
//        return updateSuccessfull;
//
//    }

    public void updateGameName(String id, String name) {
        Game game = getGameByIdOrNull(id);

        if (game != null) {
            if ( isCorrectGameName(name) && !hasTheSameGameName(name) ) {
                gamePlayDao.updateGameName(id, name);
            }
        }

    }

    public void updateGameType(String id, boolean isCommandGame) {
        Game game = getGameByIdOrNull(id);

        if (game != null) {
            gamePlayDao.updateGameType(id, isCommandGame);
        }

    }


    //------------------------------------
    //operations delete from CRUD
    public boolean deletePlayer(String id) {
        return gamePlayDao.deletePlayer(id);
    }

    public boolean deleteGame(String id) {
        return gamePlayDao.deleteGame(id);
    }


    //------------------------------------
    //relation operations create from CRUD
    public boolean addOnlyPlayerToGame(String playerId, String gameId) {
//        boolean wasAddedOnlyPlayerToGame = gamePlayDao.addOnlyPlayerToGame(playerId, gameId);
//
//        if (!wasAddedOnlyPlayerToGame) {
//            System.out.println(redText.format("We have already player with such id in this game!"));
//        }

        return gamePlayDao.addOnlyPlayerToGame(playerId, gameId);

    }

    public void addGameToPlayerInAllDb(String gameId, String playerId) {
//        boolean wasAddedGameToPlayerInAllDb = gamePlayDao.addGameToPlayerInAllDb(gameId, playerId);
//        boolean wasAddedOnlyPlayerToGame = addOnlyPlayerToGame(gameId, playerId);
//
//        if (wasAddedOnlyPlayerToGame && wasAddedGameToPlayerInAllDb) {
//            System.out.println(GamePlayService.getBlueText().format("\nGame was successfully attached to the player."));
//        } else if (!wasAddedOnlyPlayerToGame && wasAddedGameToPlayerInAllDb) {
//            System.out.println(redText.format("This game can't be added to this player in automatic mode. Please contact with support service."));
//        } else {
//            System.out.println(redText.format("We have already game with such id for this player!"));
//        }
//
//        return wasAddedGameToPlayerInAllDb;
        if (existPlayerId(playerId) && existGameId(gameId)) {
            gamePlayDao.addGameToPlayerInAllDb(gameId, playerId);
        }


    }


    //------------------------------------
    //relation operations read from CRUD
    public List<Player> getPlayersByGame(String gameId) {
        return gamePlayDao.getPlayersByGame(gameId);
    }

    public List<Game> getGamesByPlayer(String playerId) {
        return gamePlayDao.getGamesByPlayer(playerId);
    }


    //------------------------------------
    //relation operations delete from CRUD
    public boolean deleteOnlyPlayerFromGame(String playerId, String gameId) {
        boolean wasDeleteOnlyPlayerFromGame = gamePlayDao.deleteOnlyPlayerFromGame(playerId, gameId);

//        if (!wasDeleteOnlyPlayerFromGame) {
//            System.out.println(GamePlayService.getRedText().format("We don't have player with such id in this game. Please check your info."));
//        }

        return wasDeleteOnlyPlayerFromGame;
    }

    public boolean deleteGameFromPlayerInAllDb(String gameId, String playerId) {
        boolean wasDeletedGameFromPlayerInAllDb = gamePlayDao.deleteGameFromPlayerInAllDb(gameId, playerId);
        boolean wasDeleteOnlyPlayerFromGame = deleteOnlyPlayerFromGame(playerId, gameId);

//        if (wasDeleteOnlyPlayerFromGame && wasDeletedGameFromPlayerInAllDb) {
//            System.out.println(GamePlayService.getBlueText().format("\nGame was successfully deleted from player."));
//        } else if (!wasDeleteOnlyPlayerFromGame && wasDeletedGameFromPlayerInAllDb) {
//            System.out.println(GamePlayService.getRedText().format("\nGame can't be deleted from player in automatic mode. Please contact with support service."));
//        } else {
//            System.out.println(GamePlayService.getRedText().format("We don't have game with such id for this player. Please check your info."));
//        }

        return wasDeletedGameFromPlayerInAllDb;

    }


    //------------------------------------
    //check data
    public boolean hasTheSameEmail(String email) {
        //boolean hasTheSameEmail = false;
            return gamePlayDao.hasTheSameEmail(email);
    }

    public boolean hasTheSameNickname(String nickname) {
        return gamePlayDao.hasTheSameNickname(nickname);
    }

    public boolean hasTheSameGameName(String gameName) {
        return gamePlayDao.hasTheSameGameName(gameName);
    }


    //check if exist object with such id
    public boolean existPlayerId(String playerId) {
        boolean existPlayerId = gamePlayDao.existPlayerId(playerId);

        if(!existPlayerId) {
            System.out.println(redText.format("We can't find player with such id!"));
        }

        return existPlayerId;
    }

    public boolean existGameId(String gameId) {
        boolean existGameId = gamePlayDao.existGameId(gameId);

        if (!existGameId) {
            System.out.println(redText.format("We can't find game with such id!"));
        }

        return existGameId;
    }


    //check format
    public boolean isCorrectAgeFormat(String ageValue) {
        boolean correctAgeFormat;

        //age has to be only from digits
        if (ageValue.matches("\\d+")) {
            correctAgeFormat = true;
        } else {
            System.out.println(GamePlayService.getRedText().format("Invalid value! Please enter number NOT string!"));
            correctAgeFormat = false;
        }

        return correctAgeFormat;
    }

    public boolean isAgePermissible(int age) {
        boolean agePermissible;

        if (age < 18) {
            agePermissible = false;
            System.out.println(GamePlayService.getRedText().format("Grow up first and come later."));
            //System.out.println(redText.format("Unacceptable value!"));
        } else if (age > 100) {
            agePermissible = false;
            System.out.println(GamePlayService.getRedText().format("Your age is fantastic! You're too good for this game."));
        } else {
            agePermissible = true;
        }

        return agePermissible;
    }

    public boolean isCorrectNickname(String nickname) {
        boolean correctNickname;

        //nickname can't have only digits
        if (nickname.matches("\\d+")) {
            System.out.println(GamePlayService.getRedText().format("Nickname can't contain only digits!"));
            correctNickname = false;
        } else {
            correctNickname = true;
        }

        return correctNickname;
    }

    public boolean isCorrectEmail(String email) {
        boolean correctEmail;

        if (email.matches("^(.+)@(.+)$")) {
            correctEmail = true;
        } else {
            System.out.println(GamePlayService.getRedText().format("Invalid value!"));
            correctEmail = false;
        }

        return correctEmail;
    }

    public boolean isCorrectGameName(String gameName) {
        boolean correctGameName;

        //name of game can't have only digits
        if (gameName.matches("\\d+")) {
            System.out.println(getRedText().format("Name of game can't contain only digits!"));
            correctGameName = false;
        } else {
            correctGameName = true;
        }

        return correctGameName;
    }


    //formatting
    public static AnsiFormat getRedText() {
        return redText;
    }

    public static AnsiFormat getBlueText() {
        return blueText;
    }

    public static AnsiFormat getYellowText() {
        return yellowText;
    }

    public static AnsiFormat getReverse() {
        return reverse;
    }

    public static AnsiFormat getUnderlinedText() {
        return underlinedText;
    }


//    private void createPlayer(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 1. CREATE PLAYER"));
//        System.out.println();
//        System.out.print(DbStorage.getUnderlinedText().format("Please enter your age."));
//        System.out.println(" ***You must be of legal age to play this game.");
//
//        String ageS;
//        int age = 0;
//        boolean correctAge = false;
//
//        while (!correctAge) {
//            ageS = reader.readLine();
//            if (ageS.matches("\\d+")) {
//                correctAge = true;
//                age = Integer.parseInt(ageS);
//            } else {
//                System.out.println(DbStorage.getRedText().format("Invalid value! Please enter again, but number NOT string!"));
//                correctAge = false;
//            }
//        }
//
//        if (age < 18) {
//            System.out.println(DbStorage.getRedText().format("Grow up first and come later."));
//            stop();
//        } else if (age > 100) {
//            System.out.println(DbStorage.getRedText().format("Your age is fantastic! You're too good for this game"));
//            stop();
//        } else {
//            Player player = new Player();
//            player.setAge(age);
//
//            System.out.println(DbStorage.getUnderlinedText().format("\nPlease enter your email."));
//            String email = reader.readLine();
//            boolean correctEmail = player.setEmail(email);
//            while (!correctEmail) {
//                System.out.println("Please check and try to enter your email again");
//                email = reader.readLine();
//                correctEmail = player.setEmail(email);
//            }
//
//
//            System.out.print(DbStorage.getUnderlinedText().format("\nPlease enter your nickname."));
//            System.out.println(" ***Pay attention, nickname can't have only digits.");
//            String nickname = reader.readLine();
//            boolean correctNickname = player.setNickname(nickname);
//            while (!correctNickname) {
//                System.out.println("Please check and try to enter your nickname again");
//                nickname = reader.readLine();
//                correctNickname = player.setNickname(nickname);
//            }
//
//            DbStorage.addPlayer(player);
//            System.out.println(DbStorage.getBlueText().format("\nCongratulations! Your player was created."));
//
//        }
//
//
//    }
//
//    private void findPlayerById(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 2. FIND PLAYER BY ID"));
//        System.out.println();
//        System.out.println(DbStorage.getUnderlinedText().format("Please enter player ID:"));
//        String playerId = reader.readLine();
//
//        boolean existSuchPlayerId = DbStorage.existPlayerId(playerId);
//
//        if (existSuchPlayerId) {
//            Player player = DbStorage.getPlayer(playerId);
//            System.out.println(DbStorage.getYellowText().format(player.toString()));
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//
//        System.out.println();
//
//    }
//
//    private void updatePlayer(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 3. UPDATE PLAYER BY ID"));
//        System.out.println();
//        System.out.println(DbStorage.getUnderlinedText().format("Please enter player ID"));
//        String playerId = reader.readLine();
//
//        boolean existSuchPlayerId = DbStorage.existPlayerId(playerId);
//
//        if (existSuchPlayerId) {
//            Player player = DbStorage.getPlayer(playerId);
//            System.out.println("Great, please update details of player '" + player.getNickname() + "' below.");
//            System.out.println("If you don't want to update some detail, press 0 and you go to the next.");
//
//            //update age
//            System.out.println(DbStorage.getUnderlinedText().format("\nPlease enter new age:"));
//            String ageS = reader.readLine();
//
//            if (!ageS.equals("0")) {
//                int age = Integer.parseInt(ageS);
//                DbStorage.updatePlayerAge(playerId, age);
//                System.out.println(DbStorage.getBlueText().format("Age for player '" + player.getNickname() + "' was updated successfully."));
//            } else {
//                System.out.println("Age of player remains the same.");
//            }
//
//            //update nickname
//            System.out.println(DbStorage.getUnderlinedText().format("\nPlease enter new nickname:"));
//            String nickname = reader.readLine();
//
//            if (!nickname.equals("0")) {
//                boolean correctNickname = player.setNickname(nickname);
//
//                if (!correctNickname) {
//                    System.out.println("Your changes of nickname weren't saved.");
//                } else {
//                    DbStorage.updatePlayerNickname(playerId, nickname);
//                    System.out.println(DbStorage.getBlueText().format("Nickname for player was updated successfully to the '" + nickname + "'."));
//                }
//            } else {
//                System.out.println("Nickname of player remains the same.");
//            }
//
//
//            //update email
//            System.out.println(DbStorage.getUnderlinedText().format("\nPlease enter new email:"));
//            String email = reader.readLine();
//
//            if (!email.equals("0")) {
//                boolean correctEmail = player.setEmail(email);
//
//                if (!correctEmail) {
//                    System.out.println("Your changes of email weren't saved.");
//                } else {
//                    DbStorage.updatePlayerEmail(playerId, email);
//                    System.out.println(DbStorage.getBlueText().format("Email for player '" + player.getNickname() + "' was updated successfully."));
//                }
//            } else {
//                System.out.println("Email of player remains the same.");
//            }
//
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void deletePlayer(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 4. DELETE PLAYER BY ID"));
//        System.out.println();
//        System.out.println(DbStorage.getUnderlinedText().format("Please enter player ID:"));
//        String playerId = reader.readLine();
//
//        boolean existSuchPlayerId = DbStorage.existPlayerId(playerId);
//
//        if (existSuchPlayerId) {
//
//            boolean wasDeletedEverywhere = DbStorage.deletePlayer(playerId);
//            if (wasDeletedEverywhere) {
//                System.out.println(DbStorage.getBlueText().format("\nYour player was successfully deleted."));
//            } else {
//                System.out.println(DbStorage.getRedText().format("\nYour player can't be deleted in automatic mode. Please contact with support service."));
//            }
//
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void findAllPlayers() {
//        System.out.println(DbStorage.getReverse().format("\nMenu 5. FIND ALL PLAYERS"));
//        System.out.println();
//        List<Player> allPlayers = DbStorage.getAllPlayers();
//        boolean isListEmpty = true;
//
//        for (Player player: allPlayers) {
//            if (player != null) {
//                isListEmpty = false;
//                break;
//            }
//        }
//
//        if (isListEmpty) {
//            System.out.println("There are no players.");
//        } else {
//            System.out.println("Players:");
//
//            int count = 1;
//            for (Player player : allPlayers) {
//                System.out.println(DbStorage.getYellowText().format(count + ". " + player.toString()));
//                count++;
//            }
//
//        }
//
//    }
//
//
//
//    private void createGame(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 6. ENTER GAME"));
//        System.out.println();
//        Game game = new Game();
//
//        System.out.print(DbStorage.getUnderlinedText().format("Please enter name of game."));
//        System.out.println(" ***Pay attention, name of game can't have only digits.");
//        String gameName = reader.readLine();
//        boolean correctGameName = game.setName(gameName);
//        while (!correctGameName) {
//            System.out.println("Please check and try to enter name of game again");
//            gameName = reader.readLine();
//            correctGameName = game.setName(gameName);
//        }
//
//        System.out.print(DbStorage.getUnderlinedText().format("\nPlease choose type of game: command or single."));
//        System.out.println(" ***For this enter below 'y' for command game or 'n' for single.");
//        String gameType = reader.readLine();
//        boolean isCommandGame = false;
//
//        while (true) {
//            if (gameType.equalsIgnoreCase("Y")) {
//                isCommandGame = true;
//                break;
//            } else if (gameType.equalsIgnoreCase("N")) {
//                isCommandGame = false;
//                break;
//            } else {
//                System.out.println(DbStorage.getRedText().format("You entered wrong value! Please write your choice again: 'y' for command game or 'n' for single."));
//            }
//        }
//
//        game.setCommandGame(isCommandGame);
//
//        DbStorage.addGame(game);
//        System.out.println(DbStorage.getBlueText().format("\nCongratulations! Your game was recorded."));
//
//    }
//
//    private void findGameById(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 7. FIND GAME BY ID"));
//        System.out.println();
//        System.out.println(DbStorage.getUnderlinedText().format("Please enter game ID:"));
//        String gameId = reader.readLine();
//
//        boolean existSuchGameId = DbStorage.existGameId(gameId);
//
//        if (existSuchGameId) {
//            Game game = DbStorage.getGame(gameId);
//            System.out.println(DbStorage.getYellowText().format(game.toString()));
//            System.out.println();
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//
//    }
//
//    private void updateGame(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 8. UPDATE GAME BY ID"));
//        System.out.println();
//        System.out.println(DbStorage.getUnderlinedText().format("Please enter game ID"));
//        String gameId = reader.readLine();
//
//        boolean existSuchGameId = DbStorage.existGameId(gameId);
//
//        if (existSuchGameId) {
//
//            Game game = DbStorage.getGame(gameId);
//
//            System.out.println("Great, please update details of game '" + game.getName() + "' below.");
//            System.out.println("If you don't want to update some detail, press 0 and you go to the next.");
//
//            //update name
//            System.out.println(DbStorage.getUnderlinedText().format("\nPlease enter new name:"));
//            String gameName = reader.readLine();
//
//            if (!gameName.equals("0")) {
//                DbStorage.updateGameName(gameId, gameName);
//                System.out.println(DbStorage.getBlueText().format("Name for game was updated to '" + game.getName() + "' successfully."));
//            } else {
//                System.out.println("Name of game remains the same.");
//            }
//
//            //update gameType
//            System.out.print(DbStorage.getUnderlinedText().format("\nPlease enter new command type:"));
//            System.out.println(" enter below 'y' for command game or 'n' for single.");
//            String gameType = reader.readLine();
//
//            boolean isCommandGame = game.isCommandGame();
//            String gameTypeValue = "";
//
//            if (!gameType.equals("0")) {
//                if (gameType.equalsIgnoreCase("Y") && !isCommandGame) {
//                    isCommandGame = true;
//                    DbStorage.updateGameType(gameId, isCommandGame);
//
//                    if (game.isCommandGame()) {
//                        gameTypeValue = "command game";
//                    } else {
//                        gameTypeValue = "single game";
//                    }
//                    System.out.println(DbStorage.getBlueText().format("Type of game was updated successfully to the '" + gameTypeValue + "'."));
//                } else if (gameType.equalsIgnoreCase("N") && isCommandGame) {
//                    isCommandGame = false;
//                    DbStorage.updateGameType(gameId, isCommandGame);
//
//                    if (game.isCommandGame()) {
//                        gameTypeValue = "command game";
//                    } else {
//                        gameTypeValue = "single game";
//                    }
//                    System.out.println(DbStorage.getBlueText().format("Type of game was updated successfully to the '" + gameTypeValue + "'."));
//                } else {
//                    System.out.println(DbStorage.getRedText().format("You entered wrong value! Your changes weren't saved."));
//                }
//            } else {
//                System.out.println("Type of game remains the same: " + gameTypeValue + ".");
//            }
//
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//
//    }
//
//    private void deleteGame(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 9. DELETE GAME BY ID"));
//        System.out.println();
//        System.out.println(DbStorage.getUnderlinedText().format("Please enter game ID:"));
//        String gameId = reader.readLine();
//
//        boolean existSuchGameId = DbStorage.existGameId(gameId);
//
//        if (existSuchGameId) {
//
//            boolean wasDeletedEverywhere = DbStorage.deleteGame(gameId);
//            if (wasDeletedEverywhere) {
//                System.out.println(DbStorage.getBlueText().format("\nYour game was successfully deleted."));
//            } else {
//                System.out.println(DbStorage.getRedText().format("\nYour game can't be deleted in automatic mode. Please contact with support service."));
//            }
//
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//
//    }
//
//    private void findAllGames() {
//        System.out.println(DbStorage.getReverse().format("\nMenu 10. FIND ALL GAMES"));
//        System.out.println();
//        List<Game> allGames = DbStorage.getAllGames();
//        boolean isListEmpty = true;
//
//        for (Game game: allGames) {
//            if (game != null) {
//                isListEmpty = false;
//                break;
//            }
//        }
//
//        if (isListEmpty) {
//            System.out.println("There are no games.");
//        } else {
//            System.out.println("Games:");
//
//            int count = 1;
//            for (Game game : allGames) {
//                System.out.println(DbStorage.getYellowText().format(count + ". " + game.toString()));
//                count++;
//            }
//
//        }
//
//    }
//
//
//
//    private void attachGameToPlayer(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 11. ATTACH GAME TO PLAYER"));
//        System.out.println();
//
//        System.out.println(DbStorage.getUnderlinedText().format("Please enter game ID"));
//        String gameId = reader.readLine();
//
//        boolean existSuchGameId = DbStorage.existGameId(gameId);
//
//        if (existSuchGameId) {
//
//            System.out.println(DbStorage.getUnderlinedText().format("\nPlease enter player ID"));
//            String playerId = reader.readLine();
//
//            boolean existSuchPlayerId = DbStorage.existPlayerId(playerId);
//
//            if (existSuchPlayerId) {
//                DbStorage.addGameToPlayer(gameId, playerId);
//            }
//
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//
//    }
//
//    private void findAllPlayersByGame(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 12. FIND ALL PLAYERS BY GAME ID"));
//        System.out.println();
//
//        System.out.println(DbStorage.getUnderlinedText().format("Please enter game ID"));
//        String gameId = reader.readLine();
//
//        boolean existSuchGameId = DbStorage.existGameId(gameId);
//
//        if (existSuchGameId) {
//            List<Player> playersList = DbStorage.findPlayersByGame(gameId);
//            boolean isListEmpty = true;
//
//            for (Player player : playersList) {
//                if (player != null) {
//                    isListEmpty = false;
//                    break;
//                }
//            }
//
//            if (isListEmpty) {
//                System.out.println("There are no players in this game");
//            } else {
//                System.out.println("Players:");
//                int count = 1;
//                for (Player player : playersList) {
//                    System.out.println(DbStorage.getYellowText().format(count + ". " + player.toString()));
//                    count++;
//                }
//            }
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//
//    }
//
//    private void findAllGamesByPlayer(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 13. FIND ALL GAMES BY PLAYER ID"));
//        System.out.println();
//
//        System.out.println(DbStorage.getUnderlinedText().format("Please enter player ID"));
//        String playerId = reader.readLine();
//
//        boolean existSuchPlayerId = DbStorage.existPlayerId(playerId);
//
//        if (existSuchPlayerId) {
//            List<Game> gamesList = DbStorage.findGamesByPlayer(playerId);
//            boolean isListEmpty = true;
//
//            for (Game game : gamesList) {
//                if (game != null) {
//                    isListEmpty = false;
//                    break;
//                }
//            }
//
//            if (isListEmpty) {
//                System.out.println("There are no players in this game");
//            } else {
//                int count = 1;
//                for (Game game : gamesList) {
//                    System.out.println("Games:");
//                    System.out.println(DbStorage.getYellowText().format(count + ". " + game.toString()));
//                    count++;
//                }
//            }
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//
//    }
//
//    private void deleteGameFromPlayer(BufferedReader reader) throws IOException {
//        System.out.println(DbStorage.getReverse().format("\nMenu 14. DELETE GAME FROM PLAYER BY ID"));
//        System.out.println();
//
//        System.out.println(DbStorage.getUnderlinedText().format("Please enter game ID"));
//        String gameId = reader.readLine();
//
//        boolean existSuchGameId = DbStorage.existGameId(gameId);
//
//        if (existSuchGameId) {
//
//            System.out.println(DbStorage.getUnderlinedText().format("\nPlease enter player ID"));
//            String playerId = reader.readLine();
//
//            boolean existSuchPlayerId = DbStorage.existPlayerId(playerId);
//
//            if (existSuchPlayerId) {
//                DbStorage.deleteGameFromPlayer(gameId, playerId);
//            } else {
//                System.out.println("Please check and try this menu again.");
//            }
//
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//
//    }
}
