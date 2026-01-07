package estructuras;

/**
 * Nodo de Árbol Binario
 */
public class NodoArbol {
    int dato;
    NodoArbol izq, der;
    
    public NodoArbol(int dato) {
        this.dato = dato;
        this.izq = null;
        this.der = null;
    }
}
