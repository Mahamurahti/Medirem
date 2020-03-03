package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity holds the calendar and the main functions of this app
 * @author Eric Keränen
 * @version 1.1 2/2020
*/
public class MainActivity extends AppCompatActivity {

    // Git Version
    // TODO: GET CALENDAR TO WORK
    public  static  final String EXTRA_MAIN = "Main Activity Value";

    private ListView listOfMed;
    private String medicinePreferencesSaved;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfMed = findViewById(R.id.listOfMedicine);

        /**
         * Gson file to store the medicine information
         */
        gson = new Gson();

        /**
         * Setting an adapter with a list view to see all medicine from the singleton list
         */
        setAdapter();

        /**
         * For the adapter we also set up an onItemClickListener to see which element the user
         * clicks and sends an intent to the MedicineDetailsActivity with an int which will display the
         * correct details.
         */
        listOfMed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("LOG", "onItemClick(" + i + ")");
                Intent intent = new Intent(MainActivity.this, MedicineDetailsActivity.class);
                intent.putExtra(EXTRA_MAIN, i);
                startActivity(intent);
            }
        });

        /**
         * Fetching the sharedPreferences from when the app was closed. If the sharedPreferences
         * have the default value of "null", nothing will be executed, because the list is empty.
         */
        SharedPreferences prefGet = getSharedPreferences("Preferences", Activity.MODE_PRIVATE);
        medicinePreferencesSaved = prefGet.getString("medData", "null");
        if(!medicinePreferencesSaved.equals("null")){
            TypeToken<List<Medicine>> token = new TypeToken<List<Medicine>>() {};
            ArrayList<Medicine> medicineListBack = gson.fromJson(medicinePreferencesSaved, token.getType());

            SavedMedicine.getInstance().setMedicine(medicineListBack);
            setAdapter();
        }
    }

    /**
     * This method is called when the user clicks on the add medicine button.
     * Starts the AddMedicineActivity where the user can add new medicine.
     * @param v used for finding something in the screen view
     */
    public void addMedicine(View v){
        Intent intent = new Intent(MainActivity.this, AddMedicineActivity.class);
        startActivityForResult(intent, 1);
    }

    // TODO: SAVE THE MEDICINE TO A TIME
    /**
     * This method fetches the result from the AddMedicineActivity.
     * @param requestCode the code that is used to request the activity (int)
     * @param resultCode the code that is given in the setResult in the other activity (int)
     * @param data the data which is passed to the mainActivity from the other activity (Intent)
     */
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

    /**
     * This method sets the adapter so that the list will update
     */
    public void setAdapter(){
        listOfMed.setAdapter(new ArrayAdapter<Medicine>(
                this,
                android.R.layout.simple_list_item_1,
                SavedMedicine.getInstance().getMedicine()
        ));
    }

    /**
     * This method is called when the user returns from any of the activities
     * back to the main activity and will update the UI to match latest changes.
     */
    @Override
    public void onResume(){
        super.onResume();
        setAdapter();
    }

    /**
     * Upon closing the app, this method will create a new list.
     * The saved medicine list from the singleton will be stored in this new list
     * and be saved to a gson file that will be saved to sharedPreferences.
     */
    @Override
    public void onStop(){
        super.onStop();
        List<Medicine> medicineList = new ArrayList<Medicine>();
        medicineList = SavedMedicine.getInstance().getMedicine();
        String jsonMedicine = gson.toJson(medicineList);

        SharedPreferences prefPut = getSharedPreferences("Preferences",Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("medData", jsonMedicine);
        prefEditor.commit();
    }
}
