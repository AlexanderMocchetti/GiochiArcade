package com.giochi.arcade.logic.pacman.ai;

import java.util.HashSet;

public class Node {
    private final int x, y;
    private int fCost, hCost, gCost;
    public final String label;
    private HashSet<Node> neighbours;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
        label = x + " " + y;
        neighbours = new HashSet<>(4);
    }
    public void addNeighbour(Node node){
        neighbours.add(node);
        node.neighbours.add(this);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(60);
        stringBuilder.append("Node [x: ");
        stringBuilder.append(x);
        stringBuilder.append(", y: ");
        stringBuilder.append(y);
        stringBuilder.append("]\n{\n");
        for (Node neighbour : neighbours){
            stringBuilder.append("Node [x: ");
            stringBuilder.append(neighbour.x);
            stringBuilder.append(", y: ");
            stringBuilder.append(neighbour.y);
            stringBuilder.append("],\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
