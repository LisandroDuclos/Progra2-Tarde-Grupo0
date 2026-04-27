package Implementacion;
import Interfaz.ColaTDA;

public class ColaDin implements ColaTDA {
    class Nodo { int val; Nodo sig; }
    private Nodo cab, col;
    public void InicializarCola() { cab = null; col = null; }
    public void Acolar(int x) {
        Nodo n = new Nodo(); n.val = x;
        if (col != null) col.sig = n; else cab = n;
        col = n;
    }
    public void Desacolar() { if (cab != null) { cab = cab.sig; if (cab == null) col = null; } }
    public boolean ColaVacia() { return cab == null; }
    public int Primero() { return cab.val; }
}