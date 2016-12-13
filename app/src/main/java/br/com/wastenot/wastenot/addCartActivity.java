package br.com.wastenot.wastenot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.*;

public class addCartActivity extends AppCompatActivity {
    BDWrapper db;
    ListView list;
    EditText edts;
    String card,deckId,deckName;
    List<Cards> cardsList;
    List<String> cardsSelect = new ArrayList<String>();
    List<ItemDeckView> itens = new ArrayList<ItemDeckView>();
    AdapterDeckCarts adapter = null;
    private static int save = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);

        db = new BDWrapper(this);
        list = (ListView) findViewById(R.id.list);
        edts = (EditText) findViewById(R.id.edtSearch);

        Intent intent = getIntent();
        deckId =  intent.getStringExtra("deckid");
        deckName = intent.getStringExtra("deckName");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (String cards : cardsSelect) {
                   db.addCardOnDeck(deckId,cards);
                }
                if(cardsSelect.size()>1) {
                    Toast.makeText(getApplicationContext(), "cards were successfully added to " + deckName + " deck", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "One card was successfully added to " + deckName + " deck", Toast.LENGTH_LONG).show();

                }
                cardsSelect.removeAll(cardsSelect);

            }
        });
        list.setChoiceMode(list.CHOICE_MODE_MULTIPLE);

    }

    public void search(final View view) {
        if(adapter!=null){
            adapter.updateList();
        }
        getCards();
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               if (cardsSelect.contains(String.valueOf(cardsList.get(position).getId()))) {
                    cardsSelect.remove(cardsSelect.indexOf(String.valueOf(cardsList.get(position).getId())));
                    adapter.selects.remove(adapter.selects.indexOf(adapter.getItemId(position)));
                    adapter.notifyDataSetChanged();

                } else if (!cardsSelect.isEmpty()) {
                    cardsSelect.add(String.valueOf(cardsList.get(position).getId()));
                    adapter.selects.add(adapter.getItemId(position));
                    adapter.notifyDataSetChanged();
                } else {
                    Cards Cards = cardsList.get(position);
                    Intent intent = (new Intent(getApplicationContext(), CardDetail.class));
                    intent.putExtra("cards", Cards);
                    startActivity(intent);
                }
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cardsSelect.add(String.valueOf(cardsList.get(position).getId()));
                adapter.selects.add(adapter.getItemId(position));
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }
    protected   List<ItemDeckView> updateCardList(){
        int img =0;
        int icon = 0;
        card = String.valueOf(edts.getText());
        cardsList = db.getCard(card);
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
                case "B,U":
                    img = R.drawable.ub;
                    break;
                case "B,G":
                    img = R.drawable.bg;
                    break;
                case "G,B":
                    img = R.drawable.bg;
                    break;
                case "R,G":
                    img = R.drawable.rg;
                    break;
                case "G,R":
                    img = R.drawable.rg;
                    break;
                case "W,B":
                    img = R.drawable.wb;
                    break;
                case "B,W":
                    img = R.drawable.wb;
                    break;
                case "U,R":
                    img = R.drawable.ur;
                    break;
                case "R,U":
                    img = R.drawable.ur;
                    break;
                case "G,U":
                    img = R.drawable.gu;
                    break;
                case "U,G":
                    img = R.drawable.gu;
                    break;
                case "W,U":
                    img = R.drawable.wu;
                    break;
                case "U,W":
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
                case "R,B":
                    img = R.drawable.br;
                    break;
                case "G,W":
                    img = R.drawable.gw;
                    break;
                case "W,G":
                    img = R.drawable.gw;
                    break;
                default:
                    img = R.drawable.c;
            }
           /* if(cd.getWhishlist().equalsIgnoreCase("1")){
                icon = R.drawable.ic_wanted_list_black;
            } else if(cd.getHavelist().equalsIgnoreCase("1")){
                icon = R.drawable.ic_have_black;

            }else{
                icon = 0;
            }*/


            itens.add(new ItemDeckView(cd.getName(), img,""));
        }
        return itens;

    }

    protected void getCards(){
        adapter = new AdapterDeckCarts(this, updateCardList());

        list.setAdapter(adapter);
    }
}
