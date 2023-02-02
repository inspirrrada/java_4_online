package ua.com.alevel;

import ua.com.alevel.controller.FileHelperController;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new FileHelperController().start();
//        System.out.println(Arrays.toString(File.listRoots()));
//        System.out.println("FileSystemView.getFileSystemView().getRoots()[0]: " + FileSystemView.getFileSystemView().getRoots()[0]);
//        System.out.println("FileSystemView.getFileSystemView().getHomeDirectory(): " + FileSystemView.getFileSystemView().getHomeDirectory());
//        System.out.println("FileSystemView.getFileSystemView().getDefaultDirectory(): " + FileSystemView.getFileSystemView().getDefaultDirectory());
//        System.out.println("FileSystemView.getFileSystemView().getParentDirectory(): " + FileSystemView.getFileSystemView().getParentDirectory(FileSystemView.getFileSystemView().getHomeDirectory()));
    }
}