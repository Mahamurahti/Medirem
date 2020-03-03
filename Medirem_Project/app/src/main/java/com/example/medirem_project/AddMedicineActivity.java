package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Add medicine activity adds custom medicine to the list
 * @author Eric Keränen & Salla Mikkonen
 * @version 1.3 2/2020
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

        /**
         * SallaDoc
         */
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
     * This method opens the calendar and the user get to pick
     * a date where the user wants to save the medicine to.
     * After picking a date it will be displayed in a text view.
     * @param view used for finding something in the screen view (View)
     * @param year is found from datePicker activity with Calendar.YEAR (int)
     * @param month is found from datePicker activity with Calendar.MONTH (int)
     * @param dayOfMonth is found from datePicker activity with Calendar.DAY_OF_MONTH (int)
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

    /**
     * This method opens a clock and the user get to pick
     * a time where the user wants to save the medicine to.
     * After picking the time it will be displayed in a text view.
     * @param view used for finding something in the screen view (View)
     * @param hourOfDay is found from timePicker activity with Calendar.HOUR_OF_DAY (int)
     * @param minute is found from timePicker activity with Calendar.Minute (int)
     */
    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        TextView tvTime = (TextView) findViewById(R.id.timeView);
        if(hourOfDay < 10 && minute < 10){
            tvTime.setText("0" + hourOfDay + ":0" + minute);
        }else if(hourOfDay < 10){
            tvTime.setText("0" + hourOfDay + ":" + minute);
        }else if(minute < 10){
            tvTime.setText(hourOfDay + ":0" + minute);
        }else{
            tvTime.setText(hourOfDay + ":" + minute);
        }
    }


    /**
     * When the user presses the add button, the Strings in the editText and textView
     * fields will be saved into the list via saveMedicine method in the Singleton.
     * After adding the medicine this activity will end.
     * @param v used for finding something in the screen view (View)
     */
    public void addButton(View v){
        EditText etMed = (EditText) findViewById(R.id.nameTheMed);
        EditText etDesc = (EditText) findViewById(R.id.nameTheDesc);
        TextView tvDate = (TextView) findViewById(R.id.dateView);
        TextView tvTime = (TextView) findViewById(R.id.timeView);
        String medName = etMed.getText().toString();
        String medDesc = etDesc.getText().toString();
        String medDate = tvDate.getText().toString();
        String medTime = tvTime.getText().toString();
        SavedMedicine.getInstance().saveMedicine(medName, medDesc, medDate, medTime);
        setResult(1);
        finish();
    }

    //TODO: MAKE TEXT DISAPPEAR AFTER CLICKING IT FOR THE FIRST TIME
    /*
    public void nameTextClicked(View v){
        EditText edMed=findViewById(R.id.nameTheMed);
        edMed.setText(" ");
    }
    public void descTextClicked(View v){
        EditText edMed=findViewById(R.id.nameTheDesc);
        edMed.setText(" ");

    }
    */

    /**
     * When the user presses the back button, this method will call the onBackPressed()
     * method from the superclass that returns the user back to the previous view
     * @param v used for finding something in the screen view (View)
     */
    public  void  onBackPressed(View v){
        super.onBackPressed();
        setResult(0);
        finish();
    }

}
