package com.example.mat07.swim_lab1;

/**
 * Created by mat07 on 22.03.2018.
 */

import java.security.InvalidParameterException;


public abstract class BMI {

    protected double mass;
    protected double height;

    public BMI(){
        mass = 0;
        height = 0;
    }

    public double getMass() { return mass; }
    public double getHeight() { return height; }
    public void setMass(double m) { mass = m; }
    public void setHeight(double h) { height = h; }

    public abstract boolean isDataValid();
    public abstract double getBMI() throws InvalidParameterException;

}
