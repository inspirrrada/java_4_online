package ua.com.alevel.service;

import ua.com.alevel.utils.ColorUtils;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class FileHelperService {

    public File getHomeDirectory() {
        return FileSystemView.getFileSystemView().getHomeDirectory();
    }

    public void observe(String dirPath) {
        File file = new File(dirPath);
        readDir(file);
    }

    public String getDirPathByName(String dirName, HashMap<String, String> map) {
        String[] dirPath = new String[1];
        map.forEach((k, v) -> {
            if (v.equals(dirName)) {
                dirPath[0] = k;
            }
        });
        return dirPath[0];
    }

    public void readDir(File dir) {
        System.out.println(ColorUtils.getMagentaText().format(" dir: " + dir.getAbsolutePath()));
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                readDir(file);
            } else {
                System.out.println(ColorUtils.getBlueText().format("file: " + file.getAbsolutePath()));
            }
        }
    }

    public HashMap<String, String> searchDir(String dirName, File searchFrom) {
        HashMap<String, String> allDirsMap = new HashMap<>();
        HashMap<String, String> resultMap = new HashMap();
        readAllDirs(searchFrom, allDirsMap);
        allDirsMap.forEach((k, v) -> {
            if (v.equals(dirName)) {
                resultMap.put(k, v);
            }
        });
        return resultMap;
    }

    public HashMap<String, String> searchDirInSystem(String dirName) {
        File homeDir = FileSystemView.getFileSystemView().getHomeDirectory();
        HashMap<String, String> resultMap = searchDir(dirName, homeDir);
        return resultMap;
    }

    public HashMap<String, String> searchFile(String fileName) {
        File homeDir = FileSystemView.getFileSystemView().getHomeDirectory();
        HashMap<String, String> allFilesMap = new HashMap<>();
        HashMap resultMap = new HashMap();
        readAllFiles(homeDir, allFilesMap);
        allFilesMap.forEach((k, v) -> {
            if (v.equals(fileName)) {
                resultMap.put(k, v);
            }
        });
        return resultMap;
    }

    public void readAllDirs(File homeDir, HashMap<String, String> allDirsMap) {
        File[] dirs = homeDir.listFiles();
        for (File file : dirs) {
            if (file.isDirectory()) {
                allDirsMap.put(file.getAbsolutePath(), file.getName());
                readAllDirs(file, allDirsMap);
            }
        }
    }

    public void readAllFiles(File homeDir, HashMap<String, String> allFilesMap) {
        File[] files = homeDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                readAllFiles(file, allFilesMap);
            } else {
                allFilesMap.put(file.getAbsolutePath(), file.getName());
            }
        }
    }

    public void readAllDirsAndFiles(File homeDir, TreeMap<String, String> allDirsAndFilesMap) {
        File[] dirs = homeDir.listFiles();
        for (File file : dirs) {
            if (file.isDirectory()) {
                allDirsAndFilesMap.put(file.getAbsolutePath(), file.getName());
                readAllDirsAndFiles(file, allDirsAndFilesMap);
            } else {
                allDirsAndFilesMap.put(file.getAbsolutePath(), file.getName());
            }
        }
    }

    public TreeMap<String, String> getDirContent(File dir) {
        TreeMap<String, String> allDirsAndFilesMap = new TreeMap<>(Collections.reverseOrder());
        readAllDirsAndFiles(dir, allDirsAndFilesMap);
        return allDirsAndFilesMap;
    }

    public boolean hasContentInside(String parentDirPath, String dirForDelete) {
        String path = parentDirPath + File.separator + dirForDelete;
        File folder = new File(path);
        TreeMap<String, String> allDirsAndFilesMap = new TreeMap<>(Collections.reverseOrder());
        readAllDirsAndFiles(folder, allDirsAndFilesMap);
        return allDirsAndFilesMap.size() > 0;
    }

    public boolean deleteDir(String parentDirPath, String dirForDelete) {
        String path = parentDirPath + File.separator + dirForDelete;
        File folder = new File(path);
        TreeMap<String, String> dirContentMap = getDirContent(folder);
        dirContentMap.forEach((k, v) -> {
            File currentFile = new File(k);
            currentFile.delete();
        });
        return folder.delete();
    }

    public boolean deleteFile(String dirPath, String fileName) {
        String path = dirPath + File.separator + fileName;
        File file = new File(path);
        return file.delete();
    }

    public boolean createFile(String dirPath, String fileName) throws IOException {
        String filePath = dirPath + File.separator + fileName;
        File file = new File(filePath);
        return file.createNewFile();
    }

    public boolean isExist(String dirPath, String fileName) {
        String filePath = dirPath + File.separator + fileName;
        File file = new File(filePath);
        return file.exists();
    }

    public boolean createDir(String dirPath, String newDirName) {
        String path = dirPath + File.separator + newDirName;
        File newDir = new File(path);
        return newDir.mkdir();
    }

    public boolean moveFile(String oldDirPath, String fileName, String newDirPath) {
        String oldPath = oldDirPath + File.separator + fileName;
        File file = new File(oldPath);
        String newPath = newDirPath + File.separator + fileName;
        return file.renameTo(new File(newPath));
    }

    public HashMap<String, String> searchTextInDir(String dirPath, String text) {
        HashMap<String, String> allFilesMap = new HashMap<>();
        HashMap<String, String> resultMap = new HashMap();
        File dir = new File(dirPath);
        readAllFiles(dir, allFilesMap);
        allFilesMap.forEach((k, v) -> {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(k));
                Stream<String> lines = reader.lines();
                boolean containsText = lines.anyMatch(line -> line.toLowerCase().contains(text.toLowerCase()));
                if (containsText) {
                    resultMap.put(k, v);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        return resultMap;
    }
}
