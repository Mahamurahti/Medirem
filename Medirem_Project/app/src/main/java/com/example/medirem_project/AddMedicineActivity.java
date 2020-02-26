package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Add medicine activity adds custom medicine to the list
 * @author Eric Keränen & Salla Mikkonen
 * @version 1.1 2/2019
 */
public class AddMedicineActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        Button dateButton = (Button) findViewById(R.id.openDatePicker);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.medirem_project.DatePicker();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        // GETTING THE INTENT FROM MAIN ACTIVITY
        Intent intent = getIntent();
        String date = intent.getStringExtra(MainActivity.EXTRA_MAIN);
    }

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


    // UPON PRESSING THE ADD BUTTON, SENDS THE MEDICINE
    // NAME AND DESC TO THE SAVED MEDICINE LIST
    // TODO: GET DESCRIPTION TO SHOW IN THE DETAILS ABOUT MEDICINES
    public void addButton(View v){
        Intent intent = new Intent();
        Intent intent2 = new Intent();
        EditText etMed = (EditText) findViewById(R.id.nameTheMed);
        EditText etDesc = (EditText) findViewById(R.id.nameTheDesc);
        String message = etMed.getText().toString();
        String message2 = etDesc.getText().toString();
        intent.putExtra("MedName", message);
        intent2.putExtra("MedDesc", message2);
        setResult(1, intent);
        finish();
    }

    public  void  onBackPressed(View v){
        super.onBackPressed();
        setResult(0);
        finish();
    }

}
