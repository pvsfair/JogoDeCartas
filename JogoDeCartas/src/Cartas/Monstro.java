package Cartas;

public class Monstro extends Carta {

    private int vida;
    private int dano;

    public Monstro(int vida, int dano, int id, String nome, int custo) {
        super(id, nome, custo);
        this.vida = vida;
        this.dano = dano;
    }

    public boolean passivaDeInvocacao() {
        return false;
    }
    public boolean passivaDeBatalha() {
        return false;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

}
