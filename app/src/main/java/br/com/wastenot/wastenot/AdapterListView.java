package br.com.wastenot.wastenot;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julio on 3/17/16.
 */


public class AdapterListView extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ItemListView> itens;
    private List<CheckedTextView> selects = new ArrayList<>();
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
    public View getView(final int position, View view, ViewGroup parent) {
        ItemSuporte itemHolder;
        if(view == null){
            view= mInflater.inflate(R.layout.item_list,null);
            itemHolder = new ItemSuporte();
            itemHolder.txtTitle = ((CheckedTextView) view.findViewById(R.id.itemtext));
            itemHolder.imgIcon = ((ImageView) view.findViewById(R.id.itemimgview));
            itemHolder.imgType = ((ImageView) view.findViewById(R.id.type));
            view.setTag(itemHolder);
            final CheckedTextView chktxt = (CheckedTextView) view.findViewById(R.id.itemtext);
            selects.add(chktxt);
            chktxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(selects.get(position).isChecked()){
                        selects.get(position).setChecked(false);
                    }else {
                        selects.get(position).setChecked(true);
                    }
                }
            });
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
        CheckedTextView txtTitle;
        ImageView imgType;
    }

}
