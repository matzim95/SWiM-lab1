package com.example.mat07.swim_lab1;

/**
 * Created by mat07 on 22.03.2018.
 */


public class BMICale extends BMI {

    public static final double maxHeight = 150;
    public static final double minHeight = 10;
    public static final double maxMass = 1000;
    public static final double minMass = 20;
    public static final double CONSTANT_UK_FACTOR = 704;

    public BMICale(){
        mass = 0;
        height = 0;
    }

    public BMICale(double lb, double in){
        mass = lb;
        height = in;
    }

    @Override
    public boolean isDataValid() {
        return height <= maxHeight && height >= minHeight && mass <= maxMass && mass >= minMass;
    }

    @Override
    public double getBMI() throws IllegalArgumentException {
        if(isDataValid()){
            return (mass/(height*height))*CONSTANT_UK_FACTOR;
        } else {
            throw new IllegalArgumentException();
        }
    }

}

