package main.ui;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class SidePanel extends VBox {
    private Label scoreLabel; // Affiche le score actuel du joueur
    private Label levelLabel; // Affiche le niveau actuel du joueur
    private Label nextPieceLabel; // Affiche la prochaine pièce qui va tomber

    public SidePanel() {
        // Style du panneau
        setStyle("-fx-background-color: #1a1a1a; -fx-padding: 20;");
        setSpacing(10);
        
        // Labels
        scoreLabel = new Label("Score: 0");
        levelLabel = new Label("Niveau: 1");                
        // Ajoute tout au VBox
        nextPieceLabel = new Label("Suivant:");
        getChildren().addAll(scoreLabel, levelLabel, nextPieceLabel);
    }

    // Méthode pour mettre à jour le score
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    // Méthode pour mettre à jour le niveau
    public void updateLevel(int level) {
        levelLabel.setText("Niveau: " + level);
    }
}
