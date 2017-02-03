package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;

public class DriveTrain {
    VictorSP[] rightMotors;
    VictorSP[] leftMotors;
    public DriveTrain (int rightMotor1, int rightMotor2, int rightMotor3, int leftMotor1, int leftMotor2, int leftMotor3) {
        rightMotors = new VictorSP[] {new VictorSP(rightMotor1), new VictorSP(rightMotor2), new VictorSP(rightMotor3)};
        leftMotors = new VictorSP[] {new VictorSP(leftMotor1), new VictorSP(leftMotor2), new VictorSP(leftMotor3)};
        for (VictorSP m : rightMotors) {
            m.setInverted(true);
        }
        for (VictorSP m : leftMotors) {
            m.setInverted(true);
        }
//        rightMotors[0].setInverted(true);
//        rightMotors[1].setInverted(true);
//        rightMotors[2].setInverted(true);
//        leftMotors[0].setInverted(true);
//        leftMotors[1].setInverted(true);
//        leftMotors[2].setInverted(true);


    }

    public void setRightMotors (double speed) {
        System.out.println("Right = " + speed);
        for (VictorSP m : rightMotors) {
            m.set(speed);
        }
//        rightMotors[0].set(speed);
//        rightMotors[1].set(speed);
//        rightMotors[2].set(speed);
    }

    public void setLeftMotors (double speed) {
        System.out.println("Left = " + speed);
        for (VictorSP m : leftMotors) {
            m.set(speed);
        }
//        leftMotors[0].set(speed);
//        leftMotors[1].set(speed);
//        leftMotors[2].set(speed);
    }
}
