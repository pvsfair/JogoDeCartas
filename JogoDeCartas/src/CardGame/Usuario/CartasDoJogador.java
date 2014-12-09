/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Usuario;

import CardGame.Cartas.Carta;
import CardGame.Cartas.ConjuntoDeCartas;
import CardGame.Cartas.Magia;
import CardGame.Cartas.Monstro;
import CardGame.Monstros.MonstroNeutro;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paulo Victor
 */
public class CartasDoJogador extends ConjuntoDeCartas {

    public CartasDoJogador() {
        
    }

    public CartasDoJogador(ArrayList<Carta> cartas) {
        super(cartas);
    }

    @Override
    public boolean mostraCartas() {
        String cards = "";
        for (Carta carta : cartas) {
            cards += carta + "\n";
        }
        JOptionPane.showMessageDialog(null, cards);
        return true;
    }

    @Override
    public boolean contemCarta(int id) {
        return true;
    }

}
