package br.com.wastenot.wastenot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by julio on 3/17/16.
 */
public class AdapterDeckView extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ItemDeckView> itens;

    public AdapterDeckView(Context context, List<ItemDeckView> itens){
        this.itens = itens;
        mInflater= LayoutInflater.from(context);
    }
    public void updateList(List<ItemDeckView> itensUpdate){
        itens.clear();
        itens = itensUpdate;
        notifyDataSetChanged();

    }
    public void clear() {
        itens.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<ItemDeckView> list) {
        itens.addAll(list);
        notifyDataSetChanged();
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
        ItemSuporte itemHolder;
        if(view == null){
            view= mInflater.inflate(R.layout.item_list_deck,null);
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
