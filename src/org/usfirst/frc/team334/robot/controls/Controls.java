package org.usfirst.frc.team334.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class Controls {
    Joystick leftJoystick;
    Joystick rightJoystick;
    XboxController xboxController;

    public Controls(int leftJoy, int rightJoy, int xbox) {
        leftJoystick = new Joystick(leftJoy);
        rightJoystick = new Joystick(rightJoy);
        xboxController = new XboxController(xbox);
    }

    public double getLeftDrive() {
        return leftJoystick.getY();
    }

    public double getRightDrive() {
        return rightJoystick.getY();
    }

    public boolean getClimbUp() {
        return xboxController.getAButton();
    }

    public boolean getClimbDown() {
        return xboxController.getBButton();
    }

    public boolean getIntakeIn() {
        return xboxController.getXButton();
    }

    public boolean getIntakeOut() {
        return xboxController.getYButton();
    }
}