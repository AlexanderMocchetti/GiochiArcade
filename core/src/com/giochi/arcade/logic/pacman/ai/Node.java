package com.giochi.arcade.logic.pacman.ai;

import java.util.HashSet;

public class Node implements Comparable<Node> {
    private final int x, y;
    private int hCost, gCost;
    public final String label;
    private Node previous = null;
    private final HashSet<Node> neighbours = new HashSet<>(4);
    public Node(int x, int y){
        this.x = x;
        this.y = y;
        label = x + " " + y;
    }
    public void addNeighbour(Node node){
        neighbours.add(node);
        node.neighbours.add(this);
    }
    public int getFCost(){
        return hCost + gCost;
    }
    public int getManhattanDistance(Node node){
        return Math.abs(x - node.x) + Math.abs(y - node.y);
    }
    public void reset(){
        gCost = Integer.MAX_VALUE;
        previous = null;
    }
    public int getTentativeGCost(){
        return gCost + 1;
    }
    public void setHCost(Node node){
        hCost = getManhattanDistance(node);
    }

    public int getGCost() {
        return gCost;
    }

    public HashSet<Node> getNeighbours(){
        return neighbours;
    }
    public void setGCost(int gCost) {
        this.gCost = gCost;
    }
    public void setGCost(Node node){
        gCost = node.getTentativeGCost();
    }
    public Node getPrevious() {
        return previous;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Node [x: " + x + ", y: " + y + "]";
    }

    /*
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(60);
            stringBuilder.append("Node [x: ");
            stringBuilder.append(x);
            stringBuilder.append(", y: ");
            stringBuilder.append(y);
            stringBuilder.append("] {\n");
            for (Node neighbour : neighbours){
                stringBuilder.append("\t\tNode [x: ");
                stringBuilder.append(neighbour.x);
                stringBuilder.append(", y: ");
                stringBuilder.append(neighbour.y);
                stringBuilder.append("],\n");
            }
            stringBuilder.append("\t}");
            return stringBuilder.toString();
        }
         */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }
    @Override
    public int compareTo(Node node){
        return getFCost() - node.getFCost();
    }
}
