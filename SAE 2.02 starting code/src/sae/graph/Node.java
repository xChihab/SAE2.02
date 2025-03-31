package sae.graph;

import java.util.HashSet;
import java.util.Set;

public class Node {
    private int x, y;
    private Set<Node> neighbors;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.neighbors = new HashSet<>();
    }

    public void addNeighbor(Node neighbor) {
        this.neighbors.add(neighbor);
    }

    public Set<Node> getNeighbors() {
        return neighbors;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Node)) return false;
        Node other = (Node) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }

    @Override
    public String toString() {
        return "Node(" + x + ", " + y + ")";
    }
}
