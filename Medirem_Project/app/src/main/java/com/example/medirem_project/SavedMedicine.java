package com.example.medirem_project;

import java.util.ArrayList;
import java.util.List;

/**
 * SavedMedicine is the singleton that holds all the saved medicine
 * @author Eric Keränen
 * @version 1.1 2/2019
 */
public class SavedMedicine {
    private static final SavedMedicine ourInstance = new SavedMedicine();

    private List<Medicine> medicineList;

    // SINGLETON CLASS
    public static SavedMedicine getInstance() {
        return ourInstance;
    }

    private SavedMedicine() {
        medicineList = new ArrayList<Medicine>();
        medicineList.add(new Medicine("Panadol", "Särkylääke, pitkäkestoinen"));
        medicineList.add(new Medicine("Burana", "Särkylääke, lyhytkestoinen"));
        medicineList.add(new Medicine("Linatil", "Korkeanverenpaineen hallinta"));
        medicineList.add(new Medicine("Misoprolol", "Korkeanverenpaineen hallintaa ja turvotukseen"));
        medicineList.add(new Medicine("Ibuprofeeni", "Kipu ja särkylääke"));
        medicineList.add(new Medicine("Simvastatin", "Kolesterolilääke"));
        medicineList.add(new Medicine("Tryptiili", "Migreeni esto lääke"));
        medicineList.add(new Medicine("Aspirin", "Tulehdus ja päänsärky lääke"));
        medicineList.add(new Medicine("Somac", "Närästyslääke"));
        medicineList.add(new Medicine("Rennie", "Närästyslääke"));
        medicineList.add(new Medicine("Morfiini", "Krapulatärinän parannuslääke"));
    }

    public void saveMedicine(){
        // TODO: MAKE A METHOD THAT SAVES MEDICINE TO THE LIST
    }

    public List<Medicine> getMedicine(){
        return medicineList;
    }

    public Medicine getMedicine(int i){
        return medicineList.get(i);
    }

}
