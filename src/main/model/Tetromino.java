package main.model;

import javafx.scene.paint.Color;
// Classe représentant un Tetromino dans le jeu de Tetris , c'est une pièce composée de 4 blocs carrés, avec une forme spécifique et une couleur associée.
public class Tetromino {
    private TetrominoShape shape;
    private int rotationIndex;
    private int x, y;
    private Color color;

    public Tetromino(TetrominoShape shape) {
    this.shape = shape;
    this.rotationIndex = 0;
    this.x = (10 - shape.getRotation(0)[0].length) / 2;
    this.y = 0;
    this.color = switch(shape) {
        case I -> Color.CYAN;
        case O -> Color.YELLOW;
        case T -> Color.MAGENTA;
        case S -> Color.GREEN;
        case Z -> Color.RED;
        case J -> Color.BLUE;
        case L -> Color.ORANGE;
        

    };
}

    public int[][] getCurrentShape() {
        return shape.getRotation(rotationIndex);
    }

    public void rotate() {
        rotationIndex = (rotationIndex + 1) % shape.getRotationsCount();
    }

    // Getters et setters pour les propriétés
    public TetrominoShape getShape() {
        return shape;
    }   
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Color getColor() {
        return color;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setColor(Color color) {
        this.color = color;
    }


}
