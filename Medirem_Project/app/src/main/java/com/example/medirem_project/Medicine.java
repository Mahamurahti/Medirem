package com.example.medirem_project;

/**
 * Medicine class holds the information of medicine, like name and description
 * @author Eric Keränen
 * @version 1.1 2/2019
 */
public class Medicine {

    private String name, desc;

    // TODO: ADD TIME TO TAKE THE PILL
    /**
     * Constructor for Medicine class
     * @param name name of the medicine
     * @param desc description of the medicine
     */
    public Medicine(String name, String desc){
        this.name = name;
        this.desc = desc;
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
}
