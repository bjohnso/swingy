package com.swingy.metrics;

//TODO: replace current co-ordinate management with this

public class Coordinate {

    private float _axis_x;
    private float _axis_y;

    public Coordinate(){

    }

    public Coordinate(float axis_x, float axis_y){
        _axis_x = axis_x;
        _axis_y = axis_y;
    }

    public float getAxisX(){
        return _axis_x;
    }

    public float getAxisY(){
        return _axis_y;
    }

    public void setAxisX(float axis_x){
        _axis_x = axis_x;
    }

    public void setAxisY(float axis_y){
        _axis_y = axis_y;
    }

    public String toString(){
        return "Coordinates:\n" + _axis_x + "X\n" + _axis_y + "Y";
    }

}
