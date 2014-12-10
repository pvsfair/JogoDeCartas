package CardGame.Cartas;

public abstract class Monstro extends Carta {

    private final int vidaMax;
    private final String tipo;
    private int vida;
    private int dano;

    public Monstro(int id, String nome, String tipo, int custo, int vida, int dano) {
        super(id, nome, custo);
        this.vida = vidaMax = vida;
        this.dano = dano;
        this.tipo = tipo;
    }

    public abstract boolean passivaDeInvocacao();

    public abstract boolean passivaDeBatalha();

    public int getVidaMax() {
        return vidaMax;
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

    public String getTipo() {
        return tipo;
    }

}
