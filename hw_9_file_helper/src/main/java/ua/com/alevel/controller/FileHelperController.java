package ua.com.alevel.controller;

import ua.com.alevel.service.FileHelperService;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class FileHelperController {
    private FileHelperService service = new FileHelperService();
//    HashMap<String, String> allMap = service.searchDir();

    public void start() {
        System.out.println();
        System.out.println("WELCOME TO THE FILE HELPER APP!");
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
        }

    }

    private void menu() {
        System.out.println();
        System.out.println("==========================================================================");
        System.out.println("If you want to view content of exact directory, please enter 1");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("If you want to create new file in exact directory, please enter 2");
        System.out.println("If you want to create new directory in exact directory, please enter 3");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("If you want to delete file in exact directory, please enter 4");
        System.out.println("If you want to delete directory, please enter 5");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("If you want to move file from one directory to another, please enter 6");
        System.out.println("If you want to move directory from one directory to another, please enter 7");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("If you want to find file, please enter 8");
        System.out.println("If you want to find directory, please enter 9");
        System.out.println("If you want to find text, please enter 10");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("If you want to close application, please enter 11");
        System.out.println("==========================================================================");
        System.out.println();
    }

    private void crud(BufferedReader reader, String selectedOption) throws IOException {
        switch (selectedOption) {
            case "1" -> viewContentExactDir(reader);
            case "2" -> createNewFile(reader);
            case "3" -> createNewDir(reader);
            case "4" -> deleteFile(reader);
            case "5" -> deleteDir(reader);
            case "6" -> transferFileFromOneDirToAnother(reader);
            case "7" -> transferFolderFromOneDirToAnother(reader);
            case "8" -> findFile(reader);
            case "9" -> findDir(reader);
            case "10" -> searchText(reader);
            case "11" -> stop();
//            default -> System.out.println(GamePlayService.getRedText().format("Wrong value! Select menu again."));
        }
        menu();
    }

    private void viewContentExactDir(BufferedReader reader) throws IOException {
        System.out.println("Please enter name of directory:");
        String dir = reader.readLine();
        service.observe(dir);
//        File file = new File(dir);
//        service.readDir(file);
    }

    public void createNewFile(BufferedReader reader) throws IOException {
        System.out.println("Please enter name of directory: ");
        String dirName = reader.readLine();
        HashMap<String, String> map = service.getSearchDirInfo(dirName);
        String[] path = new String[1];
        if (map.size() > 1) {
            System.out.println("We found few directories in system with such name.");
            map.forEach((k,v) -> System.out.println(k));
            System.out.println("Please look details and enter the path of directory you need:");
            while(true) {
               path[0] = reader.readLine();
                int[] count = new int[1];
                map.forEach((k,v) -> {
                    if (path[0].equals(k)) {
                        count[0]++;
                    }
                });
                if (count[0] == 0) {
                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
                } else if (count[0] == 1) {
                    break;
                }
            }
        } else if (map.size() == 0) {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        } else if (map.size() == 1) {
            path[0] = service.getDirPathByName(dirName);
        }
        System.out.println("Please enter name of file with extension");
        String fileName = reader.readLine();
        service.createFile(path[0], fileName);
    }

    private void createNewDir(BufferedReader reader) throws IOException {
        System.out.println("Please enter name of directory where you want to create new folder: ");
        String dirName = reader.readLine();
        HashMap<String, String> map = service.getSearchDirInfo(dirName);
        String[] path = new String[1];
        if (map.size() > 1) {
            System.out.println("We found few directories in system with such name.");
            map.forEach((k,v) -> System.out.println(k));
            System.out.println("Please look details and enter the path of directory you need:");
            while(true) {
                path[0] = reader.readLine();
                int[] count = new int[1];
                map.forEach((k,v) -> {
                    if (path[0].equals(k)) {
                        count[0]++;
                    }
                });
                if (count[0] == 0) {
                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
                } else if (count[0] == 1) {
                    break;
                }
            }
        } else if (map.size() == 0) {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        } else if (map.size() == 1) {
            path[0] = service.getDirPathByName(dirName);
        }
        System.out.println("Please enter name of new folder");
        String newDirName = reader.readLine();
        service.createDir(path[0], newDirName);
    }

    private void findFile(BufferedReader reader) throws IOException {
        System.out.println("Please enter name of file:");
        System.out.println("***Pay attention, the search is case-sensitive.");
        String searchFile = reader.readLine();
        HashMap resultMap = service.getSearchFileInfo(searchFile);
        if (resultMap.size() > 1) {
            System.out.println("There are few files in system with such name:");
            resultMap.forEach((k,v) -> System.out.println(k));
        } else if (resultMap.size() == 1) {
            System.out.println("Search file you can find here: " );
            resultMap.forEach((k,v) -> System.out.println(k));
        } else {
            System.out.println("We can't find such file in system. Please check the name and try this menu again.");
        }
    }

    private void findDir(BufferedReader reader) throws IOException {
        System.out.println("Please enter name of directory:");
        System.out.println("***Pay attention, the search is case-sensitive.");
        String searchDir = reader.readLine();
        HashMap resultMap = service.getSearchDirInfo(searchDir);
        if (resultMap.size() > 1) {
            System.out.println("There are few directories in system with such name:");
            resultMap.forEach((k,v) -> System.out.println(k));
        } else if (resultMap.size() == 1) {
            System.out.println("Search directory you can find here: " );
            resultMap.forEach((k,v) -> System.out.println(k));
        } else {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        }
    }

    private void deleteDir(BufferedReader reader) throws IOException {
        System.out.println("Please enter name of directory:");
        String dirName = reader.readLine();
        HashMap<String, String> map = service.getSearchDirInfo(dirName);
        String[] path = new String[1];
        if (map.size() > 1) {
            System.out.println("We found few directories in system with such name.");
            map.forEach((k,v) -> System.out.println(k));
            System.out.println("Please look details and enter the path of directory you need:");
            while(true) {
                path[0] = reader.readLine();
                int[] count = new int[1];
                map.forEach((k,v) -> {
                    if (path[0].equals(k)) {
                        count[0]++;
                    }
                });
                if (count[0] == 0) {
                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
                } else if (count[0] == 1) {
                    break;
                }
            }
        } else if (map.size() == 0) {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        } else {
            path[0] = service.getDirPathByName(dirName);
        }
        service.deleteDir(path[0]);
    }

    private void deleteFile(BufferedReader reader) throws IOException {
        System.out.println("Please enter name of directory:");
        String dirName = reader.readLine();
        HashMap<String, String> map = service.getSearchDirInfo(dirName);
        String[] path = new String[1];
        if (map.size() > 1) {
            System.out.println("We found few directories in system with such name.");
            map.forEach((k,v) -> System.out.println(k));
            System.out.println("Please look details and enter the path of directory you need:");
            while(true) {
                path[0] = reader.readLine();
                int[] count = new int[1];
                map.forEach((k,v) -> {
                    if (path[0].equals(k)) {
                        count[0]++;
                    }
                });
                if (count[0] == 0) {
                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
                } else if (count[0] == 1) {
                    break;
                }
            }
        } else if (map.size() == 0) {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        } else {
            path[0] = service.getDirPathByName(dirName);
        }
        System.out.println("Please enter name of file with extension");
        String fileName = reader.readLine();
        service.deleteFile(path[0], fileName);
    }

    private void transferFileFromOneDirToAnother(BufferedReader reader) throws IOException {
        System.out.println("Please enter name of directory, from which you want to move the file:");
        String dirFrom = reader.readLine();
        HashMap<String, String> mapFrom = service.getSearchDirInfo(dirFrom);
        String[] pathFrom = new String[1];
        if (mapFrom.size() > 1) {
            System.out.println("We found few directories in system with such name.");
            mapFrom.forEach((k,v) -> System.out.println(k));
            System.out.println("Please look details and enter the path of directory you need:");
            while(true) {
                pathFrom[0] = reader.readLine();
                int[] count = new int[1];
                mapFrom.forEach((k,v) -> {
                    if (pathFrom[0].equals(k)) {
                        count[0]++;
                    }
                });
                if (count[0] == 0) {
                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
                } else if (count[0] == 1) {
                    break;
                }
            }
        } else if (mapFrom.size() == 0) {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        } else {
            pathFrom[0] = service.getDirPathByName(dirFrom);
        }
        System.out.println("Please enter file name with extension:");
        String fileName = reader.readLine();
        service.deleteFile(pathFrom[0], fileName);
        System.out.println("Please enter name of directory, where you want to move the file:");
        String dirTo = reader.readLine();
        HashMap<String, String> mapTo = service.getSearchDirInfo(dirTo);
        String[] pathTo = new String[1];
        if (mapTo.size() > 1) {
            System.out.println("We found few directories in system with such name.");
            mapTo.forEach((k,v) -> System.out.println(k));
            System.out.println("Please look details and enter the path of directory you need:");
            while(true) {
                pathTo[0] = reader.readLine();
                int[] count = new int[1];
                mapTo.forEach((k,v) -> {
                    if (pathTo[0].equals(k)) {
                        count[0]++;
                    }
                });
                if (count[0] == 0) {
                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
                } else if (count[0] == 1) {
                    break;
                }
            }
        } else if (mapTo.size() == 0) {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        } else {
            pathTo[0] = service.getDirPathByName(dirTo);
        }
        service.moveFile(pathFrom[0], fileName, pathTo[0]);
    }

    private void transferFolderFromOneDirToAnother(BufferedReader reader) throws IOException {
        System.out.println("Please enter name of directory, from which you want to move the folder:");
        String dirFrom = reader.readLine();
        HashMap<String, String> mapFrom = service.getSearchDirInfo(dirFrom);
        String[] pathFrom = new String[1];
        if (mapFrom.size() > 1) {
            System.out.println("We found few directories in system with such name.");
            mapFrom.forEach((k,v) -> System.out.println(k));
            System.out.println("Please look details and enter the path of directory you need:");
            while(true) {
                pathFrom[0] = reader.readLine();
                int[] count = new int[1];
                mapFrom.forEach((k,v) -> {
                    if (pathFrom[0].equals(k)) {
                        count[0]++;
                    }
                });
                if (count[0] == 0) {
                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
                } else if (count[0] == 1) {
                    break;
                }
            }
        } else if (mapFrom.size() == 0) {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        } else {
            pathFrom[0] = service.getDirPathByName(dirFrom);
        }
        System.out.println("Please enter folder name you want to move:");
        String folderName = reader.readLine();
        //service.deleteFile(pathFrom[0], fileName);
        System.out.println("Please enter name of directory, where you want to move the folder:");
        String dirTo = reader.readLine();
        HashMap<String, String> mapTo = service.getSearchDirInfo(dirTo);
        String[] pathTo = new String[1];
        if (mapTo.size() > 1) {
            System.out.println("We found few directories in system with such name.");
            mapTo.forEach((k,v) -> System.out.println(k));
            System.out.println("Please look details and enter the path of directory you need:");
            while(true) {
                pathTo[0] = reader.readLine();
                int[] count = new int[1];
                mapTo.forEach((k,v) -> {
                    if (pathTo[0].equals(k)) {
                        count[0]++;
                    }
                });
                if (count[0] == 0) {
                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
                } else if (count[0] == 1) {
                    break;
                }
            }
        } else if (mapTo.size() == 0) {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        } else {
            pathTo[0] = service.getDirPathByName(dirTo);
        }
        service.moveDir(pathFrom[0], folderName, pathTo[0]);
    }

    public void searchText(BufferedReader reader) throws IOException {
        System.out.println("Please enter name of directory:");
        String dirName = reader.readLine();
        HashMap<String, String> map = service.getSearchDirInfo(dirName);
        String[] path = new String[1];
        if (map.size() > 1) {
            System.out.println("We found few directories in system with such name.");
            map.forEach((k,v) -> System.out.println(k));
            System.out.println("Please look details and enter the path of directory you need:");
            while(true) {
                path[0] = reader.readLine();
                int[] count = new int[1];
                map.forEach((k,v) -> {
                    if (path[0].equals(k)) {
                        count[0]++;
                    }
                });
                if (count[0] == 0) {
                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
                } else if (count[0] == 1) {
                    break;
                }
            }
        } else if (map.size() == 0) {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        } else {
            path[0] = service.getDirPathByName(dirName);
        }
        System.out.println("Please enter text for search:");
        String searchText = reader.readLine();
        HashMap resultMap = service.searchTextInDir(path[0], searchText);
        System.out.println("Text was found here:");
        resultMap.forEach((k,v) -> System.out.println(k));

    }



