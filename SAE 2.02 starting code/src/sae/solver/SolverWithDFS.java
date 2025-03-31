package sae.solver;

import sae.graph.Node;
import java.util.*;

public class SolverWithDFS extends Solver {

    public SolverWithDFS(Node start, Node goal) {
        super(start, goal);
    }

    @Override
    public void solve() {
        Stack<Node> stack = new Stack<>();
        Map<Node, Node> predecessors = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        stack.push(start);
        visited.add(start);
        predecessors.put(start, null);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            steps++;

            if (current.equals(goal)) {
                reconstructPath(predecessors, goal);
                return;
            }

            for (Node neighbor : current.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }
    }
}
