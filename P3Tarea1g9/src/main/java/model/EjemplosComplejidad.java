package model;

/**
 * Ejemplos de diferentes complejidades algorítmicas
 * para demostrar la notación asintótica y aritmética de notación O
 */
public class EjemplosComplejidad {
    
    // ==================== O(1) - CONSTANTE ====================
    /**
     * Complejidad: O(1) - Tiempo constante
     * El tiempo de ejecución NO depende del tamaño de entrada
     */
    public static int ejemploO1(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        return arr[0]; // Acceso directo - siempre toma el mismo tiempo
    }
    
    // ==================== O(log n) - LOGARÍTMICA ====================
    /**
     * Complejidad: O(log n) - Búsqueda Binaria
     * En cada paso reduce el problema a la mitad
     */
    public static int ejemploOLogN(int[] arr, int objetivo) {
        int izq = 0, der = arr.length - 1;
        while (izq <= der) {
            int medio = izq + (der - izq) / 2;
            if (arr[medio] == objetivo) return medio;
            if (arr[medio] < objetivo) izq = medio + 1;
            else der = medio - 1;
        }
        return -1;
    }
    
    // ==================== O(n) - LINEAL ====================
    /**
     * Complejidad: O(n) - Búsqueda Secuencial
     * Recorre todos los elementos una vez
     */
    public static int ejemploON(int[] arr, int objetivo) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == objetivo) return i;
        }
        return -1;
    }
    
    /**
     * Complejidad: O(n) - Suma de elementos
     * Un solo ciclo que recorre n elementos
     */
    public static int sumaElementos(int[] arr) {
        int suma = 0;
        for (int i = 0; i < arr.length; i++) {
            suma += arr[i];
        }
        return suma;
    }
    
    // ==================== O(n log n) - LINEALÍTMICA ====================
    /**
     * Complejidad: O(n log n) - Merge Sort simplificado
     * Divide: O(log n) niveles, Combina: O(n) en cada nivel
     */
    public static void ejemploONLogN(int[] arr) {
        if (arr.length <= 1) return;
        mergeSortHelper(arr, 0, arr.length - 1);
    }
    
    private static void mergeSortHelper(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int medio = inicio + (fin - inicio) / 2;
            mergeSortHelper(arr, inicio, medio);      // O(log n) divisiones
            mergeSortHelper(arr, medio + 1, fin);
            merge(arr, inicio, medio, fin);           // O(n) combinación
        }
    }
    
    private static void merge(int[] arr, int inicio, int medio, int fin) {
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;
        int[] izq = new int[n1];
        int[] der = new int[n2];
        
        for (int i = 0; i < n1; i++) izq[i] = arr[inicio + i];
        for (int j = 0; j < n2; j++) der[j] = arr[medio + 1 + j];
        
        int i = 0, j = 0, k = inicio;
        while (i < n1 && j < n2) {
            if (izq[i] <= der[j]) arr[k++] = izq[i++];
            else arr[k++] = der[j++];
        }
        while (i < n1) arr[k++] = izq[i++];
        while (j < n2) arr[k++] = der[j++];
    }
    
    // ==================== O(n²) - CUADRÁTICA ====================
    /**
     * Complejidad: O(n²) - Bubble Sort
     * Dos ciclos anidados, cada uno recorre n elementos
     */
    public static void ejemploON2(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {           // Ciclo externo: n veces
            for (int j = 0; j < n - i - 1; j++) {   // Ciclo interno: n veces
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    /**
     * Complejidad: O(n²) - Suma de todos los pares
     * Ejemplo clásico de dos ciclos anidados
     */
    public static int sumaPares(int[] arr) {
        int suma = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                suma += arr[i] + arr[j];
            }
        }
        return suma;
    }
    
    // ==================== O(n³) - CÚBICA ====================
    /**
     * Complejidad: O(n³) - Multiplicación de matrices
     * Tres ciclos anidados
     */
    public static int[][] ejemploON3(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        
        for (int i = 0; i < n; i++) {           // n iteraciones
            for (int j = 0; j < n; j++) {       // n iteraciones
                for (int k = 0; k < n; k++) {   // n iteraciones
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
    
    // ==================== O(2^n) - EXPONENCIAL ====================
    /**
     * Complejidad: O(2^n) - Fibonacci Recursivo (ineficiente)
     * Cada llamada genera dos llamadas más: árbol binario de altura n
     */
    public static long ejemploO2N(int n) {
        if (n <= 1) return n;
        return ejemploO2N(n - 1) + ejemploO2N(n - 2);
    }
    
    // ==================== ARITMÉTICA DE NOTACIÓN O ====================
    
    /**
     * Ejemplo: O(n) + O(n) = O(n)
     * Dos operaciones lineales secuenciales siguen siendo lineales
     */
    public static void aritmeticaSuma(int[] arr) {
        // Primera operación: O(n)
        int suma = 0;
        for (int i = 0; i < arr.length; i++) {
            suma += arr[i];
        }
        
        // Segunda operación: O(n)
        int maximo = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maximo) maximo = arr[i];
        }
        
        // Total: O(n) + O(n) = O(2n) = O(n) [ignoramos constantes]
    }
    
    /**
     * Ejemplo: O(n) * O(n) = O(n²)
     * Un ciclo dentro de otro
     */
    public static void aritmeticaProducto(int[] arr) {
        // Ciclo externo: O(n)
        for (int i = 0; i < arr.length; i++) {
            // Ciclo interno: O(n)
            for (int j = 0; j < arr.length; j++) {
                // Operación constante: O(1)
                int temp = arr[i] + arr[j];
            }
        }
        // Total: O(n) * O(n) = O(n²)
    }
    
    /**
     * Ejemplo: max(O(n²), O(n)) = O(n²)
     * Cuando hay varias operaciones, domina la de mayor complejidad
     */
    public static void aritmeticaMaximo(int[] arr) {
        // Operación 1: O(n²)
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int temp = arr[i] * arr[j];
            }
        }
        
        // Operación 2: O(n)
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i] * 2;
        }
        
        // Total: max(O(n²), O(n)) = O(n²) [el término dominante]
    }
    
    /**
     * Análisis de complejidad con múltiples términos
     * f(n) = 5n² + 100n + 300 = O(n²)
     */
    public static void analisisTerminos(int n) {
        // Término constante: O(1)
        int suma = 300;
        
        // Término lineal: O(n) - 100 iteraciones
        for (int i = 0; i < n; i++) {
            suma += i;
        }
        
        // Término cuadrático: O(n²) - 5 veces n²
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                suma += i + j;
            }
        }
        
        // Total: O(1) + O(n) + O(n²) = O(n²) [domina el término de mayor grado]
    }
}
