package estructuras;

/**
 * Lista Enlazada Simple - O(1) inserción inicio, O(n) búsqueda
 */
public class ListaEnlazada {
    private Nodo cabeza;
    private int tamanio;
    
    public ListaEnlazada() {
        this.cabeza = null;
        this.tamanio = 0;
    }
    
    public void insertar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamanio++;
    }
    
    public void insertarFinal(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamanio++;
    }
    
    public boolean buscar(Object dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) return true;
            actual = actual.siguiente;
        }
        return false;
    }
    
    public boolean eliminar(Object dato) {
        if (cabeza == null) return false;
        
        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return true;
        }
        
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.equals(dato)) {
                actual.siguiente = actual.siguiente.siguiente;
                tamanio--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    
    public int getTamanio() {
        return tamanio;
    }
    
    public String toString() {
        if (cabeza == null) return "[]";
        
        String resultado = "[";
        Nodo actual = cabeza;
        while (actual != null) {
            resultado += actual.dato;
            if (actual.siguiente != null) resultado += " -> ";
            actual = actual.siguiente;
        }
        return resultado + "]";
    }
}
