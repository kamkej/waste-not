package br.com.wastenot.wastenot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class wantedList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BDWrapper db;
    ListView list;
    List<Cards> cardsList;
    MenuItem dell,have,share;
    List<String> cardsSelect = new ArrayList<String>();
    List<ItemListView> itens = new ArrayList<ItemListView>();
    AdapterListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list = (ListView) findViewById(R.id.listwanted);
        db = new BDWrapper(this);

        getCards();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (cardsSelect.contains(cardsList.get(position).getId())) {

                    cardsSelect.remove(cardsSelect.indexOf(String.valueOf(cardsList.get(position).getId())));
                    adapter.selects.remove(adapter.selects.indexOf(adapter.getItemId(position)));
                    adapter.notifyDataSetChanged();
                    if (cardsSelect.size()==1){
                        dell.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                        dell.setVisible(true);
                        have.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                        have.setVisible(true);
                    }
                    if (cardsSelect.isEmpty()) {
                        dell.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                        dell.setVisible(false);
                        have.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                        have.setVisible(false);


                    }
                } else if (!cardsSelect.isEmpty()) {
                    cardsSelect.add(String.valueOf(cardsList.get(position).getId()));
                    adapter.selects.remove(adapter.selects.indexOf(adapter.getItemId(position)));
                    adapter.notifyDataSetChanged();
                } else {
                    Cards card = cardsList.get(position);
                    Intent intent = (new Intent(getApplicationContext(), CardDetail.class));
                    intent.putExtra("cards", card);
                    startActivity(intent);


                }



            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dell.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                dell.setVisible(true);
                have.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                have.setVisible(true);

                cardsSelect.add(cardsList.get(position).getId());
                adapter.selects.add(adapter.getItemId(position));
                adapter.notifyDataSetChanged();
                return true;
            }
        });


    }

    protected   List<ItemListView> updateCardList(){
        cardsList = db.getWantedCard();
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



            itens.add(new ItemListView(cd.getName(), img,0));
        }
        return itens;

    }

    protected void getCards(){
        adapter = new AdapterListView(this, updateCardList());
        list.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wishmenu, menu);
        dell = menu.findItem(R.id.action_dell);
        have = menu.findItem(R.id.action_have);
        share = menu.findItem(R.id.action_share);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
         if(id == R.id.action_dell){
            for (String idc  : cardsSelect) {
                db.updateCard(idc,"0","0");
            }


            dell.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
            dell.setVisible(false);

             adapter.notifyDataSetChanged();
             adapter.updateList(updateCardList());
             have.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
             have.setVisible(false);
             getCards();

             if(cardsSelect.size()>1) {
                 Toast.makeText(this, "items were successfully deleted from my Wanted list", Toast.LENGTH_LONG).show();
             }else {
                 Toast.makeText(this, "One item was successfully deleted from my Wanted list", Toast.LENGTH_LONG).show();
             }
             cardsSelect.removeAll(cardsSelect);


        }else if(id == R.id.action_have){
             for (String idc  : cardsSelect) {
                 db.updateCard(idc,"1","0");
             }


             dell.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
             dell.setVisible(false);
             have.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
             have.setVisible(false);

             adapter.notifyDataSetChanged();
             adapter.updateList(updateCardList());
             getCards();

             if(cardsSelect.size()>1) {
                 Toast.makeText(this, "items were successfully moved to my cards list", Toast.LENGTH_LONG).show();
             }else {
                 Toast.makeText(this, "One item was successfully moved to my cards list", Toast.LENGTH_LONG).show();
             }
             cardsSelect.removeAll(cardsSelect);

         }else if(id==R.id.action_share){
             StringBuilder shareCads = new StringBuilder("<<<My Wantsl>>>\n");
             List<Cards> links = new ArrayList<>();
             links = db.getHaveCard();

             if(cardsSelect.size()==0) {
                 for (Cards card : links) {

                     shareCads.append("\t" + card.getName() + "\n");
                 }

             }else{
                 for (String card : cardsSelect) {

                     shareCads.append("\t"+db.getCardbyid(card).getName()+"\n");

                 }

             }
             Intent sendIntent = new Intent();
             sendIntent.setAction(Intent.ACTION_SEND);
             sendIntent.putExtra(Intent.EXTRA_TEXT, shareCads.toString());
             sendIntent.setType("text/plain");
             startActivity(sendIntent);

             adapter.notifyDataSetChanged();
             adapter.updateList(updateCardList());
             have.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
             have.setVisible(false);
             dell.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
             dell.setVisible(false);
             cardsList.removeAll(cardsList);
             getCards();

         }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //       menu.findItem(R.id.action_favorite).setVisible(true);
        share.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        share.setVisible(true);

        return super.onPrepareOptionsMenu(menu);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_decks) {
            Intent intent = (new Intent(this,MyDeckActivity.class));
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_have_list) {
            Intent intent = (new Intent(this,HaveListActivity.class));
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_wanted_list) {
            Intent intent = (new Intent(this,wantedList.class));
            startActivity(intent);
            finish();
        }else if (id == R.id.nav_home){
            Intent intent = (new Intent(this,MainActivity.class));
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
