package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GamePlayServiceTest {
    private static final GamePlayService gamePlayService = new GamePlayService();

    private static int SIZE = 10;
    private static int AGE = 18;
    private static String EMAIL = "test@gmail.com";
    private static String NICKNAME = "testNickname";
    private static String GAMENAME = "testGameName";
    private static boolean  ISCOMMANDTYPE = true;

    private static Player generatePlayer(int i) {
        Player player = new Player();
        player.setAge(AGE + i);
        player.setEmail(i + EMAIL);
        player.setNickname(NICKNAME + i);

        return player;
    }

    private static Game generateGame(int i) {
        Game game = new Game();
        game.setName(GAMENAME + i);
        game.setCommandGame(ISCOMMANDTYPE);

        return game;
    }

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < SIZE; i++) {
            Player player = generatePlayer(i);
            gamePlayService.addPlayer(player);

            Game game = generateGame(i);
            gamePlayService.addGame(game);
        }

        System.out.println("Players qty = " + gamePlayService.getAllPlayers().size());
        System.out.println("Games qty = " + gamePlayService.getAllGames().size());
    }



    @Test
    @Order(1)
    public void checkSizePlayers() {
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE);
    }


    @Test
    @Order(2)
    public void checkSizeGames() {
        Assertions.assertEquals(gamePlayService.getAllGames().size(), SIZE);
    }

    @Test
    @Order(3)
    public void checkAddNewPlayer() {
        Player player = generatePlayer(SIZE + 1);
        gamePlayService.addPlayer(player);
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE + 1);
    }

    @Test
    @Order(4)
    public void checkAddNewGame() {
        Game game = generateGame(SIZE + 1);
        gamePlayService.addGame(game);
        Assertions.assertEquals(gamePlayService.getAllGames().size(), SIZE + 1);
    }

    @Test
    @Order(5)
    public void deletePlayerWithoutAttachedGames() {
        Player player = gamePlayService.getAllPlayers().get(0);
        gamePlayService.deletePlayer(player.getId());
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE);
    }

    @Test
    @Order(6)
    public void deleteGameWithoutAttachedPlayers() {
        Game game = gamePlayService.getAllGames().get(0);
        gamePlayService.deleteGame(game.getId());
        Assertions.assertEquals(gamePlayService.getAllGames().size(), SIZE);
    }

    @Test
    @Order(7)
    public void deletePlayerWithAttachedGame() {
        Player player = gamePlayService.getAllPlayers().get(0);
        Game game = gamePlayService.getAllGames().get(0);
        gamePlayService.addGameToPlayerInAllDb(game.getId(), player.getId());
        int playerIdListSize = game.getPlayerIdList().size(); //playerIdListSize = 1

        gamePlayService.deletePlayer(player.getId()); //playerIdListSize = 0
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE - 1);
        Assertions.assertEquals(game.getPlayerIdList().size(), playerIdListSize - 1);
    }

    @Test
    @Order(8)
    public void deleteGameWithAttachedPlayer() {
        Game game = gamePlayService.getAllGames().get(0);
        Player player = gamePlayService.getAllPlayers().get(0);
        gamePlayService.addGameToPlayerInAllDb(game.getId(), player.getId());
        int gameIdListSize = player.getGameIdList().size(); //gameIdListSize = 1

        gamePlayService.deleteGame(game.getId()); //gameIdListSize = 0
        Assertions.assertEquals(gamePlayService.getAllGames().size(), SIZE - 1);
        Assertions.assertEquals(player.getGameIdList().size(), gameIdListSize - 1);
    }

    @Test
    @Order(9)
    public void shouldBeCreatePlayerWhenEmailIsDuplicateWithTheSameUpperCase() {
        Player player = gamePlayService.getAllPlayers().get(0);
        Player newPlayer = new Player();
        newPlayer.setEmail(player.getEmail());
        gamePlayService.addPlayer(newPlayer);
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(10)
    public void shouldBeCreatePlayerWhenEmailIsDuplicateWithDifferentUppercaseCase() {
        Player player = gamePlayService.getAllPlayers().get(0);
        Player newPlayer = new Player();
        newPlayer.setEmail(player.getEmail().toUpperCase());
        gamePlayService.addPlayer(newPlayer);
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(11)
    public void shouldBeCreatePlayerWhenNicknameIsDuplicateWithTheSameUpperCase() {
        Player player = gamePlayService.getAllPlayers().get(0);
        Player newPlayer = new Player();
        newPlayer.setNickname(player.getNickname());
        gamePlayService.addPlayer(newPlayer);
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(12)
    public void shouldBeCreatePlayerWhenNicknameIsDuplicateWithDifferentUpperCase() {
        Player player = gamePlayService.getAllPlayers().get(0);
        Player newPlayer = new Player();
        newPlayer.setNickname(player.getNickname().toUpperCase());
        gamePlayService.addPlayer(newPlayer);
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(13)
    public void shouldBeCreatePlayerWhenAgeIsLessThanPermissible() {
        Player player = new Player();
        player.setAge(15);
        gamePlayService.addPlayer(player);
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(14)
    public void shouldBeCreatePlayerWhenAgeIsMoreThanPermissible() {
        Player player = new Player();
        player.setAge(105);
        gamePlayService.addPlayer(player);
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(15)
    public void shouldBeCreatePlayerWhenEmailFormatIsNotValid() {
        Player player = new Player();
        player.setEmail("test");
        gamePlayService.addPlayer(player);
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(16)
    public void shouldBeCreatePlayerWhenNicknameFormatIsNotValid() {
        Player player = new Player();
        player.setNickname("12345");
        gamePlayService.addPlayer(player);
        Assertions.assertEquals(gamePlayService.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(17)
    public void shouldBeCreateGameWhenNameIsDuplicateWithTheSameUpperCase() {
        Game game = gamePlayService.getAllGames().get(0);
        Game newGame = new Game();
        newGame.setName(game.getName());
        gamePlayService.addGame(newGame);
        Assertions.assertEquals(gamePlayService.getAllGames().size(), SIZE - 1);
    }

    @Test
    @Order(18)
    public void shouldBeCreateGameWhenNameIsDuplicateWithDifferentUppercaseCase() {
        Game game = gamePlayService.getAllGames().get(0);
        Game newGame = new Game();
        newGame.setName(game.getName().toUpperCase());
        gamePlayService.addGame(newGame);
        Assertions.assertEquals(gamePlayService.getAllGames().size(), SIZE - 1);
    }

    @Test
    @Order(19)
    public void shouldBeCreateGameWhenNameFormatIsNotValid() {
        Game game = new Game();
        game.setName("12365");
        gamePlayService.addGame(game);
        Assertions.assertEquals(gamePlayService.getAllGames().size(), SIZE - 1);
    }


    @Test
    @Order(20)
    public void getPlayerByIdOrNullWhenIdExists(){
        String playerId = gamePlayService.getAllPlayers().get(0).getId();
        Player player = gamePlayService.getPlayerByIdOrNull(playerId);
        Assertions.assertNotNull(player);
    }

    @Test
    @Order(21)
    public void getPlayerByIdOrNullWhenIdDoesNotExist(){
        String playerId = "testId";
        Player player = gamePlayService.getPlayerByIdOrNull(playerId);
        Assertions.assertNull(player);
    }

    @Test
    @Order(22)
    public void getGameByIdOrNullWhenIdExists(){
        String gameId = gamePlayService.getAllGames().get(0).getId();
        Game game = gamePlayService.getGameByIdOrNull(gameId);
        Assertions.assertNotNull(game);
    }

    @Test
    @Order(23)
    public void getGameByIdOrNullWhenIdDoesNotExist(){
        String gameId = "testId";
        Game game = gamePlayService.getGameByIdOrNull(gameId);
        Assertions.assertNull(game);
    }

    @Test
    @Order(24)
    public void checkUpdatePlayerAgeWithNewCorrectValue() {
        Player player = gamePlayService.getAllPlayers().get(0);
        String playerId = player.getId();

        int newPlayerAge = 0;
        if (player.getAge() != 100) {
            newPlayerAge = player.getAge() + 1;
        } else {
            newPlayerAge = 18;
        }

        gamePlayService.updatePlayerAge(playerId, newPlayerAge);
        Assertions.assertEquals(newPlayerAge, gamePlayService.getPlayerByIdOrNull(playerId).getAge());

    }

    @Test
    @Order(25)
    public void checkUpdatePlayerEmailWithNewCorrectValue() {
        Player player = gamePlayService.getAllPlayers().get(0);
        String playerId = player.getId();

        String newEmail = "2023" + player.getEmail();

        gamePlayService.updatePlayerEmail(playerId, newEmail);

        Assertions.assertSame(newEmail, gamePlayService.getPlayerByIdOrNull(playerId).getEmail());

    }

    @Test
    @Order(26)
    public void checkUpdatePlayerNicknameWithNewCorrectValue() {
        Player player = gamePlayService.getAllPlayers().get(0);
        String playerId = player.getId();

        String newNickname = player.getNickname() + "2023";

        gamePlayService.updatePlayerNickname(playerId, newNickname);

        Assertions.assertSame(newNickname, gamePlayService.getPlayerByIdOrNull(playerId).getNickname());

    }

    @Test
    @Order(27)
    public void checkUpdateGameNameWithNewCorrectValue() {
        Game game = gamePlayService.getAllGames().get(0);
        String gameId = game.getId();

        String newGameName = game.getName() + "2023";

        gamePlayService.updateGameName(gameId, newGameName);

        Assertions.assertSame(newGameName, gamePlayService.getGameByIdOrNull(gameId).getName());

    }

    @Test
    @Order(28)
    public void checkUpdateGameTypeWithNewCorrectValue() {
        Game game = gamePlayService.getAllGames().get(0);
        String gameId = game.getId();

        boolean newIsCommandGame;
        if (game.isCommandGame()) {
            newIsCommandGame = false;
        } else {
            newIsCommandGame = true;
        }

        gamePlayService.updateGameType(gameId, newIsCommandGame);

        Assertions.assertSame(newIsCommandGame, gamePlayService.getGameByIdOrNull(gameId).isCommandGame());

    }

    @Test
    @Order(29)
    public void checkUpdatePlayerAgeWhenAgeIsNotPermissible() {
        Player player = gamePlayService.getAllPlayers().get(0);
        String playerId = player.getId();

        int newPlayerAge = 15;

        gamePlayService.updatePlayerAge(playerId, newPlayerAge);
        Assertions.assertNotEquals(newPlayerAge, gamePlayService.getPlayerByIdOrNull(playerId).getAge());

    }

    @Test
    @Order(30)
    public void checkUpdatePlayerEmailWithIncorrectValue() {
        Player player = gamePlayService.getAllPlayers().get(0);
        String playerId = player.getId();

        String newEmail = "test2023";

        gamePlayService.updatePlayerEmail(playerId, newEmail);

        Assertions.assertNotEquals(newEmail, gamePlayService.getPlayerByIdOrNull(playerId).getEmail());

    }

    @Test
    @Order(31)
    public void checkUpdatePlayerNicknameWithInCorrectValue() {
        Player player = gamePlayService.getAllPlayers().get(0);
        String playerId = player.getId();

        String newNickname = "12345678";

        gamePlayService.updatePlayerNickname(playerId, newNickname);

        Assertions.assertNotEquals(newNickname, gamePlayService.getPlayerByIdOrNull(playerId).getNickname());

    }

    @Test
    @Order(32)
    public void checkUpdateGameNameWithIncorrectValue() {
        Game game = gamePlayService.getAllGames().get(0);
        String gameId = game.getId();

        String newGameName = "202301";

        gamePlayService.updateGameName(gameId, newGameName);

        Assertions.assertNotEquals(newGameName, gamePlayService.getGameByIdOrNull(gameId).getName());

    }

    @Test
    @Order(33)
    public void checkUpdatePlayerEmailWhenAlreadyExistForOther() {
        Player player1 = gamePlayService.getAllPlayers().get(0);
        Player player2 = gamePlayService.getAllPlayers().get(1);
        String playerId = player1.getId();

        String newEmail = player2.getEmail();

        gamePlayService.updatePlayerEmail(playerId, newEmail);

        Assertions.assertNotEquals(newEmail, gamePlayService.getPlayerByIdOrNull(playerId).getEmail());

    }

    @Test
    @Order(34)
    public void checkUpdatePlayerNicknameWhenAlreadyExistForOther() {
        Player player1 = gamePlayService.getAllPlayers().get(0);
        Player player2 = gamePlayService.getAllPlayers().get(1);
        String playerId = player1.getId();

        String newNickname = player2.getNickname();

        gamePlayService.updatePlayerNickname(playerId, newNickname);

        Assertions.assertNotEquals(newNickname, gamePlayService.getPlayerByIdOrNull(playerId).getNickname());

    }

    @Test
    @Order(35)
    public void checkUpdateGameNameWhenAlreadyExistForOther() {
        Game game1 = gamePlayService.getAllGames().get(0);
        Game game2 = gamePlayService.getAllGames().get(1);
        String gameId = game1.getId();

        String newGameName = game2.getName();

        gamePlayService.updateGameName(gameId, newGameName);

        Assertions.assertNotEquals(newGameName, gamePlayService.getGameByIdOrNull(gameId).getName());

    }

    @Test
    @Order(36)
    public void checkAttachGameToThePlayer() {
        Player player = gamePlayService.getAllPlayers().get(0);
        Game game = gamePlayService.getAllGames().get(0);

        gamePlayService.addGameToPlayerInAllDb(game.getId(), player.getId());

        Assertions.assertEquals(1, player.getGameIdList().size());
        Assertions.assertEquals(1, game.getPlayerIdList().size());
    }

    @Test
    @Order(37)
    public void checkAttachGameToThePlayerWhenAlreadyWereAttached() {
        Player player = gamePlayService.getAllPlayers().get(0);
        Game game = gamePlayService.getAllGames().get(0);

        gamePlayService.addGameToPlayerInAllDb(game.getId(), player.getId());

        Assertions.assertEquals(1, player.getGameIdList().size());
        Assertions.assertEquals(1, game.getPlayerIdList().size());
    }

    @Test
    @Order(38)
    public void checkAttachGameToThePlayerWhenPlayerIdDoesNotExist() {
        Game game = gamePlayService.getAllGames().get(2);
        String playerId = "54321";

        gamePlayService.addGameToPlayerInAllDb(game.getId(), playerId);

        Assertions.assertEquals(0, game.getPlayerIdList().size());
    }

    @Test
    @Order(39)
    public void checkAttachGameToThePlayerWhenGameIdDoesNotExist() {
        Player player = gamePlayService.getAllPlayers().get(2);
        String gameId = "54321";

        gamePlayService.addGameToPlayerInAllDb(gameId, player.getId());

        Assertions.assertEquals(0, player.getGameIdList().size());
    }


    public static void deleteAllPlayers() {
        for (int i = 0; i < gamePlayService.getAllPlayers().size();) {
            Player player = gamePlayService.getAllPlayers().get(i);
            gamePlayService.getAllPlayers().remove(player);
        }
    }

    public static void deleteAllGames() {
        for (int i = 0; i < gamePlayService.getAllGames().size();) {
            Game game = gamePlayService.getAllGames().get(i);
            gamePlayService.getAllGames().remove(game);
        }
    }

    @Test
    @Order(40)
    public void checkGetAllPlayersWhenNoPlayers() {
        deleteAllPlayers();
        int playersQty = gamePlayService.getAllPlayers().size();

        Assertions.assertEquals(0,  playersQty);
    }

    @Test
    @Order(41)
    public void checkGetAllGamesWhenNoGames() {
        deleteAllGames();
        int gamesQty = gamePlayService.getAllGames().size();

        Assertions.assertEquals(0,  gamesQty);
    }

}
