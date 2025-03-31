package sae.solver;

import sae.graph.Node;
import sae.graph.GraphSoluce;

import java.util.*;

public abstract class Solver {
    protected Node start;
    protected Node goal;
    protected int steps;
    protected GraphSoluce solution;

    public Solver(Node start, Node goal) {
        this.start = start;
        this.goal = goal;
        this.steps = 0;
        this.solution = new GraphSoluce();
    }

    public abstract void solve();  

    protected void reconstructPath(Map<Node, Node> predecessors, Node end) {
        Node current = end;
        List<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = predecessors.get(current);
        }
        Collections.reverse(path);
        solution.setPath(path);
    }

    public int getSteps() {
        return steps;
    }

    public GraphSoluce getGraphSoluce() {
        return solution;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
