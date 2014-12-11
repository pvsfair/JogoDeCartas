/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Cartas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lab4
 */
public abstract class ConjuntoDeCartas {
    protected ArrayList<Carta> cartas;

    public ConjuntoDeCartas() {
        cartas = new ArrayList<>();
    }

    public ConjuntoDeCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public enum TipoOrd {

        ID, NOME, TIPO, CUSTO_INVOCACAO, VIDA, DANO
    }

    public void embaralhaCartas() {
        Collections.sort(cartas);
    }

    public void ordenaCartas(TipoOrd tipoOrd) {

    }

    public void adicionaCarta(Carta carta) {
        cartas.add(carta);
    }

    public void retiraCarta(int id) {
        for (Carta carta : cartas) {
            if (carta.getId() == id) {
                cartas.remove(carta);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Não foi encontrada a carta.");
    }

    public void editTableModel(DefaultTableModel model) {
        for (Carta carta : cartas) {
            if (carta instanceof Monstro) {
                Monstro monstro = (Monstro) carta;
                Object[] rowData = {Integer.toString(monstro.getId()),
                    monstro, "Monstro: " + monstro.getTipo(),
                    Integer.toString(monstro.getVida()), Integer.toString(monstro.getDano())};
                model.addRow(rowData);
            } else if (carta instanceof Magia) {
                Magia magia = (Magia) carta;
                Object[] rowData = {Integer.toString(magia.getId()),
                    magia, "Magia", "Magia", "Magia"};
                model.addRow(rowData);
            }
        }
    }

    public abstract boolean mostraCartas();

    public abstract boolean contemCarta(int id);

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
}
