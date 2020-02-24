package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Add medicine activity adds custom medicine to the list
 * @author Eric Keränen
 * @version 1.1 2/2019
 */
public class AddMedicineActivity extends AppCompatActivity {

    public  static  final String EXTRA_ADD = "Add Medicine Activity Value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        // GETTING THE INTENT FROM MAIN ACTIVITY
        Intent intent = getIntent();
        String date = intent.getStringExtra(MainActivity.EXTRA_MAIN);

        ((TextView)findViewById(R.id.dateView)).setText(date);
    }

    public void addButton(View v){
        Intent intent = new Intent();
        EditText etMed = (EditText) findViewById(R.id.nameTheMed);
        //EditText etDesc = (EditText) findViewById(R.id.nameTheDesc);
        String message = etMed.getText().toString();
        //String message2 = etDesc.getText().toString();
        intent.putExtra(EXTRA_ADD, message);
        setResult(1,intent);
        finish();
    }
}
