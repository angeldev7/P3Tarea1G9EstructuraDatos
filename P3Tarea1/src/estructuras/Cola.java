package estructuras;

/**
 * Cola FIFO - O(1) enqueue y dequeue
 */
public class Cola {
    private String[] elementos;
    private int frente, fin, tamanio;
    
    public Cola() {
        elementos = new String[100];
        frente = 0;
        fin = -1;
        tamanio = 0;
    }
    
    public void encolar(String dato) {
        fin = (fin + 1) % 100;
        elementos[fin] = dato;
        tamanio++;
    }
    
    public String desencolar() {
        if (tamanio == 0) return null;
        String dato = elementos[frente];
        frente = (frente + 1) % 100;
        tamanio--;
        return dato;
    }
    
    public String verFrente() {
        return tamanio > 0 ? elementos[frente] : null;
    }
    
    public boolean estaVacia() {
        return tamanio == 0;
    }
    
    public String toString() {
        if (tamanio == 0) return "Cola vacía";
        
        String resultado = "Frente -> ";
        for (int i = 0; i < tamanio; i++) {
            int index = (frente + i) % 100;
            resultado += "[" + elementos[index] + "]";
            if (i < tamanio - 1) resultado += " -> ";
        }
        return resultado + " <- Final";
    }
}
