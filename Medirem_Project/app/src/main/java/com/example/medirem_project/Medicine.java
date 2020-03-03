package com.example.medirem_project;

/**
 * Medicine class holds the information of medicine, like
 * the medicines name, description, date and time.
 * @author Eric Keränen
 * @version 1.1 2/2020
 */
public class Medicine {

    private String name, desc, date, time;

    /**
     * Constructor for Medicine class
     * @param name name of the medicine
     * @param desc description of the medicine
     * @param date the date where the medicine is saved
     * @param time the time where the medicine is saved
     */
    public Medicine(String name, String desc, String date, String time){
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    @Override
    public  String toString(){
        return this.name;
    }

    /**
     * Method returns medicine name
     * @return medicine name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Method returns medicine description
     * @return description
     */
    public String getDesc(){
        return this.desc;
    }

    /**
     * Method returns medicine date
     * @return the date where the medicine is saved
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Method returns medicine time
     * @return the time where the medicine is saved
     */
    public String getTime(){
        return this.time;
    }
}
