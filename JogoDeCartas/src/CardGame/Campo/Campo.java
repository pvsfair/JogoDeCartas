/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Campo;

import CardGame.Cartas.Carta;
import CardGame.Cartas.Magia;

/**
 *
 * @author Paulo Victor
 */
public class Campo {

    Carta[] carta = new Carta[7];

    public boolean subirCarta(int posicao) {
        int destino;
        if (carta[posicao] == null || carta[posicao] instanceof Magia) {
            return false;
        }
        if ((destino = AchaMelhorCandidato()) != -1) {
            carta[destino] = carta[posicao];
            carta[posicao] = null;
            return true;
        }
        return false;
    }

    public boolean remanejarCartas() {
        return true;
    }

    private int AchaMelhorCandidato() {
        for (int i = 0; i < carta.length - 2; i++) {
            if (carta[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private boolean MoverCarta(int origem, int destino) {
        return true;
    }

}
