/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Campo;

import CardGame.Cartas.Carta;
import CardGame.Cartas.Magia;
import CardGame.Cartas.Monstro;

/**
 *
 * @author Paulo Victor
 */
public class Campo {

    Carta[] cartas = new Carta[7];

    public boolean subirCarta(int posicao) {
        int destino;
        if (cartas[posicao] == null || cartas[posicao] instanceof Magia) {
            return false;
        }
        if ((destino = AchaMelhorCandidato()) != -1) {
            cartas[destino] = cartas[posicao];
            cartas[posicao] = null;
            return true;
        }
        return false;
    }

    public boolean remanejarCartas() {
        int i;
        for (i = 0; i < cartas.length; i++) {
            if (cartas[i] == null) {
                break;
            }
        }
        for (int j = i + 1; j < cartas.length; j++) {
            if(cartas[j] != null && cartas[j] instanceof Monstro){
                cartas[i] = cartas[j];
                cartas[j] = null;
                i++;
            }
        }
        return true;
    }

    private int AchaMelhorCandidato() {
        for (int i = 0; i < cartas.length - 2; i++) {
            if (cartas[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int getPosicaoCarta(Carta carta) {
        for (int i = 0; i < cartas.length; i++) {
            if (carta.equals(cartas[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean MoverCarta(int origem, int destino) {
        return true;
    }

}
