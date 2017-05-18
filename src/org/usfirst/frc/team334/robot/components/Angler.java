package org.usfirst.frc.team334.robot.components;


import edu.wpi.first.wpilibj.Servo;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Angler {
    private Servo angler;

    public Angler() {
//        angler = new Servo(Constants.ANGLER);
    }

//    public void setAngle() {
//        angler.setAngle(Constants.ANGLER_ANGLE);
//    }

    public void setAngle(double angle) {
        angler.setAngle(angle);
    }
}
