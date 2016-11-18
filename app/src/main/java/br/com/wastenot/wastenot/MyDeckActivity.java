package br.com.wastenot.wastenot;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDeckActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BDWrapper db;
    ListView list;
    MenuItem dell,edit,share;
    AdapterDeckView adapter;
    List<Deck> deckList;
    List<ItemDeckView> itens = new ArrayList<ItemDeckView>();
    List<String> decksSelect = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_deck);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab01);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLangDialog();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        list = (ListView) findViewById(R.id.list);
        db = new BDWrapper(this);
        getDesks();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (decksSelect.contains(String.valueOf(deckList.get(position).getId()))) {

                    decksSelect.remove(decksSelect.indexOf(String.valueOf(deckList.get(position).getId())));
                    view.setBackgroundColor(0);
                    if (decksSelect.size()==1){
                        edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                        edit.setVisible(true);
                    }
                        if (decksSelect.isEmpty()) {
                            dell.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                            dell.setVisible(false);
                            edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                            edit.setVisible(false);
                            share.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                            share.setVisible(false);

                        }
                } else if (!decksSelect.isEmpty()) {

                    edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                    edit.setVisible(false);


                    decksSelect.add(String.valueOf(deckList.get(position).getId()));
                    view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.itemselect));
                } else {
                    Deck deck = deckList.get(position);

                    Intent intent = (new Intent(getApplicationContext(), DeckDetail.class));
                    intent.putExtra("deck", deck);
                    startActivity(intent);
                }


            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                dell.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                dell.setVisible(true);
                edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                edit.setVisible(true);
                share.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                share.setVisible(true);

                decksSelect.add(String.valueOf(deckList.get(position).getId()));
                if (decksSelect.size() > 1) {
                    edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                    edit.setVisible(false);
                }

                view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.itemselect));

                return true;
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.decks, menu);
        dell = menu.findItem(R.id.action_dell);
        edit = menu.findItem(R.id.action_edit);
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
            for (String idc  : decksSelect) {
                Log.d("id", idc);
              db.dellDeck(idc);
            }
            decksSelect.removeAll(decksSelect);
            adapter.notifyDataSetChanged();
            adapter.updateList(updateDeckList());
            getDesks();
            dell.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
            dell.setVisible(false);
            edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
            edit.setVisible(false);
            Toast.makeText(this, "items Dell successfully", Toast.LENGTH_LONG).show();

        }else if(id==R.id.action_edit){

            showEdtDialog();




        }else if(id==R.id.action_share){



            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=141988&type=card");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }

        return super.onOptionsItemSelected(item);
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
    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edt_deck);

        dialogBuilder.setTitle("New Deck");
        dialogBuilder.setMessage("Enter a  name for your deck");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(!edt.getText().toString().isEmpty()) {
                    db.addDecks(edt.getText().toString());
                    adapter.notifyDataSetChanged();
                    adapter.updateList(updateDeckList());
                    getDesks();
                }else {
                    Toast.makeText(getApplicationContext(), "Type a name!", Toast.LENGTH_LONG).show();
                }
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
    public void showEdtDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edt_deck);

        dialogBuilder.setTitle("change Deck Name");
        dialogBuilder.setMessage("Enter a new name for your deck");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(!edt.getText().toString().isEmpty()) {
                    Log.d("cards", decksSelect.get(0).toString());

                    db.updateDecks(decksSelect.get(0).toString(), edt.getText().toString());

                    decksSelect.removeAll(decksSelect);
                    adapter.notifyDataSetChanged();
                    adapter.updateList(updateDeckList());
                    getDesks();
                    dell.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                    dell.setVisible(false);
                    edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                    edit.setVisible(false);
                }else {
                    Toast.makeText(getApplicationContext(), "Type a name!", Toast.LENGTH_LONG).show();
                }
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
    protected void getDesks(){
        adapter = new AdapterDeckView(this, updateDeckList());
        list.setAdapter(adapter);
    }
    protected List<ItemDeckView> updateDeckList(){
        deckList = db.getAllDecks();
        int img = img = R.drawable.ic_home;;
        for (Deck dc : deckList) {


            itens.add(new ItemDeckView(dc.getDeckName(), img,String.valueOf(dc.getQtd())));
        }
        return itens;

    }


}
