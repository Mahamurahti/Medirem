package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Add medicine activity adds custom medicine to the list
 * @author Eric Keränen
 * @version 1.1 2/2019
 */
public class AddMedicineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        // GETTING THE INTENT FROM MAIN ACTIVITY
        Intent intent = getIntent();
        String date = intent.getStringExtra(MainActivity.EXTRA_MAIN);

        ((TextView)findViewById(R.id.dateView)).setText(date);
    }

    // UPON PRESSING THE ADD BUTTON, SENDS THE MEDICINE
    // NAME AND DESC TO THE SAVED MEDICINE LIST
    // TODO: GET DESCRIPTION TO SHOW IN THE DETAILS ABOUT MEDICINES
    public void addButton(View v){
        Intent intent = new Intent();
        //Intent intent2 = new Intent();
        EditText etMed = (EditText) findViewById(R.id.nameTheMed);
        //EditText etDesc = (EditText) findViewById(R.id.nameTheDesc);
        String message = etMed.getText().toString();
        //String message2 = etDesc.getText().toString();
        intent.putExtra("MedName", message);
        //intent2.putExtra("MedDesc", message2);
        setResult(1, intent);
        finish();
    }
    /*public void nameTextClicked(View v){
        EditText edMed=findViewById(R.id.nameTheMed);
        edMed.setText(" ");
    }
    public void descTextClicked(View v){
        EditText edMed=findViewById(R.id.nameTheDesc);
        edMed.setText(" ");

    }*/

    public  void  onBackPressed(View v){
        super.onBackPressed();
        setResult(0);
        finish();
    }
}
