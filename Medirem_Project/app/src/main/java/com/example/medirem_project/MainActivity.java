package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.example.medirem_project.AddMedicineActivity.EXTRA_ADD;

/**
 * MainActivity holds the calendar and the main functions of this app
 * @author Eric Keränen
 * @version 1.1 2/2019
*/
public class MainActivity extends AppCompatActivity {

    // Git Version
    public  static  final String EXTRA_MAIN = "Main Activity Value";

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
                Intent intent = new Intent(MainActivity.this, MedicineDetailsActivity.class);
                intent.putExtra(EXTRA_MAIN, i);
                startActivity(intent);
            }
        });
    }

    // SWITCH THE ACTIVITY TO ADD A MEDICINE TO THE LIST
    public void addMedicine(View v){
        Intent intent = new Intent(MainActivity.this, AddMedicineActivity.class);
        // TODO: TAKE DATE FROM CALENDAR
        String date = "24.2.2020";
        intent.putExtra(EXTRA_MAIN, date);
        startActivityForResult(intent, 1);
    }

    // GET THE RESULT FROM "ADDMEDICINE" AND PUT THE RESULT TO THAT DATE
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            String medName = data.getStringExtra(EXTRA_ADD);
            SavedMedicine.getInstance().saveMedicine(medName, "Implement desc here");

            listOfMed.setAdapter(new ArrayAdapter<Medicine>(
                    this,
                    android.R.layout.simple_list_item_1,
                    SavedMedicine.getInstance().getMedicine()
            ));
        }
    }

}
