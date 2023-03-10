package ua.com.alevel.service.impl;

import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.persistance.dao.PlayerDao;
import ua.com.alevel.persistance.dto.GameDto;
import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Game;
import ua.com.alevel.persistance.entity.Player;
import ua.com.alevel.service.PlayerService;
import ua.com.alevel.utils.ColorUtils;

import java.util.Collection;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    @InjectBean
    private PlayerDao playerDao;

    @Override
    public void addPlayer(Player player) {
        if (isAgePermissible(player.getAge()) &&
                isCorrectEmail(player.getEmail()) && !hasTheSameEmail(player.getEmail()) &&
                isCorrectNickname(player.getNickname()) && !hasTheSameNickname(player.getNickname())) {
            playerDao.addPlayer(player);
        }
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerDao.getPlayerById(id).get();
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return playerDao.getAllPlayers();
    }

    @Override
    public void updatePlayerAge(Long id, int age) {
        if (isAgePermissible(age)) {
            playerDao.updatePlayerAge(id, age);
        }
    }

    @Override
    public void updatePlayerEmail(Long id, String email) {
        if (isCorrectEmail(email) && !hasTheSameEmail(email)) {
            playerDao.updatePlayerEmail(id, email);
        }
    }

    @Override
    public void updatePlayerNickname(Long id, String nickname) {
        if (isCorrectNickname(nickname) && !hasTheSameNickname(nickname)) {
            playerDao.updatePlayerNickname(id, nickname);
        }
    }

    @Override
    public boolean deletePlayer(Long id) {
        return playerDao.deletePlayer(id);
    }

    @Override
    public Collection<Player> getPlayersByGame(Long gameId) {
        return playerDao.getPlayersByGame(gameId);
    }

    @Override
    public Collection<PlayerDto> getGamesCountOfAllPlayers() {
        return playerDao.getGamesCountOfAllPlayers();
    }

    //
    @Override
    public boolean isCorrectAgeFormat(String ageValue) {
        boolean correctAgeFormat;
        //age has to be only from digits
        if (ageValue.matches("\\d+")) {
            correctAgeFormat = true;
        } else {
            System.out.println(ColorUtils.RED_TEXT.format("Invalid value! Please enter number NOT string!"));
            correctAgeFormat = false;
        }
        return correctAgeFormat;
    }

    @Override
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

    @Override
    public boolean isCorrectNickname(String nickname) {
        boolean correctNickname = true;
        //nickname can't have only digits
        if (nickname.matches("\\d+")) {
            System.out.println(ColorUtils.RED_TEXT.format("Nickname can't contain only digits!"));
            correctNickname = false;
        }
        return correctNickname;
    }

    @Override
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

    @Override
    public boolean hasTheSameEmail(String email) {
        boolean hasTheSameEmail = false;
        Collection<Player> allPlayers = getAllPlayers();
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

    @Override
    public boolean hasTheSameNickname(String nickname) {
        boolean hasTheSameNickname = false;
        Collection<Player> allPlayers = getAllPlayers();
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

    @Override
    public boolean existPlayerId(Long playerId) {
        boolean existPlayerId = getPlayerById(playerId) != null;
        if (!existPlayerId) {
            System.out.println(ColorUtils.RED_TEXT.format("We can't find player with such id!"));
        }
        return existPlayerId;
    }

}
