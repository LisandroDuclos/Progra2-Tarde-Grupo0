import Implementaciones.*;
import Interfaces.ColaTDA;
import Interfaces.PilaTDA;

public class Main {
    public static void main(String[] args) {

        PilaTDA pE = new PilaEstatica();
        ColaTDA cola = new ColaDinamica();
        PilaTDA pD = new PilaDinamica();

        cola.InicializarCola();
        pD.InicializarPila();
        pE.InicializarPila();

        pE.Apilar(7);
        pE.Apilar(12);
        pE.Apilar(100);
        pE.Apilar(45);
        pE.Apilar(1);
        pE.Apilar(0);
        pE.Apilar(4);
        pE.Apilar(96);
        pE.Apilar(23);
        pE.Apilar(19);

        while (!pE.PilaVacia()) {
            int x = pE.Tope();
            cola.Acolar(x);
            pE.Desapilar();
        }

        while (!cola.ColaVacia()) {
            int x = cola.Primero();
            pD.Apilar(x);
            cola.Desacolar();
        }

        while (!pD.PilaVacia()) {
            System.out.println(pD.Tope());
            pD.Desapilar();
        }


    }


}