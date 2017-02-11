package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.Servo;

public class Gear {

    private Servo left;
    private Servo right;

    public Gear(int leftPort, int rightPort) {
        left = new Servo(leftPort);
        right = new Servo(rightPort);
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
