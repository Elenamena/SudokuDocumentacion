package proyectoFinal;

import java.util.Random;

public class GeneradorSudoku {

    private Random rand = new Random();

    // Genera un tablero y elimina las celdas dependiendo de la dificultad
    public int[][] generar(String dificultad) {
        int[][] tablero = new int[9][9];
        resolver(tablero); // Se rellena completamente

        // Segun la dificultad elimina celdas
        int vaciar = switch (dificultad.toLowerCase()) {
            case "facil" -> 30;
            case "medio" -> 40;
            case "dificil" -> 50;
            default -> 40;
        };

        // Se vacian celdas al azar
        for (int i = 0; i < vaciar;) {
            int fila = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (tablero[fila][col] != 0) {
                tablero[fila][col] = 0;
                i++;
            }
        }

        return tablero;
    }

    // Se resuelve el tablero
    private boolean resolver(int[][] tablero) {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (tablero[fila][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (esValido(tablero, fila, col, num)) {
                            tablero[fila][col] = num;
                            if (resolver(tablero)) return true;
                            tablero[fila][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Si se puede colocar el numero
    private boolean esValido(int[][] tablero, int fila, int col, int num) {
        for (int i = 0; i < 9; i++)
            if (tablero[fila][i] == num || tablero[i][col] == num)
                return false;

        int boxRow = (fila / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++)
            for (int j = boxCol; j < boxCol + 3; j++)
                if (tablero[i][j] == num)
                    return false;

        return true;
    }
}


