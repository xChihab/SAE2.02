package sae.solver;

import sae.graph.Node;
import java.util.*;

public class SolverWithBFS extends Solver {

    public SolverWithBFS(Node start, Node goal) {
        super(start, goal);
    }

    @Override
    public void solve() {
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> predecessors = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        predecessors.put(start, null);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            steps++;

            if (current.equals(goal)) {
                reconstructPath(predecessors, goal);
                return;
            }

            for (Node neighbor : current.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }
    }
}
