package CardGame.Cartas;

import java.util.Random;
import javax.swing.JOptionPane;

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

    @Override
    public void showInfo() {
        JOptionPane.showMessageDialog(null, "Nome: " + nome + "\nCusto: " + custo
                + "\nTipo: " + tipo + "\nVida: " + vidaMax + "\nDano: " + dano);
    }

    public boolean atacar(Monstro alvo) {
        Random rand = new Random();
        if (rand.nextInt(10) == 1) {
            alvo.vida -= (this.dano * 1.5f);

            JOptionPane.showMessageDialog(null, "Você causou dano CRÍTICO!!");
        } else {
            alvo.vida -= this.dano;
        }
        return alvo.vida <= 0;
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
