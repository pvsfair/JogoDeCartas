package CardGame.Monstros;

import CardGame.Tipos.IAgua;
import CardGame.Tipos.ITerra;
import CardGame.Cartas.Monstro;

public class MonstroLama extends Monstro implements IAgua, ITerra {

    public MonstroLama(int id, String nome, int custo, int vida, int dano) {
        super(id, nome, "Lama", custo, vida, dano);
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
