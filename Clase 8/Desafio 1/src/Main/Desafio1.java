package Main;

import Interfaz.PilaTDA;
import Interfaz.ColaTDA;
import Implementacion.PilaDinamica;
import Implementacion.ColaDin;

public class Desafio1 {

    public static void main(String[] args) {
        ColaTDA origen = new ColaDin();
        origen.InicializarCola();

        // Carga de prueba: 1 - 2 - 1
        origen.Acolar(1);
        origen.Acolar(2);
        origen.Acolar(1);

        System.out.println("¿Es Palíndromo?: " + esPalindrome(origen));
    }

    public static boolean esPalindrome(ColaTDA origen) {
        // Instanciamos las implementaciones pero las tratamos como interfaces
        PilaTDA p = new PilaDinamica();
        p.InicializarPila();

        ColaTDA cAux = new ColaDin();
        cAux.InicializarCola();

        ColaTDA cCopia = new ColaDin();
        cCopia.InicializarCola();

        // 1. Repartimos los datos de origen a las 3 estructuras auxiliares
        while (!origen.ColaVacia()) {
            int x = origen.Primero();
            p.Apilar(x);       // Para invertir el orden
            cAux.Acolar(x);    // Para mantener el orden original y comparar
            cCopia.Acolar(x);  // Para no perder los datos de origen
            origen.Desacolar();
        }

        // 2. Restauramos la cola de origen (para que no quede vacía al final)
        while (!cCopia.ColaVacia()) {
            origen.Acolar(cCopia.Primero());
            cCopia.Desacolar();
        }

        // 3. Comparamos el orden original (cAux) con el invertido (p)
        while (!cAux.ColaVacia()) {
            if (cAux.Primero() != p.Tope()) {
                return false; // Si uno no coincide, no es palíndromo
            }
            cAux.Desacolar();
            p.Desapilar();
        }

        return true;
    }
}