package main.game;

import main.model.Tetromino;
/*
    Board represente le plateau de jeu de Tetris. 
    Il gère la grille, vérifie les positions valides pour les tetrominos et place les tetrominos sur la grille.
*/
public class Board {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;
    private int[][] grid;

    // Constructeur pour initialiser la grille du plateau
    public Board() {
        grid = new int[HEIGHT][WIDTH];
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                grid[row][col] = 0;
            }
        }
    }

    // Vérifie si la position d'un tetromino est valide sur le plateau
    public boolean isValidPosition(Tetromino t) {
        int[][] shape = t.getCurrentShape();

        // Parcourir la forme du tetromino et vérifier les collisions 
        // avec les bords du plateau et les autres pièces
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int col = t.getX() + j;
                    int row = t.getY() + i;

                    if (col < 0 || col >= WIDTH || row < 0 || row >= HEIGHT) {
                        return false;
                    }

                    if (grid[row][col] != 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // Place un tetromino sur le plateau en mettant à jour la grille
    public void placeTetromino(Tetromino t) {
        int[][] shape = t.getCurrentShape();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int col = t.getX() + j;
                    int row = t.getY() + i;
                    if (row >= 0 && row < HEIGHT && col >= 0 && col < WIDTH) {
                        grid[row][col] = 1;
                    }
                }
            }
        }
    }

    // Méthode pour obtenir la valeur d'une cellule de la grille
    public int getCell(int x, int y) {
        if (y < 0 || y >= HEIGHT || x < 0 || x >= WIDTH) {
            return -1;
        }
        return grid[y][x];
    }
}