package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Medicine Details Activity shows the description of the clicked medicine
 * @author Eric Keränen
 * @version 1.3 2/2020
 */
public class MedicineDetailsActivity extends AppCompatActivity {

    private int i;
    private Button takeMedButton;

    /**
     * During onCreate in this activity, the intent from clicking an element
     * in the main activity gets transferred into here and set to textViews
     * @param savedInstanceState a reference to a bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);

        /**
         * Receiving the int i as an intent from the mainActivity to choose the correct
         * element from the singleton list to display on the screen.
         * Displays the medicine name, description, date and time.
         */
        Bundle b = getIntent().getExtras();
        i = b.getInt(MainActivity.EXTRA_MAIN, 1);

        /**
         * If the medicine has not been taken, there is an option to take it. If the medicine has been
         * taken there will no longer be a button to take the medicine.
         */
        takeMedButton = findViewById(R.id.takeMedButton);
        if(SavedMedicine.getInstance().getMedicine(i).isMedTaken()){
            takeMedButton.setVisibility(View.GONE);
        }else if (!SavedMedicine.getInstance().getMedicine(i).isMedTaken()){
            takeMedButton.setVisibility(View.VISIBLE);
        }

        ((TextView)findViewById(R.id.medicineName)).setText(SavedMedicine.getInstance().getMedicine(i).getName());
        ((TextView)findViewById(R.id.medicineDesc)).setText(SavedMedicine.getInstance().getMedicine(i).getDesc());
        ((TextView)findViewById(R.id.medicineDate)).setText(SavedMedicine.getInstance().getMedicine(i).getDate());
        ((TextView)findViewById(R.id.medicineTime)).setText(SavedMedicine.getInstance().getMedicine(i).getTime());
        ((TextView)findViewById(R.id.isTheMedTaken)).setText(SavedMedicine.getInstance().getMedicine(i).medTakenText());
    }

    /**
     * Method to take the medicine and set the take medicine button as "gone".
     * If the take medicine button has been pressed a pop-up message asks for confirmation.
     * @param v used for finding something in the screen view (View)
     */
    public void takeMedicineButton(View v){

        /**
         * See "removeButton" method to know how the alert dialog is built.
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Taking medicine");
        builder.setMessage("Confirm taking " + SavedMedicine.getInstance().getMedicine(i).getName() + "?");
        builder.setIcon(R.drawable.chillpilllogoround);
        builder.setPositiveButton("Take", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                SavedMedicine.getInstance().getMedicine(i).takeMed();
                takeMedButton.setVisibility(View.GONE);
                ((TextView)findViewById(R.id.isTheMedTaken)).setText(SavedMedicine.getInstance().getMedicine(i).medTakenText());
                Calendar c = Calendar.getInstance();
                DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
                String currentTime = timeFormat.format(c.getTime());
                ((TextView)findViewById(R.id.medTakenTime)).setText(currentTime);
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
     * Remove button makes an alert dialog pop-up asking the user confirmation
     * to remove the current medicine from the application. If the user answers "no"
     * nothing will happen and if the user answers "remove" the medicine will be removed.
     * @param v used for finding something in the screen view (View)
     */
    public void removeButton(View v){

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
        builder.setIcon(R.drawable.chillpill_minus);
        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                SavedMedicine.getInstance().removeMedicine(i);
                setResult(1);
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
     * method from the superclass that returns the user back to the previous view.
     * @param v used for finding something in the screen view (View)
     */
    public  void  onBackPressed(View v){
        super.onBackPressed();
        setResult(0);
        finish();
    }
}
