package org.usfirst.frc.team334.robot.components;

/**
 * Created by eric on 2/3/2017.
 */
public class Ramp {
    double[] valuesL;
    double[] valuesR;
    double counterL, counterR;
    public enum SIDE {
        LEFT, RIGHT
    }
    SIDE side;

    public Ramp(int size) {
        valuesL = new double[size];
        valuesR = new double[size];
        counterL = 0;
        counterR = 0;
    }

    public void addJoystickValues(double yValue, SIDE side) {
        double[] values = (side == SIDE.LEFT) ? valuesL : valuesR;

        if(side == SIDE.LEFT)
            counterL++;
        else
            counterR++;

        for(int i = 1; i < values.length; i++)
            values[i - 1] = Math.abs(values[i]);
        values[values.length - 1] = yValue;
    }

    public double getRamp(SIDE side) {
        double[] values = (side == SIDE.LEFT) ? valuesL : valuesR;
        boolean enoughData = (side == SIDE.LEFT) ? (counterL > valuesL.length) : counterR >= valuesR.length;
        double total = 0, length = 1;

        if (enoughData) {
            length = values.length;
            for(int i = 0; i < values.length; i++) {
                total += values[i];
            }
        }
//        if (side == SIDE.LEFT && counterL >= valuesL.length) {
//            length = valuesL.length;
//            for(int i = 0; i < valuesL.length; i++) {
//                total += valuesL[i];
//            }
//        }
//        else if (side == SIDE.RIGHT && counterR >= valuesR.length) {
//            length = valuesR.length;
//            for(int i = 0; i < valuesR.length; i++) {
//                total += valuesR[i];
//            }
//        }
//        // System.out.println("total = " + total + " " + "length = " + length);
        return total / length;
    }

    public void reset(SIDE side) {
        double[] values = (side == SIDE.LEFT) ? valuesL : valuesR;
        for (int i = 0; i < values.length; i++) {
            values[i] = 0;
        }
    }
}
