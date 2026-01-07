package algoritmos;

/**
 * Backtracking - Búsqueda exhaustiva con retroceso
 */
public class Backtracking {
    
    // Clase simple para N-Reinas
    public static class SolucionReinas {
        public int[][] tablero;
        public int n;
        
        public SolucionReinas(int n) {
            this.n = n;
            this.tablero = new int[n][n];
        }
        
        public String toString() {
            String s = "";
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    s += (tablero[i][j] == 1 ? " Q " : " · ");
                }
                s += "\n";
            }
            return s;
        }
    }
    
    /**
     * N-Reinas - O(N!)
     */
    public static SolucionReinas resolverNReinas(int n) {
        SolucionReinas sol = new SolucionReinas(n);
        if (colocarReinas(sol, 0)) return sol;
        return null;
    }
    
    private static boolean colocarReinas(SolucionReinas sol, int col) {
        if (col >= sol.n) return true;
        
        for (int fila = 0; fila < sol.n; fila++) {
            if (esSeguro(sol, fila, col)) {
                sol.tablero[fila][col] = 1;
                
                if (colocarReinas(sol, col + 1)) return true;
                
                sol.tablero[fila][col] = 0; // Backtrack
            }
        }
        return false;
    }
    
    private static boolean esSeguro(SolucionReinas sol, int fila, int col) {
        // Verificar fila
        for (int j = 0; j < col; j++) {
            if (sol.tablero[fila][j] == 1) return false;
        }
        
        // Diagonal superior
        for (int i = fila, j = col; i >= 0 && j >= 0; i--, j--) {
            if (sol.tablero[i][j] == 1) return false;
        }
        
        // Diagonal inferior
        for (int i = fila, j = col; i < sol.n && j >= 0; i++, j--) {
            if (sol.tablero[i][j] == 1) return false;
        }
        
        return true;
    }
    
    /**
     * Sudoku 4x4
     */
    public static boolean resolverSudoku4x4(int[][] tablero) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tablero[i][j] == 0) {
                    for (int num = 1; num <= 4; num++) {
                        if (esValidoSudoku(tablero, i, j, num)) {
                            tablero[i][j] = num;
                            
                            if (resolverSudoku4x4(tablero)) return true;
                            
                            tablero[i][j] = 0; // Backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private static boolean esValidoSudoku(int[][] t, int fila, int col, int num) {
        // Verificar fila y columna
        for (int i = 0; i < 4; i++) {
            if (t[fila][i] == num || t[i][col] == num) return false;
        }
        
        // Verificar subcuadrícula 2x2
        int filaInicio = fila - fila % 2;
        int colInicio = col - col % 2;
        
        for (int i = filaInicio; i < filaInicio + 2; i++) {
            for (int j = colInicio; j < colInicio + 2; j++) {
                if (t[i][j] == num) return false;
            }
        }
        
        return true;
    }
    
    /**
     * Suma de subconjuntos
     */
    public static int[][] sumaSubconjuntos(int[] conjunto, int objetivo) {
        int[][] soluciones = new int[100][100];
        int[] actual = new int[100];
        int[] contador = {0};
        
        encontrarSubconjuntos(conjunto, objetivo, 0, 0, actual, 0, soluciones, contador);
        
        int[][] resultado = new int[contador[0]][];
        for (int i = 0; i < contador[0]; i++) {
            int len = 0;
            while (len < 100 && soluciones[i][len] != 0) len++;
            resultado[i] = new int[len];
            for (int j = 0; j < len; j++) {
                resultado[i][j] = soluciones[i][j];
            }
        }
        return resultado;
    }
    
    private static void encontrarSubconjuntos(int[] conjunto, int objetivo, int idx, int suma,
                                             int[] actual, int tamActual, int[][] sols, int[] count) {
        if (suma == objetivo) {
            for (int i = 0; i < tamActual; i++) {
                sols[count[0]][i] = actual[i];
            }
            count[0]++;
            return;
        }
        
        if (suma > objetivo || idx >= conjunto.length) return;
        
        // Incluir elemento
        actual[tamActual] = conjunto[idx];
        encontrarSubconjuntos(conjunto, objetivo, idx + 1, suma + conjunto[idx], 
                             actual, tamActual + 1, sols, count);
        
        // No incluir
        encontrarSubconjuntos(conjunto, objetivo, idx + 1, suma, actual, tamActual, sols, count);
    }
    
    /**
     * Coloración de grafos
     */
    public static int[] colorearGrafo(int[][] grafo, int numColores) {
        int n = grafo.length;
        int[] colores = new int[n];
        for (int i = 0; i < n; i++) colores[i] = -1;
        
        if (colorear(grafo, numColores, colores, 0)) return colores;
        return null;
    }
    
    private static boolean colorear(int[][] grafo, int numColores, int[] colores, int vertice) {
        if (vertice == grafo.length) return true;
        
        for (int color = 0; color < numColores; color++) {
            if (esColorSeguro(grafo, colores, vertice, color)) {
                colores[vertice] = color;
                
                if (colorear(grafo, numColores, colores, vertice + 1)) return true;
                
                colores[vertice] = -1; // Backtrack
            }
        }
        return false;
    }
    
    private static boolean esColorSeguro(int[][] grafo, int[] colores, int v, int color) {
        for (int i = 0; i < grafo.length; i++) {
            if (grafo[v][i] == 1 && colores[i] == color) return false;
        }
        return true;
    }
}
