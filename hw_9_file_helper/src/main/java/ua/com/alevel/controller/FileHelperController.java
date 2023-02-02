package ua.com.alevel.controller;

import ua.com.alevel.service.FileHelperService;
import ua.com.alevel.utils.ColorUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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
            System.out.println(ColorUtils.getRedText().format("Oops...Something went wrong:( Please try again later."));
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
            case "1" -> viewDirContent(reader);
            case "2" -> createNewFile(reader);
            case "3" -> createNewDir(reader);
            case "4" -> deleteFile(reader);
            case "5" -> deleteDir(reader);
//            case "6" -> transferFileFromOneDirToAnother(reader);
//            case "7" -> transferFolderFromOneDirToAnother(reader);
//            case "8" -> findFile(reader);
//            case "9" -> findDir(reader);
//            case "10" -> searchText(reader);
            case "11" -> stop();
            default -> System.out.println(ColorUtils.getRedText().format("Wrong value! Select menu again."));
        }
        menu();
    }

    private String getExactDirPath(String dirName, BufferedReader reader) throws IOException {
        HashMap<String, String> map = service.searchDirInSystem(dirName);
        String[] path = new String[1];
        if (map.size() > 1) {
            System.out.println();
            System.out.println("We found few directories in system with such name.");
            System.out.println(ColorUtils.getYellowText().format("---------------------------------------------------"));
            map.forEach((k,v) -> System.out.println(ColorUtils.getYellowText().format(k)));
            System.out.println(ColorUtils.getYellowText().format("---------------------------------------------------"));
            System.out.println();
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
                    System.out.println(ColorUtils.getRedText().format("Wrong value! Please choose and enter the path of directory you entered earlier."));
                } else if (count[0] == 1) {
                    break;
                }
            }
        } else if (map.size() == 0) {
            System.out.println(ColorUtils.getRedText().format("We can't find such directory in system! Please check the name and try this menu again."));
            return null;
        } else {
            path[0] = service.getDirPathByName(dirName, map);
        }
        return path[0];
    }

    private void viewDirContent(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 1. VIEW ALL DIRS/FILES INSIDE"));
        System.out.println();
        System.out.println("Please enter name of directory:");
        String dirName = reader.readLine();
        service.searchDirInSystem(dirName);
        String dirPath = getExactDirPath(dirName, reader);
        if (dirPath != null) {
            System.out.println();
            System.out.println("RESULT:");
            service.observe(dirPath);
        }
    }

    public void createNewFile(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 2. CREATE NEW FILE"));
        System.out.println();
        System.out.println("Please enter name of directory where you want to create new file: ");
        String dirName = reader.readLine();
        service.searchDirInSystem(dirName);
        String dirPath = getExactDirPath(dirName, reader);
        if (dirPath != null) {
            System.out.println();
            System.out.println("Please enter name of new file with extension");
            String fileName = reader.readLine();
            System.out.println();
            boolean wasCreatedFile = service.createFile(dirPath, fileName);
            if (wasCreatedFile) {
                System.out.println(ColorUtils.getCyanText().format("Congratulations! The file '" + fileName + "' was created in directory '" + dirPath + "'"));
            } else {
                boolean isAlreadyExist = service.isExist(dirPath, fileName);
                if (isAlreadyExist) {
                    System.out.println(ColorUtils.getRedText().format("File with such name already exists in this directory!"));
                } else {
                    System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                }
            }
        }
    }

    private void createNewDir(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 3. CREATE NEW FOLDER"));
        System.out.println();
        System.out.println("Please enter name of directory where you want to create new folder: ");
        String dirName = reader.readLine();
        service.searchDirInSystem(dirName);
        String dirPath = getExactDirPath(dirName, reader);
        if (dirPath != null) {
            System.out.println();
            System.out.println("Please enter name of new folder");
            String newDirName = reader.readLine();
            System.out.println();
            boolean wasCreatedDir = service.createDir(dirPath, newDirName);
            if (wasCreatedDir) {
                System.out.println(ColorUtils.getCyanText().format("Congratulations! The folder '" + newDirName + "' was created in directory '" + dirPath + "'"));
            } else {
                boolean isAlreadyExist = service.isExist(dirPath, newDirName);
                if (isAlreadyExist) {
                    System.out.println(ColorUtils.getRedText().format("Folder with such name already exists in this directory!"));
                } else {
                    System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                }
            }
        }
    }

    private void deleteFile(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 4. DELETE FILE"));
        System.out.println();
        System.out.println("Please enter name of directory where you want to delete file:");
        String dirName = reader.readLine();
        service.searchDirInSystem(dirName);
        String dirPath = getExactDirPath(dirName, reader);
        if (dirPath != null) {
            System.out.println();
            System.out.println("Please enter name of file with extension:");
            String fileName = reader.readLine();
            System.out.println();
            boolean isExistFile = service.isExist(dirPath, fileName);
            if (isExistFile) {
                boolean wasDeletedFile = service.deleteFile(dirPath, fileName);
                if (wasDeletedFile) {
                    System.out.println(ColorUtils.getCyanText().format("Congratulations! The file '" + fileName + "' was deleted from directory '" + dirPath + "'"));
                } else {
                    System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                }
            } else {
                System.out.println(ColorUtils.getRedText().format("There are no files with such name in this directory. Please check and try this menu again."));
            }
        }
    }

    private void deleteDir(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 5. DELETE FOLDER"));
        System.out.println();
        System.out.println("Please enter name of directory where you want to delete folder:");
        String dirName = reader.readLine();
        service.searchDirInSystem(dirName);
        String dirPath = getExactDirPath(dirName, reader);
        if (dirPath != null) {
            System.out.println();
            System.out.println("Please enter name of folder:");
            String folderName = reader.readLine();
            System.out.println();
            boolean isExistFolder = service.isExist(dirPath, folderName);
            if (isExistFolder) {
                boolean hasContentInside = service.hasContentInside(dirPath, folderName);
                if (hasContentInside) {
                    System.out.println("Folder '" + folderName + "' is not empty. Are you sure you want to delete this folder?");
                    System.out.println(ColorUtils.getItalicText().format("For confirmation please enter 'y'. Other answer will back you to the main menu."));
                    String answer = reader.readLine();
                    if (answer.equalsIgnoreCase("y")) {
                        boolean wasDeletedFolder = service.deleteDir(dirPath, folderName);
                        if (wasDeletedFolder) {
                            System.out.println(ColorUtils.getCyanText().format("Congratulations! The folder '" + folderName + "' was deleted from directory '" + dirPath + "'"));
                        } else {
                            System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                        }
                    }
                } else {
                    boolean wasDeletedFolder = service.deleteDir(dirPath, folderName);
                    if (wasDeletedFolder) {
                        System.out.println(ColorUtils.getCyanText().format("Congratulations! The folder '" + folderName + "' was deleted from directory '" + dirPath + "'"));
                    } else {
                        System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                    }
                }
            } else {
                System.out.println(ColorUtils.getRedText().format("There are no folders with such name in this directory. Please check and try this menu again."));
            }
        }
    }

