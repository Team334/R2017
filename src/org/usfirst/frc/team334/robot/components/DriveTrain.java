package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class DriveTrain extends Subsystem {

    private VictorSP rightMotors;
    private VictorSP leftMotors;

    public DriveTrain () {
        rightMotors = new VictorSP(Constants.DRIVETRAIN_RIGHT);
        leftMotors = new VictorSP(Constants.DRIVETRAIN_LEFT);

        rightMotors.setInverted(true);
        leftMotors.setInverted(true);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void setRightMotors (double speed) {
        rightMotors.set(speed);
    }

    public void setLeftMotors (double speed) {
        leftMotors.set(speed);
    }

    public void stop() {
        setLeftMotors(0);
        setRightMotors(0);
    }

}
