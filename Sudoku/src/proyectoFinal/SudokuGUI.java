package proyectoFinal;

import javax.swing.*;
import java.awt.*;

public class SudokuGUI extends JFrame {

    private Sudoku sudoku;
    private JTextField[][] campos = new JTextField[9][9];

    public SudokuGUI(String dificultad) {
        sudoku = new Sudoku(); // Crea el tablero
        sudoku.generarTablero("dificultad");

        // Ventana principal
        setTitle("SUDOKU - Dificultad: " + dificultad.toUpperCase());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout());

        JPanel panelTablero = new JPanel(new GridLayout(9, 9));
        JPanel panelBoton = new JPanel();

        int[][] tablero = sudoku.getTablero();
        boolean[][] fijas = sudoku.getCeldasFijas();

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                JTextField campo = new JTextField();
                campo.setHorizontalAlignment(JTextField.CENTER); // Centrado

                if (tablero[fila][col] != 0) {
                    campo.setText(String.valueOf(tablero[fila][col]));
                    campo.setEditable(false);
                    campo.setBackground(Color.LIGHT_GRAY);
                }
                campos[fila][col] = campo;
                panelTablero.add(campo);
            }
        }

        JButton verificar = new JButton("Verificar");
        verificar.addActionListener(e -> {
            for (int fila = 0; fila < 9; fila++) {
                for (int col = 0; col < 9; col++) {
                    if (!fijas[fila][col]) {
                        try {
                            int val = Integer.parseInt(campos[fila][col].getText());
                            sudoku.colocarNumero(fila, col, val);
                        } catch (NumberFormatException ignored) { // Esta excepcion es cuando intentas convertir una cadena a numero pero lo que introduces no es valido
                            // Campo vacío o no numérico
                        }
                    }
                }
            }

            if (sudoku.estaResuelto()) {
                JOptionPane.showMessageDialog(this, "¡Sudoku resuelto! :)");
            } else {
                JOptionPane.showMessageDialog(this, "Hay errores :)");
            }
        });

        panelBoton.add(verificar);

        add(panelTablero, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
        setVisible(true);
    }

    // Constructor por defecto (opcional)
    public SudokuGUI() {
        this("facil");
    }
}


