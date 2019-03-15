package com.example.bitp3453labs;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Bitmap;

import java.util.Calendar;

public class FirstActivity extends AppCompatActivity {

    TextView textViewAge;
    EditText editTextName, editTextYear;
    Button buttonMe;
    ImageView imgaeViewPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        textViewAge = findViewById(R.id.textViewAge);
        editTextName = findViewById(R.id.editTextName);
        editTextYear = findViewById(R.id.editTextYear);
        imgaeViewPicture = findViewById(R.id.imageViewPicture);
    }


    public void fnGreet(View vw) {
        String strName = editTextName.getText().toString();
        int bornYear = Integer.parseInt(editTextYear.getText().toString());

        //get current year
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        int age = currentYear - bornYear;
        textViewAge.setText("Hello and welcome " + strName + ". You are " + age + " years old.");
    }

    public void fnThreadActivity (View vw) {
        Intent intent = new Intent(this, ThreadedActivity.class);
        String strMsg = editTextName.getText().toString();
        intent.putExtra("varStr1", strMsg);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        imgaeViewPicture.setImageBitmap(bp);
    }
}
