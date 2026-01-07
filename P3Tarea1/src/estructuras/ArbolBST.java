package estructuras;

/**
 * Árbol Binario de Búsqueda - O(log n) promedio
 */
public class ArbolBST {
    private NodoArbol raiz;
    private int tamanio;   
    public void insertar(int dato) {
        raiz = insertarRec(raiz, dato);
        tamanio++;
    }   
    private NodoArbol insertarRec(NodoArbol nodo, int dato) {
        if (nodo == null) return new NodoArbol(dato);
        
        if (dato < nodo.dato) {
            nodo.izq = insertarRec(nodo.izq, dato);
        } else if (dato > nodo.dato) {
            nodo.der = insertarRec(nodo.der, dato);
        }
        return nodo;
    }  
    public boolean buscar(int dato) {
        return buscarRec(raiz, dato);
    }    
    private boolean buscarRec(NodoArbol nodo, int dato) {
        if (nodo == null) return false;
        if (dato == nodo.dato) return true;
        return dato < nodo.dato ? buscarRec(nodo.izq, dato) : buscarRec(nodo.der, dato);
    }
    
    public void inOrden() {
        inOrdenRec(raiz);
        System.out.println();
    }   
    private void inOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izq);
            System.out.print(nodo.dato + " ");
            inOrdenRec(nodo.der);
        }
    }
    
    public void preOrden() {
        preOrdenRec(raiz);
        System.out.println();
    }
    
    private void preOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " ");
            preOrdenRec(nodo.izq);
            preOrdenRec(nodo.der);
        }
    }
    
    public void postOrden() {
        postOrdenRec(raiz);
        System.out.println();
    }
    
    private void postOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            postOrdenRec(nodo.izq);
            postOrdenRec(nodo.der);
            System.out.print(nodo.dato + " ");
        }
    }
    
    public int altura() {
        return alturaRec(raiz);
    }
    
    private int alturaRec(NodoArbol nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(alturaRec(nodo.izq), alturaRec(nodo.der));
    }
    
    public int getTamanio() {
        return tamanio;
    }
}
