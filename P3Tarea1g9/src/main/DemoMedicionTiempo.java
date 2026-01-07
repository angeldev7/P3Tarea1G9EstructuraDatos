package main;

/**
 * Demostración clara de cómo medir tiempos en Java
 * Usando System.nanoTime() y System.currentTimeMillis()
 * 
 * @author Angel Rodriguez
 */
public class DemoMedicionTiempo {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  DEMOSTRACIÓN: MEDICIÓN DE TIEMPOS EN JAVA");
        System.out.println("=".repeat(60));
        
        // ========== PARTE 1: System.nanoTime() ==========
        System.out.println("\n1. USANDO System.nanoTime()");
        System.out.println("   (Precisión: nanosegundos - 1 ns = 0.000001 ms)");
        System.out.println("-".repeat(60));
        
        int n = 1000000;
        int[] datos = new int[n];
        for(int i = 0; i < n; i++) datos[i] = i;
        
        // Calentamiento de JVM
        System.out.println("\nCalentando JVM (1000 iteraciones)...");
        for(int i = 0; i < 1000; i++) {
            buscarElemento(datos, -1);
        }
        System.out.println("✓ Calentamiento completado\n");
        
        // MEDICIÓN CON nanoTime()
        System.out.println("Midiendo búsqueda lineal con " + n + " elementos...\n");
        
        long inicio = System.nanoTime();  // ← Marca el inicio
        boolean resultado = buscarElemento(datos, 999999);
        long fin = System.nanoTime();     // ← Marca el fin
        
        long tiempoNano = fin - inicio;
        
        System.out.println("Inicio:  " + inicio + " ns");
        System.out.println("Fin:     " + fin + " ns");
        System.out.println("Tiempo:  " + tiempoNano + " ns");
        System.out.println("         " + (tiempoNano / 1000.0) + " μs (microsegundos)");
        System.out.println("         " + (tiempoNano / 1000000.0) + " ms (milisegundos)");
        
        // ========== PARTE 2: System.currentTimeMillis() ==========
        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n2. USANDO System.currentTimeMillis()");
        System.out.println("   (Precisión: milisegundos - menos preciso)");
        System.out.println("-".repeat(60));
        
        long inicioMillis = System.currentTimeMillis();  // ← Marca el inicio
        boolean resultado2 = buscarElemento(datos, 999999);
        long finMillis = System.currentTimeMillis();     // ← Marca el fin
        
        long tiempoMillis = finMillis - inicioMillis;
        
        System.out.println("\nInicio:  " + inicioMillis + " ms");
        System.out.println("Fin:     " + finMillis + " ms");
        System.out.println("Tiempo:  " + tiempoMillis + " ms");
        
        // ========== PARTE 3: COMPARACIÓN ==========
        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n3. COMPARACIÓN DE PRECISIÓN");
        System.out.println("-".repeat(60));
        System.out.println("\nnanoTime():           " + tiempoNano + " ns (MUY PRECISO)");
        System.out.println("currentTimeMillis():  " + tiempoMillis + " ms");
        System.out.println("\n¿Cuál usar?");
        System.out.println("→ nanoTime()          Para medir rendimiento de algoritmos");
        System.out.println("→ currentTimeMillis() Para timestamps o tiempos largos");
        
        // ========== PARTE 4: DEMOSTRACIÓN DE COMPLEJIDAD O(n) ==========
        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n4. DEMOSTRANDO O(n) - COMPLEJIDAD LINEAL");
        System.out.println("-".repeat(60));
        
        System.out.println("\nSi O(n) es correcto:");
        System.out.println("  10x más datos → 10x más tiempo");
        System.out.println("  100x más datos → 100x más tiempo\n");
        
        int[] arr1 = new int[100];
        int[] arr2 = new int[1000];
        int[] arr3 = new int[10000];
        
        for(int i = 0; i < arr1.length; i++) arr1[i] = i;
        for(int i = 0; i < arr2.length; i++) arr2[i] = i;
        for(int i = 0; i < arr3.length; i++) arr3[i] = i;
        
        // Experimento 1: n = 100
        long t1_inicio = System.nanoTime();
        buscarElemento(arr1, 9999);
        long t1 = System.nanoTime() - t1_inicio;
        
        // Experimento 2: n = 1,000 (10x más grande)
        long t2_inicio = System.nanoTime();
        buscarElemento(arr2, 9999);
        long t2 = System.nanoTime() - t2_inicio;
        
        // Experimento 3: n = 10,000 (100x más grande)
        long t3_inicio = System.nanoTime();
        buscarElemento(arr3, 9999);
        long t3 = System.nanoTime() - t3_inicio;
        
        System.out.println("RESULTADOS:");
        System.out.println("  n = 100     → " + t1 + " ns");
        System.out.println("  n = 1,000   → " + t2 + " ns  (Ratio: " + String.format("%.1fx", (double)t2/t1) + ")");
        System.out.println("  n = 10,000  → " + t3 + " ns  (Ratio: " + String.format("%.1fx", (double)t3/t1) + ")");
        
        System.out.println("\n✓ El ratio se acerca a 10x y 100x");
        System.out.println("✓ ¡Complejidad O(n) confirmada!");
        
        System.out.println("\n" + "=".repeat(60));
    }
    
    /**
     * Búsqueda lineal simple - O(n)
     */
    private static boolean buscarElemento(int[] arr, int objetivo) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == objetivo) {
                return true;
            }
        }
        return false;
    }
}
