package main.model;

import java.util.Random;

/* 
 * Fabric de création de tetrominos.
 */

public class TetrominoFactory {
    private static final Random RANDOM = new Random();

    // Génère un tetromino aléatoire en choisissant une forme au hasard parmi les formes disponibles
    public static Tetromino generateRandom() {
        TetrominoShape[] shapes = TetrominoShape.values();
        int index = RANDOM.nextInt(shapes.length);
        return new Tetromino(shapes[index]);
    }
}