package algoritmos;

/**
 * Utilidades para análisis de complejidad y medición de rendimiento
 * Permite demostrar empíricamente las diferencias entre algoritmos
 */
public class AnalizadorComplejidad {
    
    /**
     * Mide el tiempo de ejecución de una operación
     */
    public static long medirTiempo(Runnable operacion) {
        long inicio = System.nanoTime();
        operacion.run();
        long fin = System.nanoTime();
        return fin - inicio;
    }
    
    /**
     * Formatea el tiempo en nanosegundos a una representación legible
     */
    public static String formatearTiempo(long nanosegundos) {
        if (nanosegundos < 1000) {
            return nanosegundos + " ns";
        } else if (nanosegundos < 1000000) {
            return String.format("%.2f μs", nanosegundos / 1000.0);
        } else if (nanosegundos < 1000000000) {
            return String.format("%.2f ms", nanosegundos / 1000000.0);
        } else {
            return String.format("%.2f s", nanosegundos / 1000000000.0);
        }
    }
    
    /**
     * Compara el rendimiento de dos algoritmos
     */
    public static void compararAlgoritmos(String nombre1, Runnable alg1, 
                                          String nombre2, Runnable alg2) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        COMPARACIÓN DE RENDIMIENTO DE ALGORITMOS           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\nEjecutando " + nombre1 + "...");
        long tiempo1 = medirTiempo(alg1);
        
        System.out.println("Ejecutando " + nombre2 + "...");
        long tiempo2 = medirTiempo(alg2);
        
        System.out.println("\n┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ RESULTADOS:                                                │");
        System.out.println("├────────────────────────────────────────────────────────────┤");
        System.out.printf("│ %-30s : %20s │\n", nombre1, formatearTiempo(tiempo1));
        System.out.printf("│ %-30s : %20s │\n", nombre2, formatearTiempo(tiempo2));
        System.out.println("├────────────────────────────────────────────────────────────┤");
        
        if (tiempo1 < tiempo2) {
            double factor = (double) tiempo2 / tiempo1;
            System.out.printf("│ %s es %.2fx más rápido      │\n", 
                nombre1, factor);
        } else {
            double factor = (double) tiempo1 / tiempo2;
            System.out.printf("│ %s es %.2fx más rápido      │\n", 
                nombre2, factor);
        }
        System.out.println("└────────────────────────────────────────────────────────────┘");
    }
    
    /**
     * Muestra la notación Big-O de forma visual
     */
    public static void mostrarComplejidades() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         JERARQUÍA DE COMPLEJIDADES COMPUTACIONALES         ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║                                                            ║");
        System.out.println("║  Complejidad  │  Nombre           │  Ejemplo                ║");
        System.out.println("║  ────────────────────────────────────────────────────────  ║");
        System.out.println("║  O(1)         │  Constante        │  Acceso a arreglo       ║");
        System.out.println("║  O(log n)     │  Logarítmica      │  Búsqueda binaria       ║");
        System.out.println("║  O(n)         │  Lineal           │  Búsqueda secuencial    ║");
        System.out.println("║  O(n log n)   │  Linealítmica     │  Merge Sort, Quick Sort ║");
        System.out.println("║  O(n²)        │  Cuadrática       │  Bubble Sort, Insertion ║");
        System.out.println("║  O(n³)        │  Cúbica           │  Multiplicación matrices║");
        System.out.println("║  O(2ⁿ)        │  Exponencial      │  Fibonacci recursivo    ║");
        System.out.println("║  O(n!)        │  Factorial        │  Permutaciones, TSP     ║");
        System.out.println("║                                                            ║");
        System.out.println("║  Escalabilidad (de mejor a peor): ↑ hacia abajo ↓         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Genera un arreglo aleatorio para pruebas
     */
    public static int[] generarArregloAleatorio(int tamanio, int valorMax) {
        int[] arr = new int[tamanio];
        for (int i = 0; i < tamanio; i++) {
            arr[i] = (int) (Math.random() * valorMax);
        }
        return arr;
    }
    
    /**
     * Copia un arreglo
     */
    public static int[] copiarArreglo(int[] original) {
        int[] copia = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copia[i] = original[i];
        }
        return copia;
    }
    
    /**
     * Imprime un arreglo de forma legible
     */
    public static void imprimirArreglo(int[] arr, int maxElementos) {
        System.out.print("[");
        int limite = Math.min(arr.length, maxElementos);
        for (int i = 0; i < limite; i++) {
            System.out.print(arr[i]);
            if (i < limite - 1) {
                System.out.print(", ");
            }
        }
        if (arr.length > maxElementos) {
            System.out.print(", ...");
        }
        System.out.print("]");
    }
}
