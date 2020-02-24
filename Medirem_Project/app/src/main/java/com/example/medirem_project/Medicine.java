package com.example.medirem_project;

/**
 * Medicine class holds the information of medicine, like description
 * @author Eric Keränen
 * @version 1.1 2/2019
 */
public class Medicine {

    private String name, desc;

    // A NORMAL CLASS TO STORE MEDICINE DATA
    public Medicine(String name, String desc){
        this.name = name;
        this.desc = desc;

    }

    @Override
    public  String toString(){
        return this.name;
    }

    public String getName(){
        return this.name;
    }

    public String getDesc(){
        return this.desc;
    }
}
