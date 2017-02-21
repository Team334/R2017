package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Gear extends Subsystem {

    private Servo left;
    private Servo right;

    public Gear() {
        left = new Servo(Constants.GEAR_LEFT);
        right = new Servo(Constants.GEAR_RIGHT);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void pushOutGear() {
        left.setAngle(60);
        right.setAngle(-60);
    }

    public void resetServos() {
        left.setAngle(0);
        right.setAngle(0);
    }

}
