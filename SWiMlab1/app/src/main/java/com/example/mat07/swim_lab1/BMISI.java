package com.example.mat07.swim_lab1;

/**
 * Created by mat07 on 22.03.2018.
 */



public class BMISI extends BMI {

    public static final double maxHeight = 4.0;
    public static final double minHeight = 0.5;
    public static final double maxMass = 600;
    public static final double minMass = 30;

    public BMISI(){
        mass = 0;
        height = 0;
    }

    public BMISI(double kg, double m){
        mass = kg;
        height = m;
    }

    @Override
    public boolean isDataValid() {
        return height <= maxHeight && height >= minHeight && mass <= maxMass && mass >= minMass;
    }

    @Override
    public double getBMI() throws IllegalArgumentException {
        if(isDataValid()){
            return mass/(height*height);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
