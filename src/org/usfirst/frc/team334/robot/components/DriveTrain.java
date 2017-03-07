package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class DriveTrain extends Subsystem {

    private VictorSP rightMotors;
    private VictorSP leftMotors;

    private Encoder encLeft;
    private Encoder encRight;

    private boolean leftInverted = false;
    private boolean rightInverted = true;

    public DriveTrain () {
        rightMotors = new VictorSP(Constants.DRIVETRAIN_RIGHT);
        leftMotors = new VictorSP(Constants.DRIVETRAIN_LEFT);

        rightMotors.setInverted(rightInverted);
        leftMotors.setInverted(leftInverted);

        encLeft = new Encoder(Constants.ENCODER_LEFT_1, Constants.ENCODER_LEFT_2);
        encLeft.setDistancePerPulse(Constants.DRIVEWHEEL_CIRCUMFERENCE * (1 / Constants.DRIVE_PULSES_PER_REVOLUTION));

        encRight = new Encoder(Constants.ENCODER_RIGHT_1, Constants.ENCODER_RIGHT_2);
        encRight.setDistancePerPulse(Constants.DRIVEWHEEL_CIRCUMFERENCE * (1 / Constants.DRIVE_PULSES_PER_REVOLUTION));
    }

    @Override
    protected void initDefaultCommand() {}

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

    public double getDistanceTraveled() {
        return (encLeft.getDistance() + encRight.getDistance()) / 2;
    }

    public void invertDirection() {
        leftInverted = !leftInverted;
        rightInverted = !rightInverted;

        leftMotors.setInverted(leftInverted);
        rightMotors.setInverted(rightInverted);
    }
}
