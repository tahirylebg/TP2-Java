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

public class TetrisApp extends Application {
    
    @Override
    public void start(Stage stage) {
        // 1. Canvas
        Canvas canvas = new Canvas(300, 600);

        // 2. SidePanel et Renderer
        SidePanel sidePanel = new SidePanel();
        GameRenderer renderer = new GameRenderer(canvas);

        // 4. HBox
        HBox root = new HBox();
        root.getChildren().addAll(canvas, sidePanel);

        // 5. Scene + Stage
        Scene scene = new Scene(root);

        // 3. Game objects
        Board board = new Board();
        GameState state = new GameState();
        GameLoop gameLoop = new GameLoop(board, state, renderer);
        InputHandler inputHandler = new InputHandler(scene, gameLoop, board, state);
        gameLoop.start();

        stage.setTitle("Tetris");
        stage.setScene(scene);
        stage.show();
    }
}