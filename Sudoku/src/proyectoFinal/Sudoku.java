package proyectoFinal;

import java.util.*;

public class Sudoku {

	// Tablero
    private int[][] tablero = new int[9][9];
    private boolean[][] celdasFijas = new boolean[9][9];

    // Para acceder al tablero
    public int[][] getTablero() {
        return tablero;
    }
    
    // Para acceder a las celdas
    public boolean[][] getCeldasFijas() {
        return celdasFijas;
    }

    // Genera un tablero segun la dificultad
    public void generarTablero(String dificultad) {
        // Genera solución completa
        generarSolucionCompleta();

        // Marca todas como fijas inicialmente
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                celdasFijas[fila][col] = true;
            }
        }

        int celdasVisibles;
        switch (dificultad.toLowerCase()) {
            case "facil":
                celdasVisibles = 40;
                break;
            case "medio":
                celdasVisibles = 30;
                break;
            case "dificil":
                celdasVisibles = 22;
                break;
            default:
                celdasVisibles = 40;
        }

        ocultarCeldas(81 - celdasVisibles);
    }

    
    
    private boolean generarSolucionCompleta() {
        return resolver(0, 0);
    }

    private boolean resolver(int fila, int col) {
        if (fila == 9) return true;
        if (col == 9) return resolver(fila + 1, 0);

        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 9; i++) numeros.add(i);
        Collections.shuffle(numeros);

        for (int num : numeros) {
            if (esMovimientoValido(fila, col, num)) {
                tablero[fila][col] = num;
                if (resolver(fila, col + 1)) return true;
                tablero[fila][col] = 0;
            }
        }
        return false;
    }

    
    
    private void ocultarCeldas(int cantidad) {
        Random random = new Random();
        int borradas = 0;

        while (borradas < cantidad) {
            int fila = random.nextInt(9);
            int col = random.nextInt(9);

            if (tablero[fila][col] != 0) {
                tablero[fila][col] = 0;
                celdasFijas[fila][col] = false;
                borradas++;
            }
        }
    }



    // Mira si se puede colocar un numero o no(segun las reglas)
    public boolean esMovimientoValido(int fila, int columna, int valor) {
        if (tablero[fila][columna] != 0 || valor < 1 || valor > 9)
            return false;

        for (int i = 0; i < 9; i++)
            if (tablero[fila][i] == valor || tablero[i][columna] == valor)
                return false;

        int boxRow = (fila / 3) * 3;
        int boxCol = (columna / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++)
            for (int j = boxCol; j < boxCol + 3; j++)
                if (tablero[i][j] == valor)
                    return false;

        return true;
    }

    // Coloca un numero sino es una celda fija
    public boolean colocarNumero(int fila, int columna, int valor) {
        if (!celdasFijas[fila][columna] && esMovimientoValido(fila, columna, valor)) {
            tablero[fila][columna] = valor;
            return true;
        }
        return false;
    }

    // Verifica si el tablero esta resuelto y valido
    public boolean estaResuelto() {
        for (int fila = 0; fila < 9; fila++)
            for (int col = 0; col < 9; col++) {
                int val = tablero[fila][col];
                tablero[fila][col] = 0;
                if (!esMovimientoValido(fila, col, val)) {
                    tablero[fila][col] = val;
                    return false;
                }
                tablero[fila][col] = val;
            }
        return true;
    }

    // Muestra el tablero
    public void mostrarTablero() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println("-------------------------------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(tablero[i][j] == 0 ? ". " : tablero[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------------------------");
    }
}


