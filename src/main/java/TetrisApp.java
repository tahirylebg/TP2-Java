package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import main.ui.GameRenderer;
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

        // 3. HBox
        HBox root = new HBox();
        root.getChildren().addAll(canvas, sidePanel); // ← ici après création

        // 4. Scene + Stage
        Scene scene = new Scene(root);
        stage.setTitle("Tetris");
        stage.setScene(scene);
        stage.show();
    }
}