//    private void transferFileFromOneDirToAnother(BufferedReader reader) throws IOException {
//        System.out.println(ColorUtils.getReverse().format("\nMenu 6. MOVE FILE"));
//        System.out.println();
//        System.out.println("Please enter name of directory, from which you want to move the file:");
//        String dirFrom = reader.readLine();
//        HashMap<String, String> mapFrom = service.getSearchDirInfo(dirFrom);
//        String[] pathFrom = new String[1];
//        if (mapFrom.size() > 1) {
//            System.out.println("We found few directories in system with such name.");
//            mapFrom.forEach((k,v) -> System.out.println(k));
//            System.out.println("Please look details and enter the path of directory you need:");
//            while(true) {
//                pathFrom[0] = reader.readLine();
//                int[] count = new int[1];
//                mapFrom.forEach((k,v) -> {
//                    if (pathFrom[0].equals(k)) {
//                        count[0]++;
//                    }
//                });
//                if (count[0] == 0) {
//                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
//                } else if (count[0] == 1) {
//                    break;
//                }
//            }
//        } else if (mapFrom.size() == 0) {
//            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
//        } else {
//            pathFrom[0] = service.getDirPathByName(dirFrom);
//        }
//        System.out.println("Please enter file name with extension:");
//        String fileName = reader.readLine();
//        service.deleteFile(pathFrom[0], fileName);
//        System.out.println("Please enter name of directory, where you want to move the file:");
//        String dirTo = reader.readLine();
//        HashMap<String, String> mapTo = service.getSearchDirInfo(dirTo);
//        String[] pathTo = new String[1];
//        if (mapTo.size() > 1) {
//            System.out.println("We found few directories in system with such name.");
//            mapTo.forEach((k,v) -> System.out.println(k));
//            System.out.println("Please look details and enter the path of directory you need:");
//            while(true) {
//                pathTo[0] = reader.readLine();
//                int[] count = new int[1];
//                mapTo.forEach((k,v) -> {
//                    if (pathTo[0].equals(k)) {
//                        count[0]++;
//                    }
//                });
//                if (count[0] == 0) {
//                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
//                } else if (count[0] == 1) {
//                    break;
//                }
//            }
//        } else if (mapTo.size() == 0) {
//            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
//        } else {
//            pathTo[0] = service.getDirPathByName(dirTo);
//        }
//        service.moveFile(pathFrom[0], fileName, pathTo[0]);
//    }
//
//    private void transferFolderFromOneDirToAnother(BufferedReader reader) throws IOException {
//        System.out.println(ColorUtils.getReverse().format("\nMenu 7. MOVE DIR"));
//        System.out.println();
//        System.out.println("Please enter name of directory, from which you want to move the folder:");
//        String dirFrom = reader.readLine();
//        HashMap<String, String> mapFrom = service.getSearchDirInfo(dirFrom);
//        String[] pathFrom = new String[1];
//        if (mapFrom.size() > 1) {
//            System.out.println("We found few directories in system with such name.");
//            mapFrom.forEach((k,v) -> System.out.println(k));
//            System.out.println("Please look details and enter the path of directory you need:");
//            while(true) {
//                pathFrom[0] = reader.readLine();
//                int[] count = new int[1];
//                mapFrom.forEach((k,v) -> {
//                    if (pathFrom[0].equals(k)) {
//                        count[0]++;
//                    }
//                });
//                if (count[0] == 0) {
//                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
//                } else if (count[0] == 1) {
//                    break;
//                }
//            }
//        } else if (mapFrom.size() == 0) {
//            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
//        } else {
//            pathFrom[0] = service.getDirPathByName(dirFrom);
//        }
//        System.out.println("Please enter folder name you want to move:");
//        String folderName = reader.readLine();
//        //service.deleteFile(pathFrom[0], fileName);
//        System.out.println("Please enter name of directory, where you want to move the folder:");
//        String dirTo = reader.readLine();
//        HashMap<String, String> mapTo = service.getSearchDirInfo(dirTo);
//        String[] pathTo = new String[1];
//        if (mapTo.size() > 1) {
//            System.out.println("We found few directories in system with such name.");
//            mapTo.forEach((k,v) -> System.out.println(k));
//            System.out.println("Please look details and enter the path of directory you need:");
//            while(true) {
//                pathTo[0] = reader.readLine();
//                int[] count = new int[1];
//                mapTo.forEach((k,v) -> {
//                    if (pathTo[0].equals(k)) {
//                        count[0]++;
//                    }
//                });
//                if (count[0] == 0) {
//                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
//                } else if (count[0] == 1) {
//                    break;
//                }
//            }
//        } else if (mapTo.size() == 0) {
//            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
//        } else {
//            pathTo[0] = service.getDirPathByName(dirTo);
//        }
//        service.moveDir(pathFrom[0], folderName, pathTo[0]);
//    }
//
//    private void findFile(BufferedReader reader) throws IOException {
//        System.out.println(ColorUtils.getReverse().format("\nMenu 8. FIND FILE"));
//        System.out.println();
//        System.out.println("Please enter name of file:");
//        System.out.println("***Pay attention, the search is case-sensitive.");
//        String searchFile = reader.readLine();
//        HashMap resultMap = service.getSearchFileInfo(searchFile);
//        if (resultMap.size() > 1) {
//            System.out.println("There are few files in system with such name:");
//            resultMap.forEach((k,v) -> System.out.println(k));
//        } else if (resultMap.size() == 1) {
//            System.out.println("Search file you can find here: " );
//            resultMap.forEach((k,v) -> System.out.println(k));
//        } else {
//            System.out.println("We can't find such file in system. Please check the name and try this menu again.");
//        }
//    }
//
//    private void findDir(BufferedReader reader) throws IOException {
//        System.out.println(ColorUtils.getReverse().format("\nMenu 9. FIND DIR"));
//        System.out.println();
//        System.out.println("Please enter name of directory:");
//        System.out.println("***Pay attention, the search is case-sensitive.");
//        String searchDir = reader.readLine();
//        HashMap resultMap = service.getSearchDirInfo(searchDir);
//        if (resultMap.size() > 1) {
//            System.out.println("There are few directories in system with such name:");
//            resultMap.forEach((k,v) -> System.out.println(k));
//        } else if (resultMap.size() == 1) {
//            System.out.println("Search directory you can find here: " );
//            resultMap.forEach((k,v) -> System.out.println(k));
//        } else {
//            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
//        }
//    }
//
//    public void searchText(BufferedReader reader) throws IOException {
//        System.out.println(ColorUtils.getReverse().format("\nMenu 10. FIND TEXT"));
//        System.out.println();
//        System.out.println("Please enter name of directory:");
//        String dirName = reader.readLine();
//        HashMap<String, String> map = service.getSearchDirInfo(dirName);
//        String[] path = new String[1];
//        if (map.size() > 1) {
//            System.out.println("We found few directories in system with such name.");
//            map.forEach((k,v) -> System.out.println(k));
//            System.out.println("Please look details and enter the path of directory you need:");
//            while(true) {
//                path[0] = reader.readLine();
//                int[] count = new int[1];
//                map.forEach((k,v) -> {
//                    if (path[0].equals(k)) {
//                        count[0]++;
//                    }
//                });
//                if (count[0] == 0) {
//                    System.out.println("Wrong value! Please choose and enter the path of directory you entered earlier.");
//                } else if (count[0] == 1) {
//                    break;
//                }
//            }
//        } else if (map.size() == 0) {
//            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
//        } else {
//            path[0] = service.getDirPathByName(dirName);
//        }
//        System.out.println("Please enter text for search:");
//        String searchText = reader.readLine();
//        HashMap resultMap = service.searchTextInDir(path[0], searchText);
//        System.out.println("Text was found here:");
//        resultMap.forEach((k,v) -> System.out.println(k));
//
//    }

    private void stop() {
        System.out.println("\nThe application is finished.\n");
        System.exit(0);
    }
}
