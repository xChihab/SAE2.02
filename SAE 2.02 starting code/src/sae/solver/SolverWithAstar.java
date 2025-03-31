package sae.solver;

import sae.graph.Node;
import java.util.*;

public class SolverWithAstar extends Solver {

    public SolverWithAstar(Node start, Node goal) {
        super(start, goal);
    }

    private int heuristic(Node a, Node b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    @Override
    public void solve() {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> heuristic(n, goal)));
        Map<Node, Node> predecessors = new HashMap<>();
        Map<Node, Integer> gScore = new HashMap<>();

        openSet.add(start);
        gScore.put(start, 0);
        predecessors.put(start, null);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            steps++;

            if (current.equals(goal)) {
                reconstructPath(predecessors, goal);
                return;
            }

            for (Node neighbor : current.getNeighbors()) {
                int tentativeGScore = gScore.get(current) + 1;
                if (!gScore.containsKey(neighbor) || tentativeGScore < gScore.get(neighbor)) {
                    gScore.put(neighbor, tentativeGScore);
                    predecessors.put(neighbor, current);
                    openSet.add(neighbor);
                }
            }
        }
    }
}
