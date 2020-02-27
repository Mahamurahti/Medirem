package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Medicine Details Activity shows the description of the clicked medicine
 * @author Eric Keränen
 * @version 1.1 2/2019
 */
public class MedicineDetailsActivity extends AppCompatActivity {

    /**
     * During onCreate in this activity, the intent from clicking an element
     * in the main activity gets transferred into here and set to textViews
     * @param savedInstanceState a reference to a bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);

        // TODO: SAVE TIME
        /**
         * Receiving the int i from the mainActivity to choose the correct element from the
         * singleton list to display on the screen. Displays the medicine name, description and date.
         */
        Bundle b = getIntent().getExtras();
        int i = b.getInt(MainActivity.EXTRA_MAIN, 1);

        ((TextView)findViewById(R.id.medicineName)).setText(SavedMedicine.getInstance().getMedicine(i).getName());
        ((TextView)findViewById(R.id.medicineDesc)).setText(SavedMedicine.getInstance().getMedicine(i).getDesc());
        ((TextView)findViewById(R.id.medicineDate)).setText(SavedMedicine.getInstance().getMedicine(i).getDate());
    }
}
