package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Gear extends Subsystem {

    private Servo left;
    private Servo right;

    private boolean isGearOut;

    public Gear() {
        left = new Servo(Constants.GEAR_LEFT);
        right = new Servo(Constants.GEAR_RIGHT);

        isGearOut = false;
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void pushOutGear() {
        left.setAngle(Constants.lEFT_SERVO_OUT);
        right.setAngle(Constants.RIGHT_SERVO_OUT);
        isGearOut = true;
    }

    public void gripGear() {
        left.setAngle(Constants.LEFT_SERVO_GRIP);
        right.setAngle(Constants.RIGHT_SERVO_GRIP);
        isGearOut = false;
    }

    public void resetServos() {
        left.setAngle(Constants.LEFT_SERVO_RESET);
        right.setAngle(Constants.RIGHT_SERVO_RESET);
        isGearOut = false;
    }

    public boolean isGearOut() {
        return isGearOut;
    }

}
