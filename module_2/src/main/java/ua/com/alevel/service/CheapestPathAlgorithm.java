package ua.com.alevel.service;

import ua.com.alevel.utils.Graph;
import ua.com.alevel.utils.Node;
import java.util.*;

public final class CheapestPathAlgorithm {
    public static Graph calculateProfitableValue(Graph graph, Node startNode) {
        startNode.setPathCost(0);
        Set<Node> scannedNodes = new HashSet<>();
        Set<Node> unscannedNodes = new HashSet<>();
        unscannedNodes.add(startNode);
        while(unscannedNodes.size() != 0) {
            Node currentNode = getNodeWithLowestValue(unscannedNodes);
            unscannedNodes.remove(currentNode);
            Map<Integer, Integer> neighborNodes = currentNode.getNeighborNodesMap();
            for(Map.Entry<Integer, Integer> neighborPair : neighborNodes.entrySet()) {
                Integer neighborNodeIndex = neighborPair.getKey();
                Node neighborNode = graph.getNodesSet().stream()
                        .filter(v -> v.getIndex() == neighborNodeIndex).findFirst().get();
                Integer neighborValue = neighborPair.getValue();
                if(!scannedNodes.contains(neighborNode)) {
                    calculateMinimumValue(currentNode, neighborNode, neighborValue);
                    unscannedNodes.add(neighborNode);
                }
            }
            scannedNodes.add(currentNode);
        }
        return graph;
    }

    private static void calculateMinimumValue(Node startNode, Node nextNode, Integer costValue) {
        Integer startNodeCostValue = startNode.getPathCost();
        if ( (startNodeCostValue + costValue) < nextNode.getPathCost() ) {
            nextNode.setPathCost(startNodeCostValue + costValue);
            List<Node> cheapestPath = new ArrayList<>(startNode.getCheapestPath());
            cheapestPath.add(startNode);
            nextNode.setCheapestPath(cheapestPath);
        }
    }

    public static Node getNodeWithLowestValue(Set<Node> unscannedNodes) {
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
