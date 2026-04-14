package main.game;

import javafx.animation.AnimationTimer;
import main.model.Tetromino;
import main.model.TetrominoFactory;
import main.ui.GameRenderer;

public class GameLoop extends AnimationTimer {
    private Board board;
    private GameState state;
    private Tetromino current;
    private GameRenderer renderer;
    private long lastUpdate = 0;
    private long dropInterval = 500_000_000L;

    public GameLoop(Board board, GameState state, GameRenderer renderer) {
        this.board = board;
        this.state = state;
        this.renderer = renderer;
        this.current = TetrominoFactory.generateRandom();
    }

    public Tetromino getCurrentTetromino() {
        return current;
    }

    public void spawnNextTetromino() {
        current = TetrominoFactory.generateRandom();
        if (!board.isValidPosition(current)) {
            state.setGameOver(true);
        }
    }

    @Override
    public void handle(long now) {
        if (state.isGameOver() || state.isPaused()) return;

        if (now - lastUpdate >= dropInterval) {
            // 1. Descend la pièce d'une case
            current.setY(current.getY() + 1);

            // 2. Si position invalide → annule, place, génère nouvelle pièce
            if (!board.isValidPosition(current)) {
                current.setY(current.getY() - 1);
                board.placeTetromino(current);
                spawnNextTetromino();
            }

            lastUpdate = now;
        }

        renderer.render(board, current);
    }
}