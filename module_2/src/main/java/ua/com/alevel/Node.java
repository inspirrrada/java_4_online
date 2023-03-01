package ua.com.alevel;

import java.util.*;

public class Node {
    private String name;
    private List<Node> cheapestPathList = new ArrayList<>();
    private Integer pathCost = 200_000;
    private Map<Node, Integer> neighborNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addDestinationCity(Node destinationCity, int pathCost) {
        neighborNodes.put(destinationCity, pathCost);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Map<Node, Integer> getNeighborNodes() {
        return neighborNodes;
    }

    public void setNeighborNodes(Map<Node, Integer> neighborNodes) {
        this.neighborNodes = neighborNodes;
    }
}