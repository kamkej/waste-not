package br.com.wastenot.wastenot;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeckDetail extends AppCompatActivity {

    BDWrapper db;
    ListView list;
    AdapterDeckView adapter;
    List<ItemDeckView> itens = new ArrayList<ItemDeckView>();
    List<Cards> cardsList;
    Deck deck;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        adapter.notifyDataSetChanged();
        adapter.updateList(updateCardList());
        getCards();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_detail);
        Intent intent = getIntent();
         deck = (Deck) intent.getSerializableExtra("deck");
        TextView title = (TextView) findViewById(R.id.txtDeckTitle);
        title.setText(deck.getDeckName());



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(getApplicationContext(),addCartActivity.class));
                intent.putExtra("deckid", String.valueOf(deck.getId()));
                intent.putExtra("deckName",String.valueOf(deck.getDeckName()));
                startActivity(intent);
            }
        });

        list = (ListView) findViewById(R.id.listDeck);
        db = new BDWrapper(this);
        getCards();

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cardsList.get(position).getId();
                if(cardsList.get(position).getQtd().equalsIgnoreCase("1")){
                    showDelete(cardsList.get(position).getId());
                }else{
                    showChangeLangDialog(cardsList.get(position).getId());
                }


                return true;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cards Cards = cardsList.get(position);
                Intent intent = (new Intent(getApplicationContext(), CardDetail.class));
                intent.putExtra("cards", Cards);
                startActivity(intent);
            }
        });

    }

    public void showChangeLangDialog(final String idcard) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edt_deck);
        edt.setInputType(InputType.TYPE_CLASS_NUMBER);


        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                db.dellCardOfDeck(deck.getId(), idcard, Integer.parseInt(edt.getText().toString()));
                adapter.clear();

                getCards();
                Toast.makeText(getApplicationContext(), " Cards deleted", Toast.LENGTH_LONG).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
    public void showDelete(final String idcard) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.delete_dialog, null);

        dialogBuilder.setView(dialogView);
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                db.dellCardOfDeck(deck.getId(), idcard, 1);
                adapter.clear();

                getCards();
                Toast.makeText(getApplicationContext()," Card deleted",Toast.LENGTH_LONG).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


    protected void getCards(){
        adapter = new AdapterDeckView(this, updateCardList());
        list.setAdapter(adapter);
    }
    protected List<ItemDeckView> updateCardList(){

        cardsList = db.getCardOfDeck(String.valueOf(deck.getId()));
        int img =0;
        for (Cards cd : cardsList) {

            switch (cd.getColorIdentity())
            {
                case "U":
                    img = R.drawable.u;
                    break;
                case "R":
                    img = R.drawable.r;
                    break;
                case "G":
                    img = R.drawable.g;
                    break;
                case "B":
                    img = R.drawable.b;
                    break;
                case "W":
                    img = R.drawable.w;
                    break;
                case "U,B":
                    img = R.drawable.ub;
                    break;
                case "B,G":
                    img = R.drawable.bg;
                    break;
                case "R,G":
                    img = R.drawable.rg;
                    break;
                case "W,B":
                    img = R.drawable.wb;
                    break;
                case "U,R":
                    img = R.drawable.ur;
                    break;
                case "G,U":
                    img = R.drawable.gu;
                    break;
                case "W,U":
                    img = R.drawable.wu;
                    break;
                case "R,W":
                    img = R.drawable.rw;
                    break;
                case "W,R":
                    img = R.drawable.rw;
                    break;
                case "B,R":
                    img = R.drawable.br;
                    break;
                case "G,W":
                    img = R.drawable.gw;
                    break;
                default:
                    img = R.drawable.c;
            }


            itens.add(new ItemDeckView(cd.getName(), img,cd.getQtd()));
        }
        return itens;

    }
}
