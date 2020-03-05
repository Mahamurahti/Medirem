package com.example.medirem_project;

import androidx.annotation.NonNull;
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
import android.widget.CalendarView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * MainActivity holds the calendar and the main functions of this app
 * @author Eric Keränen & Salla Mikkonen
 * @version 1.5 2/2020
*/
public class MainActivity extends AppCompatActivity {

    // Git Version
    // TODO: GET NOTIFICATIONS WORKING (POP-UP AND MAYBE SOUND)
    public  static  final String EXTRA_MAIN = "Main Activity Value";

    private ListView listOfMed;
    private CalendarView c;
    private String currentDate;
    private ArrayAdapter<Medicine> adapter;
    private ArrayList<Medicine> newMedList;
    private String medicinePreferencesSaved;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("LOG", "onCreate started");

        listOfMed = findViewById(R.id.listOfMedicine);
        c = findViewById(R.id.calendarView);
        newMedList = new ArrayList<Medicine>();

        /**
         * Gson file to store the medicine information
         */
        gson = new Gson();

        /**
         * Setting an opening view for the app, that set the current date to the variable currentDate (String)
         *
         * After setting the view an adapter set up with a list view to see all medicine from the singleton list
         */
        SetOpeningView();
        setAdapter(newMedList);

        /**
         * An onItemClickListener is set up for the adapter to see which element the user
         * clicks and sends an intent to the MedicineDetailsActivity with an int which will display the
         * correct details. The index is fiddled with because we need the original lists index.
         */
        listOfMed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("LOG", "onItemClick(" + i + ")");
                Intent intent = new Intent(MainActivity.this, MedicineDetailsActivity.class);
                int trueIndex = SavedMedicine.getInstance().getMedicine().indexOf(newMedList.get(i));
                intent.putExtra(EXTRA_MAIN, trueIndex);
                // START MEDICINE DETAILS ACTIVITY
                startActivityForResult(intent, 2);
            }
        });

        /**
         * An onDateChangeListener is set up for the calendar view to see which element of the calendar
         * the user clicks. The onSelectedDayChange method creates a date (String) that is used to
         * identify which day it is in the code. This piece of software only works in operating systems
         * that give the date in a number format, in this case it doesn't work with english but works
         * with finnish.
         *
         * After formatting accordingly a new list is made to host temporary elements from the main list
         * which is located in the singleton class SavedMedicine. Before inserting new elements in the
         * temporary list it is cleared not to bloat it (with RefreshList). After refreshing only the medicine are added
         * to the list that have the same date as the currently selected day from the calendar. This
         * way get the effect that only the medicine added to a certain date are displayed in the list.
         */
        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                currentDate = dayOfMonth + "." + (month + 1) + "." + year;
                Log.d("LOG", "onDateClick(" + dayOfMonth + "." + (month + 1) + "." + year + ")");
                Log.d("LOG", "onDateClick in format is " + currentDate);

                RefreshList();
                setAdapter(newMedList);
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
            setAdapter(newMedList);
        }

        /**
         * Refreshing the list after the shared preferences have been inserted into the singleton list
         */
        RefreshList();

    }

    /**
     * This method is called when the user clicks on the add medicine button.
     * Starts the AddMedicineActivity where the user can add new medicine.
     * @param v used for finding something in the screen view
     */
    public void addMedicine(View v){
        Intent intent = new Intent(MainActivity.this, AddMedicineActivity.class);
        // START ADD MEDICINE ACTIVITY
        startActivityForResult(intent, 1);
    }

    /**
     * This method fetches the result from the AddMedicineActivity and MedicineDetailsActivity.
     * @param requestCode the code that is used to request the activity (int)
     * @param resultCode the code that is given in the setResult in the other activity (int)
     * @param data the data which is passed to the mainActivity from the other activity (Intent)
     */
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // ADD MEDICINE ACTIVITY
        if(requestCode == 1){
            Log.d("LOG", "Request Code was one!");
            if(resultCode == 2){
                // REPEAT
                Log.d("LOG", "Result code was two! Repeat was turned on.");
                RefreshList();
                setAdapter(newMedList);
            }else if(resultCode == 1){
                // NO REPEAT
                Log.d("LOG", "Result Code was one! Repeat was turned off.");
                RefreshList();
                setAdapter(newMedList);
            }else if(resultCode == 0){
                // BACK
                Log.d("LOG", "Result Code was zero! Back was pressed.");
                setAdapter(newMedList);
            }
        }
        // MEDICINE DETAILS ACTIVITY
        if(requestCode == 2){
            Log.d("LOG", "Request Code was two!");
            if(resultCode == 1){
                // MEDICINE REMOVED
                Log.d("LOG", "Result Code was one! Medicine was removed.");
                RefreshList();
                setAdapter(newMedList);
            }else if(resultCode == 0) {
                // BACK
                Log.d("LOG", "Result Code was zero! Back was pressed.");
                setAdapter(newMedList);
            }
        }
    }

    /**
     * This method sets the adapter so that the list will update. A temporary list used rather than
     * the actual list to host elements from the singleton temporarily depending on the selected day.
     */
    public void setAdapter(ArrayList<Medicine> theList){
        adapter = new ArrayAdapter<Medicine>(
                this,
                android.R.layout.simple_list_item_1,
                theList
        );
        listOfMed.setAdapter(adapter);
    }

    /**
     * This method holds a loop that deletes everything form the temporary list and
     * adds the medicine that have the same date as the currently selected date.
     */
    public void RefreshList(){
        newMedList.clear();
        for (int i = 0; i < SavedMedicine.getInstance().getMedicine().size(); i++) {
            if (currentDate.equals(SavedMedicine.getInstance().getMedicine(i).getDate())) {
                newMedList.add(SavedMedicine.getInstance().getMedicine(i));
            }
        }
    }

    /**
     * Setting the current date to the current date variable (String)
     */
    public void SetOpeningView(){
        SimpleDateFormat openingDateFormat = new SimpleDateFormat("d.M.yyyy");
        currentDate = openingDateFormat.format(Calendar.getInstance().getTime());
        Log.d("LOG", "currentDate is " + currentDate);
    }

    /**
     * This method creates a list to hold all the information about the saved medicine of the
     * singleton list and then saves the newly created list to a string that uses the gson
     * library. This string is then put into the sharedPreferences and committed.
     */
    public void SaveMedicineSharedPreferences(){
        List<Medicine> medicineList = new ArrayList<Medicine>();
        medicineList = SavedMedicine.getInstance().getMedicine();
        String jsonMedicine = gson.toJson(medicineList);

        SharedPreferences prefPut = getSharedPreferences("Preferences",Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("medData", jsonMedicine);
        prefEditor.commit();
    }

    /**
     * This method is called when the user returns from any of the activities
     * back to the main activity and will update the UI to match latest changes.
     *
     * This method also saves the medicine to the sharedPreferences.
     */
    @Override
    public void onResume(){
        super.onResume();
        SaveMedicineSharedPreferences();
        setAdapter(newMedList);
    }

    /**
     * Upon closing the app, this method will save the medicine to the sharedPreferences.
     */
    @Override
    public void onStop(){
        super.onStop();
        SaveMedicineSharedPreferences();
    }

    /**
     * Also upon destroying the application the saved medicine will be saved to the sharedPreferences.
     */
    @Override
    public void onDestroy(){
        super.onDestroy();
        SaveMedicineSharedPreferences();
    }
}
