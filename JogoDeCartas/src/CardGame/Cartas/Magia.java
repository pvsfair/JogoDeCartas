package CardGame.Cartas;

import javax.swing.JOptionPane;

public class Magia extends Carta {

    public Magia(int id, String nome, int custo) {
        super(id, nome, custo);
    }

    public Magia(Magia magia) {
        super(magia.getId(), magia.getNome(), magia.getCusto());
    }

    public boolean Magia() {
        return true;
    }

    @Override
    public void showInfo() {
        JOptionPane.showMessageDialog(null, "Nome: " + nome + "\nCusto: " + custo + "\nTipo: Magia");
    }

}
