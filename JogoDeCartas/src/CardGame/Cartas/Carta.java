package CardGame.Cartas;

import java.util.Random;

public abstract class Carta implements Comparable<Carta> {

    protected int id;
    protected String nome;
    protected int custo;

    public Carta(int id, String nome, int custo) {
        this.id = id;
        this.nome = nome;
        this.custo = custo;
    }

    @Override
    public int compareTo(Carta o) {
        Random rand = new Random();

        return (rand.nextInt(2) == 0) ? 1 : -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        return this.id == other.id;
    }

    public abstract void showInfo();

    @Override
    public String toString() {
        return nome + '(' + custo + ')';
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

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

}
