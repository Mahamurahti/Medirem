package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Medicine Details Activity shows the description of the clicked medicine
 * @author Eric Keränen
 * @version 1.3 2/2020
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
         * Receiving the int i as an intent from the mainActivity to choose the correct
         * element from the singleton list to display on the screen.
         * Displays the medicine name, description, date and time.
         */
        Bundle b = getIntent().getExtras();
        int i = b.getInt(MainActivity.EXTRA_MAIN, 1);

        ((TextView)findViewById(R.id.medicineName)).setText(SavedMedicine.getInstance().getMedicine(i).getName());
        ((TextView)findViewById(R.id.medicineDesc)).setText(SavedMedicine.getInstance().getMedicine(i).getDesc());
        ((TextView)findViewById(R.id.medicineDate)).setText(SavedMedicine.getInstance().getMedicine(i).getDate());
        ((TextView)findViewById(R.id.medicineTime)).setText(SavedMedicine.getInstance().getMedicine(i).getTime());
    }

    /**
     * Remove button makes an alert dialog pop-up asking the user confirmation
     * to remove the current medicine from the application. If the user answers "no"
     * nothing will happen and if the user answers "remove" the medicine will be removed.
     * @param v used for finding something in the screen view (View)
     */
    public void removeButton(View v){

        Bundle b = getIntent().getExtras();
        final int i = b.getInt(MainActivity.EXTRA_MAIN, 0);

        /**
         * Building the dialog:
         * 1. Creating the dialog
         * 2. Setting the title, message and icon
         * 3. Setting the buttons
         * 4. Setting an onClickListener
         * 5. Dismissing the dialog after either one is clicked
         *    and executing commands accordingly
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Removing medicine");
        builder.setMessage("Do you want to remove " + SavedMedicine.getInstance().getMedicine(i).getName() + "?");
        builder.setIcon(R.drawable.chill_pill);
        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                SavedMedicine.getInstance().removeMedicine(i);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * When the user presses the back button, this method will call the onBackPressed()
     * method that returns the user back to the previous view
     * @param v used for finding something in the screen view (View)
     */
    public  void  onBackPressed(View v){
        super.onBackPressed();
    }
}
