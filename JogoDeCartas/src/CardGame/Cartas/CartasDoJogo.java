/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Cartas;

import CardGame.Monstros.MonstroAgua;
import CardGame.Monstros.MonstroFogo;
import CardGame.Monstros.MonstroLama;
import CardGame.Monstros.MonstroNeutro;
import CardGame.Monstros.MonstroTerra;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Victor
 */
public class CartasDoJogo extends ConjuntoDeCartas {

    public CartasDoJogo() {
        carregarDoTexto();
    }

    private void carregarDoTexto() {
        try {
            InputStream in = new FileInputStream("cartas.txt");
            Scanner s = new Scanner(in, "ISO-8859-1");
            String linha = "";
            String[] tags = {"TIPO", "ID", "NOME", "CUSTO", "VIDA", "DANO"};

            while (s.hasNextLine()) {
                linha = s.nextLine();
//                System.out.println(linha);
                if (linha.charAt(0) != '#') {
                    Carta carta = null;
                    String[] split = linha.split("::");
                    switch (split[0]) {
                        case "Neutro":
                            carta = new MonstroNeutro(Integer.parseInt(split[1]),
                                    split[2], Integer.parseInt(split[3]),
                                    Integer.parseInt(split[4]), Integer.parseInt(split[5]));
                            break;
                        case "Agua":
                            carta = new MonstroAgua(Integer.parseInt(split[1]),
                                    split[2], Integer.parseInt(split[3]),
                                    Integer.parseInt(split[4]), Integer.parseInt(split[5]));
                            break;
                        case "Fogo":
                            carta = new MonstroFogo(Integer.parseInt(split[1]),
                                    split[2], Integer.parseInt(split[3]),
                                    Integer.parseInt(split[4]), Integer.parseInt(split[5]));
                            break;
                        case "Terra":
                            carta = new MonstroTerra(Integer.parseInt(split[1]),
                                    split[2], Integer.parseInt(split[3]),
                                    Integer.parseInt(split[4]), Integer.parseInt(split[5]));
                            break;
                        case "Lama":
                            carta = new MonstroLama(Integer.parseInt(split[1]),
                                    split[2], Integer.parseInt(split[3]),
                                    Integer.parseInt(split[4]), Integer.parseInt(split[5]));
                            break;
                        case "Magia":
                            carta = new Magia(Integer.parseInt(split[1]), split[2], Integer.parseInt(split[3]));
                            break;
                        default:
                            System.out.println("Tipo Não Identificado");
                            System.out.println("Id: " + split[1] + '\n' + "Nome: " + split[2]);
                    }
                    if (carta != null) {
                        cartas.add(carta);
                    }
                }
            }
            s.close();
            in.close();
        } catch (IOException ex) {
            System.out.println("erro");
        }
    }
    
    public Carta getCarta(int id){
        for (Carta carta : cartas) {
            if(carta.getId() == id){
                return carta;
            }
        }
        return null;
    }

    @Override
    public boolean mostraCartas() {
        return false;
    }

    @Override
    public boolean contemCarta(int id) {
        return false;
    }

}
