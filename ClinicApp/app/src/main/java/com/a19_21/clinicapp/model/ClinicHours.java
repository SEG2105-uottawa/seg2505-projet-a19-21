package com.a19_21.clinicapp.model;

import java.util.ArrayList;

public class ClinicHours {

    private ArrayList<String> startTime;
    private ArrayList<String> endTime;

    public ClinicHours(){
        this.startTime= new ArrayList<>();
        this.endTime= new ArrayList<>();

        for(int i=0;i<7;i++){
            this.startTime.add("--");
            this.endTime.add("--");
        }
    }
    public ClinicHours(ArrayList<String> startTime, ArrayList<String> endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ArrayList<String> getStratTime(){
        return this.startTime;
    }

    public void setStartTime(ArrayList<String> startTime){
        this.startTime = startTime;
    }

    public ArrayList<String> getEndTime(){
        return this.endTime;
    }

    public  void setEndTime(ArrayList<String> endTime){
        this.endTime= endTime;
    }

    public boolean isOpen(int day){
        if(this.getStratTime().get(day).equals("--")){
            return false;
        }
        return true;
    }
}
