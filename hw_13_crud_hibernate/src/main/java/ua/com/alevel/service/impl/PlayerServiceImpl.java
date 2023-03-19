package ua.com.alevel.service.impl;

import ua.com.alevel.dao.GameDao;
import ua.com.alevel.dao.PlayerDao;
import ua.com.alevel.dao.impl.GameDaoImpl;
import ua.com.alevel.dao.impl.PlayerDaoImpl;
import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;
import ua.com.alevel.service.PlayerService;
import ua.com.alevel.utils.ColorUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class PlayerServiceImpl implements PlayerService {

    private PlayerDao playerDao = new PlayerDaoImpl();
    private GameDao gameDao = new GameDaoImpl();


    @Override
    public void create(Player player) {
        if (isAgePermissible(player.getAge()) &&
                isCorrectEmail(player.getEmail()) && !hasTheSameEmail(player.getEmail()) &&
                isCorrectNickname(player.getNickname()) && !hasTheSameNickname(player.getNickname())) {
            playerDao.create(player);
        }
    }

    @Override
    public void update(Player player) {
        if (isAgePermissible(player.getAge()) ||
                (isCorrectEmail(player.getEmail()) && !hasTheSameEmail(player.getEmail())) ||
                (isCorrectNickname(player.getNickname()) && !hasTheSameNickname(player.getNickname()))) {
            playerDao.update(player);
        }
    }

    @Override
    public boolean delete(Player player) {
        Set<Game> games = player.getGames();
        for (Game game : games) {
            Set<Player> players = game.getPlayers();
            for (Player playerCurrent : players) {
                if (playerCurrent.getId().equals(player.getId())) {
                    players.remove(player);
                    gameDao.update(game);
                    break;
                }
            }
        }
//        Game game = gameDao.findById(gameId).get();
//        Player player = playerDao.findById(playerId).get();
//
//        players.remove(player);
//        gameDao.update(game);
//        Set<Game> games = player.getGames();
//        games.remove(game);
//        playerDao.update(player);

//        return true;
        return playerDao.delete(player);
    }

    @Override
    public Player findById(Long id) {
        Optional<Player> playerOptional = playerDao.findById(id);
        if (playerOptional.isPresent()) {
            return playerDao.findById(id).get();
        }
        return null;
    }

    @Override
    public Collection<Player> findAll() {
        return playerDao.findAll();
    }

    @Override
    public Collection<Player> findPlayersByGame(Long gameId) {
        return playerDao.findPlayersByGame(gameId);
    }

    @Override
    public Collection<PlayerDto> findPlayerDto() {
        return playerDao.findPlayerDto();
    }

    public boolean isAgePermissible(int age) {
        boolean agePermissible;
        if (age < 18) {
            agePermissible = false;
            System.out.println(ColorUtils.RED_TEXT.format("Grow up first and come later."));
        } else if (age > 100) {
            agePermissible = false;
            System.out.println(ColorUtils.RED_TEXT.format("Your age is fantastic! You're too good for this game."));
        } else {
            agePermissible = true;
        }
        return agePermissible;
    }

    public boolean isCorrectNickname(String nickname) {
        boolean correctNickname = true;
        //nickname can't have only digits
        if (nickname.matches("\\d+")) {
            System.out.println(ColorUtils.RED_TEXT.format("Nickname can't contain only digits!"));
            correctNickname = false;
        }
        return correctNickname;
    }

    public boolean isCorrectEmail(String email) {
        boolean correctEmail;
        if (email.matches("^(.+)@(.+)$")) {
            correctEmail = true;
        } else {
            System.out.println(ColorUtils.RED_TEXT.format("Invalid value!"));
            correctEmail = false;
        }
        return correctEmail;
    }

    public boolean hasTheSameEmail(String email) {
        boolean hasTheSameEmail = false;
        Collection<Player> allPlayers = findAll();
        if (allPlayers != null) {
            for (Player player : allPlayers) {
                if (player.getEmail().equalsIgnoreCase(email)) {
                    hasTheSameEmail = true;
                    break;
                }
            }
        }
        return hasTheSameEmail;
    }

    public boolean hasTheSameNickname(String nickname) {
        boolean hasTheSameNickname = false;
        Collection<Player> allPlayers = findAll();
        if (allPlayers != null) {
            for (Player player : allPlayers) {
                if (player.getNickname().equalsIgnoreCase(nickname)) {
                    hasTheSameNickname = true;
                    break;
                }
            }
        }
        return hasTheSameNickname;
    }

    public boolean existPlayerId(Long playerId) {
        boolean existPlayerId = findById(playerId) != null;
        if (!existPlayerId) {
            System.out.println(ColorUtils.RED_TEXT.format("We can't find player with such id!"));
        }
        return existPlayerId;
    }
}
