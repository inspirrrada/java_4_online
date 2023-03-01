package ua.com.alevel;

import java.util.*;

public final class SearchCheapestPath {
    public static Graph calculateCheapestPathFromStart(Graph graph, Node startCity) {
        startCity.setPathCost(0);
        Set<Node> scannedNodes = new HashSet<>();
        Set<Node> unscannedNodes = new HashSet<>();
        unscannedNodes.add(startCity);
        while(unscannedNodes.size() != 0) {
            Node currentNode = getCheapestCostNode(unscannedNodes);
            unscannedNodes.remove(currentNode);
            Map<Node, Integer> neighborNodes = currentNode.getNeighborNodes();
            for(Map.Entry<Node, Integer> neighborPair : neighborNodes.entrySet()) {
                Node neighborNode = neighborPair.getKey();
                Integer neighborValue = neighborPair.getValue();
                if(!scannedNodes.contains(neighborNode)) {
                    calculateLowestValue(currentNode, neighborNode, neighborValue);
                    unscannedNodes.add(neighborNode);
                }
            }
            scannedNodes.add(currentNode);
        }
        return graph;
    }

    private static void calculateLowestValue(Node startNode, Node nextNode, Integer costValue) {
        Integer startNodeCostValue = startNode.getPathCost();
        if ( (startNodeCostValue + costValue) < nextNode.getPathCost() ) {
            nextNode.setPathCost(startNodeCostValue + costValue);
            List<Node> cheapestPath = new ArrayList<>(startNode.getCheapestPath());
            cheapestPath.add(startNode);
            nextNode.setCheapestPath(cheapestPath);
        }
    }

    public static Node getCheapestCostNode(Set<Node> unscannedNodes) {
        Node cheapestCostNode = null;
        int cheapestCost = 200_000;
        for (Node currentNode : unscannedNodes) {
            int currentNodeCost = currentNode.getPathCost();
            if (currentNodeCost < cheapestCost) {
                cheapestCost = currentNodeCost;
                cheapestCostNode = currentNode;
            }
        }
        return cheapestCostNode;
    }
}
