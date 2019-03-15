package com.example.bitp3453labs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ThreadedActivity extends AppCompatActivity {

    ImageView imageViewProfile;
    TextView textViewHello;
    Bitmap bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded);

        //get variables from previous activity
        imageViewProfile = findViewById(R.id.imageViewProfile);
        textViewHello = findViewById(R.id.textViewHello);

        Intent intent = getIntent();
        textViewHello.setText("Welcome to new activity wahai " + intent.getStringExtra("varStr1"));

    }

    public void fnTakePic (View vw) {

        //thread - handle run process run parallel
        Runnable run = new Runnable() {
            @Override //method is already but definition not yet defined
            public void run() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewHello.setText(textViewHello.getText().toString() + ".. This is your picture heheh");
                    }
                });
            }
        };

        Thread thr = new Thread(run);
        thr.start();
    }

    //press ALT + Insert or Right Click generate insert
    @Override
    protected void  onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        bp = (Bitmap) data.getExtras().get("data");
        imageViewProfile.setImageBitmap(bp);

        Intent intent = new Intent();
        intent.putExtra("data", bp);
        setResult(0, data);
        //finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


}
