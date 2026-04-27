package Interfaz;

public interface ConjuntoTDA {
    void InicializarConjunto();
    void Agregar(int x);
    void Sacar(int x);
    boolean Pertenece(int x);
    int Elegir();
    boolean ConjuntoVacio();
}
