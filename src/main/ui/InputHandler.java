package main.ui;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import main.game.Board;
import main.game.GameLoop;
import main.game.GameState;
import main.model.Tetromino;

/*
    InputHandler est responsable de gérer les entrées clavier du joueur. 
    Il écoute les événements de touche pressée et modifie l'état du jeu en conséquence, 
    comme déplacer ou faire tourner la pièce active, ou mettre le jeu en pause.
*/
public class InputHandler {
    public InputHandler(Scene scene, GameLoop gameLoop, Board board, GameState state) {
        scene.setOnKeyPressed(event -> {
            if (state.isGameOver() || state.isPaused() && event.getCode() != KeyCode.P) {
                if (event.getCode() == KeyCode.P) {
                    state.setPaused(!state.isPaused());
                }
                return;
            }

            Tetromino current = gameLoop.getCurrentTetromino();
            if (current == null) {
                return;
            }

            switch (event.getCode()) {
                case LEFT -> {
                    current.setX(current.getX() - 1);
                    if (!board.isValidPosition(current)) {
                        current.setX(current.getX() + 1);
                    }
                }
                case RIGHT -> {
                    current.setX(current.getX() + 1);
                    if (!board.isValidPosition(current)) {
                        current.setX(current.getX() - 1);
                    }
                }
                case DOWN -> {
                    current.setY(current.getY() + 1);
                    if (!board.isValidPosition(current)) {
                        current.setY(current.getY() - 1);
                    }
                }
                case UP -> {
                    current.rotate();
                    if (!board.isValidPosition(current)) {
                        current.rotate();
                        current.rotate();
                        current.rotate();
                    }
                }
                case SPACE -> {
                    while (board.isValidPosition(current)) {
                        current.setY(current.getY() + 1);
                    }
                    current.setY(current.getY() - 1);
                    gameLoop.lockCurrentTetromino();
                }
                case P -> state.setPaused(!state.isPaused());
                default -> {
                }
            }
        });
    }
}
