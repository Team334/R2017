package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team334.robot.controls.Constants;

public class DriveTrain {

//    private VictorSP rightMotors;
//    private VictorSP leftMotors;

    private TalonSRX leftA, leftB, rightA, rightB;

    public DriveTrain () {
//        rightMotors = new VictorSP(Constants.DRIVETRAIN_RIGHT);
//        leftMotors = new VictorSP(Constants.DRIVETRAIN_LEFT);

//        rightMotors.setInverted(true);
//        leftMotors.setInverted(true);

        leftA = new TalonSRX(1);
        leftA.setInverted(true);
        leftB = new TalonSRX(0);
        leftB.setInverted(true);

        // invert right
        rightA = new TalonSRX(2);
        rightB = new TalonSRX(3);

    }

    public void setRightMotors (double speed) {
//        rightMotors.set(speed);
        rightA.set(speed);
        rightB.set(speed);
    }

    public void setLeftMotors (double speed) {
//        leftMotors.set(speed);
        leftA.set(speed);
        leftB.set(speed);
    }

    public void stop() {
        setLeftMotors(0);
        setRightMotors(0);
    }

}
