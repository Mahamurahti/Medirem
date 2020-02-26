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

        /**
         * Getting the intent from main activity, which is the date
         * and setting it to the screen as the current date
         */
        Intent intent = getIntent();
        String date = intent.getStringExtra(MainActivity.EXTRA_MAIN);

        ((TextView)findViewById(R.id.dateView)).setText(date);
    }

    /**
     * When the user presses the add button, the Strings in the editText
     * fields will be saved into the list via saveMedicine method in the Singleton.
     * @param v used for finding something in the screen view (View)
     */
    public void addButton(View v){
        EditText etMed = (EditText) findViewById(R.id.nameTheMed);
        EditText etDesc = (EditText) findViewById(R.id.nameTheDesc);
        String medName = etMed.getText().toString();
        String medDesc = etDesc.getText().toString();
        SavedMedicine.getInstance().saveMedicine(medName, medDesc);
        setResult(1);
        finish();
    }

    /**
     * When the user presses the back button, this method will call the onBackPressed()
     * method that returns the user back to the previous view
     * @param v used for finding something in the screen view (View)
     */
    public  void  onBackPressed(View v){
        super.onBackPressed();
        setResult(0);
        finish();
    }
}
