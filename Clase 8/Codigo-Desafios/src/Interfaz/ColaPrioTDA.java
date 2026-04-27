package Interfaz;

public interface ColaPrioTDA {
    void InicializarCola();
    void AcolarPrioridad(int x, int p);
    void Desacolar();
    int Primero();
    int Prioridad();
    boolean ColaVacia();
}
