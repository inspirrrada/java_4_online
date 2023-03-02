package ua.com.alevel.utils;

import java.util.*;

public class Node {
    private String name;
    private int index;
    private List<Node> cheapestPathList = new ArrayList<>();
    private static Integer MAX_COST = 200_000;
    private Integer pathCost = MAX_COST;
    private Map<Integer, Integer> neighborNodesMap = new HashMap<>();

    public Node(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public void addDestinationCity(int destinationCityIndex, int pathCost) {
        neighborNodesMap.put(destinationCityIndex, pathCost);
    }

    public String getName() {
        return name;
    }

    public List<Node> getCheapestPath() {
        return cheapestPathList;
    }

    public void setCheapestPath(List<Node> cheapestPathList) {
        this.cheapestPathList = cheapestPathList;
    }

    public Integer getPathCost() {
        return pathCost;
    }

    public void setPathCost(Integer pathCost) {
        this.pathCost = pathCost;
    }

    public Map<Integer, Integer> getNeighborNodesMap() {
        return neighborNodesMap;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static Integer getMaxCost() {
        return MAX_COST;
    }

    @Override
    public String toString() {
        return "\n" + "Node{" +
                "name='" + name +
                ", pathCost=" + pathCost +
                '}';
    }
}