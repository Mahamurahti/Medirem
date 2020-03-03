package com.example.medirem_project;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

/**
 * SallaDoc
 * @author Salla Mikkonen
 * @version 1.1 3/2020
 */
public class TimePicker extends DialogFragment {

    /**
     * This method defines the clock which will be opened in AddMedicineActivity.
     * @return new TimePickerDialog that defines the current time to the clock.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),
                (TimePickerDialog.OnTimeSetListener) getActivity(),
                hour, minute,
                DateFormat.is24HourFormat(getActivity()
            ));
    }
}
