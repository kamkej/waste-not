package br.com.wastenot.wastenot;

import java.io.Serializable;

/**
 * Created by julio on 6/8/16.
 */
public class Deck implements Serializable {
    private int id;
    private String deckName;
    private int qtd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
