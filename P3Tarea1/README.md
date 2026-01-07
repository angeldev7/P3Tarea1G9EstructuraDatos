# Paradigmas Algorítmicos - Proyecto Simplificado

## Descripción
Proyecto de Estructura de Datos que demuestra 4 paradigmas algorítmicos fundamentales mediante implementaciones personalizadas **sin usar librerías de Java**.

## Estructura del Proyecto

```
P3Tarea1/
├── src/
│   ├── main/
│   │   └── MainApp.java              (Programa principal - 826 líneas)
│   ├── estructuras/
│   │   ├── Nodo.java                 (14 líneas)
│   │   ├── ListaEnlazada.java        (82 líneas)
│   │   ├── Pila.java                 (41 líneas)
│   │   ├── Cola.java                 (50 líneas)
│   │   ├── NodoArbol.java            (15 líneas)
│   │   └── ArbolBST.java             (87 líneas)
│   ├── algoritmos/
│   │   ├── DivideYVenceras.java      (180 líneas)
│   │   ├── ProgramacionDinamica.java (224 líneas)
│   │   ├── AlgoritmosVoraces.java    (155 líneas)
│   │   ├── Backtracking.java         (466 líneas)
│   │   └── AnalizadorComplejidad.java(130 líneas)
│   └── interfaz/
│       └── MenuConsola.java          (190 líneas)
```

## Estructuras de Datos (Sin Genéricos)

Todas las estructuras fueron implementadas desde cero, sin usar `<T>` para mayor simplicidad:

- **ListaEnlazada**: Opera con enteros (`int`)
- **Pila**: Maneja Strings con array fijo de 100 elementos
- **Cola**: Cola circular de Strings con capacidad 100
- **ArbolBST**: Árbol binario de búsqueda para enteros

## Paradigmas Algorítmicos

### 1️⃣ Divide y Vencerás
- **MergeSort**: O(n log n) - ordenamiento por división
- **QuickSort**: O(n log n) promedio - pivote y partición
- **Búsqueda Binaria**: O(log n) - búsqueda en arreglo ordenado

### 2️⃣ Programación Dinámica
- **Fibonacci**: O(n) con memoización y tabulación
- **Mochila 0-1**: O(n·W) - maximiza valor sin exceder capacidad
- **LCS**: O(m·n) - subsecuencia común más larga
- **Cambio de Monedas**: O(n·cantidad) - mínimo de monedas
- **LIS**: O(n²) - subsecuencia creciente más larga

### 3️⃣ Algoritmos Voraces
- **Cambio de Monedas**: O(n) - greedy con sistema canónico
- **Selección de Actividades**: O(n log n) - maximiza actividades sin superposición
- **Mochila Fraccionaria**: O(n log n) - permite fracciones de items

### 4️⃣ Backtracking
- **N-Reinas**: Colocar N reinas sin que se ataquen
- **Sudoku 4x4**: Resolver tablero con restricciones
- **Suma de Subconjuntos**: Encontrar subconjuntos con suma objetivo
- **Coloración de Grafos**: Asignar colores evitando adyacentes iguales

## Simplificaciones Realizadas

✅ **Eliminados todos los genéricos `<T>`** - Código más legible  
✅ **Reducción del 40-50% en líneas** - Métodos más cortos  
✅ **Arrays en lugar de clases complejas** - Retornos simples  
✅ **Variables con nombres cortos** - `izq/der` en lugar de `izquierdo/derecho`  
✅ **Sin verbosidad** - Directos al punto  

## Cómo Ejecutar

1. Abrir el proyecto en Eclipse
2. Ejecutar `MainApp.java`
3. Navegar por el menú interactivo:

```
╔══════════════════════════════════════════════════════════════╗
║       PARADIGMAS ALGORÍTMICOS - ESTRUCTURAS DE DATOS         ║
╚══════════════════════════════════════════════════════════════╝

1. Estructuras de Datos Propias
2. Divide y Vencerás
3. Programación Dinámica
4. Algoritmos Voraces
5. Backtracking
6. Análisis de Complejidad
7. Demostración Completa
0. Salir
```

## Características

- 🚫 **Sin librerías de Java Collections** (ArrayList, LinkedList, etc.)
- ✅ **Implementaciones propias** de todas las estructuras
- 📊 **Análisis de complejidad** incluido en cada algoritmo
- 🎯 **Menú interactivo** con ejemplos predefinidos
- 📝 **Código simplificado** sin genéricos para facilitar comprensión

## Complejidades Temporales

| Algoritmo | Complejidad | Paradigma |
|-----------|-------------|-----------|
| MergeSort | O(n log n) | Divide y Vencerás |
| QuickSort | O(n log n) | Divide y Vencerás |
| Búsqueda Binaria | O(log n) | Divide y Vencerás |
| Fibonacci (PD) | O(n) | Programación Dinámica |
| Mochila 0-1 | O(n·W) | Programación Dinámica |
| LCS | O(m·n) | Programación Dinámica |
| Cambio Voraz | O(n) | Algoritmo Voraz |
| Selección Act. | O(n log n) | Algoritmo Voraz |
| N-Reinas | O(N!) | Backtracking |
| Sudoku | Exponencial | Backtracking |

## Autor
Proyecto para curso de Estructura de Datos - Universidad

## Notas
- El código prioriza claridad sobre eficiencia extrema
- Los ejemplos están preparados para demostración educativa
- Cada paradigma tiene múltiples implementaciones para comparar enfoques
