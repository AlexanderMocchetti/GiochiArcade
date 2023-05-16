package com.giochi.arcade.logic.pacman.ai;

import java.util.HashMap;

public class Graph {
    private final HashMap<String, Node> vertices = new HashMap<>(150);
    public void addNode(Node node){
        vertices.put(node.label, node);
    }
    public void addNode(String label){
        String[] coordinates = label.split(" ");
        int x, y;
        x = Integer.parseInt(coordinates[0]);
        y = Integer.parseInt(coordinates[1]);
        vertices.put(label, new Node(x, y));
    }
    public void addNode(int x, int y){
        vertices.put(x + " " + y, new Node(x, y));
    }
    public Node getNode(String label){
        return vertices.get(label);
    }
    public Node getNode(int x, int y){
        return vertices.get(x + " " + y);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(100);
        Node node;
        stringBuilder.append("Graph {\n");
        for(String label : vertices.keySet()){
            node = getNode(label);
            stringBuilder.append('\t');
            stringBuilder.append(node);
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}