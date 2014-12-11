/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Usuario;

import CardGame.Campo.CampoDeBatalha.Lado;
import CardGame.Deck.CriarDeck;
import CardGame.Deck.Deck;
import CardGame.Habilidades.ArvoreHabilidades;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Lab4
 */
public class Usuario {

    private String nomeUsr;
    private CartasDoJogador cartas;
    private ArrayList<Deck> baralhos;
    private Deck defaultDeck = null;
    private ArvoreHabilidades arvoreHabilidades;
    private int nivel;
    private int experiencia;

    public Usuario(String nomeUsr, CartasDoJogador cartas, ArrayList<Deck> baralhos,
            ArvoreHabilidades arvoreHabilidades, int nivel, int experiencia) {
        this.setNomeUsr(nomeUsr);
        this.cartas = cartas;
        this.baralhos = baralhos;
        this.arvoreHabilidades = arvoreHabilidades;
        this.nivel = (nivel <= 0) ? 1 : nivel;
        this.experiencia = (experiencia < 0) ? 0 : experiencia;
    }

    public boolean montarBaralho() {
        if (!CriarDeck.novoDeck(this)) {
            JOptionPane.showMessageDialog(null, "Deck não foi criado.");
            return false;
        }
        return true;
    }

    public boolean verCartas() {
        return true;
    }

    public boolean melhorarCarta(int id) {
        return true;
    }

    public boolean verArvoreHabilidades() {
        return true;
    }

    public boolean melhorarHabilidade(int id) {
        return true;
    }

    public Jogador iniciarBatalha(Lado lado) {
        return new Jogador(new Deck(defaultDeck), null, lado);
    }

    public CartasDoJogador getCartas() {
        return cartas;
    }

    public void setCartas(CartasDoJogador cartas) {
        this.cartas = cartas;
    }

    public ArrayList<Deck> getBaralhos() {
        return baralhos;
    }

    public void setBaralhos(ArrayList<Deck> baralhos) {
        this.baralhos = baralhos;
    }

    private void setNomeUsr(String nomeUsr) {
        this.nomeUsr = nomeUsr;
    }

    public String getNomeUsr() {
        return nomeUsr;
    }

    public Deck getDefaultDeck() {
        return defaultDeck;
    }

    public void setDefaultDeck(int id) {
        for (Deck baralho : baralhos) {
            if (baralho.getId() == id) {
                defaultDeck = baralho;
                return;
            }
        }
        defaultDeck = baralhos.get(0);
    }
}
