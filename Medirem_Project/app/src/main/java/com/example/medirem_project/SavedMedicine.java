package com.example.medirem_project;

import java.util.ArrayList;
import java.util.List;

public class SavedMedicine {
    private static final SavedMedicine ourInstance = new SavedMedicine();

    private List<Medicine> medicineList;

    // SINGLETON CLASS
    public static SavedMedicine getInstance() {
        return ourInstance;
    }

    private SavedMedicine() {
        medicineList = new ArrayList<Medicine>();
        medicineList.add(new Medicine("Panadol", "For headache, effects slower"));
        medicineList.add(new Medicine("Burana", "For headache, effects faster"));
    }

    public void saveMedicine(){
        // TODO: MAKE A METHOD THAT SAVES MEDICINE TO THE LIST
    }

    public List<Medicine> getMedicine(){
        return medicineList;
    }

}
