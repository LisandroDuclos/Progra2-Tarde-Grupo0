package Implementaciones;
import Interfaces.ColaTDA;


public class ColaDinamica implements ColaTDA {
    private NodeC frente;
    private NodeC fondo;

    @Override
    public void InicializarCola(){
        frente = null;
        fondo = null;
    }

    @Override
    public void Acolar(int x) {
        // El nuevo nodo se inserta al final (fondo).
        NodeC node = new NodeC(x, null);
        if (ColaVacia()) {
            frente = node;
        } else {
            fondo.setNext(node);
        }
        fondo = node;
    }

    @Override
    public void Desacolar() {
        if (!ColaVacia()) {
            // Avanzamos frente al siguiente nodo, descartando el nodo actual.
            frente = frente.getNext();
            if (frente == null) {
                fondo = null;
            }
        }
    }

    @Override
    public int Primero() {
        return frente.getData();
    }

    @Override
    public boolean ColaVacia() {
        return frente == null;
    }
}
