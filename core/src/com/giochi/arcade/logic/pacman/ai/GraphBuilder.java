package com.giochi.arcade.logic.pacman.ai;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.giochi.arcade.ui.PacmanScreen;

public class GraphBuilder {
    private final Array<Rectangle> walls;
    private final Graph graph = new Graph();
    public GraphBuilder(Array<Rectangle> walls){
        this.walls = walls;
    }
    private void generateNodes(){
        Node node;
        boolean[][] unwalkableCellsMatrix = getUnwalkableCells();
        for (int i = 0; i < unwalkableCellsMatrix.length; i++) {
           for (int j = 0; j < unwalkableCellsMatrix[i].length; j++) {
                if (unwalkableCellsMatrix[i][j])
                    continue;
                node = new Node(j, i);
                if (i - 1 > 0 && !unwalkableCellsMatrix[i - 1][j])
                    node.addNeighbour(graph.getNode(j, i - 1));
                if (j - 1 > 0 && !unwalkableCellsMatrix[i][j - 1])
                    node.addNeighbour(graph.getNode(j - 1, i));
            }
        }
    }
    private boolean[][] getUnwalkableCells(){
        int wallY, wallX;
        boolean[][] unwalkableCells = new boolean[(int) PacmanScreen.WORLD_HEIGHT][(int) PacmanScreen.WORLD_WIDTH];
        for (Rectangle wall : walls){
            wallX = (int) wall.x;
            wallY = (int) wall.y;
            for(int y = wallY; y < wallY + (int) wall.height; y++){
                for(int x = wallX; x < wallX + (int) wall.width; x++){
                    unwalkableCells[y][x] = true;
                }
            }
        }
        return unwalkableCells;
    }
    public Graph getGraph(){
        generateNodes();
        return graph;
    }
}
