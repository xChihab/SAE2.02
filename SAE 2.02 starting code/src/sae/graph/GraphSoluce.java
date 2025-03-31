package sae.graph;

import java.util.List;

public class GraphSoluce {
    private List<Node> path;

    public GraphSoluce() {}

    public void setPath(List<Node> path) {
        this.path = path;
    }

    public List<Node> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Solution path: " + path;
    }
}
