package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.TimeZoneFormat;
import android.util.TimeFormatException;
import android.widget.DatePicker;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Add medicine activity adds custom medicine to the list
 * @author Eric Keränen & Salla Mikkonen
 * @version 1.1 2/2019
 */
public class AddMedicineActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        /**
         * Date button to open the calendar (DatePicker fragment) is being created.
         * DatePicker comes from DatePicker class and calls the calendar.
         */
        Button dateButton = (Button) findViewById(R.id.openDatePicker);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.medirem_project.DatePicker();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        Button timeButton = (Button) findViewById(R.id.openTimePicker);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new com.example.medirem_project.TimePicker();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    /**
     *The text to the date view window is being set by
     * onDateSet method to the textWindow and is found by DateFormat.getDateInstance which is the
     * picked day
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView dateTextView = (TextView) findViewById(R.id.dateView);
        dateTextView.setText(currentDateString);
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.timeView);
        textView.setText( "hh:mm " + hourOfDay + " : " + minute);
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
    /*public void nameTextClicked(View v){
        EditText edMed=findViewById(R.id.nameTheMed);
        edMed.setText(" ");
    }
    public void descTextClicked(View v){
        EditText edMed=findViewById(R.id.nameTheDesc);
        edMed.setText(" ");

    }*/

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
