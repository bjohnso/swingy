package com.swingy.artifacts;

import java.util.ArrayList;

public class Artifact {

    protected long _id;
    protected String _type;
    protected int _level = 0;
    protected String _name;

    public Artifact(String name, String type){
        _name = name;
        _type = type;
    }

}
