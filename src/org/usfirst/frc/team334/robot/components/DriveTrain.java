package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;

public class DriveTrain {
    VictorSP rightMotors;
    VictorSP leftMotors;

    public DriveTrain (int rightMotor, int leftMotor) {
        rightMotors = new VictorSP(rightMotor);
        leftMotors = new VictorSP(leftMotor);

        rightMotors.setInverted(true);
        leftMotors.setInverted(true);
    }

    public void setRightMotors (double speed) {
        rightMotors.set(speed);
    }

    public void setLeftMotors (double speed) {
        leftMotors.set(speed);
    }
}
