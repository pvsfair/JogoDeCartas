/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Cartas;

import java.util.ArrayList;

/**
 *
 * @author Lab4
 */
public class CartasNaMao extends ConjuntoDeCartas {

    
    int maxCartas;

    public CartasNaMao(int maxCartas) {
        this.maxCartas = maxCartas;
    }

    public boolean avaliarFimTurno() {
        return false;
    }

    private boolean checarMaxCartas() {
        return false;
    }

    @Override
    public boolean mostraCartas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contemCarta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }


}