//    private void createPlayer(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 1. CREATE PLAYER"));
//        System.out.println();
//        System.out.print(GamePlayService.getUnderlinedText().format("Please enter your age."));
//        System.out.println(" ***You must be of legal age to play this game.");
//        String ageValue = "";
//        boolean isCorrectAgeFormat = false;
//        while(!isCorrectAgeFormat) {
//            ageValue = reader.readLine();
//            isCorrectAgeFormat = service.isCorrectAgeFormat(ageValue);
//        }
//        int age = Integer.parseInt(ageValue);
//        boolean isAgePermissible = service.isAgePermissible(age);
//        if (!isAgePermissible) {
//            stop();
//        } else {
//            System.out.println(GamePlayService.getUnderlinedText().format("\nPlease enter your email."));
//            String email = "";
//            boolean correctEmail = false;
//            boolean hasTheSameEmail = false;
//            while (true) {
//                email = reader.readLine();
//                correctEmail = service.isCorrectEmail(email);
//                if (correctEmail) {
//                    hasTheSameEmail = service.hasTheSameEmail(email);
//                    if (!hasTheSameEmail) {
//                        break;
//                    } else {
//                        System.out.println(GamePlayService.getRedText().format("This email is already registered!"));
//                        System.out.println("Please check and try to enter your email again");
//                    }
//                } else {
//                    System.out.println("Please check and try to enter your email again");
//                }
//            }
//            System.out.print(GamePlayService.getUnderlinedText().format("\nPlease enter your nickname."));
//            System.out.println(" ***Pay attention, nickname can't have only digits.");
//            String nickname = "";
//            boolean correctNickname = false;
//            boolean hasTheSameNickname = false;
//            while (true) {
//                nickname = reader.readLine();
//                correctNickname = service.isCorrectNickname(nickname);
//                if (correctNickname) {
//                    hasTheSameNickname = service.hasTheSameNickname(nickname);
//                    if (!hasTheSameNickname) {
//                        break;
//                    } else {
//                        System.out.println(GamePlayService.getRedText().format("This nickname is already registered!"));
//                        System.out.println("Please check and try to enter your nickname again");
//                    }
//                } else {
//                    System.out.println("Please check and try to enter your nickname again");
//                }
//            }
//            Player player = new Player();
//            player.setAge(age);
//            player.setEmail(email);
//            player.setNickname(nickname);
//            service.addPlayer(player);
//            System.out.println(GamePlayService.getBlueText().format("\nCongratulations! Your player was created."));
//        }
//    }
//
//    private void findPlayerById(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 2. FIND PLAYER BY ID"));
//        System.out.println();
//        System.out.println(GamePlayService.getUnderlinedText().format("Please enter player ID:"));
//        String playerId = reader.readLine();
//        Player player = service.getPlayerByIdOrNull(playerId);
//        if (player != null) {
//            System.out.println(GamePlayService.getYellowText().format(player.toString()));
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void updatePlayer(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 3. UPDATE PLAYER BY ID"));
//        System.out.println();
//        System.out.println(GamePlayService.getUnderlinedText().format("Please enter player ID"));
//        String playerId = reader.readLine();
//        Player playerNew = service.getPlayerByIdOrNull(playerId);
//        int ageOld = playerNew.getAge();
//
//        if (playerNew != null) {
//            System.out.println("Great, please update details of player '" + playerNew.getNickname() + "' below.");
//            System.out.println("If you don't want to update some detail, press 0 and you go to the next. Updates for old values will not be saved.");
//
//            //update age
//            System.out.println(GamePlayService.getUnderlinedText().format("\nPlease enter new age:"));
//            String ageValue = reader.readLine();
//
//            //"0" means without changes
//            if (!ageValue.equals("0")) {
//                boolean isCorrectAgeFormat = service.isCorrectAgeFormat(ageValue);
//                if (isCorrectAgeFormat) {
//                    int age = Integer.parseInt(ageValue);
//                    boolean isAgePermissible = service.isAgePermissible(age);
//                    if (isAgePermissible) {
//                        if (age != ageOld) {
//                            playerNew.setAge(age);
//                            service.updatePlayerAge(playerId, age);
//                            System.out.println(GamePlayService.getBlueText().format("Age for player '" + playerNew.getNickname() + "' was updated successfully."));
//                        } else {
//                            System.out.println(GamePlayService.getRedText().format("You entered old value, your changes weren't saved."));
//                        }
//                    } else {
//                        System.out.println(GamePlayService.getRedText().format("New age is unacceptable, your changes weren't saved."));
//                    }
//                } else {
//                    System.out.println("Your changes weren't saved.");
//                }
//            } else {
//                System.out.println("Age of player remains the same.");
//            }
//
//            //update nickname
//            System.out.println(GamePlayService.getUnderlinedText().format("\nPlease enter new nickname:"));
//            String nickname = reader.readLine();
//
//            //"0" means without changes
//            if (!nickname.equals("0")) {
//                boolean correctNickname = service.isCorrectNickname(nickname);
//                if (!correctNickname) {
//                    System.out.println("Your changes of nickname weren't saved.");
//                } else {
//                    boolean hasTheSameNickname = service.hasTheSameNickname(nickname);
//                    if (!hasTheSameNickname) {
//                        playerNew.setNickname(nickname);
//                        service.updatePlayerNickname(playerId, nickname);
//                        System.out.println(GamePlayService.getBlueText().format("Nickname for player was updated successfully to the '" + nickname + "'."));
//                    } else {
//                        System.out.println(GamePlayService.getRedText().format("This nickname is already registered. Your changes weren't saved."));
//                    }
//                }
//            } else {
//                System.out.println("Nickname of player remains the same.");
//            }
//
//            //update email
//            System.out.println(GamePlayService.getUnderlinedText().format("\nPlease enter new email:"));
//            String email = reader.readLine();
//
//            //"0" means without changes
//            if (!email.equals("0")) {
//                boolean correctEmail = service.isCorrectEmail(email);
//                if (!correctEmail) {
//                    System.out.println("Your changes of email weren't saved.");
//                } else {
//                    boolean hasTheSameEmail = service.hasTheSameEmail(email);
//                    if (!hasTheSameEmail) {
//                        playerNew.setEmail(email);
//                        service.updatePlayerEmail(playerId, email);
//                        System.out.println(GamePlayService.getBlueText().format("Email for player '" + playerNew.getNickname() + "' was updated successfully."));
//                    } else {
//                        System.out.println(GamePlayService.getRedText().format("This email is already registered. Your changes weren't saved."));
//                    }
//                }
//            } else {
//                System.out.println("Email of player remains the same.");
//            }
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void deletePlayer(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 4. DELETE PLAYER BY ID"));
//        System.out.println();
//        System.out.println(GamePlayService.getUnderlinedText().format("Please enter player ID:"));
//        String playerId = reader.readLine();
//        boolean existSuchPlayerId = service.existPlayerId(playerId);
//        if (existSuchPlayerId) {
//            boolean wasDeletedEverywhere = service.deletePlayer(playerId);
//            if (wasDeletedEverywhere) {
//                System.out.println(GamePlayService.getBlueText().format("\nYour player was successfully deleted."));
//            } else {
//                System.out.println(GamePlayService.getRedText().format("\nYour player can't be deleted in automatic mode. Please contact with support service."));
//            }
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void findAllPlayers() {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 5. FIND ALL PLAYERS"));
//        System.out.println();
//        List<Player> allPlayers = service.getAllPlayers();
//        if (allPlayers.isEmpty()) {
//            System.out.println("There are no players.");
//        } else {
//            System.out.println("Players:");
//            int count = 1;
//            for (Player player : allPlayers) {
//                System.out.println(GamePlayService.getYellowText().format(count + ". " + player.toString()));
//                count++;
//            }
//        }
//    }
//
//    private void createGame(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 6. ENTER GAME"));
//        System.out.println();
//        System.out.print(GamePlayService.getUnderlinedText().format("Please enter name of game."));
//        System.out.println(" ***Pay attention, name of game can't have only digits.");
//        String gameName = "";
//        boolean correctGameName = false;
//        boolean hasTheSameGameName = false;
//        while (true) {
//            gameName = reader.readLine();
//            correctGameName = service.isCorrectGameName(gameName);
//            if (correctGameName) {
//                hasTheSameGameName = service.hasTheSameGameName(gameName);
//                if (!hasTheSameGameName) {
//                    break;
//                } else {
//                    System.out.println(GamePlayService.getRedText().format("This name of game is already registered!"));
//                    System.out.println("Please check and try to enter name of game again");
//                }
//            } else {
//                System.out.println("Please check and try to enter name of game again");
//            }
//        }
//        System.out.print(GamePlayService.getUnderlinedText().format("\nPlease choose type of game: command or single."));
//        System.out.println(" ***For this enter below 'y' for command game or 'n' for single.");
//        String gameType = "";
//        boolean isCommandGame = false;
//        while (true) {
//            gameType = reader.readLine();
//            if (gameType.equalsIgnoreCase("Y")) {
//                isCommandGame = true;
//                break;
//            } else if (gameType.equalsIgnoreCase("N")) {
//                isCommandGame = false;
//                break;
//            } else {
//                System.out.println(GamePlayService.getRedText().format("You entered wrong value! Please write your choice again: 'y' for command game or 'n' for single."));
//            }
//        }
//        Game game = new Game();
//        game.setName(gameName);
//        game.setCommandGame(isCommandGame);
//        service.addGame(game);
//        System.out.println(GamePlayService.getBlueText().format("\nCongratulations! Your game was recorded."));
//
//    }
//
//    private void findGameById(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 7. FIND GAME BY ID"));
//        System.out.println();
//        System.out.println(GamePlayService.getUnderlinedText().format("Please enter game ID:"));
//        String gameId = reader.readLine();
//        Game game = service.getGameByIdOrNull(gameId);
//        if (game != null) {
//            System.out.println(GamePlayService.getYellowText().format(game.toString()));
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void updateGame(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 8. UPDATE GAME BY ID"));
//        System.out.println();
//        System.out.println(GamePlayService.getUnderlinedText().format("Please enter game ID"));
//        String gameId = reader.readLine();
//        Game game = service.getGameByIdOrNull(gameId);
//        if (game != null) {
//            System.out.println("Great, please update details of game '" + game.getName() + "' below.");
//            System.out.println("If you don't want to update some detail, press 0 and you go to the next. Updates for old values will not be saved.");
//            //update name
//            System.out.println(GamePlayService.getUnderlinedText().format("\nPlease enter new name:"));
//            String gameName = reader.readLine();
//            //"0" means without changes
//            if (!gameName.equals("0")) {
//                boolean isCorrectGameName = service.isCorrectGameName(gameName);
//                if (isCorrectGameName) {
//                    boolean hasTheSameGameName = service.hasTheSameGameName(gameName);
//                    if (!hasTheSameGameName) {
//                        game.setName(gameName);
//                        //boolean updateSuccessfull = service.updateGame(game);
//                        service.updateGameName(gameId, gameName);
//                        System.out.println(GamePlayService.getBlueText().format("Name for game was updated to '" + game.getName() + "' successfully."));
//                    } else {
//                        System.out.println(GamePlayService.getRedText().format("This game name is already registered. Your changes weren't saved."));
//                    }
//                }
//            } else {
//                System.out.println("Name of game remains the same.");
//            }
//
//            //update gameType
//            System.out.print(GamePlayService.getUnderlinedText().format("\nPlease enter new command type:"));
//            System.out.println(" enter below 'y' for command game or 'n' for single.");
//            String gameType = reader.readLine();
//            boolean isCommandGame = game.isCommandGame();
//            String gameTypeValue = "";
//            //"0" means without changes
//            if (!gameType.equals("0")) {
//                if (gameType.equalsIgnoreCase("Y") && !isCommandGame) {
//                    isCommandGame = true;
//                    game.setCommandGame(isCommandGame);
//                    if (game.isCommandGame()) {
//                        gameTypeValue = "command game";
//                    } else {
//                        gameTypeValue = "single game";
//                    }
//                    service.updateGameType(gameId, isCommandGame);
//                    System.out.println(GamePlayService.getBlueText().format("Type of game was updated successfully to the '" + gameTypeValue + "'."));
//                } else if (gameType.equalsIgnoreCase("N") && isCommandGame) {
//                    isCommandGame = false;
//                    game.setCommandGame(isCommandGame);
//                    if (game.isCommandGame()) {
//                        gameTypeValue = "command game";
//                    } else {
//                        gameTypeValue = "single game";
//                    }
//                    service.updateGameType(gameId, isCommandGame);
//                    System.out.println(GamePlayService.getBlueText().format("Type of game was updated successfully to the '" + gameTypeValue + "'."));
//                } else {
//                    System.out.println(GamePlayService.getRedText().format("You entered wrong or old value! Your changes weren't saved."));
//                }
//            } else {
//                System.out.println("Type of game remains the same: " + gameTypeValue + ".");
//            }
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void deleteGame(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 9. DELETE GAME BY ID"));
//        System.out.println();
//        System.out.println(GamePlayService.getUnderlinedText().format("Please enter game ID:"));
//        String gameId = reader.readLine();
//        boolean existSuchGameId = service.existGameId(gameId);
//        if (existSuchGameId) {
//            boolean wasDeletedEverywhere = service.deleteGame(gameId);
//            if (wasDeletedEverywhere) {
//                System.out.println(GamePlayService.getBlueText().format("\nYour game was successfully deleted."));
//            } else {
//                System.out.println(GamePlayService.getRedText().format("\nYour game can't be deleted in automatic mode. Please contact with support service."));
//            }
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void findAllGames() {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 10. FIND ALL GAMES"));
//        System.out.println();
//        List<Game> allGames = service.getAllGames();
//        if (allGames.isEmpty()) {
//            System.out.println("There are no games.");
//        } else {
//            System.out.println("Games:");
//            int count = 1;
//            for (Game game : allGames) {
//                System.out.println(GamePlayService.getYellowText().format(count + ". " + game.toString()));
//                count++;
//            }
//        }
//    }
//
//    private void attachGameToPlayer(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 11. ATTACH GAME TO PLAYER"));
//        System.out.println();
//        System.out.println(GamePlayService.getUnderlinedText().format("Please enter game ID"));
//        String gameId = reader.readLine();
//        boolean existSuchGameId = service.existGameId(gameId);
//        if (existSuchGameId) {
//            System.out.println(GamePlayService.getUnderlinedText().format("\nPlease enter player ID"));
//            String playerId = reader.readLine();
//            boolean existSuchPlayerId = service.existPlayerId(playerId);
//            if (existSuchPlayerId) {
//                service.addGameToPlayerInAllDb(gameId, playerId);
//            }
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void findAllPlayersByGame(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 12. FIND ALL PLAYERS BY GAME ID"));
//        System.out.println();
//        System.out.println(GamePlayService.getUnderlinedText().format("Please enter game ID"));
//        String gameId = reader.readLine();
//        boolean existSuchGameId = service.existGameId(gameId);
//        if (existSuchGameId) {
//            List<Player> playersList = service.getPlayersByGame(gameId);
//            if (playersList.isEmpty()) {
//                System.out.println("There are no players in this game");
//            } else {
//                System.out.println("Players:");
//                int count = 1;
//                for (Player player : playersList) {
//                    System.out.println(GamePlayService.getYellowText().format(count + ". " + player.toString()));
//                    count++;
//                }
//            }
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void findAllGamesByPlayer(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 13. FIND ALL GAMES BY PLAYER ID"));
//        System.out.println();
//        System.out.println(GamePlayService.getUnderlinedText().format("Please enter player ID"));
//        String playerId = reader.readLine();
//        boolean existSuchPlayerId = service.existPlayerId(playerId);
//        if (existSuchPlayerId) {
//            List<Game> gamesList = service.getGamesByPlayer(playerId);
//            if (gamesList.isEmpty()) {
//                System.out.println("There are no games for this player");
//            } else {
//                int count = 1;
//                System.out.println("Games:");
//                for (Game game : gamesList) {
//                    System.out.println(GamePlayService.getYellowText().format(count + ". " + game.toString()));
//                    count++;
//                }
//            }
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }
//
//    private void deleteGameFromPlayer(BufferedReader reader) throws IOException {
//        System.out.println(GamePlayService.getReverse().format("\nMenu 14. DELETE GAME FROM PLAYER BY ID"));
//        System.out.println();
//        System.out.println(GamePlayService.getUnderlinedText().format("Please enter game ID"));
//        String gameId = reader.readLine();
//        boolean existSuchGameId = service.existGameId(gameId);
//        if (existSuchGameId) {
//            System.out.println(GamePlayService.getUnderlinedText().format("\nPlease enter player ID"));
//            String playerId = reader.readLine();
//            boolean existSuchPlayerId = service.existPlayerId(playerId);
//            if (existSuchPlayerId) {
//                service.deleteGameFromPlayerInAllDb(gameId, playerId);
//            } else {
//                System.out.println("Please check and try this menu again.");
//            }
//        } else {
//            System.out.println("Please check and try this menu again.");
//        }
//    }

    private void stop() {
        System.out.println("\nThe application is finished.\n");
        System.exit(0);
    }
}
