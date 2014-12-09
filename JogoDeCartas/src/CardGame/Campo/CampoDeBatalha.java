/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Campo;

import CardGame.Usuario.Jogador;
import javax.swing.JDialog;

/**
 *
 * @author Paulo Victor
 */
public class CampoDeBatalha extends javax.swing.JDialog {

    public enum Lado {

        AZUL, VERMELHO
    }

    protected Jogador jogadorAzul;
    protected Jogador jogadorVermelho;
    protected Campo campoAzul = new Campo();
    protected Campo campoVermelho = new Campo();
    protected int turno;
    protected Lado jogadorDaVez;

    public CampoDeBatalha(Jogador jogadorAzul, Jogador jogadorVermelho) {
        super((JDialog) null, true);
        this.jogadorAzul = jogadorAzul;
        this.jogadorVermelho = jogadorVermelho;
    }

    public boolean invocarCarta(int id, Lado lado) {
        return true;
    }

    public boolean finalizarTurnoJogadorAtual() {
        return true;
    }

    public boolean remanejarCartas() {
        return true;
    }

    public boolean subirCarta(int posicao) {
        return true;
    }

    private int AchaMelhorCandidato(int posicao) {
        return 0;
    }

    private boolean MoverCarta(int origem, int destino) {
        return true;
    }

    public int getTurno() {
        return turno;
    }

    public Lado getJogadorDaVez() {
        return jogadorDaVez;
    }

    public void setJogadorDaVez(Lado jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }
}
