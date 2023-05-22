package com.giochi.arcade.Pacman;

public class GameManager {
    public static final float
            pixelToGrid = 1/32f,
            centerTileError = 0.05f,
            scalePill = 1/4f,
            scalePillBig = 0.8f,
            pillIntermittentTime = 0.1f,
            playerAnimationTimeFrame = 0.08f,
            ghostAnimationTimeFrame = 0.12f,
            ghostIntermittentPathfindingTime = 0.5f,
            redGhostActivation = 5f,
            timeInvincibile = 4f,
            ghostScaredSpeed = 1.5f;

    private int pacmanLives = 3;
    private int totalPillsEaten = 0;
    private int score = 0;
    private boolean gameOver = false;
    private boolean pacmanInvincible = false;
    private float timeSinceGainedInvincibility = 0;
    private final Map parent;
    public GameManager(Map parent){
        this.parent = parent;
    }


    public void reset() {
        pacmanLives = 3;
        gameOver = false;
        score = 0;
    }
    public void update(float delta) {
        score = totalPillsEaten * 10;
        if (pacmanInvincible)
            timeSinceGainedInvincibility += delta;
        if (checkPlayerGhostCollision()) {
            if (pacmanInvincible)
                parent.getGhost().setEaten(true);
            else {
                gameOver = true;
                pacmanLives--;
            }
        }
        if (timeSinceGainedInvincibility >= timeInvincibile) {
            pacmanInvincible = false;
            timeSinceGainedInvincibility = 0;
        }
        if (totalPillsEaten == 100) {
            totalPillsEaten = 0;
            pacmanLives++;
        }
        if (checkWin()) {
            gameOver = true;
        }
        if (gameOver){
            parent.reset();
            gameOver = false;
        }
    }
    private boolean checkWin(){
        return parent.getPills().getPillsEaten() == Pills.PILL_NUMBER;
    }

    public void setPacmanInvincible(boolean pacmanInvincible) {
        this.pacmanInvincible = pacmanInvincible;
    }

    public boolean isPacmanInvincible() {
        return pacmanInvincible;
    }

    private boolean checkPlayerGhostCollision(){
        return parent.getGhost().checkPlayerCollision();
    }
}