package main.model;

/* 
 * Enumération représentant les différentes formes de Tetrominos dans le jeu de Tetris.
 * Chaque forme est définie par une série de rotations, qui sont des matrices 2D indiquant la disposition des blocs pour chaque rotation.
 */

public enum TetrominoShape {
    // Definir les formes des tetrominos avec leurs rotations
    I(new int[][][] {
        {
            {0,0,0,0},
            {1,1,1,1},
            {0,0,0,0},
            {0,0,0,0}
        },
        {
            {0,1,0,0},
            {0,1,0,0},
            {0,1,0,0},
            {0,1,0,0}
        }
    }),
    O(new int[][][] {
        {
            {1,1},
            {1,1}
        }
    }),
    T(new int[][][] {
        {
            {0,1,0},
            {1,1,1},
            {0,0,0}
        },
        {
            {0,1,0},
            {0,1,1},
            {0,1,0}
        },
        {
            {0,0,0},
            {1,1,1},
            {0,1,0}
        },
        {
            {0,1,0},
            {1,1,0},
            {0,1,0}
        }
    }),
    S(new int[][][] {
        {
            {0,1,1},
            {1,1,0},
            {0,0,0}
        },
        {
            {0,1,0},
            {0,1,1},
            {0,0,1}
        }
    }),
    Z(new int[][][] {
        {
            {1,1,0},
            {0,1,1},
            {0,0,0}
        },
        {
            {0,0,1},
            {0,1,1},
            {0,1,0}
        }
    }),
    J(new int[][][] {
        {
            {1,0,0},
            {1,1,1},
            {0,0,0}
        },
        {
            {0,1,1},
            {0,1,0},
            {0,1,0}
        },
        {
            {0,0,0},
            {1,1,1},
            {0,0,1}
        },
        {
            {0,1,0},
            {0,1,0},
            {1,1,0}
        }
    }),
    L(new int[][][] {
        {
            {0,0,1},
            {1,1,1},
            {0,0,0}
        },
        {
            {0,1,0},
            {0,1,0},
            {0,1,1}
        },
        {
            {0,0,0},
            {1,1,1},
            {1,0,0}
        },
        {
            {1,1,0},
            {0,1,0},
            {0,1,0}
        }
    });

    // Stocker les rotations de chaque forme

    private final int[][][] rotations;

    // Constructeur pour initialiser les rotations
    TetrominoShape(int[][][] rotations) {
        this.rotations = rotations;
    }

    // Méthode pour obtenir une rotation spécifique
    public int[][] getRotation(int index) {
        return rotations[index];
    }

    public int getRotationsCount() {
        return rotations.length;
    }
}