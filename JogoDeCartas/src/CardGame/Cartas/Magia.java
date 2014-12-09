package CardGame.Cartas;

public class Magia extends Carta {

    public Magia(int id, String nome, int custo) {
        super(id, nome, custo);
    }
    
    public boolean Magia(){
        return true;
    }

    @Override
    public String toString() {
        return Integer.toString(getId());
    }

}
