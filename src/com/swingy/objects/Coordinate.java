package com.swingy.objects;

public class Coordinate {

    private int _axis_x;
    private int _axis_y;

    public Coordinate(){

    }

    public Coordinate(int axis_x, int axis_y){
        _axis_x = axis_x;
        _axis_y = axis_y;
    }

    public int getAxisX(){
        return _axis_x;
    }

    public int getAxisY(){
        return _axis_y;
    }

    public void setAxisX(int axis_x){
        _axis_x = axis_x;
    }

    public void setAxisY(int axis_y){
        _axis_y = axis_y;
    }

    public String toString(){
        return "Coordinates:\n" + _axis_x + "X\n" + _axis_y + "Y";
    }

}
