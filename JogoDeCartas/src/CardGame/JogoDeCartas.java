/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame;

import CardGame.Cartas.Monstro;
import CardGame.Monstros.MonstroNeutro;
import CardGame.Usuario.CartasDoJogador;
import java.util.ArrayList;

/**
 *
 * @author Paulo Victor
 */
public class JogoDeCartas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CartasDoJogador cartas = new CartasDoJogador();
        
//        cartas.mostraCartas();
        
        cartas.adicionaCarta(new MonstroNeutro(1, "Servo", 1, 3, 2));
        cartas.adicionaCarta(new MonstroNeutro(2, "Servo", 1, 3, 2));
        cartas.adicionaCarta(new MonstroNeutro(3, "Servo", 1, 3, 2));
        cartas.adicionaCarta(new MonstroNeutro(4, "Servo", 1, 3, 2));
        cartas.adicionaCarta(new MonstroNeutro(5, "Servo", 1, 3, 2));
        cartas.adicionaCarta(new MonstroNeutro(6, "Servo", 1, 3, 2));
        
        cartas.mostraCartas();
        cartas.embaralhaCartas();
        cartas.mostraCartas();
        cartas.retiraCarta(3);
        cartas.mostraCartas();
    }

}
