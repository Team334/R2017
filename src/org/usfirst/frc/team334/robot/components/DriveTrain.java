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
    }

    public void setRightMotors (double speed) {
        for (VictorSP m : rightMotors) {
            m.set(speed);
        }
    }

    public void setLeftMotors (double speed) {
        for (VictorSP m : leftMotors) {
            m.set(speed);
        }
    }
}
