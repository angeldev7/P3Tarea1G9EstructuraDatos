package main;

import algoritmos.*;
import java.util.Scanner;

/**
 * Análisis Empírico de Complejidad Algorítmica
 * Demostración práctica de notación Big-O mediante medición de rendimiento
 * 
 * @author Diego Montesdeoca, Angel Rodriguez
 * @course Estructura de Datos - ESPE
 */
public class MainApp {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean salir = false;
        
        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1: demostrarO1(); break;
                case 2: demostrarON(); break;
                case 3: demostrarON2(); break;
                case 4: compararTodos(); break;
                case 0: 
                    System.out.println("\nGracias por usar el programa!");
                    salir = true;
                    break;
                default:
                    System.out.println("\nOpción inválida. Intenta de nuevo.");
            }
        }
        scanner.close();
    }
    
    // ============ MENÚ ============
    private static void mostrarMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("   ANÁLISIS DE COMPLEJIDAD ALGORÍTMICA");
        System.out.println("=".repeat(50));
        System.out.println("[1] Demostración O(1) - Constante");
        System.out.println("[2] Demostración O(n) - Lineal");
        System.out.println("[3] Demostración O(n²) - Cuadrática");
        System.out.println("[4] Comparar todas las complejidades");
        System.out.println("[0] Salir");
        System.out.print("\nSelecciona: ");
    }
    
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
    
    // ============ DEMOSTRACIÓN 1: O(1) - CONSTANTE ============
    private static void demostrarO1() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("   DEMOSTRACIÓN: O(1) - COMPLEJIDAD CONSTANTE");
        System.out.println("=".repeat(50));
        
        System.out.println("\n¿Qué hace?");
        System.out.println("→ Accede al primer elemento de un arreglo");
        System.out.println("→ Tiempo constante, no importa el tamaño\n");
        
        // Generar datos
        int n1 = 1000;
        int n2 = 1000000; // 1000x más grande
        int[] datos1 = generarArreglo(n1);
        int[] datos2 = generarArreglo(n2);
        
        // Calentamiento de JVM
        System.out.println("Calentando JVM...");
        for(int i = 0; i < 1000; i++) {
            EjemplosComplejidad.ejemploO1(datos1);
        }
        
        // Experimento
        long tiempo1 = medirOperacion(datos1, n1);
        long tiempo2 = medirOperacion(datos2, n2);
        
        // Resultados
        System.out.println("\n" + "-".repeat(50));
        System.out.println("RESULTADOS:");
        System.out.println("n = " + n1 + "       → " + tiempo1 + " ns");
        System.out.println("n = " + n2 + "    → " + tiempo2 + " ns");
        System.out.println("Ratio: " + String.format("%.2f", (double)tiempo2/tiempo1) + "x");
        System.out.println("\n✓ Ratio ≈ 1 confirma O(1) - ¡Tiempo constante!");
        System.out.println("-".repeat(50));
        
        pausar();
    }
    
    // ============ DEMOSTRACIÓN 2: O(n) - LINEAL ============
    private static void demostrarON() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("   DEMOSTRACIÓN: O(n) - COMPLEJIDAD LINEAL");
        System.out.println("=".repeat(50));
        
        System.out.println("\n¿Qué hace?");
        System.out.println("→ Búsqueda lineal (peor caso: elemento no existe)");
        System.out.println("→ Tiempo crece proporcionalmente a n\n");
        
        // Generar datos
        int n1 = 100;
        int n2 = 1000;   // 10x más grande
        int n3 = 10000;  // 100x más grande
        
        int[] datos1 = AnalizadorComplejidad.generarArregloAleatorio(n1, 1000);
        int[] datos2 = AnalizadorComplejidad.generarArregloAleatorio(n2, 1000);
        int[] datos3 = AnalizadorComplejidad.generarArregloAleatorio(n3, 1000);
        
        // Calentamiento de JVM
        System.out.println("Calentando JVM...");
        for(int i = 0; i < 1000; i++) {
            EjemplosComplejidad.ejemploON(datos1, 9999);
        }
        
        // Experimentos
        System.out.println("Ejecutando experimentos...\n");
        
        long inicio1 = System.nanoTime();
        EjemplosComplejidad.ejemploON(datos1, 9999);
        long tiempo1 = System.nanoTime() - inicio1;
        
        long inicio2 = System.nanoTime();
        EjemplosComplejidad.ejemploON(datos2, 9999);
        long tiempo2 = System.nanoTime() - inicio2;
        
        long inicio3 = System.nanoTime();
        EjemplosComplejidad.ejemploON(datos3, 9999);
        long tiempo3 = System.nanoTime() - inicio3;
        
        // Resultados
        System.out.println("-".repeat(50));
        System.out.println("RESULTADOS:");
        System.out.println("n = " + n1 + "      → " + tiempo1 + " ns");
        System.out.println("n = " + n2 + "     → " + tiempo2 + " ns  (Ratio: " + 
                         String.format("%.1f", (double)tiempo2/tiempo1) + "x)");
        System.out.println("n = " + n3 + "    → " + tiempo3 + " ns  (Ratio: " + 
                         String.format("%.1f", (double)tiempo3/tiempo1) + "x)");
        System.out.println("\n✓ Ratio ≈ 10x y 100x confirma O(n) - ¡Lineal!");
        System.out.println("-".repeat(50));
        
        pausar();
    }
    
    // ============ DEMOSTRACIÓN 3: O(n²) - CUADRÁTICA ============
    private static void demostrarON2() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("   DEMOSTRACIÓN: O(n²) - COMPLEJIDAD CUADRÁTICA");
        System.out.println("=".repeat(50));
        
        System.out.println("\n¿Qué hace?");
        System.out.println("→ Bubble Sort (dos ciclos anidados)");
        System.out.println("→ Si n se duplica, tiempo se cuadruplica\n");
        
        // Generar datos
        int n1 = 100;
        int n2 = 200;  // 2x → esperamos 4x tiempo
        int n3 = 400;  // 4x → esperamos 16x tiempo
        
        int[] datos1 = AnalizadorComplejidad.generarArregloAleatorio(n1, 1000);
        int[] datos2 = AnalizadorComplejidad.generarArregloAleatorio(n2, 1000);
        int[] datos3 = AnalizadorComplejidad.generarArregloAleatorio(n3, 1000);
        
        // Calentamiento de JVM
        System.out.println("Calentando JVM...");
        for(int i = 0; i < 100; i++) {
            int[] temp = AnalizadorComplejidad.copiarArreglo(datos1);
            EjemplosComplejidad.ejemploON2(temp);
        }
        
        // Experimentos
        System.out.println("Ejecutando experimentos...\n");
        
        int[] copia1 = AnalizadorComplejidad.copiarArreglo(datos1);
        long inicio1 = System.nanoTime();
        EjemplosComplejidad.ejemploON2(copia1);
        long tiempo1 = System.nanoTime() - inicio1;
        
        int[] copia2 = AnalizadorComplejidad.copiarArreglo(datos2);
        long inicio2 = System.nanoTime();
        EjemplosComplejidad.ejemploON2(copia2);
        long tiempo2 = System.nanoTime() - inicio2;
        
        int[] copia3 = AnalizadorComplejidad.copiarArreglo(datos3);
        long inicio3 = System.nanoTime();
        EjemplosComplejidad.ejemploON2(copia3);
        long tiempo3 = System.nanoTime() - inicio3;
        
        // Resultados
        System.out.println("-".repeat(50));
        System.out.println("RESULTADOS:");
        System.out.println("n = " + n1 + "  → " + tiempo1 + " ns");
        System.out.println("n = " + n2 + "  → " + tiempo2 + " ns  (Ratio: " + 
                         String.format("%.1f", (double)tiempo2/tiempo1) + "x, esperado: 4x)");
        System.out.println("n = " + n3 + "  → " + tiempo3 + " ns  (Ratio: " + 
                         String.format("%.1f", (double)tiempo3/tiempo1) + "x, esperado: 16x)");
        System.out.println("\n✓ Crece cuadráticamente: 2x input → 4x tiempo");
        System.out.println("-".repeat(50));
        
        pausar();
    }
    
    // ============ COMPARACIÓN COMPLETA ============
    private static void compararTodos() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("   COMPARACIÓN: O(1) vs O(n) vs O(n²)");
        System.out.println("=".repeat(50));
        
        System.out.println("\nTodos trabajarán con 1,000 elementos\n");
        
        int n = 1000;
        int[] datos = AnalizadorComplejidad.generarArregloAleatorio(n, 1000);
        
        // O(1)
        long t1 = System.nanoTime();
        EjemplosComplejidad.ejemploO1(datos);
        long tiempoO1 = System.nanoTime() - t1;
        
        // O(n)
        long t2 = System.nanoTime();
        EjemplosComplejidad.ejemploON(datos, 9999);
        long tiempoON = System.nanoTime() - t2;
        
        // O(n²) - usamos menos datos
        System.out.println("Nota: O(n²) usa solo 100 elementos (muy lento con 1,000)\n");
        int[] datos100 = AnalizadorComplejidad.generarArregloAleatorio(100, 1000);
        int[] copia = AnalizadorComplejidad.copiarArreglo(datos100);
        long t3 = System.nanoTime();
        EjemplosComplejidad.ejemploON2(copia);
        long tiempoON2 = System.nanoTime() - t3;
        
        // Resultados
        System.out.println("-".repeat(50));
        System.out.println("TIEMPOS DE EJECUCIÓN:");
        System.out.println("O(1)   → " + tiempoO1 + " ns  ★★★★★ (Súper rápido)");
        System.out.println("O(n)   → " + tiempoON + " ns  ★★★☆☆ (Rápido)");
        System.out.println("O(n²)  → " + tiempoON2 + " ns  ★☆☆☆☆ (Lento, n=100)");
        System.out.println("\n✓ Conclusión: Usa O(1) u O(n) siempre que puedas");
        System.out.println("✓ Evita O(n²) con datasets grandes");
        System.out.println("-".repeat(50));
        
        pausar();
    }
    
    // ============ UTILIDADES ============
    private static int[] generarArreglo(int n) {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }
    
    private static long medirOperacion(int[] datos, int n) {
        long inicio = System.nanoTime();
        int resultado = EjemplosComplejidad.ejemploO1(datos);
        long fin = System.nanoTime();
        return fin - inicio;
    }
    
    private static void pausar() {
        System.out.print("\nPresiona ENTER para continuar...");
        scanner.nextLine();
    }
}
