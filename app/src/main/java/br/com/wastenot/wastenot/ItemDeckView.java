package br.com.wastenot.wastenot;

/**
 * Created by julio on 3/17/16.
 */
public class ItemDeckView {
    private String texto;
    private int iconeRid;
    private String qtd;

    public ItemDeckView(){
        this("", -1,"");
    }


    public ItemDeckView(String texto, int iconeRid, String qtd) {
        this.texto = texto;
        this.iconeRid = iconeRid;
        this.qtd = qtd;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIconeRid() {
        return iconeRid;
    }

    public void setIconeRid(int iconeRid) {
        this.iconeRid = iconeRid;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }
}
