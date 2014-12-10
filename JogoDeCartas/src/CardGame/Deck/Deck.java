package CardGame.Deck;

import CardGame.Cartas.Carta;
import CardGame.Cartas.ConjuntoDeCartas;
import java.util.ArrayList;

public class Deck extends ConjuntoDeCartas {

    private int id;
    private String nome;

    public Deck(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.cartas = new ArrayList<>();
    }

    public Deck(int id, String nome, ArrayList<Carta> cartas) {
        this.id = id;
        this.nome = nome;
        this.cartas = cartas;
    }

    public Deck(Deck deck) {
        this.id = deck.id;
        this.nome = deck.nome;
        this.cartas = new ArrayList<>(deck.cartas);
    }

    @Override
    public String toString() {
        return id + " - " + nome + '(' + cartas.size() + ')';
    }

    @Override
    public boolean mostraCartas() {
        return true;
    }

    @Override
    public boolean contemCarta(int id) {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

}
