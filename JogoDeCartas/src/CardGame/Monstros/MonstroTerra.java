package CardGame.Monstros;

import CardGame.Tipos.ITerra;
import CardGame.Cartas.Monstro;

public class MonstroTerra extends Monstro implements ITerra {

    public MonstroTerra(int id, String nome, int custo, int vida, int dano) {
        super(id, nome, "Terra", custo, vida, dano);
    }
    
    public MonstroTerra(Monstro monstro){
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
