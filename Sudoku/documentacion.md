## DOCUMENTACIÓN


## Objetivos
- Desarrollar el juego del Sudoku
- Pruebas de validación


## Requisitos funcionales
| ID    | Descripción                                                    | Prioridad | Fuente  | Estado       |
| ----- | -------------------------------------------------------------- | --------- | ------- | ------------ |
| RF-01 | El sistema debe generar un tablero Sudoku válido.              | Alta      | Usuario | Implementado |
| RF-02 | El usuario puede rellenar celdas con números del 1 al 9.       | Alta      | Usuario | Implementado |
| RF-03 | Validar si la solución introducida por el usuario es correcta. | Alta      | Usuario | Implementado |
| RF-04 | Resolver automáticamente el tablero actual.                    | Media     | Usuario | Implementado |

## Requisitos no funcionales
| ID     | Descripción                                            | Categoría      | Métrica          | Nivel Objetivo | Comentarios                   |
| ------ | ------------------------------------------------------ | -------------- | ---------------- | -------------- | ----------------------------- |
| RNF-01 | La interfaz debe responder en menos de 1 segundo.         | Rendimiento    | Tiempo respuesta | < 1 segundo    | Validado en entorno local.    |
| RNF-02 | Código documentado y comentado. | Mantenibilidad | Porcentaje doc.  | ≥ 90 %         | Incluido en todas las clases. |
| RNF-03 | Aplicación debe ejecutarse sin errores en Java 11+.    | Compatibilidad | Versiones Java   | Java 11 y 17   | Probado con OpenJDK.          |


## Casos de uso
| ID    | Nombre                           | Actor   | Flujo Principal                                                             | Postcondición                  |
| ----- | -------------------------------- | ------- | --------------------------------------------------------------------------- | ------------------------------ |
| CU-01 | Iniciar partida                  | Usuario | 1. Usuario abre aplicación<br>2. Sistema genera tablero                     | Tablero visible                |
| CU-02 | Introducir número en celda       | Usuario | 1. Usuario hace clic en celda<br>2. Ingresa número<br>3. Valida restricción | Número insertado si es válido  |
| CU-03 | Verificar solución               | Usuario | 1. Usuario pulsa botón "Verificar"<br>2. Sistema analiza y da resultado     | Feedback mostrado al usuario   |
| CU-04 | Resolver tablero automáticamente | Usuario | 1. Usuario pulsa botón "Resolver"<br>2. Algoritmo llena el tablero          | Tablero resuelto completamente |



## Diagramas
``` plantuml
@startuml
package "proyectoFinal" {
  
  class Principal {
    + main(String[]): void
  }

  class SudokuGUI {
    - sudoku: Sudoku
    - campos: JTextField[][]
    + SudokuGUI(dificultad: String)
    + SudokuGUI()
  }

  class Sudoku {
    - tablero: int[][]
    - celdasFijas: boolean[][]
    + getTablero(): int[][]
    + getCeldasFijas(): boolean[][]
    + generarTablero(dificultad: String): void
    + colocarNumero(fila: int, columna: int, valor: int): boolean
    + estaResuelto(): boolean
    + esMovimientoValido(fila: int, columna: int, valor: int): boolean
    + mostrarTablero(): void
  }

  class SudokuException {
    + SudokuException(msg: String)
  }

  class MovimientoInvalidoException {
    + MovimientoInvalidoException(msg: String)
  }

  class EntradaFueraDeRangoException {
    + EntradaFueraDeRangoException(msg: String)
  }

  class CeldaFijaException {
    + CeldaFijaException()
  }

  SudokuException <|-- MovimientoInvalidoException
  SudokuException <|-- EntradaFueraDeRangoException
  SudokuException <|-- CeldaFijaException

  Principal --> SudokuGUI
  SudokuGUI --> Sudoku
  Sudoku --> SudokuException
}
@enduml
```


## Matriz de trazabilidad
| Requisito | Clase / Módulo          | Método Principal            | Caso de Uso | Test Unitario         |
| --------- | ----------------------- | --------------------------- | ----------- | --------------------- |
| RF-01     | GeneradorSudoku         | `generar()`                 | CU-01       | `testGeneracion()`    |
| RF-02     | Celda / InterfazGrafica | `setValor()`, `onClick()`   | CU-02       | `testEntradaCelda()`  |
| RF-03     | Sudoku                  | `verificar()`, `esValido()` | CU-03       | `testVerificacion()`  |
| RF-04     | Solucionador            | `resolver()`                | CU-04       | `testSolucionador()`  |
| RNF-01    | InterfazGrafica         | `render()`                  | Todos       | `testRendimiento()`   |
| RNF-02    | Todo el código          | Comentarios JavaDoc         | —           | Revisión manual       |
| RNF-03    | Proyecto en general     | Estructura Maven o Ant      | —           | Probado en varios JDK |




