package main.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.game.Board;
import main.model.Tetromino;

/*
    GameRenderer est responsable de dessiner le plateau de jeu et les pièces actives sur le Canvas. 
    Il utilise GraphicsContext pour dessiner les éléments graphiques.
*/

public class GameRenderer {
    private Canvas canvas;
    private GraphicsContext gc;
    private static final int CELL_SIZE = 30;
    private static final int COLUMNS = 10;
    private static final int ROWS = 20;

    public GameRenderer(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void render(Board board, Tetromino activeTetromino) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Fond de la grille
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Grille fixe
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (board.getCell(col, row) != 0) {
                    gc.setFill(Color.DARKGRAY);
                    gc.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    gc.setStroke(Color.BLACK);
                    gc.strokeRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        // Pièce active
        if (activeTetromino != null) {
            int[][] shape = activeTetromino.getCurrentShape();
            gc.setFill(activeTetromino.getColor());

            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] == 1) {
                        int col = activeTetromino.getX() + j;
                        int row = activeTetromino.getY() + i;
                        gc.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        gc.setStroke(Color.BLACK);
                        gc.strokeRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    }
                }
            }
        }
    }
}

