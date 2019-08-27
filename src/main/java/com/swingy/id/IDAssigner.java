package com.swingy.id;

import java.util.ArrayList;

public class IDAssigner {

    private int baseID;
    private ArrayList<String> mobileIDs = new ArrayList<>();

    public IDAssigner(int baseID){
        this.baseID = baseID;
    }

    public int next(){
        return baseID++;
    }

    public int getCurrentID(){
        return baseID;
    }
}
