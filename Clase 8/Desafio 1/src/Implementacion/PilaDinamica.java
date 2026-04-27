package Implementacion;
import Interfaz.PilaTDA;

public class PilaDinamica implements PilaTDA {
    class Nodo {
        int val;
        Nodo sig;
    }
    private Nodo primero;

    public void InicializarPila() { primero = null; }
    public void Apilar(int x) {
        Nodo n = new Nodo();
        n.val = x;
        n.sig = primero;
        primero = n;
    }
    public void Desapilar() { if (primero != null) primero = primero.sig; }
    public boolean PilaVacia() { return primero == null; }
    public int Tope() { return primero.val; }
}