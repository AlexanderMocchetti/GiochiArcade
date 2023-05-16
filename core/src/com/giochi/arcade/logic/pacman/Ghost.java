package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.giochi.arcade.logic.pacman.ai.Graph;
import com.giochi.arcade.logic.pacman.ai.Node;
import java.util.Deque;

public class Ghost {
    private Sprite sprite;
    private Graph graph;
    private Deque<Node> pathToPacman;
    private Node currentNode;
    private float speed;
    public void update(float delta){
        currentNode = pathToPacman.pop();
    }
    public void draw(Batch batch){
        sprite.draw(batch);
    }
}