package algoritmos;

/**
 * Implementación de algoritmos Divide y Vencerás
 * Paradigma: Divide el problema en subproblemas, resuelve recursivamente y combina
 * Complejidad típica: O(n log n) para ordenamiento
 */
public class DivideYVenceras {
    
    /**
     * MERGE SORT - Algoritmo de ordenamiento por mezcla
     * Complejidad: O(n log n) en todos los casos
     * Espacio: O(n) - necesita arreglo auxiliar
     * 
     * Divide el arreglo en mitades, ordena cada mitad recursivamente
     * y luego las combina en orden
     */
    public static void mergeSort(int[] arreglo) {
        if (arreglo == null || arreglo.length <= 1) {
            return;
        }
        mergeSortRecursivo(arreglo, 0, arreglo.length - 1);
    }
    
    private static void mergeSortRecursivo(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int medio = inicio + (fin - inicio) / 2;
            
            // Divide: ordena la mitad izquierda
            mergeSortRecursivo(arr, inicio, medio);
            
            // Divide: ordena la mitad derecha
            mergeSortRecursivo(arr, medio + 1, fin);
            
            // Vencerás: combina las dos mitades ordenadas
            merge(arr, inicio, medio, fin);
        }
    }
    
    private static void merge(int[] arr, int inicio, int medio, int fin) {
        // Tamaños de los subarreglos
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;        
        // Arreglos temporales
        int[] izq = new int[n1];
        int[] der = new int[n2];       
        // Copiar datos a arreglos temporales
        for (int i = 0; i < n1; i++) {
            izq[i] = arr[inicio + i];
        }
        for (int j = 0; j < n2; j++) {
            der[j] = arr[medio + 1 + j];
        }        
        // Mezclar los arreglos temporales
        int i = 0, j = 0, k = inicio;
        
        while (i < n1 && j < n2) {
            if (izq[i] <= der[j]) {
                arr[k] = izq[i];
                i++;
            } else {
                arr[k] = der[j];
                j++;
            }
            k++;
        }   
        // Copiar elementos restantes de izq[]
        while (i < n1) {
            arr[k] = izq[i];
            i++;
            k++;
        } 
        // Copiar elementos restantes de der[]
        while (j < n2) {
            arr[k] = der[j];
            j++;
            k++;
        }
    } 
    /**
     * BÚSQUEDA BINARIA - Búsqueda en arreglo ordenado
     * Complejidad: O(log n)
     * Requisito: El arreglo debe estar ordenado
     * 
     * Divide el espacio de búsqueda a la mitad en cada paso
     */
    public static int busquedaBinaria(int[] arreglo, int objetivo) {
        return busquedaBinariaRecursiva(arreglo, objetivo, 0, arreglo.length - 1);
    }
    
    private static int busquedaBinariaRecursiva(int[] arr, int objetivo, int inicio, int fin) {
        if (inicio > fin) {
            return -1; // No encontrado
        }
        
        int medio = inicio + (fin - inicio) / 2;
        
        if (arr[medio] == objetivo) {
            return medio; // Encontrado
        } else if (arr[medio] > objetivo) {
            // Buscar en la mitad izquierda
            return busquedaBinariaRecursiva(arr, objetivo, inicio, medio - 1);
        } else {
            // Buscar en la mitad derecha
            return busquedaBinariaRecursiva(arr, objetivo, medio + 1, fin);
        }
    }
    
    /**
     * QUICK SORT - Algoritmo de ordenamiento rápido
     * Complejidad promedio: O(n log n)
     * Peor caso: O(n²) - cuando el pivote siempre es el menor/mayor
     * Espacio: O(log n) - por la recursión
     */
    public static void quickSort(int[] arreglo) {
        if (arreglo == null || arreglo.length <= 1) {
            return;
        }
        quickSortRecursivo(arreglo, 0, arreglo.length - 1);
    }
    
    private static void quickSortRecursivo(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            // Particionar el arreglo y obtener la posición del pivote
            int pivote = particionar(arr, inicio, fin);
            
            // Ordenar los elementos antes y después del pivote
            quickSortRecursivo(arr, inicio, pivote - 1);
            quickSortRecursivo(arr, pivote + 1, fin);
        }
    }
    
    private static int particionar(int[] arr, int inicio, int fin) {
        int pivote = arr[fin];
        int i = inicio - 1;
        
        for (int j = inicio; j < fin; j++) {
            if (arr[j] <= pivote) {
                i++;
                // Intercambiar arr[i] y arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Intercambiar arr[i+1] y arr[fin] (pivote)
        int temp = arr[i + 1];
        arr[i + 1] = arr[fin];
        arr[fin] = temp;
        
        return i + 1;
    }
    
    /**
     * Potencia usando Divide y Vencerás
     * Complejidad: O(log n) en lugar de O(n)
     * Calcula base^exponente eficientemente
     */
    public static long potencia(int base, int exponente) {
        if (exponente == 0) {
            return 1;
        }
        
        if (exponente % 2 == 0) {
            // Si el exponente es par: base^n = (base^(n/2))^2
            long mitad = potencia(base, exponente / 2);
            return mitad * mitad;
        } else {
            // Si el exponente es impar: base^n = base * base^(n-1)
            return base * potencia(base, exponente - 1);
        }
    }
}
