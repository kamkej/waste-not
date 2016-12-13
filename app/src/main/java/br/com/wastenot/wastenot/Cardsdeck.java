package br.com.wastenot.wastenot;

import java.io.Serializable;

/**
 * Created by julio on 6/8/16.
 */
public class Cardsdeck implements Serializable {
    private String deckid;
    private String cardid;

    public String getDeckid() {
        return deckid;
    }

    public void setDeckid(String deckid) {
        this.deckid = deckid;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }
}
