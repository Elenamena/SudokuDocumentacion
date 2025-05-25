package proyectoFinal;

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Principal {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            String[] opciones = {"Fácil", "Medio", "Difícil"};
            String dificultad = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona dificultad:",
                "Menú Sudoku",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (dificultad != null) {
            	try {
                    new SudokuGUI(dificultad.toLowerCase());
                } catch (Exception e) { // Esta excepcion es por si al intentar crear lo anterior hay un error pues se hace esta excepcion
                    e.printStackTrace(); // Mostrar error
                    JOptionPane.showMessageDialog(null, "Error");
                }
            } else {
                System.exit(0);
            }
        });
    }
}


