package br.com.wastenot.wastenot;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julio on 3/17/16.
 */
public class AdapterDeckCarts extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ItemDeckView> itens;

    public AdapterDeckCarts(Context context, List<ItemDeckView> itens){
        this.itens = itens;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void updateList(){
        itens.clear();
    }
    public void updateList(Context context, List<ItemDeckView> itens,Long id){
        this.itens = itens;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ItemSuporte itemHolder;
        if(view == null){
            view= mInflater.inflate(R.layout.item_list_carts,null);
            itemHolder = new ItemSuporte();
            itemHolder.txtTitle = ((TextView) view.findViewById(R.id.itemtext));
            itemHolder.imgIcon = ((ImageView) view.findViewById(R.id.itemimgview));
            itemHolder.qtd = ((TextView) view.findViewById(R.id.txtqtd));
            view.setTag(itemHolder);
        }else {
            itemHolder = (ItemSuporte) view.getTag();
        }
        ItemDeckView item = itens.get(position);
        itemHolder.txtTitle.setText(item.getTexto());
        itemHolder.imgIcon.setImageResource(item.getIconeRid());
        itemHolder.qtd.setText(item.getQtd());

        return view;
    }
    private class ItemSuporte{
        ImageView imgIcon;
        TextView txtTitle;
        TextView qtd;
    }


}
