package main.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.model.Tetromino;

public class SidePanel extends VBox {
    private Label scoreLabel; // Affiche le score actuel du joueur
    private Label levelLabel; // Affiche le niveau actuel du joueur
    private Label nextPieceLabel; // Affiche la prochaine pièce qui va tomber
    private Canvas nextPieceCanvas;

    public SidePanel() {
        // Style du panneau
        setStyle("-fx-background-color: #1a1a1a; -fx-padding: 20;");
        setSpacing(10);
        
        // Labels
        scoreLabel = new Label("Score: 0");
        levelLabel = new Label("Niveau: 1");                
        nextPieceLabel = new Label("Suivant:");

        // Canvas pour la prochaine pièce
        nextPieceCanvas = new Canvas(120, 120);
        getChildren().addAll(scoreLabel, levelLabel, nextPieceLabel, nextPieceCanvas);
    }

    // Méthode pour mettre à jour le score
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    // Méthode pour mettre à jour le niveau
    public void updateLevel(int level) {
        levelLabel.setText("Niveau: " + level);
    }

    // Méthode pour mettre à jour la prochaine pièce
    public void updateNextPiece(Tetromino next) {
        nextPieceLabel.setText("Suivant: " + next.getShape().name());

        GraphicsContext gc = nextPieceCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, nextPieceCanvas.getWidth(), nextPieceCanvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, nextPieceCanvas.getWidth(), nextPieceCanvas.getHeight());

        int[][] shape = next.getCurrentShape();
        int cellSize = 25;
        gc.setFill(next.getColor());
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    gc.fillRect(j * cellSize + 10, i * cellSize + 10, cellSize - 2, cellSize - 2);
                }
            }
        }
    }
}

