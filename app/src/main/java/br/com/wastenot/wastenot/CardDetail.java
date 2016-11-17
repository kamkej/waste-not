package br.com.wastenot.wastenot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
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

import static br.com.wastenot.wastenot.R.*;


public class CardDetail extends AppCompatActivity {
    ImageButton imgCard;
    TextView textViewLoad;
    private ProgressBar mProgress;
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
        Cards cards = (Cards) intent.getSerializableExtra("cards");

        RelativeLayout layout = (RelativeLayout) findViewById(id.layoutid);
        layout.setBackgroundResource(drawable.roundeditext);

        LinearLayout border = (LinearLayout) findViewById(id.layoutBorder);
        int color;
        if(cards.getBorder().equalsIgnoreCase("black")) {
             color = R.color.black;
        }else if (cards.getBorder().equalsIgnoreCase("silver")){
             color = R.color.silver;
        }else if(cards.getBorder().equalsIgnoreCase("white")){
             color = R.color.white;
        }else {
             color = R.color.black;
        }




        border.setBackgroundColor(ContextCompat.getColor(this, color));
        mProgress = (ProgressBar) findViewById(id.progressBar);
        textViewLoad = (TextView) findViewById(id.txtLoading);

        TextView title = (TextView) findViewById(id.txtTitle);
        TextView text = (TextView) findViewById(id.txtText);
        TextView mana = (TextView) findViewById(id.txtMana);
        TextView type = (TextView) findViewById(id.txtType);
        TextView flavor = (TextView) findViewById(id.txtFlavor);
        TextView artist = (TextView) findViewById(id.txtArtist);
        TextView yaer = (TextView) findViewById(id.txtYear);
        TextView number = (TextView) findViewById(id.txtNumber);
        TextView pt = (TextView) findViewById(id.txtPT);



        imgCard = (ImageButton) findViewById(id.imgcard);


        title.setText(cards.getName());
        text.setText(cards.getText());
        mana.setText(cards.getManaCost());
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
                imgCard.setImageBitmap(bitmap);
                mProgress.setVisibility(View.INVISIBLE);
                textViewLoad.setVisibility(View.INVISIBLE);

                imgCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = (new Intent(getApplicationContext(), ImgDetail.class));
                        intent.putExtra("img", bitmap);
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


