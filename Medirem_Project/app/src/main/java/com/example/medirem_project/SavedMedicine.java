package com.example.medirem_project;

import java.util.ArrayList;
import java.util.List;

/**
 * SavedMedicine is the singleton class that holds all the saved medicine information
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

    /**
     * Singleton constructor, must be private so it can be instantiated only once,
     * henceforth it's a singleton.
     */
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
        medicineList.add(new Medicine("Medirest", "Melatoniini"));
    }

    /**
     * Method for saving medicine to the list
     * @param name name of the medicine (String)
     * @param desc description of the medicine (String)
     */
    public void saveMedicine(String name, String desc){
        medicineList.add(new Medicine(name, desc));
    }

    /**
     * Method for returning all medicine from the list
     * @return all data from the list
     */
    public List<Medicine> getMedicine(){
        return medicineList;
    }

    /**
     * Method for returning a specific medicine from the list
     * @param i specific element from the list (int)
     * @return all the data from the specific medicine from the list
     */
    public Medicine getMedicine(int i){
        return medicineList.get(i);
    }

}
