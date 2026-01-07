package main;

import interfaz.MenuConsola;
import estructuras.*;
import algoritmos.*;
import algoritmos.AlgoritmosVoraces.*;
import algoritmos.Backtracking.*;
import algoritmos.ProgramacionDinamica.*;
import java.util.Scanner;

/**
 * Demostración de Paradigmas Algorítmicos
 * @author Diego Montesdeoca, Angel Rodriguez
 * @course Estructura de Datos - ESPE
 */
public class MainApp {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean salir = false;
        
        while (!salir) {
            MenuConsola.mostrarMenuPrincipal();
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    menuEstructurasDatos();
                    break;
                case 2:
                    menuDivideYVenceras();
                    break;
                case 3:
                    menuProgramacionDinamica();
                    break;
                case 4:
                    menuAlgoritmosVoraces();
                    break;
                case 5:
                    menuBacktracking();
                    break;
                case 6:
                    mostrarAnalisisComplejidad();
                    break;
                case 7:
                    demostracionCompleta();
                    break;
                case 0:
                    System.out.println("\n" + "=".repeat(50));
                    System.out.println("           SISTEMA FINALIZADO");
                    System.out.println("=".repeat(50));
                    System.out.println("\nGracias por usar el sistema.");
                    salir = true;
                    break;
                default:
                    System.out.println("\nERROR: Ingrese un numero del 0 al 7");
                    MenuConsola.pausar();
            }
        }
        
        scanner.close();
    }
    
    // ============ MENÚ 1: ESTRUCTURAS DE DATOS ============
    
    private static void menuEstructurasDatos() {
        boolean volver = false;
        
        while (!volver) {
            MenuConsola.mostrarMenuEstructuras();
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    demoListaEnlazada();
                    break;
                case 2:
                    demoPila();
                    break;
                case 3:
                    demoCola();
                    break;
                case 4:
                    demoArbolBST();
                    break;
                case 5:
                    compararEstructuras();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    MenuConsola.mostrarError("Opción no válida");
                    MenuConsola.pausar();
            }
        }
    }
    
    private static void demoListaEnlazada() {
        MenuConsola.mostrarEncabezado("LISTA ENLAZADA");
        
        System.out.println("\nEstructura: Cadena de nodos enlazados\n");
        
        ListaEnlazada lista = new ListaEnlazada();
        
        System.out.println("Insertando elementos: 10, 20, 30, 40, 50");
        lista.insertar(50);
        lista.insertar(40);
        lista.insertar(30);
        lista.insertar(20);
        lista.insertar(10);
        System.out.println("Lista: " + lista);
        
        System.out.println("\nInsertando 60 al final");
        lista.insertarFinal(60);
        System.out.println("Lista: " + lista);
        
        System.out.println("\nBuscar 30: " + (lista.buscar(30) ? "Encontrado" : "No encontrado"));
        System.out.println("Buscar 100: " + (lista.buscar(100) ? "Encontrado" : "No encontrado"));
        
        System.out.println("\nEliminando 30");
        lista.eliminar(30);
        System.out.println("Lista: " + lista);
        
        System.out.println("\nComplejidad: O(1) insercion, O(n) busqueda");
        MenuConsola.pausar();
    }
    
    private static void demoPila() {
        MenuConsola.mostrarEncabezado("PILA (STACK)");
        
        System.out.println("\nEstructura: LIFO - Last In, First Out\n");
        
        Pila pila = new Pila();
        
        System.out.println("Apilando: A, B, C, D");
        pila.push("A");
        pila.push("B");
        pila.push("C");
        pila.push("D");
        System.out.println(pila);
        
        System.out.println("\nElemento en el tope: " + pila.peek());
        
        System.out.println("\nDesapilando elementos:");
        while (!pila.estaVacia()) {
            System.out.println("  Pop: " + pila.pop());
        }
        
        System.out.println("\nComplejidad: O(1) todas las operaciones");
        MenuConsola.pausar();
    }
    
    private static void demoCola() {
        MenuConsola.mostrarEncabezado("COLA (QUEUE)");
        
        System.out.println("\nEstructura: FIFO - First In, First Out\n");
        
        Cola cola = new Cola();
        
        System.out.println("Encolando: Cliente 1, Cliente 2, Cliente 3");
        cola.encolar("Cliente 1");
        cola.encolar("Cliente 2");
        cola.encolar("Cliente 3");
        System.out.println(cola);
        
        System.out.println("\nDesencolando elementos:");
        while (!cola.estaVacia()) {
            System.out.println("  Desencolado: " + cola.desencolar());
        }
        
        System.out.println("\nComplejidad: O(1) encolar y desencolar");
        MenuConsola.pausar();
    }
    
    private static void demoArbolBST() {
        MenuConsola.mostrarEncabezado("ARBOL BINARIO DE BUSQUEDA");
        
        System.out.println("\nEstructura: Arbol ordenado (izq < raiz < der)\n");
        
        ArbolBST arbol = new ArbolBST();
        
        System.out.println("Insertando: 50, 30, 70, 20, 40, 60, 80");
        int[] vals = {50, 30, 70, 20, 40, 60, 80};
        for (int v : vals) arbol.insertar(v);
        
        System.out.println("\nRecorrido InOrden (ascendente):");
        System.out.print("  ");
        arbol.inOrden();
        
        System.out.println("\nBuscar 40: " + (arbol.buscar(40) ? "Encontrado" : "No encontrado"));
        System.out.println("Altura del arbol: " + arbol.altura());
        
        System.out.println("\nComplejidad: O(log n) busqueda e insercion");
        MenuConsola.pausar();
    }
    
    private static void compararEstructuras() {
        MenuConsola.mostrarEncabezado("COMPARACIÓN DE COMPLEJIDADES - Estructuras de Datos");
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                TABLA COMPARATIVA DE COMPLEJIDADES                      ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Estructura      │ Acceso  │ Búsqueda │ Inserción │ Eliminación         ║");
        System.out.println("║─────────────────┼─────────┼──────────┼───────────┼─────────────────────║");
        System.out.println("║ Arreglo         │ O(1)    │ O(n)     │ O(n)      │ O(n)                ║");
        System.out.println("║ Lista Enlazada  │ O(n)    │ O(n)     │ O(1)*     │ O(n)                ║");
        System.out.println("║ Pila            │ O(n)    │ O(n)     │ O(1)      │ O(1)                ║");
        System.out.println("║ Cola            │ O(n)    │ O(n)     │ O(1)      │ O(1)                ║");
        System.out.println("║ Árbol BST       │ O(log n)│ O(log n) │ O(log n)  │ O(log n)            ║");
        System.out.println("║ Hash Table      │ N/A     │ O(1)**   │ O(1)**    │ O(1)**              ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ * Inserción al inicio. Al final es O(n)                                ║");
        System.out.println("║ ** Promedio. Peor caso O(n) por colisiones                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        
        MenuConsola.pausar();
    }
    
    // ============ MENÚ 2: DIVIDE Y VENCERÁS ============
    
    private static void menuDivideYVenceras() {
        boolean volver = false;
        
        while (!volver) {
            MenuConsola.mostrarMenuDivideVenceras();
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    demoMergeSort();
                    break;
                case 2:
                    demoQuickSort();
                    break;
                case 3:
                    demoBusquedaBinaria();
                    break;
                case 4:
                    demoPotencia();
                    break;
                case 5:
                    compararMergeQuick();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    MenuConsola.mostrarError("Opción no válida");
                    MenuConsola.pausar();
            }
        }
    }
    
    private static void demoMergeSort() {
        MenuConsola.mostrarEncabezado("DIVIDE Y VENCERÁS: Merge Sort - O(n log n)");
        
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("\nArreglo original:");
        AnalizadorComplejidad.imprimirArreglo(arr, 20);
        
        System.out.println("\n\nAplicando Merge Sort...");
        long tiempo = AnalizadorComplejidad.medirTiempo(() -> DivideYVenceras.mergeSort(arr));
        
        System.out.println("\nArreglo ordenado:");
        AnalizadorComplejidad.imprimirArreglo(arr, 20);
        
        System.out.println("\n\nTiempo de ejecución: " + AnalizadorComplejidad.formatearTiempo(tiempo));
        
        MenuConsola.mostrarComplejidad(
            "Merge Sort",
            "O(n log n) en todos los casos",
            "Divide en mitades, ordena recursivamente y combina"
        );
        
        MenuConsola.pausar();
    }
    
    private static void demoQuickSort() {
        MenuConsola.mostrarEncabezado("DIVIDE Y VENCERÁS: Quick Sort - O(n log n) promedio");
        
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("\nArreglo original:");
        AnalizadorComplejidad.imprimirArreglo(arr, 20);
        
        System.out.println("\n\nAplicando Quick Sort...");
        long tiempo = AnalizadorComplejidad.medirTiempo(() -> DivideYVenceras.quickSort(arr));
        
        System.out.println("\nArreglo ordenado:");
        AnalizadorComplejidad.imprimirArreglo(arr, 20);
        
        System.out.println("\n\nTiempo de ejecución: " + AnalizadorComplejidad.formatearTiempo(tiempo));
        
        MenuConsola.mostrarComplejidad(
            "Quick Sort",
            "O(n log n) promedio, O(n²) peor caso",
            "Usa pivote para particionar y ordena recursivamente"
        );
        
        MenuConsola.pausar();
    }
    
    private static void demoBusquedaBinaria() {
        MenuConsola.mostrarEncabezado("DIVIDE Y VENCERÁS: Búsqueda Binaria - O(log n)");
        
        int[] arr = {3, 9, 10, 27, 38, 43, 82};
        System.out.println("\nArreglo ordenado:");
        AnalizadorComplejidad.imprimirArreglo(arr, 20);
        
        int objetivo = 27;
        System.out.println("\n\nBuscando el valor: " + objetivo);
        
        int resultado = DivideYVenceras.busquedaBinaria(arr, objetivo);
        
        if (resultado != -1) {
            MenuConsola.mostrarExito("Elemento encontrado en el índice: " + resultado);
        } else {
            MenuConsola.mostrarError("Elemento no encontrado");
        }
        
        MenuConsola.mostrarComplejidad(
            "Búsqueda Binaria",
            "O(log n)",
            "Divide el espacio de búsqueda a la mitad en cada paso"
        );
        
        MenuConsola.pausar();
    }
    
    private static void demoPotencia() {
        MenuConsola.mostrarEncabezado("DIVIDE Y VENCERÁS: Potencia Eficiente - O(log n)");
        
        int base = 2;
        int exponente = 10;
        
        System.out.println("\nCalculando " + base + "^" + exponente);
        long resultado = DivideYVenceras.potencia(base, exponente);
        
        System.out.println("Resultado: " + resultado);
        System.out.println("\nComparación con algoritmo ingenuo O(n):");
        System.out.println("- Algoritmo ingenuo: 10 multiplicaciones");
        System.out.println("- Divide y Vencerás: ~" + (int)(Math.log(exponente)/Math.log(2)) + " multiplicaciones");
        
        MenuConsola.mostrarComplejidad(
            "Potencia Eficiente",
            "O(log n)",
            "Divide el exponente a la mitad recursivamente"
        );
        
        MenuConsola.pausar();
    }
    
    private static void compararMergeQuick() {
        MenuConsola.mostrarEncabezado("COMPARACIÓN: Merge Sort vs Quick Sort");
        
        System.out.println("\nGenerando arreglo aleatorio de 10,000 elementos...");
        int[] arrOriginal = AnalizadorComplejidad.generarArregloAleatorio(10000, 100000);
        
        int[] arrMerge = AnalizadorComplejidad.copiarArreglo(arrOriginal);
        int[] arrQuick = AnalizadorComplejidad.copiarArreglo(arrOriginal);
        
        AnalizadorComplejidad.compararAlgoritmos(
            "Merge Sort O(n log n)",
            () -> DivideYVenceras.mergeSort(arrMerge),
            "Quick Sort O(n log n) prom",
            () -> DivideYVenceras.quickSort(arrQuick)
        );
        
        MenuConsola.pausar();
    }
    
    // ============ MENÚ 3: PROGRAMACIÓN DINÁMICA ============
    
    private static void menuProgramacionDinamica() {
        boolean volver = false;
        
        while (!volver) {
            MenuConsola.mostrarMenuProgramacionDinamica();
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    demoFibonacci();
                    break;
                case 2:
                    demoMochila01();
                    break;
                case 3:
                    demoLCS();
                    break;
                case 4:
                    demoCambioMonedas();
                    break;
                case 5:
                    demoLIS();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    MenuConsola.mostrarError("Opción no válida");
                    MenuConsola.pausar();
            }
        }
    }
    
    private static void demoFibonacci() {
        MenuConsola.mostrarEncabezado("PROGRAMACIÓN DINÁMICA: Fibonacci - O(n) vs O(2^n)");
        
        int n = 40;
        System.out.println("\nCalculando Fibonacci(" + n + ")...\n");
        
        System.out.println("1. Con Programación Dinámica (Memoization):");
        long inicio = System.nanoTime();
        long resultMemo = ProgramacionDinamica.fibonacciMemoization(n);
        long tiempoMemo = System.nanoTime() - inicio;
        System.out.println("   Resultado: " + resultMemo);
        System.out.println("   Tiempo: " + AnalizadorComplejidad.formatearTiempo(tiempoMemo));
        System.out.println("   Complejidad: O(n)");
        
        System.out.println("\n2. Con Tabulación (Bottom-up):");
        inicio = System.nanoTime();
        long resultTab = ProgramacionDinamica.fibonacciTabulacion(n);
        long tiempoTab = System.nanoTime() - inicio;
        System.out.println("   Resultado: " + resultTab);
        System.out.println("   Tiempo: " + AnalizadorComplejidad.formatearTiempo(tiempoTab));
        System.out.println("   Complejidad: O(n)");
        
        System.out.println("\n3. Sin Programación Dinámica (recursión simple) sería O(2^n)");
        System.out.println("   Para n=40 tomaría minutos o incluso horas!");
        
        MenuConsola.mostrarComplejidad(
            "Fibonacci con PD",
            "O(n) tiempo, O(n) espacio",
            "Almacena resultados para evitar recalcular"
        );
        
        MenuConsola.pausar();
    }
    
    private static void demoMochila01() {
        MenuConsola.mostrarEncabezado("Programación Dinámica: Mochila 0-1");
        
        int[] pesos = {2, 3, 4, 5};
        int[] valores = {3, 4, 5, 6};
        int cap = 8;
        
        System.out.println("\nCapacidad: " + cap);
        for (int i = 0; i < pesos.length; i++) {
            System.out.println("  Item " + (i+1) + ": Peso=" + pesos[i] + ", Valor=" + valores[i]);
        }
        
        ProgramacionDinamica.ResultadoMochila r = ProgramacionDinamica.mochila01(pesos, valores, cap);
        
        System.out.println("\n✓ Valor máximo: " + r.valorMaximo);
        System.out.println("Items:");
        for (int i = 0; i < r.itemsSeleccionados.length; i++) {
            if (r.itemsSeleccionados[i]) {
                System.out.println("  ✓ Item " + (i+1));
            }
        }
        
        MenuConsola.pausar();
    }
    
    private static void demoLCS() {
        MenuConsola.mostrarEncabezado("Programación Dinámica: LCS");
        
        String t1 = "ABCDGH";
        String t2 = "AEDFHR";
        
        System.out.println("\nTexto 1: " + t1);
        System.out.println("Texto 2: " + t2);
        
        String lcs = ProgramacionDinamica.lcs(t1, t2);
        
        System.out.println("\n✓ LCS: '" + lcs + "' (longitud: " + lcs.length() + ")");
        
        MenuConsola.pausar();
    }
    
    private static void demoCambioMonedas() {
        MenuConsola.mostrarEncabezado("Programación Dinámica: Cambio de Monedas");
        
        int[] denoms = {1, 5, 10, 25};
        int cant = 30;
        
        System.out.print("\nDenominaciones: ");
        for (int d : denoms) System.out.print(d + " ");
        System.out.println("\nCantidad: $" + cant);
        
        int num = ProgramacionDinamica.cambioMonedas(denoms, cant);
        
        if (num != -1) {
            System.out.println("\n✓ Mínimo de monedas: " + num);
        } else {
            System.out.println("\n✗ No posible");
        }
        
        MenuConsola.pausar();
    }
    
    private static void demoLIS() {
        MenuConsola.mostrarEncabezado("Programación Dinámica: LIS");
        
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        
        System.out.print("\nArreglo: ");
        for (int v : arr) System.out.print(v + " ");
        
        int longitud = ProgramacionDinamica.lis(arr);
        
        System.out.println("\n\n✓ Longitud LIS: " + longitud);
        
        MenuConsola.pausar();
    }
    
    // ============ MENÚ 4: ALGORITMOS VORACES ============
    
    private static void menuAlgoritmosVoraces() {
        boolean volver = false;
        
        while (!volver) {
            MenuConsola.mostrarMenuVoraces();
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    demoCambioVoraz();
                    break;
                case 2:
                    demoSeleccionActividades();
                    break;
                case 3:
                    demoMochilaFraccionaria();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    MenuConsola.mostrarError("Opción no válida");
                    MenuConsola.pausar();
            }
        }
    }
    
    private static void demoCambioVoraz() {
        MenuConsola.mostrarEncabezado("Algoritmo Voraz: Cambio de Monedas");
        
        int[] denoms = {25, 10, 5, 1};
        int cantidad = 67;
        
        System.out.println("\nCantidad: $" + cantidad);
        String resultado = AlgoritmosVoraces.cambioVoraz(denoms, cantidad);
        
        if (resultado != null) {
            System.out.println("\n✓ " + resultado);
        } else {
            System.out.println("\n✗ No se puede dar cambio exacto");
        }
        
        MenuConsola.pausar();
    }
    
    private static void demoSeleccionActividades() {
        MenuConsola.mostrarEncabezado("Algoritmo Voraz: Selección de Actividades");
        
        Actividad[] acts = {
            new Actividad(1, 1, 4, "Reunión A"),
            new Actividad(2, 3, 5, "Clase B"),
            new Actividad(3, 0, 6, "Conferencia C"),
            new Actividad(4, 5, 7, "Seminario D"),
            new Actividad(5, 3, 9, "Taller E")
        };
        
        System.out.println("\nActividades disponibles:");
        for (Actividad a : acts) System.out.println("  " + a);
        
        Actividad[] selec = AlgoritmosVoraces.seleccionActividades(acts);
        
        System.out.println("\n✓ Seleccionadas: " + selec.length);
        for (Actividad a : selec) System.out.println("  " + a);
        
        MenuConsola.pausar();
    }
    
    private static void demoMochilaFraccionaria() {
        MenuConsola.mostrarEncabezado("Algoritmo Voraz: Mochila Fraccionaria");
        
        Item[] items = {
            new Item("Oro", 10, 60),
            new Item("Plata", 20, 100),
            new Item("Bronce", 30, 120)
        };
        int cap = 50;
        
        System.out.println("\nCapacidad: " + cap + " kg");
        for (Item i : items) {
            System.out.println("  " + i + " Ratio:" + String.format("%.2f", i.ratio));
        }
        
        String resultado = AlgoritmosVoraces.mochilaFraccionaria(items, cap);
        System.out.println("\n✓ " + resultado);
        
        MenuConsola.pausar();
    }
    
    // ============ MENÚ 5: BACKTRACKING ============
    
    private static void menuBacktracking() {
        boolean volver = false;
        
        while (!volver) {
            MenuConsola.mostrarMenuBacktracking();
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    demoNReinas();
                    break;
                case 2:
                    demoSudoku();
                    break;
                case 3:
                    demoSumaSubconjuntos();
                    break;
                case 4:
                    demoColoracionGrafos();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    MenuConsola.mostrarError("Opción no válida");
                    MenuConsola.pausar();
            }
        }
    }
    
    private static void demoNReinas() {
        MenuConsola.mostrarEncabezado("Backtracking: N-Reinas");
        
        int n = 8;
        System.out.println("\nResolviendo " + n + " reinas...");
        
        SolucionReinas sol = Backtracking.resolverNReinas(n);
        
        if (sol != null) {
            System.out.println("\n✓ Solución:");
            System.out.println(sol);
        } else {
            System.out.println("\n✗ No hay solución");
        }
        
        MenuConsola.pausar();
    }
    
    private static void demoSudoku() {
        MenuConsola.mostrarEncabezado("Backtracking: Sudoku 4x4");
        
        int[][] tablero = {
            {1, 0, 0, 0},
            {0, 0, 2, 0},
            {0, 3, 0, 0},
            {0, 0, 0, 4}
        };
        
        System.out.println("\nInicial:");
        imprimirTablero(tablero);
        
        if (Backtracking.resolverSudoku4x4(tablero)) {
            System.out.println("\n✓ Resuelto:");
            imprimirTablero(tablero);
        } else {
            System.out.println("\n✗ Sin solución");
        }
        
        MenuConsola.pausar();
    }
    
    private static void demoSumaSubconjuntos() {
        MenuConsola.mostrarEncabezado("Backtracking: Suma de Subconjuntos");
        
        int[] conjunto = {3, 5, 6, 7, 2};
        int objetivo = 9;
        
        System.out.println("\nConjunto: {3, 5, 6, 7, 2}");
        System.out.println("Objetivo: " + objetivo);
        
        int[][] sols = Backtracking.sumaSubconjuntos(conjunto, objetivo);
        
        System.out.println("\n✓ Soluciones: " + sols.length);
        for (int i = 0; i < sols.length; i++) {
            System.out.print("  {");
            for (int j = 0; j < sols[i].length; j++) {
                System.out.print(sols[i][j]);
                if (j < sols[i].length - 1) System.out.print(", ");
            }
            System.out.println("}");
        }
        
        MenuConsola.pausar();
    }
    
    private static void demoColoracionGrafos() {
        MenuConsola.mostrarEncabezado("Backtracking: Coloración de Grafos");
        
        int[][] grafo = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        int numColores = 3;
        
        System.out.println("\nGrafo: 4 vértices");
        System.out.println("Colores disponibles: " + numColores);
        
        int[] colores = Backtracking.colorearGrafo(grafo, numColores);
        
        if (colores != null) {
            System.out.println("\n✓ Coloración:");
            for (int i = 0; i < colores.length; i++) {
                System.out.println("  Vértice " + i + " -> Color " + colores[i]);
            }
        } else {
            System.out.println("\n✗ No se puede colorear");
        }
        
        MenuConsola.pausar();
    }
    
    // ============ MENÚ 6: ANÁLISIS DE COMPLEJIDAD ============
    
    private static void mostrarAnalisisComplejidad() {
        MenuConsola.mostrarEncabezado("ANÁLISIS DE COMPLEJIDAD COMPUTACIONAL");
        
        AnalizadorComplejidad.mostrarComplejidades();
        
        System.out.println("\n\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         PRINCIPIOS DEL ANÁLISIS DE COMPLEJIDAD            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n1. NOTACIÓN BIG-O (O): Cota superior (peor caso)");
        System.out.println("   Ejemplo: Si f(n) = 5n² + 100n + 300, entonces f(n) = O(n²)");
        System.out.println("   El término de mayor grado domina para valores grandes de n.");
        
        System.out.println("\n2. NOTACIÓN OMEGA (Ω): Cota inferior (mejor caso)");
        System.out.println("   Representa el mínimo de recursos necesarios.");
        
        System.out.println("\n3. NOTACIÓN THETA (Θ): Cota ajustada");
        System.out.println("   Cuando las cotas superior e inferior coinciden.");
        
        System.out.println("\n4. TÉRMINO DOMINANTE:");
        System.out.println("   Para n grande, solo importa el término de mayor grado.");
        System.out.println("   Las constantes y términos menores se ignoran.");
        
        System.out.println("\n5. REGLAS DE SUMA Y PRODUCTO:");
        System.out.println("   - Suma: O(f(n)) + O(g(n)) = O(max(f(n), g(n)))");
        System.out.println("   - Producto: O(f(n)) * O(g(n)) = O(f(n) * g(n))");
        
        MenuConsola.pausar();
    }
    
    // ============ MENÚ 7: DEMOSTRACIÓN COMPLETA ============
    
    private static void demostracionCompleta() {
        MenuConsola.mostrarEncabezado("DEMOSTRACION COMPLETA");
        
        System.out.println("\nEjemplo de cada paradigma algoritmico\n");
        MenuConsola.pausar();
        
        System.out.println("\n[1] ESTRUCTURAS DE DATOS");
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarFinal(10);
        lista.insertarFinal(20);
        lista.insertarFinal(30);
        System.out.println("Lista: " + lista);
        
        System.out.println("\n[2] DIVIDE Y VENCERAS: Merge Sort");
        int[] arr = {64, 34, 25, 12, 22};
        System.out.print("Arreglo original: ");
        for (int v : arr) System.out.print(v + " ");
        DivideYVenceras.mergeSort(arr);
        System.out.print("\nArreglo ordenado: ");
        for (int v : arr) System.out.print(v + " ");
        
        System.out.println("\n\n[3] PROGRAMACION DINAMICA: Fibonacci");
        long fib = ProgramacionDinamica.fibonacciTabulacion(20);
        System.out.println("Fibonacci(20) = " + fib);
        
        System.out.println("\n[4] ALGORITMO VORAZ: Cambio de Monedas");
        String cambio = AlgoritmosVoraces.cambioVoraz(new int[]{25,10,5,1}, 47);
        System.out.println(cambio);
        
        System.out.println("\n[5] BACKTRACKING: N-Reinas");
        SolucionReinas r = Backtracking.resolverNReinas(4);
        System.out.println(r);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Demostracion finalizada");
        System.out.println("=".repeat(50));
        MenuConsola.pausar();
    }
    
    // ============ MÉTODOS AUXILIARES ============
    
    private static int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void imprimirTablero(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] == 0 ? "." : tablero[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}