package br.com.wastenot.wastenot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static br.com.wastenot.wastenot.R.*;


public class CardDetail extends AppCompatActivity {
    ImageButton imgCard;
    TextView textViewLoad;
    private ProgressBar mProgress;
    Cards cards;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_card_detail);
        Intent intent = getIntent();
        cards = (Cards) intent.getSerializableExtra("cards");

        RelativeLayout layout = (RelativeLayout) findViewById(id.layoutid);
        layout.setBackgroundResource(drawable.roundeditext);

        LinearLayout border = (LinearLayout) findViewById(id.layoutBorder);
        int color;
        if (cards.getBorder().equalsIgnoreCase("black")) {
            color = R.color.black;
        } else if (cards.getBorder().equalsIgnoreCase("silver")) {
            color = R.color.silver;
        } else if (cards.getBorder().equalsIgnoreCase("white")) {
            color = R.color.white;
        } else {
            color = R.color.black;
        }


        border.setBackgroundColor(ContextCompat.getColor(this, color));
        mProgress = (ProgressBar) findViewById(id.progressBar);
        textViewLoad = (TextView) findViewById(id.txtLoading);

        TextView title = (TextView) findViewById(id.txtTitle);
        TextView text = (TextView) findViewById(id.txtText);
        //TextView mana = (TextView) findViewById(id.txtMana);
        // ImageView mana0= (ImageView) findViewById(id.imgMana0);

        TextView type = (TextView) findViewById(id.txtType);
        TextView flavor = (TextView) findViewById(id.txtFlavor);
        TextView artist = (TextView) findViewById(id.txtArtist);
        TextView yaer = (TextView) findViewById(id.txtYear);
        TextView number = (TextView) findViewById(id.txtNumber);
        TextView pt = (TextView) findViewById(id.txtPT);


        imgCard = (ImageButton) findViewById(id.imgcard);


        title.setText(cards.getName());
        text.setText(cards.getText());



       // text.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable.tx,0,0,0);

        //  mana.setText(cards.getManaCost());
        String[] manacost = cards.getManaCost().split(",");
        // Log.d("mana",manacost[0]);
        // Log.d("n",String.valueOf(manacost.length));
        //   mana0.setImageResource(drawable.b);
        for (int i=0; i < manacost.length; i++){
            ImageView mana = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(48, 48);
            mana.setLayoutParams(layoutParams);
            LinearLayout layout1mana = (LinearLayout) findViewById(id.layoutmana);
            switch (manacost[i])
            {
                case "U":
                    mana.setImageResource(drawable.u);
                    break;
                case "R":
                    mana.setImageResource(drawable.r);
                    break;
                case "G":
                    mana.setImageResource(drawable.g);
                    break;
                case "B":
                    mana.setImageResource(drawable.b);
                    break;
                case "W":
                    mana.setImageResource(drawable.w);
                    break;
                case "U/B":
                    mana.setImageResource(drawable.ub);
                    break;
                case "B/G":
                    mana.setImageResource(drawable.bg);
                    break;
                case "R/G":
                    mana.setImageResource(drawable.rg);
                    break;
                case "W/B":
                    mana.setImageResource(drawable.wb);
                    break;
                case "U/R":
                    mana.setImageResource(drawable.ur);
                    break;
                case "G/U":
                    mana.setImageResource(drawable.gu);
                    break;
                case "W/U":
                    mana.setImageResource(drawable.wu);
                    break;
                case "R/W":
                    mana.setImageResource(drawable.rw);
                    break;
                case "W/R":
                    mana.setImageResource(drawable.rw);
                    break;
                case "B/R":
                    mana.setImageResource(drawable.br);
                    break;
                case "G/lW":
                    mana.setImageResource(drawable.gw);
                    break;
                case "0":
                    mana.setImageResource(drawable.n0);
                    break;
                case "1":
                    mana.setImageResource(drawable.n1);
                    break;
                case "2":
                    mana.setImageResource(drawable.n2);
                    break;
                case "3":
                    mana.setImageResource(drawable.n3);
                    break;
                case "4":
                    mana.setImageResource(drawable.n4);
                    break;
                case "5":
                    mana.setImageResource(drawable.n5);
                    break;
                case "6":
                    mana.setImageResource(drawable.n6);
                    break;
                case "7":
                    mana.setImageResource(drawable.n7);
                    break;
                case "8":
                    mana.setImageResource(drawable.n8);
                    break;
                case "9":
                    mana.setImageResource(drawable.n9);
                    break;
                case "10":
                    mana.setImageResource(drawable.n10);
                    break;
                case "11":
                    mana.setImageResource(drawable.n11);
                    break;
                case "12":
                    mana.setImageResource(drawable.n12);
                    break;
                case "13":
                    mana.setImageResource(drawable.n13);
                    break;
                case "14":
                    mana.setImageResource(drawable.n14);
                case "15":
                    mana.setImageResource(drawable.n15);
                    break;
                case "16":
                    mana.setImageResource(drawable.n16);
                    break;
                case "17":
                    mana.setImageResource(drawable.n17);
                    break;
                case "18":
                    mana.setImageResource(drawable.n18);
                    break;
                case "19":
                    mana.setImageResource(drawable.n19);
                    break;
                case "20":
                    mana.setImageResource(drawable.n20);
                    break;
                default:
                    mana.setImageResource(0);
            }


            layout1mana.addView(mana);
        }


        type.setText(cards.getType());
        flavor.setText(cards.getFlavor());
        artist.setText(cards.getArtist());
        yaer.setText(cards.getReleaseDate());
        number.setText(cards.getNumber());
        if(cards.getPower().equalsIgnoreCase("")){
            pt.setText("");
        }else {
            pt.setText(cards.getPower() + "/" + cards.getToughness());
        }

        new LoadImage().execute("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=" + cards.getMultiverseid() + "&type=card");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Log.d("url","http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=" + cards.getMultiverseid() + "&type=card");
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CardDetail Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://br.com.wastenot.wastenot/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CardDetail Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://br.com.wastenot.wastenot/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {

        Bitmap bitmap;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            super.onPreExecute();

            ;
        }

        @Override
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(final Bitmap bitmap) {
            if (bitmap != null) {

                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
                imgCard.setBackground(bitmapDrawable);
                mProgress.setVisibility(View.INVISIBLE);
                textViewLoad.setVisibility(View.INVISIBLE);

                imgCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = (new Intent(getApplicationContext(), ImgDetail.class));
                        intent.putExtra("img", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=" + cards.getMultiverseid() + "&type=card");
                        startActivity(intent);

                    }
                });


            } else {
                Toast.makeText(CardDetail.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
                mProgress.setVisibility(View.INVISIBLE);
                textViewLoad.setVisibility(View.INVISIBLE);

            }

        }
    }

}


