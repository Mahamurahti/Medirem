package com.example.medirem_project;

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
