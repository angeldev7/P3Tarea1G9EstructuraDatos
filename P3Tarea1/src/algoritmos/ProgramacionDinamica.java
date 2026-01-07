package algoritmos;

/**
 * Implementación de algoritmos de Programación Dinámica
 * Paradigma: Almacena resultados de subproblemas para evitar recalcularlos
 * Complejidad: Reduce de exponencial a polinomial en muchos casos
 * 
 * Principios:
 * 1. Subestructura óptima: la solución óptima contiene soluciones óptimas de subproblemas
 * 2. Subproblemas superpuestos: los mismos subproblemas se resuelven múltiples veces
 */
public class ProgramacionDinamica {
    
    /**
     * FIBONACCI - Versión con Programación Dinámica (Memoization)
     * Sin PD: O(2^n) - recursión simple
     * Con PD: O(n) - cada valor se calcula una vez
     * Espacio: O(n)
     */
    public static long fibonacciMemoization(int n) {
        long[] memo = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return fibMemo(n, memo);
    }
    
    private static long fibMemo(int n, long[] memo) {
        if (n <= 1) {
            return n;
        }
        
        if (memo[n] != -1) {
            return memo[n]; // Ya calculado
        }
        
        memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        return memo[n];
    }
    
    /**
     * FIBONACCI - Versión con Tabulación (Bottom-up)
     * Complejidad: O(n)
     * Espacio: O(n) - puede optimizarse a O(1)
     */
    public static long fibonacciTabulacion(int n) {
        if (n <= 1) {
            return n;
        }
        
        long[] tabla = new long[n + 1];
        tabla[0] = 0;
        tabla[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            tabla[i] = tabla[i - 1] + tabla[i - 2];
        }
        
        return tabla[n];
    }
    
    /**
     * PROBLEMA DE LA MOCHILA 0-1 (Knapsack Problem)
     * Dado un conjunto de items con peso y valor, y una capacidad máxima,
     * determinar la combinación de items que maximiza el valor sin exceder la capacidad
     * 
     * Complejidad: O(n * W) donde n = número de items, W = capacidad
     * Espacio: O(n * W)
     */
    public static class ResultadoMochila {
        public int valorMaximo;
        public boolean[] itemsSeleccionados;
        
        public ResultadoMochila(int valorMaximo, boolean[] itemsSeleccionados) {
            this.valorMaximo = valorMaximo;
            this.itemsSeleccionados = itemsSeleccionados;
        }
    }
    
    public static ResultadoMochila mochila01(int[] pesos, int[] valores, int capacidad) {
        int n = pesos.length;
        int[][] dp = new int[n + 1][capacidad + 1];
        
        // Construir tabla dp de forma bottom-up
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacidad; w++) {
                if (pesos[i - 1] <= w) {
                    // Podemos incluir el item i-1
                    // Máximo entre incluirlo o no
                    dp[i][w] = Math.max(
                        valores[i - 1] + dp[i - 1][w - pesos[i - 1]], // Incluir
                        dp[i - 1][w]  // No incluir
                    );
                } else {
                    // No podemos incluir el item i-1 (excede capacidad)
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        // Reconstruir la solución (qué items fueron seleccionados)
        boolean[] seleccionados = new boolean[n];
        int w = capacidad;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                seleccionados[i - 1] = true;
                w -= pesos[i - 1];
            }
        }
        
        return new ResultadoMochila(dp[n][capacidad], seleccionados);
    }
    
    /**
     * SUBSECUENCIA COMÚN MÁS LARGA (Longest Common Subsequence - LCS)
     * Encuentra la subsecuencia más larga que aparece en ambas cadenas
     * (no necesariamente consecutiva)
     * 
     * Complejidad: O(m * n) donde m y n son las longitudes de las cadenas
     * Espacio: O(m * n)
     */
    public static String lcs(String texto1, String texto2) {
        int m = texto1.length();
        int n = texto2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // Construir la tabla dp
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (texto1.charAt(i - 1) == texto2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Reconstruir la LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        
        while (i > 0 && j > 0) {
            if (texto1.charAt(i - 1) == texto2.charAt(j - 1)) {
                lcs.insert(0, texto1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        
        return lcs.toString();
    }
    
    /**
     * PROBLEMA DEL CAMBIO DE MONEDAS
     * Dado un conjunto de denominaciones y una cantidad objetivo,
     * encuentra el número mínimo de monedas necesarias
     * 
     * Complejidad: O(n * cantidad) donde n = número de denominaciones
     * Espacio: O(cantidad)
     */
    public static int cambioMonedas(int[] denominaciones, int cantidad) {
        int[] dp = new int[cantidad + 1];
        
        // Inicializar con valor máximo (infinito)
        for (int i = 1; i <= cantidad; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0; // 0 monedas para cantidad 0
        
        // Para cada denominación
        for (int denom : denominaciones) {
            // Actualizar todas las cantidades que pueden formarse
            for (int i = denom; i <= cantidad; i++) {
                if (dp[i - denom] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - denom] + 1);
                }
            }
        }
        
        return dp[cantidad] == Integer.MAX_VALUE ? -1 : dp[cantidad];
    }
    
    /**
     * SUBSECUENCIA CRECIENTE MÁS LARGA (Longest Increasing Subsequence - LIS)
     * Complejidad: O(n²) - versión básica con PD
     * Espacio: O(n)
     */
    public static int lis(int[] arreglo) {
        if (arreglo == null || arreglo.length == 0) {
            return 0;
        }
        
        int n = arreglo.length;
        int[] dp = new int[n];
        
        // Cada elemento es una subsecuencia de longitud 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        
        // Para cada elemento
        for (int i = 1; i < n; i++) {
            // Revisar todos los elementos anteriores
            for (int j = 0; j < i; j++) {
                if (arreglo[j] < arreglo[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        // Encontrar el máximo en dp
        int maxLIS = 0;
        for (int i = 0; i < n; i++) {
            maxLIS = Math.max(maxLIS, dp[i]);
        }
        
        return maxLIS;
    }
}
