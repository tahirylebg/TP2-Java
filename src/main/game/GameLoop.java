package main.game;

import javafx.animation.AnimationTimer;
import main.model.Tetromino;
import main.model.TetrominoFactory;
import main.ui.GameRenderer;
import main.ui.SidePanel;
/*
    La classe GameLoop est responsable de la logique principale du jeu, y compris la gestion du temps, le déplacement des pièces, la vérification des collisions et la mise à jour de l'état du jeu. Elle utilise AnimationTimer pour créer une boucle de jeu qui s'exécute à chaque frame, permettant ainsi de faire descendre les
    pièces automatiquement et de réagir aux entrées du joueur en temps réel.
 */
public class GameLoop extends AnimationTimer {
    private Board board;
    private GameState state;
    private Tetromino current;
    private Tetromino nextTetromino;
    private GameRenderer renderer;
    private SidePanel sidePanel;
    private long lastUpdate = 0;
    private long dropInterval = 500_000_000L;

    // Constructeur pour initialiser les composants du jeu
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

    public int getGhostPieceY() {
        int originalY = current.getY();
        int y = originalY;
        
        while (true) {
            current.setY(y + 1);
            if (!board.isValidPosition(current)) {
                break;
            }
            y++;
        }
        
        current.setY(originalY); // Restore original position
        return y;
    }

    // Méthode pour faire apparaître la prochaine pièce et vérifier si le jeu est terminé
    public void spawnNextTetromino() {
        current = nextTetromino;
        nextTetromino = TetrominoFactory.generateRandom();
        sidePanel.updateNextPiece(nextTetromino);
        if (!board.isValidPosition(current)) {
            state.setGameOver(true);
        }
    }

    // Méthode pour verrouiller la pièce actuelle sur le plateau, vérifier les lignes complètes et mettre à jour le score et le niveau

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

        renderer.render(board, current, state, getGhostPieceY());
    }
}