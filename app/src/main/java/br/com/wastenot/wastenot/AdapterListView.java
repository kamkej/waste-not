package br.com.wastenot.wastenot;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by julio on 3/17/16.
 */
public class AdapterListView extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ItemListView> itens;

    public  AdapterListView(Context context,List<ItemListView> itens){
        this.itens = itens;
        mInflater= LayoutInflater.from(context);

    }
    public void updateList(List<ItemListView> itensUpdate){
        itens.clear();
        itens = itensUpdate;
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
            view= mInflater.inflate(R.layout.item_list,null);
            itemHolder = new ItemSuporte();
            itemHolder.txtTitle = ((TextView) view.findViewById(R.id.itemtext));
            itemHolder.imgIcon = ((ImageView) view.findViewById(R.id.itemimgview));
            itemHolder.imgType = ((ImageView) view.findViewById(R.id.type));
            view.setTag(itemHolder);
        }else {
            itemHolder = (ItemSuporte) view.getTag();
        }
        ItemListView item = itens.get(position);
        itemHolder.txtTitle.setText(item.getTexto());
        itemHolder.imgIcon.setImageResource(item.getIconeRid());
        itemHolder.imgType.setImageResource(item.getIconeType());

        return view;
    }
    private class ItemSuporte{
        ImageView imgIcon;
        TextView txtTitle;
        ImageView imgType;
        boolean select = false;
    }

}
