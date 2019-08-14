package com.swingy.artifacts;

public class Artifact {

    protected long id;
    protected String type;
    protected String name;

    public Artifact(String type){
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String get_name() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
