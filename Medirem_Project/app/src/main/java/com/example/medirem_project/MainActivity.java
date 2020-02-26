package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * MainActivity holds the calendar and the main functions of this app
 * @author Eric Keränen
 * @version 1.1 2/2019
*/
public class MainActivity extends AppCompatActivity {

    // Git Version
    // TODO: GET CALENDAR TO WORK
    public  static  final String EXTRA_MAIN = "Main Activity Value";

    private ListView listOfMed;
    private String medicineNameSaved, medicineDescSaved;
    private String saveName, saveDesc;
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        calendar = findViewById(R.id.calendarView);

        //SETTING CALENDAR DAYCLICK
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavedMedicine.getInstance().getMedicine();
            }
        });

        listOfMed = findViewById(R.id.listOfMedicine);

        // SETTING AN ADAPTER WITH A LIST VIEW TO SEE ALL MEDICINE
        setAdapter();

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

        // CREATING PREFERENCES TO SAVE ADDED MEDICINE
        SharedPreferences prefGet = getSharedPreferences("Preferences", Activity.MODE_PRIVATE);
        medicineNameSaved = prefGet.getString("MedicineName", "Null");
        medicineDescSaved = prefGet.getString("MedicineDesc", "Null");
        if(medicineNameSaved.equals("Null") && medicineDescSaved.equals("Null")){
            Log.d("LOG", "No savedPreferences");
        }else{
            SavedMedicine.getInstance().saveMedicine(medicineNameSaved, medicineDescSaved);
            setAdapter();
        }
    }

    // SWITCH THE ACTIVITY TO ADD A MEDICINE TO THE LIST
    public void addMedicine(View v){
        Intent intent = new Intent(MainActivity.this, AddMedicineActivity.class);
        // TODO: TAKE DATE FROM CALENDAR
        String date = "24.2.2020";
        intent.putExtra(EXTRA_MAIN, date);
        startActivityForResult(intent, 1);
    }

    // GET THE RESULT FROM "ADDMEDICINE" AND PUT THE RESULT TO THE LIST
    // TODO: SAVE THE MEDICINE TO A DATE
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Log.d("LOG", "Request Code was one!");
            if(resultCode == 1){
                Log.d("LOG", "Result Code was one!");
                setAdapter();
            }else if(resultCode == 0){
                Log.d("LOG", "Result Code was zero!");
                setAdapter();
            }
        }
    }

    // SET ADAPTER METHOD TO MAKE THE CODE MORE SIMPLE
    public void setAdapter(){
        listOfMed.setAdapter(new ArrayAdapter<Medicine>(
                this,
                android.R.layout.simple_list_item_1,
                SavedMedicine.getInstance().getMedicine()
        ));
    }

    // WHEN THE APPLICATION IS STOPPED, SAVE THE MEDICINE THAT HAS BEEN ADDED TO THE LIST
    // TODO: SAVE MEDICINE TO A LIST THAT WILL STORE ALL THE DATA, CURRENTLY SAVES ONLY THE LATEST MEDICINE
    @Override
    public void onStop(){
        super.onStop();
        saveName = SavedMedicine.getInstance().getMedicine(SavedMedicine.getInstance().getMedicine().size() - 1).getName();
        saveDesc = SavedMedicine.getInstance().getMedicine(SavedMedicine.getInstance().getMedicine().size() - 1).getDesc();

        SharedPreferences prefPut =getSharedPreferences("Preferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("MedicineName", saveName);
        prefEditor.putString("MedicineDesc", saveDesc);
        prefEditor.commit();
    }
}
