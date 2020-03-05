package com.example.medirem_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.util.Log;
import android.widget.DatePicker;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Add medicine activity adds custom medicine to the list which the user has to type in.
 * @author Eric Keränen & Salla Mikkonen
 * @version 1.5 2/2020
 */
public class AddMedicineActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private boolean repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        repeat = false;

        /**
         * Date button to open a calendar (DatePicker fragment) when the user presses the select date button.
         * Date picker comes from DatePicker class and calls the calendar.
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
         * Time button to open a clock (TimePicker fragment) when the user presses the select time button.
         * Time picker comes from TimePicker class and calls the clock.
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
     * This method saves the information that the user selects in the opened calendar.
     * After picking a date it will be displayed in a text view.
     *
     * @param view       used for finding something in the screen view (View)
     * @param year       is found from datePicker activity with Calendar.YEAR (int)
     * @param month      is found from datePicker activity with Calendar.MONTH (int)
     * @param dayOfMonth is found from datePicker activity with Calendar.DAY_OF_MONTH (int)
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.MEDIUM).format(c.getTime());

        TextView dateTextView = (TextView) findViewById(R.id.dateView);
        dateTextView.setText(currentDateString);
    }

    public void radioButton(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.yesRepeat:
                if (checked) {
                    Log.d("LOG", "Repeat is on");
                    repeat = true;
                }
                break;
            case R.id.noRepeat:
                if (checked) {
                    Log.d("LOG", "Repeat is off");
                    repeat = false;
                }
                break;
        }
    }

    /**
     * This method saves the information that the user selects in the opened clock.
     * After picking the time it will be displayed in a text view. The displaying has
     * a small logic pool to add zeroes in front of numbers smaller than 10 (e.g. 8:1 -> 08:01).
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
        if(!repeat){
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
        }else if(repeat){
            EditText etMed = (EditText) findViewById(R.id.nameTheMed);
            EditText etDesc = (EditText) findViewById(R.id.nameTheDesc);
            TextView tvDate = (TextView) findViewById(R.id.dateView);
            TextView tvTime = (TextView) findViewById(R.id.timeView);

            for(int i = 0; i <= 6; i++){
                String medName = etMed.getText().toString();
                String medDesc = etDesc.getText().toString();

                String oldMedDate = tvDate.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.M.yyyy");
                Calendar c = Calendar.getInstance();
                try{
                    c.setTime(simpleDateFormat.parse(oldMedDate));
                }catch(ParseException e){
                    e.printStackTrace();
                }
                c.add(Calendar.DAY_OF_MONTH, i);
                String newMedDate = simpleDateFormat.format(c.getTime());

                String medTime = tvTime.getText().toString();

                Log.d("LOG", "newMedDate is " + newMedDate);
                SavedMedicine.getInstance().saveMedicine(medName, medDesc, newMedDate, medTime);
            }
            setResult(2);
            finish();
        }
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
