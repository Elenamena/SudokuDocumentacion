package proyectoFinal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class PruebasUnitarias {
	
	@Test
    void testGenerarTableroFacil() {
        Sudoku sudoku = new Sudoku();
        sudoku.generarTablero("facil"); // Genera el tablero con dificultad facil
        int[][] tablero = sudoku.getTablero(); // Matriz del tablero
        boolean[][] celdasFijas = sudoku.getCeldasFijas();

        int visibles = 0; // Contador
        
        // Para validar
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                assertTrue(tablero[fila][col] >= 0 && tablero[fila][col] <= 9, "Valor fuera de rango en [" + fila + "][" + col + "]");
                if (!celdasFijas[fila][col]) {
                    visibles++;
                }
            }
        }
        // Verifica el numero de celdas
        assertTrue(visibles = 30, "Debe haber 30 celdas visibles en la dificultad fácil");
    }

	

	@Test // Verifica que haya 9 filas y 9 columnas
    void testDimensionesTablero() {
        Sudoku sudoku = new Sudoku();
        int[][] tablero = sudoku.getTablero();
        assertEquals(9, tablero.length, "El tablero debe tener 9 filas");
        for (int[] fila : tablero) {
            assertEquals(9, fila.length, "Cada fila debe tener 9 columnas");
        }
    }

	@Test // Ejecuta con dificultad inválida
    void testDificultadInvalida() {
        Sudoku sudoku = new Sudoku();
        assertDoesNotThrow(() -> sudoku.generarTablero("imposible"), "No debe lanzar excepción con dificultad inválida");
    }

	@Test // Verifica que las esquinas esten dentro del rango
    void testEsquinasDelTablero() {
        Sudoku sudoku = new Sudoku();
        sudoku.generarTablero("medio");
        int[][] tablero = sudoku.getTablero();
        assertTrue(tablero[0][0] >= 0 && tablero[0][0] <= 9, "Esquina superior izquierda inválida");
        assertTrue(tablero[8][8] >= 0 && tablero[8][8] <= 9, "Esquina inferior derecha inválida");
    }

}


