package br.com.wastenot.wastenot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Log.d("JS", "LOAD...");
        String json = null;
        TextView  log = (TextView) findViewById(R.id.log);


           // Log.d("json", String.valueOf(jsonArray.length()));
            log.setText("ok");




    }
}
