package com.example.medirem_project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
/**
 * DatePicker defines calendar and current day to it
 * https://www.youtube.com/watch?v=33BFCdL0Di0&t=402s
 * @author Salla Mikkonen
 * @version 1.1 2/2020
 */
public class DatePicker extends DialogFragment {

    /**
     * This method defines the calendar which will be opened in AddMedicineActivity.
     * @return new DatePickerDialog that defines the current day to the calendar
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }
}
