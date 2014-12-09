package CardGame.Monstros;

import CardGame.Tipos.IFogo;
import CardGame.Cartas.Monstro;

public class MonstroFogo extends Monstro implements IFogo {

    public MonstroFogo(int id, String nome, int custo, int vida, int dano) {
        super(id, nome, "Fogo", custo, vida, dano);
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
