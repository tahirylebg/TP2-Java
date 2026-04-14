package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import main.game.Board;
import main.game.GameLoop;
import main.game.GameState;
import main.ui.GameRenderer;
import main.ui.InputHandler;
import main.ui.SidePanel;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
/* 
 * Classe principale de l'application Tetris
 */
public class TetrisApp extends Application {
    
    @Override
    public void start(Stage stage) {
        // 1. Canvas
        Canvas canvas = new Canvas(300, 600);

        //SidePanel affiche les informations du jeu telles que le score, le niveau et la prochaine pièce. GameRenderer est responsable de dessiner le plateau de jeu et les pièces actives sur le Canvas.
        SidePanel sidePanel = new SidePanel();
        GameRenderer renderer = new GameRenderer(canvas);

        //HBox sert à organiser le Canvas et le SidePanel côte à côte 
        HBox root = new HBox();
        root.getChildren().addAll(canvas, sidePanel);

        // Scene + Stage
        Scene scene = new Scene(root);

        // Game objects
        Board board = new Board();
        GameState state = new GameState();
        GameLoop gameLoop = new GameLoop(board, state, renderer, sidePanel);
        InputHandler inputHandler = new InputHandler(scene, gameLoop, board, state);
        gameLoop.start();

        stage.setTitle("Tetris");
        stage.setScene(scene);
        stage.show();
    }
}