package CardGame.Monstros;

import CardGame.Tipos.INeutro;
import CardGame.Cartas.Monstro;

public class MonstroNeutro extends Monstro implements INeutro {

    public MonstroNeutro(int id, String nome, int custo, int vida, int dano) {
        super(id, nome, "Neutro", custo, vida, dano);
    }
    
    public MonstroNeutro(Monstro monstro){
        super(monstro.getId(), monstro.getNome(), monstro.getTipo(), monstro.getCusto(), monstro.getVida(), monstro.getDano());
    }

    @Override
    public boolean passivaDeInvocacao() {
        return false;
    }

    @Override
    public boolean passivaDeBatalha() {
        return false;
    }

}
