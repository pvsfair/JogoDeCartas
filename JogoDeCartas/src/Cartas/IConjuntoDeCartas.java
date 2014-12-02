/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

/**
 *
 * @author Lab4
 */
public interface IConjuntoDeCartas {

    public enum TipoOrd {

        ID, NOME, TIPO, CUSTO_INVOCACAO, VIDA, DANO
    }

    public void embaralhaCartas();

    public void ordenaCartas(TipoOrd tipoOrd);

    public void adicionaCarta(Carta carta);

    public void retiraCarta(int id);

    public boolean mostraCartas();

    public boolean contemCarta(int id);
}
