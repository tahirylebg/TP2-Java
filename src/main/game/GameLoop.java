package main.game;

import javafx.animation.AnimationTimer;
import main.model.Tetromino;
import main.model.TetrominoFactory;
import main.ui.GameRenderer;
import main.ui.SidePanel;

public class GameLoop extends AnimationTimer {
    private Board board;
    private GameState state;
    private Tetromino current;
    private Tetromino nextTetromino;
    private GameRenderer renderer;
    private SidePanel sidePanel;
    private long lastUpdate = 0;
    private long dropInterval = 500_000_000L;

    public GameLoop(Board board, GameState state, GameRenderer renderer, SidePanel sidePanel) {
        this.board = board;
        this.state = state;
        this.renderer = renderer;
        this.sidePanel = sidePanel;
        this.current = TetrominoFactory.generateRandom();
        this.nextTetromino = TetrominoFactory.generateRandom();
        this.sidePanel.updateNextPiece(this.nextTetromino);
    }

    public Tetromino getCurrentTetromino() {
        return current;
    }

    public void spawnNextTetromino() {
        current = nextTetromino;
        nextTetromino = TetrominoFactory.generateRandom();
        sidePanel.updateNextPiece(nextTetromino);
        if (!board.isValidPosition(current)) {
            state.setGameOver(true);
        }
    }

    public void lockCurrentTetromino() {
        board.placeTetromino(current);

        int lines = board.clearLines();
        if (lines > 0) {
            state.addScore(lines);
            dropInterval = Math.max(100_000_000L,
                500_000_000L - (state.getLevel() - 1) * 50_000_000L);
            sidePanel.updateScore(state.getScore());
            sidePanel.updateLevel(state.getLevel());
        }

        spawnNextTetromino();
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
                lockCurrentTetromino();
            }

            lastUpdate = now;
        }

        renderer.render(board, current, state);
    }
}