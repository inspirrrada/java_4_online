package ua.com.alevel.utils;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Node> nodesSet = new HashSet<>();

    public void addNode(Node node) {
        nodesSet.add(node);
    }

    public Set<Node> getNodesSet() {
        return nodesSet;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodesSet=" + nodesSet +
                '}';
    }
}
