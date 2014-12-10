package CardGame.Usuario;

import CardGame.Campo.CampoDeBatalha;
import CardGame.Cartas.Carta;
import CardGame.Cartas.CartasNaMao;
import CardGame.Cartas.ConjuntoDeCartas;
import CardGame.Deck.Deck;
import CardGame.Habilidades.Habilidade;
import javax.swing.JOptionPane;

public class Jogador {

    private int vida;
    private Deck baralho;
    private Habilidade habilidade;
    private CartasNaMao cartasNaMao;
    private CampoDeBatalha.Lado ladoDoCampo;
    private int energia;

    public Jogador(Deck baralho, Habilidade habilidade, CampoDeBatalha.Lado ladoDoCampo) {
        vida = 10;
        energia = 1;
        cartasNaMao = new CartasNaMao(5);
        this.baralho = baralho;
        this.habilidade = habilidade;
        this.ladoDoCampo = ladoDoCampo;
    }

    public void iniciarTurno(int turno) {
        energia += ((energia <= 10) && (turno % 2 == 0)) ? 1 : 0;
        vida += (vida < 10) ? 1 : 0;
        comprarCarta();
    }

    public boolean comprarCarta() {
        Carta carta = baralho.getCartas().remove(0);
        cartasNaMao.adicionaCarta(carta);
        return true;
    }

    public Carta pegarCarta(int id) {
        for (Carta carta : cartasNaMao.getCartas()) {
            if (carta.getId() == id) {
                return carta;
            }
        }
        return null;
    }

    public void jogarCartaFora(int id) {
        cartasNaMao.retiraCarta(id);
    }

    public boolean avaliarFimTurno() {
        if (cartasNaMao.checarMaxCartas()) {
            JOptionPane.showMessageDialog(null, "Você tem mais de 5 cartas na mão, jogue fora ou invoque algumas para terminar o turno.");
            return false;
        }
        return true;
    }

    public int invocarCarta(int id) {
        return 0;
    }

    public boolean usarHabilidade() {
        return false;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Deck getBaralho() {
        return baralho;
    }

    public void setBaralho(Deck baralho) {
        this.baralho = baralho;
    }

    public Habilidade getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(Habilidade habilidade) {
        this.habilidade = habilidade;
    }

    public CartasNaMao getCartasNaMao() {
        return cartasNaMao;
    }

    public void setCartasNaMao(CartasNaMao cartasNaMao) {
        this.cartasNaMao = cartasNaMao;
    }

    public CampoDeBatalha.Lado getLadoDoCampo() {
        return ladoDoCampo;
    }

    public void setLadoDoCampo(CampoDeBatalha.Lado ladoDoCampo) {
        this.ladoDoCampo = ladoDoCampo;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }
}
