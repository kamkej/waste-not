package br.com.wastenot.wastenot;

/**
 * Created by julio on 3/17/16.
 */
public class ItemListView {
    private String texto;
    private int iconeRid;
    private int iconeType;

    public ItemListView(){
        this("", -1,-1);
    }


    public ItemListView(String texto, int iconeRid,int iconeType) {
        this.texto = texto;
        this.iconeRid = iconeRid;
        this.iconeType = iconeType;
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

    public int getIconeType() {
        return iconeType;
    }

    public void setIconeType(int iconeType) {
        this.iconeType = iconeType;
    }
}
