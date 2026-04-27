import Interfaz.*;
import Implementacion.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== LABORATORIO DE DESAFÍOS - CLASE 8 ======");

        // -----------------------------------------------------------------------
        // DESAFÍO 1: "El espejo de la Cola" (Cola + Pila)
        // Objetivo: Verificar palíndromo sin destruir la estructura original.
        // -----------------------------------------------------------------------
        ColaTDA c1 = new Implementaciones.ColaDin();
        c1.InicializarCola();
        c1.Acolar(1); c1.Acolar(2); c1.Acolar(1); // Carga: 1-2-1
        System.out.println("\n[Desafío 1] ¿La cola 1-2-1 es Palíndromo?: " + esPalindrome(c1));


        // -----------------------------------------------------------------------
        // DESAFÍO 2: "El turno justo" (Cola con Prioridad)
        // Objetivo: Contar elementos de P=1 sin perder los de otras prioridades.
        // -----------------------------------------------------------------------
        ColaPrioTDA cp2 = new Implementaciones.ColaPrioDin();
        cp2.InicializarCola();
        cp2.AcolarPrioridad(10, 1);
        cp2.AcolarPrioridad(20, 0);
        cp2.AcolarPrioridad(30, 1);
        System.out.println("[Desafío 2] Cantidad de elementos con Prioridad 1: " + contarPrioridadUno(cp2));


        // -----------------------------------------------------------------------
        // DESAFÍO 3: "La pila que no miente" (Pila + Conjunto)
        // Objetivo: Detectar duplicados usando un Conjunto como memoria.
        // -----------------------------------------------------------------------
        PilaTDA p3 = new Implementaciones.PilaDin();
        p3.InicializarPila();
        p3.Apilar(5); p3.Apilar(10); p3.Apilar(5); // El 5 está duplicado
        System.out.println("[Desafío 3] ¿La pila (5-10-5) tiene duplicados?: " + tieneDuplicados(p3));


        // -----------------------------------------------------------------------
        // DESAFÍO 4: "La frontera del conjunto" (Diferencia Simétrica)
        // Objetivo: Calcular (A-B) U (B-A) con conjuntos dinámicos.
        // -----------------------------------------------------------------------
        ConjuntoTDA cA = new Implementaciones.ConjuntoDin(); cA.InicializarConjunto();
        ConjuntoTDA cB = new Implementaciones.ConjuntoDin(); cB.InicializarConjunto();
        cA.Agregar(1); cA.Agregar(2); // Conjunto A: {1, 2}
        cB.Agregar(2); cB.Agregar(3); // Conjunto B: {2, 3}
        ConjuntoTDA res4 = diferenciaSimetrica(cA, cB);
        System.out.print("[Desafío 4] Dif. Simétrica {1,2} Δ {2,3}: { ");
        while(!res4.ConjuntoVacio()) {
            int v = res4.Elegir(); System.out.print(v + " "); res4.Sacar(v);
        }
        System.out.println("}");


        // -----------------------------------------------------------------------
        // DESAFÍO 5: "La cola que se ordena sola" (Tracing de Prioridades)
        // Objetivo: Entender que la ColaPrio acepta duplicados y ordena por P.
        // -----------------------------------------------------------------------
        System.out.print("[Desafío 5] Tracing de salida (P0 sale antes que P2): ");
        tracingPrioridad();

        System.out.println("\n===============================================");
    }

    // ===========================================================================
    // MÉTODOS DE LÓGICA (ALGORITMOS EXTERNOS)
    // ===========================================================================

    // LÓGICA DESAFÍO 1
    public static boolean esPalindrome(ColaTDA origen) {
        PilaTDA p = new Implementaciones.PilaDin(); p.InicializarPila();
        ColaTDA cAux = new Implementaciones.ColaDin(); cAux.InicializarCola();
        ColaTDA cCopia = new Implementaciones.ColaDin(); cCopia.InicializarCola();

        while (!origen.ColaVacia()) {
            int x = origen.Primero();
            p.Apilar(x); cAux.Acolar(x); cCopia.Acolar(x);
            origen.Desacolar();
        }
        // Restauración de la original
        while (!cCopia.ColaVacia()) { origen.Acolar(cCopia.Primero()); cCopia.Desacolar(); }
        // Comparación Espejo
        while (!cAux.ColaVacia()) {
            if (cAux.Primero() != p.Tope()) return false;
            cAux.Desacolar(); p.Desapilar();
        }
        return true;
    }

    // LÓGICA DESAFÍO 2
    public static int contarPrioridadUno(ColaPrioTDA origen) {
        ColaPrioTDA aux = new Implementaciones.ColaPrioDin(); aux.InicializarCola();
        int cant = 0;
        while (!origen.ColaVacia()) {
            if (origen.Prioridad() == 1) cant++;
            aux.AcolarPrioridad(origen.Primero(), origen.Prioridad());
            origen.Desacolar();
        }
        // Restauración
        while (!aux.ColaVacia()) {
            origen.AcolarPrioridad(aux.Primero(), aux.Prioridad());
            aux.Desacolar();
        }
        return cant;
    }

    // LÓGICA DESAFÍO 3
    public static boolean tieneDuplicados(PilaTDA origen) {
        PilaTDA aux = new Implementaciones.PilaDin(); aux.InicializarPila();
        ConjuntoTDA con = new Implementaciones.ConjuntoDin(); con.InicializarConjunto();
        boolean dup = false;
        while (!origen.PilaVacia()) {
            if (con.Pertenece(origen.Tope())) dup = true;
            con.Agregar(origen.Tope());
            aux.Apilar(origen.Tope());
            origen.Desapilar();
        }
        while (!aux.PilaVacia()) { origen.Apilar(aux.Tope()); aux.Desapilar(); }
        return dup;
    }

    // LÓGICA DESAFÍO 4
    public static ConjuntoTDA diferenciaSimetrica(ConjuntoTDA a, ConjuntoTDA b) {
        ConjuntoTDA res = new Implementaciones.ConjuntoDin(); res.InicializarConjunto();
        ConjuntoTDA aAux = copiarConjunto(a);
        ConjuntoTDA bAux = copiarConjunto(b);
        // Elementos en A que no están en B
        while (!aAux.ConjuntoVacio()) {
            int x = aAux.Elegir();
            if (!b.Pertenece(x)) res.Agregar(x);
            aAux.Sacar(x);
        }
        // Elementos en B que no están en A
        while (!bAux.ConjuntoVacio()) {
            int x = bAux.Elegir();
            if (!a.Pertenece(x)) res.Agregar(x);
            bAux.Sacar(x);
        }
        return res;
    }

    // MÉTODO AUXILIAR PARA DESAFÍO 4
    private static ConjuntoTDA copiarConjunto(ConjuntoTDA origen) {
        ConjuntoTDA aux = new Implementaciones.ConjuntoDin(); ConjuntoTDA copia = new Implementaciones.ConjuntoDin();
        aux.InicializarConjunto(); copia.InicializarConjunto();
        while (!origen.ConjuntoVacio()) {
            int x = origen.Elegir(); aux.Agregar(x); copia.Agregar(x); origen.Sacar(x);
        }
        while (!aux.ConjuntoVacio()) {
            int x = aux.Elegir(); origen.Agregar(x); aux.Sacar(x);
        }
        return copia;
    }

    // LÓGICA DESAFÍO 5
    public static void tracingPrioridad() {
        ColaPrioTDA cp = new Implementaciones.ColaPrioDin(); cp.InicializarCola();
        cp.AcolarPrioridad(5, 2); // Llega primero pero P=2
        cp.AcolarPrioridad(5, 2); // Los duplicados se mantienen
        cp.AcolarPrioridad(-4, 0); // Mayor prioridad, salta al frente

        while(!cp.ColaVacia()){
            System.out.print(cp.Primero() + " ");
            cp.Desacolar();
        }
        System.out.println();
    }
}
