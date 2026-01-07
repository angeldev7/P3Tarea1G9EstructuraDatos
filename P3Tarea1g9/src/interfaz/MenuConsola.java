package interfaz;

/**
 * Sistema de menú interactivo por consola
 * Proporciona una interfaz amigable para demostrar todos los conceptos
 * del informe de Estructura de Datos
 */
public class MenuConsola {
    
    private static final String LINEA_DOBLE = "════════════════════════════════════════════════════════════";
    private static final String LINEA_SIMPLE = "────────────────────────────────────────────────────────────";
    
    /**
     * Muestra el menú principal del sistema
     */
    public static void mostrarMenuPrincipal() {
        limpiarPantalla();
        System.out.println("\n" + "=".repeat(50));
        System.out.println("    ESTRUCTURAS DE DATOS Y ALGORITMOS - ESPE");
        System.out.println("=".repeat(50));
        System.out.println("\n[1] Estructuras de Datos");
        System.out.println("[2] Divide y Venceras");
        System.out.println("[3] Programacion Dinamica");
        System.out.println("[4] Algoritmos Voraces");
        System.out.println("[5] Backtracking");
        System.out.println("[6] Analisis de Complejidad");
        System.out.println("[7] Demostracion Completa");
        System.out.println("[0] Salir");
        System.out.print("\nOpcion: ");
    }
    
    /**
     * Muestra submenú de estructuras de datos
     */
    public static void mostrarMenuEstructuras() {
        mostrarEncabezado("ESTRUCTURAS DE DATOS");
        System.out.println("\n[1] Lista Enlazada");
        System.out.println("[2] Pila (Stack)");
        System.out.println("[3] Cola (Queue)");
        System.out.println("[4] Arbol Binario de Busqueda (BST)");
        System.out.println("[0] Volver");
        System.out.print("\nOpcion: ");
    }
    
    /**
     * Muestra submenú de Divide y Vencerás
     */
    public static void mostrarMenuDivideVenceras() {
        mostrarEncabezado("DIVIDE Y VENCERAS");
        System.out.println("\n[1] Merge Sort");
        System.out.println("[2] Quick Sort");
        System.out.println("[3] Busqueda Binaria");
        System.out.println("[4] Potencia Rapida");
        System.out.println("[0] Volver");
        System.out.print("\nOpcion: ");
    }
    
    /**
     * Muestra submenú de Programación Dinámica
     */
    public static void mostrarMenuProgramacionDinamica() {
        mostrarEncabezado("PROGRAMACION DINAMICA");
        System.out.println("\n[1] Fibonacci");
        System.out.println("[2] Problema de la Mochila");
        System.out.println("[3] Subsecuencia Comun Mas Larga (LCS)");
        System.out.println("[4] Subsecuencia Creciente Mas Larga (LIS)");
        System.out.println("[0] Volver");
        System.out.print("\nOpcion: ");
    }
    
    /**
     * Muestra submenú de Algoritmos Voraces
     */
    public static void mostrarMenuVoraces() {
        mostrarEncabezado("ALGORITMOS VORACES");
        System.out.println("\n[1] Cambio de Monedas");
        System.out.println("[2] Seleccion de Actividades");
        System.out.println("[3] Mochila Fraccionaria");
        System.out.println("[0] Volver");
        System.out.print("\nOpcion: ");
    }
    
    /**
     * Muestra submenú de Backtracking
     */
    public static void mostrarMenuBacktracking() {
        mostrarEncabezado("BACKTRACKING");
        System.out.println("\n[1] N-Reinas");
        System.out.println("[2] Sudoku 4x4");
        System.out.println("[3] Suma de Subconjuntos");
        System.out.println("[4] Coloracion de Grafos");
        System.out.println("[0] Volver");
        System.out.print("\nOpcion: ");
    }
    
    // ====== MÉTODOS AUXILIARES ======
    
    private static void mostrarTitulo(String titulo) {
        limpiarPantalla();
        System.out.println("\n╔" + LINEA_DOBLE + "╗");
        System.out.printf("║ %-58s ║\n", centrarTexto(titulo, 58));
        System.out.println("╠" + LINEA_DOBLE + "╣");
        System.out.println("║                                                            ║");
    }
    
    private static String centrarTexto(String texto, int ancho) {
        int espacios = (ancho - texto.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < espacios; i++) {
            sb.append(" ");
        }
        sb.append(texto);
        while (sb.length() < ancho) {
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public static void mostrarEncabezado(String texto) {
        System.out.println("\n┌" + LINEA_SIMPLE + "┐");
        System.out.printf("│ %-58s │\n", texto);
        System.out.println("└" + LINEA_SIMPLE + "┘");
    }
    
    public static void mostrarSeparador() {
        System.out.println("\n" + LINEA_SIMPLE);
    }
    
    public static void mostrarExito(String mensaje) {
        System.out.println("\n✓ " + mensaje);
    }
    
    public static void mostrarError(String mensaje) {
        System.out.println("\n✗ ERROR: " + mensaje);
    }
    
    public static void mostrarInfo(String mensaje) {
        System.out.println("\nℹ " + mensaje);
    }
    
    public static void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        try {
            System.in.read();
            // Limpiar el buffer
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // Ignorar excepción
        }
    }
    
    private static void limpiarPantalla() {
        // Aproximación para limpiar pantalla en consola
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }
    
    public static void mostrarComplejidad(String algoritmo, String complejidad, String descripcion) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              ANÁLISIS DE COMPLEJIDAD                       ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.printf("║ Algoritmo   : %-44s ║\n", algoritmo);
        System.out.printf("║ Complejidad : %-44s ║\n", complejidad);
        System.out.printf("║ Descripción : %-44s ║\n", descripcion);
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
