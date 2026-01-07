package estructuras;

/**
 * Pila LIFO - O(1) todas las operaciones
 */
public class Pila {
    private String[] elementos;
    private int tope;
    
    public Pila() {
        elementos = new String[100];
        tope = -1;
    }
    
    public void push(String dato) {
        elementos[++tope] = dato;
    }
    
    public String pop() {
        return tope >= 0 ? elementos[tope--] : null;
    }
    
    public String peek() {
        return tope >= 0 ? elementos[tope] : null;
    }
    
    public boolean estaVacia() {
        return tope == -1;
    }
    
    public String toString() {
        if (tope == -1) return "Pila vacía";
        
        String resultado = "Tope -> ";
        for (int i = tope; i >= 0; i--) {
            resultado += "[" + elementos[i] + "]";
            if (i > 0) resultado += " -> ";
        }
        return resultado;
    }
}
