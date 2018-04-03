package com.example.mat07.swim_lab1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class UnitTestForBMI {


    //Metric BMI calculator
    @Test
    public void bmi_metric_isCorrect(){
        BMI bmi = new BMISI(85, 1.88);
        assertEquals(24.049, bmi.getBMI(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_zero_bmi_metric_throw_exception(){
        BMI bmi = new BMISI(0,  0);
        bmi.getBMI();
    }

    @Test(expected = IllegalArgumentException.class)
    public void metric_for_enormous_mass_throw_exception() {
        BMI bmi = new BMISI(1000,  1.88);
        bmi.getBMI();
    }

    @Test(expected = IllegalArgumentException.class)
    public void metric_for_low_mass_throw_exception() {
        BMI bmi = new BMISI(0.1,  1.88);
        bmi.getBMI();
    }


    //Imperial BMI calculator
    @Test
    public void bmi_imperial_isCorrect() {
        BMI bmi = new BMICale(189,  74);
        assertEquals(24.298, bmi.getBMI(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_zero_bmi_imperial_throw_exception(){
        BMI bmi = new BMICale(0,  0);
        bmi.getBMI();
    }


    @Test(expected = IllegalArgumentException.class)
    public void imperial_for_enormous_mass_throw_exception() {
        BMI bmi = new BMICale(1500,  1.88);
        bmi.getBMI();
    }

    @Test(expected = IllegalArgumentException.class)
    public void imperial_for_negative_height_throw_exception() {
        BMI bmi = new BMICale(180,  -1);
        bmi.getBMI();
    }

}