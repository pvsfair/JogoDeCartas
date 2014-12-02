package Cartas;

public class Carta {

    private int id;
    private String nome;
    private int custo;

    public Carta(int id, String nome, int custo) {
        this.id = id;
        this.nome = nome;
        this.custo = custo;
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
