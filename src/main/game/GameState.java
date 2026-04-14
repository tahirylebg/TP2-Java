package main.game;

public class GameState {
    private int score;
    private int level;
    private boolean gameOver;
    private boolean paused;
    // + getters/setters

        public GameState() {
            score = 0; // Initialiser le score à 0
            level = 1; // Initialiser le niveau à 1
            gameOver = false; // Le jeu n'est pas terminé au début
            paused = false; // Le jeu n'est pas en pause au début
        }

    public int getScore() {
        return score;   
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    
}