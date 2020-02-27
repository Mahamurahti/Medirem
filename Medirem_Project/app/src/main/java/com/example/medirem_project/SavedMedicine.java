package com.example.medirem_project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * SavedMedicine is the singleton class that holds all the saved medicine information
 * @author Eric Keränen
 * @version 1.1 2/2019
 */
public class SavedMedicine {
    private static final SavedMedicine ourInstance = new SavedMedicine();

    private ArrayList<Medicine> medicineList;

    /**
     * Method for getting the instance of the singleton
     * @return the instance that is the constructor
     */
    public static SavedMedicine getInstance() {
        return ourInstance;
    }

    /**
     * Singleton constructor, must be private so it can be instantiated only once,
     * henceforth it's a singleton.
     */
    private SavedMedicine() {
        medicineList = new ArrayList<Medicine>();
    }

    /**
     * Method for saving medicine to the list
     * @param name name of the medicine (String)
     * @param desc description of the medicine (String)
     */
    public void saveMedicine(String name, String desc, String date){
        medicineList.add(new Medicine(name, desc, date));
    }

    /**
     * Method for removing medicine form the list
     * @param i removes the medicine determined by the index
     */
    public void removeMedicine(int i){
        medicineList.remove(i);
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

    /**
     * Method for setting a new list to the Singleton list
     * @param list the new list you want to paste into the singleton list
     */
    public void setMedicine(ArrayList<Medicine> list){
        medicineList = list;
    }

}
