package ua.com.alevel;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Node> nodesSet = new HashSet<>();

    public void addNode (Node node) {
        nodesSet.add(node);
    }

    public Set<Node> getNodesSet() {
        return nodesSet;
    }

    public void setNodesSet(Set<Node> nodesSet) {
        this.nodesSet = nodesSet;
    }
}
