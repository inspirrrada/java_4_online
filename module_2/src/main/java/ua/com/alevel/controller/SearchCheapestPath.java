package ua.com.alevel.controller;

import ua.com.alevel.service.CheapestPathAlgorithm;
import ua.com.alevel.utils.ColorUtils;
import ua.com.alevel.utils.Graph;
import ua.com.alevel.utils.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class SearchCheapestPath {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public void start() {
        showDetails();
        calculations();
    }

    public void showDetails() {
        System.out.println();
        System.out.println(ColorUtils.REVERSE.format("WELCOME TO THE APP FOR FINDING THE MOST PROFITABLE PATH"));
        System.out.println();
        System.out.println("==> All input data are taken from file 'input.txt' in the folder 'module_2'");
        System.out.println("==> If you want to change input data, please edit the file.");
        System.out.println("==> All results you can view in the file 'output.txt' in the folder 'module_2'");
    }

    public void calculations() {
        Graph graph = new Graph();
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             FileWriter writer = new FileWriter(OUTPUT_FILE)) {
            int count = 1;
            int cityQty = Integer.parseInt(reader.readLine());
            System.out.println();
            System.out.println(ColorUtils.YELLOW_TEXT.format("General quantity of cities is " + cityQty));
            System.out.println(ColorUtils.YELLOW_TEXT.format("Please see detailed information about every city below:"));
            for (int i = 1; i <= cityQty; i++) {
                String city = reader.readLine();
                Node node = new Node(city, i);
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("City " + count + ": " + city));
                int neighborsQty = Integer.parseInt(reader.readLine());
                System.out.println("Neighbors qty: " + neighborsQty);
                for (int j = 0; j < neighborsQty; j++) {
                    String line = reader.readLine();
                    String[] lineArray = line.split(" ");
                    node.addDestinationCity(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]));
                    System.out.println("Neighbor number: " + Integer.parseInt(lineArray[0]) + ". Cost between cities: " + Integer.parseInt(lineArray[1]));
                }
                graph.addNode(node);
                count++;
            }
            int cheapestPathRequests = Integer.parseInt(reader.readLine());
            System.out.println();
            System.out.println(ColorUtils.YELLOW_TEXT.format("Need to find " + cheapestPathRequests + " cheapest paths between next cities, result:"));
            System.out.println();
            for (int i = 0; i < cheapestPathRequests; i++) {
                graph.getNodesSet().stream()
                        .forEach(v -> v.setPathCost(Node.getMaxCost())); //set start values of costs
                String line = reader.readLine();
                String[] lineArray = line.split(" ");
                String startCity = lineArray[0];
                String destinationCity = lineArray[1];
                Node startNode = graph.getNodesSet().stream()
                        .filter(v -> v.getName().equals(startCity)).findFirst().get();
                CheapestPathAlgorithm.calculateProfitableValue(graph, startNode);
                int cheapestCost = graph.getNodesSet().stream()
                        .filter(v -> v.getName().equals(destinationCity)).findFirst().get().getPathCost();
                System.out.println(ColorUtils.BLUE_TEXT.format("For pair " + startCity + " - " + destinationCity + " the cheapest cost is " + cheapestCost));
                writer.write(cheapestCost + "\n");
                writer.flush();
            }
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
