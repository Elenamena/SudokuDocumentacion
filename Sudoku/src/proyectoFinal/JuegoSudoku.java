package proyectoFinal;

import java.util.Scanner;

public class JuegoSudoku {

	// Para jugar desde consola
    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        Sudoku sudoku = new Sudoku();

        System.out.println("Escoge la dificultad (facil, medio, dificil):");
        String dificultad = sc.nextLine();
        sudoku.generarTablero(dificultad);

        // Bucle que se repite hasta que el tablero esta resuelto
        while (!sudoku.estaResuelto()) {
            sudoku.mostrarTablero();

            System.out.print("Fila (0-8): ");
            int fila = sc.nextInt();
            System.out.print("Columna (0-8): ");
            int columna = sc.nextInt();
            System.out.print("Valor (1-9): ");
            int valor = sc.nextInt();

            // Coloca el numero
            if (!sudoku.colocarNumero(fila, columna, valor)) {
                System.out.println("Movimiento no válido. Intenta de nuevo");
            }
        }

        // Muestra el tablero
        System.out.println("Sudoku resuelto");
        sudoku.mostrarTablero();
    }
}


