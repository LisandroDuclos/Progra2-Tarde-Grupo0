package Implementaciones;
import Interfaces.PilaTDA;

public class PilaEstatica implements PilaTDA {
    public static final int MAX = 10;
    private int[] datos;
    private int cantidad;

    @Override
    public void InicializarPila() {
        datos = new int[MAX];
        cantidad = 0;
    }

    @Override
    public void Apilar(int x) {
        datos[cantidad] = x;
        cantidad++;
    }

    @Override
    public void Desapilar() {
        if (!PilaVacia())
            cantidad--;
    }

    @Override
    public int Tope() {return datos[cantidad - 1];}

    @Override
    public boolean PilaVacia() {return cantidad == 0;}
}
