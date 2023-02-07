package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Game;
import ua.com.alevel.entity.Player;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GamePlayServiceImplTest {
    private static final GamePlayServiceImpl GAME_PLAY_SERVICE_IMPL = new GamePlayServiceImpl();

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
            GAME_PLAY_SERVICE_IMPL.addPlayer(player);

            Game game = generateGame(i);
            GAME_PLAY_SERVICE_IMPL.addGame(game);
        }

        System.out.println("Players qty = " + GAME_PLAY_SERVICE_IMPL.getAllPlayers().size());
        System.out.println("Games qty = " + GAME_PLAY_SERVICE_IMPL.getAllGames().size());
    }



    @Test
    @Order(1)
    public void checkSizePlayers() {
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE);
    }


    @Test
    @Order(2)
    public void checkSizeGames() {
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllGames().size(), SIZE);
    }

    @Test
    @Order(3)
    public void checkAddNewPlayer() {
        Player player = generatePlayer(SIZE + 1);
        GAME_PLAY_SERVICE_IMPL.addPlayer(player);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE + 1);
    }

    @Test
    @Order(4)
    public void checkAddNewGame() {
        Game game = generateGame(SIZE + 1);
        GAME_PLAY_SERVICE_IMPL.addGame(game);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllGames().size(), SIZE + 1);
    }

    @Test
    @Order(5)
    public void deletePlayerWithoutAttachedGames() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        GAME_PLAY_SERVICE_IMPL.deletePlayer(player.getId());
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE);
    }

    @Test
    @Order(6)
    public void deleteGameWithoutAttachedPlayers() {
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);
        GAME_PLAY_SERVICE_IMPL.deleteGame(game.getId());
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllGames().size(), SIZE);
    }

    @Test
    @Order(7)
    public void deletePlayerWithAttachedGame() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);
        GAME_PLAY_SERVICE_IMPL.addGameToPlayerInAllDb(game.getId(), player.getId());
        int playerIdListSize = game.getPlayerIdList().size(); //playerIdListSize = 1

        GAME_PLAY_SERVICE_IMPL.deletePlayer(player.getId()); //playerIdListSize = 0
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE - 1);
        Assertions.assertEquals(game.getPlayerIdList().size(), playerIdListSize - 1);
    }

    @Test
    @Order(8)
    public void deleteGameWithAttachedPlayer() {
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        GAME_PLAY_SERVICE_IMPL.addGameToPlayerInAllDb(game.getId(), player.getId());
        int gameIdListSize = player.getGameIdList().size(); //gameIdListSize = 1

        GAME_PLAY_SERVICE_IMPL.deleteGame(game.getId()); //gameIdListSize = 0
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllGames().size(), SIZE - 1);
        Assertions.assertEquals(player.getGameIdList().size(), gameIdListSize - 1);
    }

    @Test
    @Order(9)
    public void shouldBeCreatePlayerWhenEmailIsDuplicateWithTheSameUpperCase() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        Player newPlayer = new Player();
        newPlayer.setEmail(player.getEmail());
        GAME_PLAY_SERVICE_IMPL.addPlayer(newPlayer);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(10)
    public void shouldBeCreatePlayerWhenEmailIsDuplicateWithDifferentUppercaseCase() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        Player newPlayer = new Player();
        newPlayer.setEmail(player.getEmail().toUpperCase());
        GAME_PLAY_SERVICE_IMPL.addPlayer(newPlayer);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(11)
    public void shouldBeCreatePlayerWhenNicknameIsDuplicateWithTheSameUpperCase() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        Player newPlayer = new Player();
        newPlayer.setNickname(player.getNickname());
        GAME_PLAY_SERVICE_IMPL.addPlayer(newPlayer);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(12)
    public void shouldBeCreatePlayerWhenNicknameIsDuplicateWithDifferentUpperCase() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        Player newPlayer = new Player();
        newPlayer.setNickname(player.getNickname().toUpperCase());
        GAME_PLAY_SERVICE_IMPL.addPlayer(newPlayer);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(13)
    public void shouldBeCreatePlayerWhenAgeIsLessThanPermissible() {
        Player player = new Player();
        player.setAge(15);
        GAME_PLAY_SERVICE_IMPL.addPlayer(player);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(14)
    public void shouldBeCreatePlayerWhenAgeIsMoreThanPermissible() {
        Player player = new Player();
        player.setAge(105);
        GAME_PLAY_SERVICE_IMPL.addPlayer(player);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(15)
    public void shouldBeCreatePlayerWhenEmailFormatIsNotValid() {
        Player player = new Player();
        player.setEmail("test");
        GAME_PLAY_SERVICE_IMPL.addPlayer(player);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(16)
    public void shouldBeCreatePlayerWhenNicknameFormatIsNotValid() {
        Player player = new Player();
        player.setNickname("12345");
        GAME_PLAY_SERVICE_IMPL.addPlayer(player);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllPlayers().size(), SIZE - 1);
    }

    @Test
    @Order(17)
    public void shouldBeCreateGameWhenNameIsDuplicateWithTheSameUpperCase() {
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);
        Game newGame = new Game();
        newGame.setName(game.getName());
        GAME_PLAY_SERVICE_IMPL.addGame(newGame);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllGames().size(), SIZE - 1);
    }

    @Test
    @Order(18)
    public void shouldBeCreateGameWhenNameIsDuplicateWithDifferentUppercaseCase() {
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);
        Game newGame = new Game();
        newGame.setName(game.getName().toUpperCase());
        GAME_PLAY_SERVICE_IMPL.addGame(newGame);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllGames().size(), SIZE - 1);
    }

    @Test
    @Order(19)
    public void shouldBeCreateGameWhenNameFormatIsNotValid() {
        Game game = new Game();
        game.setName("12365");
        GAME_PLAY_SERVICE_IMPL.addGame(game);
        Assertions.assertEquals(GAME_PLAY_SERVICE_IMPL.getAllGames().size(), SIZE - 1);
    }


    @Test
    @Order(20)
    public void getPlayerByIdOrNullWhenIdExists(){
        String playerId = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0).getId();
        Player player = GAME_PLAY_SERVICE_IMPL.getPlayerByIdOrNull(playerId);
        Assertions.assertNotNull(player);
    }

    @Test
    @Order(21)
    public void getPlayerByIdOrNullWhenIdDoesNotExist(){
        String playerId = "testId";
        Player player = GAME_PLAY_SERVICE_IMPL.getPlayerByIdOrNull(playerId);
        Assertions.assertNull(player);
    }

    @Test
    @Order(22)
    public void getGameByIdOrNullWhenIdExists(){
        String gameId = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0).getId();
        Game game = GAME_PLAY_SERVICE_IMPL.getGameByIdOrNull(gameId);
        Assertions.assertNotNull(game);
    }

    @Test
    @Order(23)
    public void getGameByIdOrNullWhenIdDoesNotExist(){
        String gameId = "testId";
        Game game = GAME_PLAY_SERVICE_IMPL.getGameByIdOrNull(gameId);
        Assertions.assertNull(game);
    }

    @Test
    @Order(24)
    public void checkUpdatePlayerAgeWithNewCorrectValue() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        String playerId = player.getId();

        int newPlayerAge = 0;
        if (player.getAge() != 100) {
            newPlayerAge = player.getAge() + 1;
        } else {
            newPlayerAge = 18;
        }

        GAME_PLAY_SERVICE_IMPL.updatePlayerAge(playerId, newPlayerAge);
        Assertions.assertEquals(newPlayerAge, GAME_PLAY_SERVICE_IMPL.getPlayerByIdOrNull(playerId).getAge());

    }

    @Test
    @Order(25)
    public void checkUpdatePlayerEmailWithNewCorrectValue() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        String playerId = player.getId();

        String newEmail = "2023" + player.getEmail();

        GAME_PLAY_SERVICE_IMPL.updatePlayerEmail(playerId, newEmail);

        Assertions.assertSame(newEmail, GAME_PLAY_SERVICE_IMPL.getPlayerByIdOrNull(playerId).getEmail());

    }

    @Test
    @Order(26)
    public void checkUpdatePlayerNicknameWithNewCorrectValue() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        String playerId = player.getId();

        String newNickname = player.getNickname() + "2023";

        GAME_PLAY_SERVICE_IMPL.updatePlayerNickname(playerId, newNickname);

        Assertions.assertSame(newNickname, GAME_PLAY_SERVICE_IMPL.getPlayerByIdOrNull(playerId).getNickname());

    }

    @Test
    @Order(27)
    public void checkUpdateGameNameWithNewCorrectValue() {
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);
        String gameId = game.getId();

        String newGameName = game.getName() + "2023";

        GAME_PLAY_SERVICE_IMPL.updateGameName(gameId, newGameName);

        Assertions.assertSame(newGameName, GAME_PLAY_SERVICE_IMPL.getGameByIdOrNull(gameId).getName());

    }

    @Test
    @Order(28)
    public void checkUpdateGameTypeWithNewCorrectValue() {
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);
        String gameId = game.getId();

        boolean newIsCommandGame;
        if (game.isCommandGame()) {
            newIsCommandGame = false;
        } else {
            newIsCommandGame = true;
        }

        GAME_PLAY_SERVICE_IMPL.updateGameType(gameId, newIsCommandGame);

        Assertions.assertSame(newIsCommandGame, GAME_PLAY_SERVICE_IMPL.getGameByIdOrNull(gameId).isCommandGame());

    }

    @Test
    @Order(29)
    public void checkUpdatePlayerAgeWhenAgeIsNotPermissible() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        String playerId = player.getId();

        int newPlayerAge = 15;

        GAME_PLAY_SERVICE_IMPL.updatePlayerAge(playerId, newPlayerAge);
        Assertions.assertNotEquals(newPlayerAge, GAME_PLAY_SERVICE_IMPL.getPlayerByIdOrNull(playerId).getAge());

    }

    @Test
    @Order(30)
    public void checkUpdatePlayerEmailWithIncorrectValue() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        String playerId = player.getId();

        String newEmail = "test2023";

        GAME_PLAY_SERVICE_IMPL.updatePlayerEmail(playerId, newEmail);

        Assertions.assertNotEquals(newEmail, GAME_PLAY_SERVICE_IMPL.getPlayerByIdOrNull(playerId).getEmail());

    }

    @Test
    @Order(31)
    public void checkUpdatePlayerNicknameWithInCorrectValue() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        String playerId = player.getId();

        String newNickname = "12345678";

        GAME_PLAY_SERVICE_IMPL.updatePlayerNickname(playerId, newNickname);

        Assertions.assertNotEquals(newNickname, GAME_PLAY_SERVICE_IMPL.getPlayerByIdOrNull(playerId).getNickname());

    }

    @Test
    @Order(32)
    public void checkUpdateGameNameWithIncorrectValue() {
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);
        String gameId = game.getId();

        String newGameName = "202301";

        GAME_PLAY_SERVICE_IMPL.updateGameName(gameId, newGameName);

        Assertions.assertNotEquals(newGameName, GAME_PLAY_SERVICE_IMPL.getGameByIdOrNull(gameId).getName());

    }

    @Test
    @Order(33)
    public void checkUpdatePlayerEmailWhenAlreadyExistForOther() {
        Player player1 = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        Player player2 = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(1);
        String playerId = player1.getId();

        String newEmail = player2.getEmail();

        GAME_PLAY_SERVICE_IMPL.updatePlayerEmail(playerId, newEmail);

        Assertions.assertNotEquals(newEmail, GAME_PLAY_SERVICE_IMPL.getPlayerByIdOrNull(playerId).getEmail());

    }

    @Test
    @Order(34)
    public void checkUpdatePlayerNicknameWhenAlreadyExistForOther() {
        Player player1 = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        Player player2 = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(1);
        String playerId = player1.getId();

        String newNickname = player2.getNickname();

        GAME_PLAY_SERVICE_IMPL.updatePlayerNickname(playerId, newNickname);

        Assertions.assertNotEquals(newNickname, GAME_PLAY_SERVICE_IMPL.getPlayerByIdOrNull(playerId).getNickname());

    }

    @Test
    @Order(35)
    public void checkUpdateGameNameWhenAlreadyExistForOther() {
        Game game1 = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);
        Game game2 = GAME_PLAY_SERVICE_IMPL.getAllGames().get(1);
        String gameId = game1.getId();

        String newGameName = game2.getName();

        GAME_PLAY_SERVICE_IMPL.updateGameName(gameId, newGameName);

        Assertions.assertNotEquals(newGameName, GAME_PLAY_SERVICE_IMPL.getGameByIdOrNull(gameId).getName());

    }

    @Test
    @Order(36)
    public void checkAttachGameToThePlayer() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);

        GAME_PLAY_SERVICE_IMPL.addGameToPlayerInAllDb(game.getId(), player.getId());

        Assertions.assertEquals(1, player.getGameIdList().size());
        Assertions.assertEquals(1, game.getPlayerIdList().size());
    }

    @Test
    @Order(37)
    public void checkAttachGameToThePlayerWhenAlreadyWereAttached() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(0);
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(0);

        GAME_PLAY_SERVICE_IMPL.addGameToPlayerInAllDb(game.getId(), player.getId());

        Assertions.assertEquals(1, player.getGameIdList().size());
        Assertions.assertEquals(1, game.getPlayerIdList().size());
    }

    @Test
    @Order(38)
    public void checkAttachGameToThePlayerWhenPlayerIdDoesNotExist() {
        Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(2);
        String playerId = "54321";

        GAME_PLAY_SERVICE_IMPL.addGameToPlayerInAllDb(game.getId(), playerId);

        Assertions.assertEquals(0, game.getPlayerIdList().size());
    }

    @Test
    @Order(39)
    public void checkAttachGameToThePlayerWhenGameIdDoesNotExist() {
        Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(2);
        String gameId = "54321";

        GAME_PLAY_SERVICE_IMPL.addGameToPlayerInAllDb(gameId, player.getId());

        Assertions.assertEquals(0, player.getGameIdList().size());
    }


    public static void deleteAllPlayers() {
        for (int i = 0; i < GAME_PLAY_SERVICE_IMPL.getAllPlayers().size();) {
            Player player = GAME_PLAY_SERVICE_IMPL.getAllPlayers().get(i);
            GAME_PLAY_SERVICE_IMPL.getAllPlayers().remove(player);
        }
    }

    public static void deleteAllGames() {
        for (int i = 0; i < GAME_PLAY_SERVICE_IMPL.getAllGames().size();) {
            Game game = GAME_PLAY_SERVICE_IMPL.getAllGames().get(i);
            GAME_PLAY_SERVICE_IMPL.getAllGames().remove(game);
        }
    }

    @Test
    @Order(40)
    public void checkGetAllPlayersWhenNoPlayers() {
        deleteAllPlayers();
        int playersQty = GAME_PLAY_SERVICE_IMPL.getAllPlayers().size();

        Assertions.assertEquals(0,  playersQty);
    }

    @Test
    @Order(41)
    public void checkGetAllGamesWhenNoGames() {
        deleteAllGames();
        int gamesQty = GAME_PLAY_SERVICE_IMPL.getAllGames().size();

        Assertions.assertEquals(0,  gamesQty);
    }

}
