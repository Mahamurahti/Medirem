package com.example.medirem_project;

/**
 * Medicine class holds the information of medicine, like
 * the medicines name, description, date and time.
 * @author Eric Keränen
 * @version 1.3 2/2020
 */
public class Medicine {

    private String name, desc, date, time;
    private boolean medTaken;

    /**
     * Constructor for Medicine class
     * @param name name of the medicine (String)
     * @param desc description of the medicine (String)
     * @param date the date where the medicine is saved (String)
     * @param time the time where the medicine is saved (String)
     */
    public Medicine(String name, String desc, String date, String time){
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.medTaken = false;
    }

    @Override
    public  String toString(){
        return this.name;
    }

    /**
     * Method returns medicine name
     * @return medicine name (String)
     */
    public String getName(){
        return this.name;
    }

    /**
     * Method returns medicine description
     * @return description (String)
     */
    public String getDesc(){
        return this.desc;
    }

    /**
     * Method returns medicine date
     * @return the date where the medicine is saved (String)
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Method returns medicine time
     * @return the time where the medicine is saved (String)
     */
    public String getTime(){
        return this.time;
    }

    /**
     * Method for setting the medicine to a taken state
     */
    public void takeMed(){
        medTaken = true;
    }

    /**
     * Method for receiving the text about medicines state
     * @return a text that tells you if the medicine is taken (String)
     */
    public String medTakenText(){
        if(medTaken){
            return "Medicine has been taken";
        }else{
            return "Medicine has not been taken";
        }
    }

    /**
     * Method for knowing is the medicine taken
     * @return returns true if the medicine has been taken and false if not (boolean)
     */
    public boolean isMedTaken(){
        if(medTaken){
            return true;
        }else{
            return false;
        }
    }
}
