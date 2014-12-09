package CardGame.Monstros;

import CardGame.Tipos.IAgua;
import CardGame.Cartas.Monstro;

public class MonstroAgua extends Monstro implements IAgua {

    public MonstroAgua(int id, String nome, int custo, int vida, int dano) {
        super(id, nome, "Agua", custo, vida, dano);
    }

    @Override
    public boolean passivaDeInvocacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean passivaDeBatalha() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
