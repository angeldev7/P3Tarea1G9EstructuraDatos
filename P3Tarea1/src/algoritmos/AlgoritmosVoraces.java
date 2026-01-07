package algoritmos;

/**
 * Algoritmos Voraces (Greedy) - Decisión local óptima en cada paso
 */
public class AlgoritmosVoraces {
    
    // Clase simple para Actividades
    public static class Actividad {
        public int id, inicio, fin;
        public String nombre;
        
        public Actividad(int id, int inicio, int fin, String nombre) {
            this.id = id;
            this.inicio = inicio;
            this.fin = fin;
            this.nombre = nombre;
        }
        
        public String toString() {
            return nombre + " (" + inicio + "-" + fin + ")";
        }
    }
    
    // Clase simple para Items de mochila
    public static class Item {
        public String nombre;
        public int peso, valor;
        public double ratio;
        
        public Item(String nombre, int peso, int valor) {
            this.nombre = nombre;
            this.peso = peso;
            this.valor = valor;
            this.ratio = (double) valor / peso;
        }
        
        public String toString() {
            return nombre + " (Peso:" + peso + ", Valor:" + valor + ")";
        }
    }
    
    /**
     * Cambio de monedas voraz - O(n)
     */
    public static String cambioVoraz(int[] denoms, int cantidad) {
        ordenarDesc(denoms);
        int totalMonedas = 0;
        String resultado = "";
        
        for (int d : denoms) {
            if (cantidad >= d) {
                int num = cantidad / d;
                totalMonedas += num;
                resultado += num + " x $" + d + "\n";
                cantidad %= d;
            }
        }
        
        if (cantidad > 0) return null;
        return "Total: " + totalMonedas + " monedas\n" + resultado;
    }
    
    /**
     * Selección de actividades - O(n log n)
     */
    public static Actividad[] seleccionActividades(Actividad[] acts) {
        ordenarPorFin(acts);
        
        Actividad[] seleccionadas = new Actividad[acts.length];
        int count = 0;
        
        if (acts.length > 0) {
            seleccionadas[count++] = acts[0];
            int ultimoFin = acts[0].fin;
            
            for (int i = 1; i < acts.length; i++) {
                if (acts[i].inicio >= ultimoFin) {
                    seleccionadas[count++] = acts[i];
                    ultimoFin = acts[i].fin;
                }
            }
        }
        
        Actividad[] resultado = new Actividad[count];
        for (int i = 0; i < count; i++) {
            resultado[i] = seleccionadas[i];
        }
        return resultado;
    }
    
    /**
     * Mochila fraccionaria - O(n log n)
     */
    public static String mochilaFraccionaria(Item[] items, int capacidad) {
        ordenarPorRatio(items);
        
        double valorTotal = 0;
        String resultado = "";
        
        for (Item item : items) {
            if (capacidad == 0) break;
            
            if (item.peso <= capacidad) {
                valorTotal += item.valor;
                capacidad -= item.peso;
                resultado += item.nombre + " (100%)\n";
            } else {
                double fraccion = (double) capacidad / item.peso;
                valorTotal += item.valor * fraccion;
                resultado += item.nombre + " (" + String.format("%.1f", fraccion * 100) + "%)\n";
                capacidad = 0;
            }
        }
        
        return "Valor total: " + String.format("%.2f", valorTotal) + "\n" + resultado;
    }
    
    // Métodos auxiliares
    private static void ordenarDesc(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    
    private static void ordenarPorFin(Actividad[] acts) {
        for (int i = 0; i < acts.length - 1; i++) {
            for (int j = i + 1; j < acts.length; j++) {
                if (acts[i].fin > acts[j].fin) {
                    Actividad temp = acts[i];
                    acts[i] = acts[j];
                    acts[j] = temp;
                }
            }
        }
    }
    
    private static void ordenarPorRatio(Item[] items) {
        for (int i = 0; i < items.length - 1; i++) {
            for (int j = i + 1; j < items.length; j++) {
                if (items[i].ratio < items[j].ratio) {
                    Item temp = items[i];
                    items[i] = items[j];
                    items[j] = temp;
                }
            }
        }
    }
}
