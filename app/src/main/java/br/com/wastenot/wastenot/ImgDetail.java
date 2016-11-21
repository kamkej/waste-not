package br.com.wastenot.wastenot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class ImgDetail extends AppCompatActivity {
    ImageView imgCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_detail);

        Intent intent = getIntent();
        String link =  intent.getStringExtra("img");
        Log.d("link",link);
        imgCard = (ImageView)findViewById(R.id.imgDetail);
        new LoadImage().execute(link);

    }

    public class LoadImage extends AsyncTask<String, String, Bitmap> {

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




            } else {
                Toast.makeText(ImgDetail.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();


            }

        }
    }


}
