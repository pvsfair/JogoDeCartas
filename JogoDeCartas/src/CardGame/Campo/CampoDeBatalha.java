/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Campo;

import CardGame.Cartas.Carta;
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
    protected int turno = 1;
    private boolean controladorTurno = false;
    protected Lado jogadorDaVez;

    public CampoDeBatalha(Jogador jogadorAzul, Jogador jogadorVermelho) {
        super((JDialog) null, true);
        this.jogadorAzul = jogadorAzul;
        this.jogadorVermelho = jogadorVermelho;
        iniciarBatalha();
    }

    private void iniciarBatalha() {
        jogadorDaVez = selecionarJogadorAleatorio();
        jogadorAzul.getBaralho().embaralhaCartas();
        jogadorVermelho.getBaralho().embaralhaCartas();
        for (int i = 0; i < 5; i++) {
            jogadorAzul.comprarCarta();
            jogadorVermelho.comprarCarta();
        }
        inicarTurno();
    }

    protected Lado ladoOposto(Lado lado) {
        if (lado == Lado.AZUL) {
            return Lado.VERMELHO;
        } else {
            return Lado.AZUL;
        }
    }

    private Lado selecionarJogadorAleatorio() {
        if (Math.random() < 0.5) {
            return Lado.AZUL;
        } else {
            return Lado.VERMELHO;
        }

    }

    public boolean invocarCarta(int id, Lado lado) {
        if (lado == Lado.AZUL) {
            Carta carta = jogadorAzul.pegarCarta(id);
            if (!jogadorAzul.invocarCarta(carta)) {
                return false;
            }
            if (campoAzul.carta[5] == null) {
                campoAzul.carta[5] = carta;
                return true;
            } else if (campoAzul.carta[6] == null) {
                campoAzul.carta[6] = carta;
                return true;
            }
        } else {
            Carta carta = jogadorVermelho.pegarCarta(id);
            if (!jogadorVermelho.invocarCarta(carta)) {
                return false;
            }
            if (campoVermelho.carta[5] == null) {
                campoVermelho.carta[5] = carta;
                return true;
            } else if (campoVermelho.carta[6] == null) {
                campoVermelho.carta[6] = carta;
                return true;
            }
        }

        return false;
    }

    public boolean finalizarTurnoJogadorAtual() {
        if (jogadorDaVez == Lado.AZUL) {
            if (!jogadorAzul.avaliarFimTurno()) {
                return false;
            }
            if (campoAzul.carta[5] != null) {
                campoAzul.subirCarta(5);
            }
            if (campoAzul.carta[6] != null) {
                campoAzul.subirCarta(6);
            }
        } else {
            if (!jogadorVermelho.avaliarFimTurno()) {
                return false;
            }
            if (campoVermelho.carta[5] != null) {
                campoVermelho.subirCarta(5);
            }
            if (campoVermelho.carta[6] != null) {
                campoVermelho.subirCarta(6);
            }
        }
        if (controladorTurno) {
            turno++;
        }
        controladorTurno = !controladorTurno;
        jogadorDaVez = ladoOposto(jogadorDaVez);
        inicarTurno();
        return true;
    }

    public void inicarTurno() {
        if (jogadorDaVez == Lado.AZUL) {
            jogadorAzul.iniciarTurno(turno);
        } else {
            jogadorVermelho.iniciarTurno(turno);
        }
    }
    
    public boolean atacar(Carta origem){
        
        return false;
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
