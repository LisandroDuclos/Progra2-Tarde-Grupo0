import Interfaces.*;
import Implementaciones.*;

public class Main {

    public static int contarPrioridad1(ColaPrioridadTDA cola) {
        ColaPrioridadDinamica auxiliar1 = new ColaPrioridadDinamica();
        ColaPrioridadDinamica auxiliar2 = new ColaPrioridadDinamica();
        auxiliar1.InicializarColaPrioridad();
        auxiliar2.InicializarColaPrioridad();

        while (!cola.ColaVacia() && cola.Prioridad() != 1) {
            auxiliar1.AcolarPrioridad(cola.Primero(), cola.Prioridad());
            cola.Desacolar();
        }

        int contador = 0;
        while (!cola.ColaVacia() && cola.Prioridad() == 1) {
            auxiliar2.AcolarPrioridad(cola.Primero(), cola.Prioridad());
            cola.Desacolar();
            contador++;
        }

        while (!auxiliar2.ColaVacia()) {
            cola.AcolarPrioridad(auxiliar2.Primero(), auxiliar2.Prioridad());
            auxiliar2.Desacolar();
        }
        while (!auxiliar1.ColaVacia()) {
            cola.AcolarPrioridad(auxiliar1.Primero(), auxiliar1.Prioridad());
            auxiliar1.Desacolar();
        }

        return contador;
    }

    public static void main(String[] args) {
        ColaPrioridadDinamica cola = new ColaPrioridadDinamica();
        cola.InicializarColaPrioridad();

        cola.AcolarPrioridad(10, 2);
        cola.AcolarPrioridad(20, 2);
        cola.AcolarPrioridad(30, 1);
        cola.AcolarPrioridad(40, 1);
        cola.AcolarPrioridad(50, 1);

        int resultado = contarPrioridad1(cola);
        System.out.println("Cantidad de P=1: " + resultado); // debe imprimir 3

        System.out.println("Cola restaurada:");
        while (!cola.ColaVacia()) {
            System.out.println("  valor=" + cola.Primero() + " prioridad=" + cola.Prioridad());
            cola.Desacolar();
        }
    }
}
