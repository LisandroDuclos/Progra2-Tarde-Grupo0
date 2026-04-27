package Implementacion;
import Interfaz.*;

class Nodo {
    int val;
    int prio;
    Nodo sig;
}

public class Implementaciones {

    public static class PilaDin implements PilaTDA {
        private Nodo primero;
        public void InicializarPila() { primero = null; }
        public void Apilar(int x) {
            Nodo n = new Nodo(); n.val = x; n.sig = primero; primero = n;
        }
        public void Desapilar() { primero = primero.sig; }
        public boolean PilaVacia() { return primero == null; }
        public int Tope() { return primero.val; }
    }

    public static class ColaDin implements ColaTDA {
        private Nodo cab, col;
        public void InicializarCola() { cab = null; col = null; }
        public void Acolar(int x) {
            Nodo n = new Nodo(); n.val = x; n.sig = null;
            if (col != null) col.sig = n; else cab = n;
            col = n;
        }
        public void Desacolar() { cab = cab.sig; if (cab == null) col = null; }
        public boolean ColaVacia() { return cab == null; }
        public int Primero() { return cab.val; }
    }

    public static class ColaPrioDin implements ColaPrioTDA {
        private Nodo mayor;
        public void InicializarCola() { mayor = null; }
        public void AcolarPrioridad(int x, int p) {
            Nodo n = new Nodo(); n.val = x; n.prio = p;
            if (mayor == null || p < mayor.prio) {
                n.sig = mayor; mayor = n;
            } else {
                Nodo aux = mayor;
                while (aux.sig != null && aux.sig.prio <= p) aux = aux.sig;
                n.sig = aux.sig; aux.sig = n;
            }
        }
        public void Desacolar() { mayor = mayor.sig; }
        public int Primero() { return mayor.val; }
        public int Prioridad() { return mayor.prio; }
        public boolean ColaVacia() { return mayor == null; }
    }

    public static class ConjuntoDin implements ConjuntoTDA {
        private Nodo primero;
        public void InicializarConjunto() { primero = null; }
        public void Agregar(int x) {
            if (!this.Pertenece(x)) {
                Nodo n = new Nodo(); n.val = x; n.sig = primero; primero = n;
            }
        }
        public void Sacar(int x) {
            if (primero != null) {
                if (primero.val == x) primero = primero.sig;
                else {
                    Nodo aux = primero;
                    while (aux.sig != null && aux.sig.val != x) aux = aux.sig;
                    if (aux.sig != null) aux.sig = aux.sig.sig;
                }
            }
        }
        public boolean Pertenece(int x) {
            Nodo aux = primero;
            while (aux != null && aux.val != x) aux = aux.sig;
            return aux != null;
        }
        public int Elegir() { return primero.val; }
        public boolean ConjuntoVacio() { return primero == null; }
    }
}