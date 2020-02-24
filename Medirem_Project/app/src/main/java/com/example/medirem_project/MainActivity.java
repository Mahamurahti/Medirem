package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * MainActivity holds the calendar and the main functions of this app
 * @author Eric Keränen
 * @version 1.1 2/2019
*/
public class MainActivity extends AppCompatActivity {

    // Git Version
    public  static  final String EXTRA = "Value";

    private ListView listOfMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfMed = findViewById(R.id.listOfMedicine);

        // SETTING AN ADAPTER WITH A LIST VIEW TO SEE ALL MEDICINE
        listOfMed.setAdapter(new ArrayAdapter<Medicine>(
            this,
            android.R.layout.simple_list_item_1,
            SavedMedicine.getInstance().getMedicine()
        ));

        // SETTING THE ON CLICK LISTENER TO DETECT WHICH ELEMENT OF THE
        // LIST WAS CLICKED AND SHOW THE DETAILS OF THE MEDICINE
        listOfMed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("LOG", "onItemClick(" + i + ")");
                Intent intent = new Intent(MainActivity.this, MedicineDetailsActivity.class); // TODO: CREATE CLASS
                intent.putExtra(EXTRA, i);
                startActivity(intent);
            }
        });
    }

    public void addMedicine(View v){
        Intent intent = new Intent(MainActivity.this, AddMedicineActivity.class); // TODO: CREATE CLASS
        // TODO: TAKE DATE FROM CALENDAR
        String date = ;
        intent.putExtra(EXTRA, date);
        startActivityForResult(intent, 1);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            // TODO: CREATE SECOND ACTIVITY
        }
    }

}
