package com.giochi.arcade.logic.pacman.ai;

import java.util.*;

public class Graph {
    private final HashMap<String, Node> vertices = new HashMap<>(151);
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
    private Deque<Node> reconstructPath(Node currentNode){
        LinkedList<Node> path = new LinkedList<>();
        path.push(currentNode);
        while(currentNode != null){
            currentNode = currentNode.getPrevious();
            path.push(currentNode);
        }
        path.pop();
        return path;
    }
    // TODO: see if pacman effect causes heuristic function to fuck up, in which case change algorithm
    public Deque<Node> getShortestPath(Node sourceNode, Node destinationNode){
        PriorityQueue<Node> openSet = new PriorityQueue<>(35);
        HashSet<Node> closeSet = new HashSet<>(35);
        for (Node node : vertices.values()){
            node.reset();
            node.setHCost(destinationNode);
        }
        sourceNode.setGCost(0);
        Node currentNode = sourceNode;
        closeSet.add(sourceNode);
        while (!currentNode.equals(destinationNode)){
            for (Node neighbourNode : currentNode.getNeighbours()){
                if(closeSet.contains(neighbourNode))
                    continue;
                if(!openSet.contains(neighbourNode))
                    openSet.offer(neighbourNode);
                if (currentNode.getTentativeGCost() < neighbourNode.getGCost()){
                    neighbourNode.setGCost(currentNode);
                    neighbourNode.setPrevious(currentNode);
                }
            }
            currentNode = openSet.remove();
            closeSet.add(currentNode);
        }
        return reconstructPath(currentNode);
    }
    public Deque<Node> getShortestPath(String sourceLabel, String destinationLabel){
        return getShortestPath(getNode(sourceLabel), getNode(destinationLabel));
    }
    public Deque<Node> getShortestPath(int[] sourceCoordinates, int[] destinationCoordinates){
        return getShortestPath(getNode(sourceCoordinates[0], sourceCoordinates[1]),
                getNode(destinationCoordinates[0], destinationCoordinates[1]));
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(1000);
        Node node;
        stringBuilder.append("Graph {\n");
        for(String label : vertices.keySet()){
            node = getNode(label);
            stringBuilder.append('\t');
            stringBuilder.append(node);
            stringBuilder.append(",\n");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}