package controller;

import model.AnalizadorComplejidad;
import model.EjemplosComplejidad;

/**
 * Controlador principal: orquesta los experimentos de complejidad y
 * devuelve resultados listos para mostrar en la interfaz.
 */
public class AppController {

    public O1Result ejecutarO1() {
        int[] arr1 = generarOrdenado(1000);
        int[] arr2 = generarOrdenado(1_000_000);

        // Warmup
        for (int i = 0; i < 500; i++) {
            EjemplosComplejidad.ejemploO1(arr1);
        }

        long t1 = medir(() -> EjemplosComplejidad.ejemploO1(arr1));
        long t2 = medir(() -> EjemplosComplejidad.ejemploO1(arr2));
        double ratio = (t1 == 0) ? 0 : (double) t2 / (double) t1;
        return new O1Result(t1, t2, ratio);
    }

    public ONResult ejecutarON() {
        int[] a = AnalizadorComplejidad.generarArregloAleatorio(100, 1000);
        int[] b = AnalizadorComplejidad.generarArregloAleatorio(1_000, 1000);
        int[] c = AnalizadorComplejidad.generarArregloAleatorio(10_000, 1000);

        // Warmup
        for (int i = 0; i < 300; i++) {
            EjemplosComplejidad.ejemploON(a, 9999);
        }

        long tA = medir(() -> EjemplosComplejidad.ejemploON(a, 9999));
        long tB = medir(() -> EjemplosComplejidad.ejemploON(b, 9999));
        long tC = medir(() -> EjemplosComplejidad.ejemploON(c, 9999));

        return new ONResult(tA, tB, tC);
    }

    public ON2Result ejecutarON2() {
        int[] a = AnalizadorComplejidad.generarArregloAleatorio(100, 1000);
        int[] b = AnalizadorComplejidad.generarArregloAleatorio(200, 1000);
        int[] c = AnalizadorComplejidad.generarArregloAleatorio(400, 1000);

        // Warmup
        for (int i = 0; i < 50; i++) {
            int[] tmp = AnalizadorComplejidad.copiarArreglo(a);
            EjemplosComplejidad.ejemploON2(tmp);
        }

        long tA = medir(() -> EjemplosComplejidad.ejemploON2(AnalizadorComplejidad.copiarArreglo(a)));
        long tB = medir(() -> EjemplosComplejidad.ejemploON2(AnalizadorComplejidad.copiarArreglo(b)));
        long tC = medir(() -> EjemplosComplejidad.ejemploON2(AnalizadorComplejidad.copiarArreglo(c)));

        return new ON2Result(tA, tB, tC);
    }

    private int[] generarOrdenado(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private long medir(Runnable r) {
        long ini = System.nanoTime();
        r.run();
        return System.nanoTime() - ini;
    }

    // Resultados simples para UI
    public static class O1Result {
        public final long tSmall;
        public final long tLarge;
        public final double ratio;

        public O1Result(long tSmall, long tLarge, double ratio) {
            this.tSmall = tSmall;
            this.tLarge = tLarge;
            this.ratio = ratio;
        }
    }

    public static class ONResult {
        public final long t100;
        public final long t1k;
        public final long t10k;

        public ONResult(long t100, long t1k, long t10k) {
            this.t100 = t100;
            this.t1k = t1k;
            this.t10k = t10k;
        }
    }

    public static class ON2Result {
        public final long t100;
        public final long t200;
        public final long t400;

        public ON2Result(long t100, long t200, long t400) {
            this.t100 = t100;
            this.t200 = t200;
            this.t400 = t400;
        }
    }
}
