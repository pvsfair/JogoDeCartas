package CardGame.Monstros;

import CardGame.Tipos.IAgua;
import CardGame.Cartas.Monstro;

public class MonstroAgua extends Monstro implements IAgua {

    public MonstroAgua(int id, String nome, int custo, int vida, int dano) {
        super(id, nome, "Agua", custo, vida, dano);
    }
    
    public MonstroAgua(Monstro monstro){
        super(monstro.getId(), monstro.getNome(), monstro.getTipo(), monstro.getCusto(), monstro.getVida(), monstro.getDano());
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
