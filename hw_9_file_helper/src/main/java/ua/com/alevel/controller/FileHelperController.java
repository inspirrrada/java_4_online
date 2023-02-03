package ua.com.alevel.controller;

import ua.com.alevel.service.FileHelperService;
import ua.com.alevel.utils.ColorUtils;
import java.io.*;
import java.util.HashMap;


public class FileHelperController {
    private FileHelperService service = new FileHelperService();

    public void start() {
        System.out.println();
        System.out.println("WELCOME TO THE FILE HELPER APP!");
        System.out.println();
        System.out.println("==> Application works in home directory of your device: " + service.getHomeDirectory());
        System.out.println("==> For select home directory, just enter in terminal 'h' instead of directory name.");
        System.out.println("==> Pay attention, all values has to be case-sensitive. ");
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
            case "6" -> transferFile(reader);
            case "7" -> transferDir(reader);
            case "8" -> findFile(reader);
            case "9" -> findDir(reader);
            case "10" -> searchText(reader);
            case "11" -> stop();
            default -> System.out.println(ColorUtils.getRedText().format("Wrong value! Select menu again."));
        }
        menu();
    }

    private String getExactDirPath(String dirName, BufferedReader reader) throws IOException {
        //тут змінна path зроблена через масив з 1 елемента, бо тільки таку змінну можна використати у лямбда-функції map.forEach((k,v) -> ...,
        //в іншому випадку просить final змінну і дає помилку:(
        String[] path = new String[1];
        if (dirName.equalsIgnoreCase("h")) {
            path[0] = service.getHomeDirectory().getAbsolutePath();
        } else {
            HashMap<String, String> map = service.searchDirInSystem(dirName);
            if (map.size() > 1) {
                System.out.println();
                System.out.println("We found few directories in system with such name.");
                System.out.println(ColorUtils.getYellowText().format("---------------------------------------------------"));
                map.forEach((k, v) -> System.out.println(ColorUtils.getYellowText().format(k)));
                System.out.println(ColorUtils.getYellowText().format("---------------------------------------------------"));
                System.out.println();
                System.out.println("Please look details and enter the path of directory you need:");
                while (true) {
                    path[0] = reader.readLine();
                    int[] count = new int[1];
                    map.forEach((k, v) -> {
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
        }
        return path[0];
    }

    private void viewDirContent(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 1. VIEW ALL DIRS/FILES INSIDE"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory:"));
        System.out.println("For select start system directory, enter in terminal 'h'");
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
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory where you want to create new file: "));
        System.out.println("For select start system directory, enter in terminal 'h'");
        String dirName = reader.readLine();
        service.searchDirInSystem(dirName);
        String dirPath = getExactDirPath(dirName, reader);
        if (dirPath != null) {
            System.out.println();
            System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of new file with extension"));
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
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory where you want to create new folder: "));
        System.out.println("For select start system directory, enter in terminal 'h'");
        String dirName = reader.readLine();
        service.searchDirInSystem(dirName);
        String dirPath = getExactDirPath(dirName, reader);
        if (dirPath != null) {
            System.out.println();
            System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of new folder"));
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
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory where you want to delete file:"));
        System.out.println("For select start system directory, enter in terminal 'h'");
        String dirName = reader.readLine();
        service.searchDirInSystem(dirName);
        String dirPath = getExactDirPath(dirName, reader);
        if (dirPath != null) {
            System.out.println();
            System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of file with extension:"));
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
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory where you want to delete folder:"));
        System.out.println("For select start system directory, enter in terminal 'h'");
        String dirName = reader.readLine();
        service.searchDirInSystem(dirName);
        String dirPath = getExactDirPath(dirName, reader);
        if (dirPath != null) {
            System.out.println();
            System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of folder:"));
            String folderName = reader.readLine();
            System.out.println();
            boolean isExistFolder = service.isExist(dirPath, folderName);
            if (isExistFolder) {
                boolean hasContentInside = service.hasContentInside(dirPath, folderName);
                if (hasContentInside) {
                    System.out.println(ColorUtils.getOrangeText().format("Folder '" + folderName + "' is not empty. Are you sure you want to delete this folder?"));
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

    private void transferFile(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 6. MOVE FILE"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory, from where you want to move the file:"));
        System.out.println("For select start system directory, enter in terminal 'h'");
        String dirFrom = reader.readLine();
        service.searchDirInSystem(dirFrom);
        String dirFromPath = getExactDirPath(dirFrom, reader);
        if (dirFromPath != null) {
            System.out.println();
            System.out.println(ColorUtils.getUnderlinedText().format("Please enter file name with extension:"));
            String fileName = reader.readLine();
            System.out.println();
            boolean existFileThere = service.isExist(dirFromPath, fileName);
            if (existFileThere) {
                System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory where you want to move the file:"));
                System.out.println("For select start system directory, enter in terminal 'h'");
                String dirTo = reader.readLine();
                System.out.println();
                service.searchDirInSystem(dirTo);
                String dirToPath = getExactDirPath(dirTo, reader);
                if (dirToPath != null) {
                    boolean alreadyExistDuplicate = service.isExist(dirToPath, fileName);
                    if (alreadyExistDuplicate) {
                        System.out.println(ColorUtils.getOrangeText().format("In directory '" + dirToPath + "' there is already file with name '" + fileName + "'."));
                        System.out.println(ColorUtils.getBoldext().format("***If continue moving, old file will be overwritten, all data inside will be removed. Do you still want to continue?"));
                        System.out.println(ColorUtils.getItalicText().format("For confirmation please enter 'y'. Other answer will back you to the main menu."));
                        String answer = reader.readLine();
                        if (answer.equalsIgnoreCase("y")) {
                            boolean wasDeletedDuplicate = service.deleteFile(dirToPath, fileName);
                            if (wasDeletedDuplicate) {
                                boolean successfullyMoved = service.moveFile(dirFromPath, fileName, dirToPath);
                                if (successfullyMoved) {
                                    System.out.println(ColorUtils.getCyanText().format("Congratulations! File '" + fileName + "' was moved successfully."));
                                } else {
                                    System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                                }
                            } else {
                                System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                            }
                        }
                    } else {
                        boolean successfullyMoved = service.moveFile(dirFromPath, fileName, dirToPath);
                        if (successfullyMoved) {
                            System.out.println();
                            System.out.println(ColorUtils.getCyanText().format("Congratulations! File '" + fileName + "' was moved successfully."));
                        } else {
                            System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                        }
                    }
                }
            } else {
                System.out.println(ColorUtils.getRedText().format("There is no one file with such name in this directory. Please check and try this menu again."));
            }
        }
    }

    private void transferDir(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 7. MOVE FOLDER"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory, from where you want to move the folder:"));
        System.out.println("For select start system directory, enter in terminal 'h'");
        String dirFrom = reader.readLine();
        service.searchDirInSystem(dirFrom);
        String dirFromPath = getExactDirPath(dirFrom, reader);
        if (dirFromPath != null) {
            System.out.println();
            System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of folder you want to move:"));
            String folderName = reader.readLine();
            System.out.println();
            service.searchDirInSystem(folderName);
            boolean existFolderThere = service.isExist(dirFromPath, folderName);
            if (existFolderThere) {
                System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory, where you want to move the folder:"));
                System.out.println("For select start system directory, enter in terminal 'h'");
                String dirTo = reader.readLine();
                System.out.println();
                service.searchDirInSystem(dirTo);
                String dirToPath = getExactDirPath(dirTo, reader);
                if (dirToPath != null) {
                    boolean alreadyExistDuplicate = service.isExist(dirToPath, folderName);
                    if (alreadyExistDuplicate) {
                        System.out.println(ColorUtils.getOrangeText().format("In directory '" + dirToPath + "' there is already folder with name '" + folderName + "'."));
                        System.out.println(ColorUtils.getBoldext().format("***If continue moving, old folder will be overwritten, all data inside will be removed. Do you still want to continue?"));
                        System.out.println(ColorUtils.getItalicText().format("For confirmation please enter 'y'. Other answer will back you to the main menu."));
                        String answer = reader.readLine();
                        if (answer.equalsIgnoreCase("y")) {
                            boolean wasDeletedDuplicate = service.deleteDir(dirToPath, folderName);
                            if (wasDeletedDuplicate) {
                                boolean successfullyMoved = service.moveFile(dirFromPath, folderName, dirToPath);
                                if (successfullyMoved) {
                                    System.out.println();
                                    System.out.println(ColorUtils.getCyanText().format("Congratulations! Folder '" + folderName + "' was moved successfully."));
                                } else {
                                    System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                                }
                            } else {
                                System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                            }
                        }
                    } else {
                        boolean successfullyMoved = service.moveFile(dirFromPath, folderName, dirToPath);
                        if (successfullyMoved) {
                            System.out.println(ColorUtils.getCyanText().format("Congratulations! Folder '" + folderName + "' was moved successfully."));
                        } else {
                            System.out.println(ColorUtils.getRedText().format("Something went wrong:( Please contact with our client support."));
                        }
                    }
                }
            } else {
                System.out.println(ColorUtils.getRedText().format("There is no one folder with such name in this directory. Please check and try this menu again."));
            }
        }
    }

    private void findFile(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 8. FIND FILE"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of file you want to find:"));
        String searchFile = reader.readLine();
        System.out.println();
        HashMap<String, String> resultMap = service.searchFile(searchFile);
        if (resultMap.size() > 1) {
            System.out.println("There are few files in system with such name:");
            resultMap.forEach((k, v) -> System.out.println(ColorUtils.getYellowText().format(k)));
        } else if (resultMap.size() == 1) {
            System.out.println("Search file you can find here: ");
            resultMap.forEach((k, v) -> System.out.println(ColorUtils.getYellowText().format(k)));
        } else {
            System.out.println(ColorUtils.getRedText().format("We can't find such file in system. Please check the name and try this menu again."));
        }
    }

    private void findDir(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 9. FIND DIR"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory you want to find:"));
        String searchDir = reader.readLine();
        System.out.println();
        HashMap<String, String> resultMap = service.searchDirInSystem(searchDir);
        if (resultMap.size() > 1) {
            System.out.println("There are few directories in system with such name:");
            resultMap.forEach((k, v) -> System.out.println(ColorUtils.getYellowText().format(k)));
        } else if (resultMap.size() == 1) {
            System.out.println("Search directory you can find here: ");
            resultMap.forEach((k, v) -> System.out.println(ColorUtils.getYellowText().format(k)));
        } else {
            System.out.println("We can't find such directory in system. Please check the name and try this menu again.");
        }
    }

    public void searchText(BufferedReader reader) throws IOException {
        System.out.println(ColorUtils.getReverse().format("\nMenu 10. FIND TEXT"));
        System.out.println();
        System.out.println(ColorUtils.getUnderlinedText().format("Please enter name of directory:"));
        System.out.println("For select start system directory, enter in terminal 'h'");
        System.out.println();
        String dirName = reader.readLine();
        service.searchDirInSystem(dirName);
        String dirPath = getExactDirPath(dirName, reader);
        if (dirPath != null) {
            System.out.println();
            System.out.println(ColorUtils.getUnderlinedText().format("Please enter text for search:"));
            String searchText = reader.readLine();
            System.out.println(ColorUtils.getSlowBlink().format("...Searching..."));
            HashMap<String, String> resultMap = service.searchTextInDir(dirPath, searchText);
            if (resultMap.size() > 0) {
                System.out.println();
                System.out.println("Text was found here:");
                resultMap.forEach((k, v) -> System.out.println(ColorUtils.getYellowText().format(k)));
            } else {
                System.out.println();
                System.out.println(ColorUtils.getRedText().format("Text wasn't found anywhere in chosen directory."));
            }
        }
    }

    private void stop() {
        System.out.println("\nThe application is finished.\n");
        System.exit(0);
    }
}