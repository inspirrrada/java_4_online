package ua.com.alevel.service.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.dao.PlayerDao;
import ua.com.alevel.persistance.dto.PlayerDto;
import ua.com.alevel.persistance.entity.Player;
import ua.com.alevel.service.PlayerService;
import ua.com.alevel.utils.ColorUtils;

import java.util.Collection;
import java.util.Optional;

@BeanClass
public class PlayerServiceImpl implements PlayerService {

    @InjectBean
    private PlayerDao playerDao;

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
        if (isAgePermissible(player.getAge()) &&
                isCorrectEmail(player.getEmail()) && !hasTheSameEmail(player.getEmail()) &&
                isCorrectNickname(player.getNickname()) && !hasTheSameNickname(player.getNickname())) {
            playerDao.update(player);
        }
    }

    @Override
    public void delete(Player player) {
        playerDao.delete(player);
    }

    @Override
    public Player findById(Long id) {
        return playerDao.findById(id).get();
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
