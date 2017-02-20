package org.usfirst.frc.team334.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team334.robot.components.Ramp;
import org.usfirst.frc.team334.robot.components.Target;

public class Controls {

    private Joystick leftJoystick;
    private Joystick rightJoystick;
    private XboxController xboxController;

    public Controls() {
        leftJoystick = new Joystick(Constants.JOYSTICK_LEFT);
        rightJoystick = new Joystick(Constants.JOYSTICK_RIGHT);
        xboxController = new XboxController(Constants.XBOX);
    }

    // JOYSTICKS
    public boolean getSlowRamp(Ramp.SIDE side) {
        return (side == Ramp.SIDE.LEFT) ? leftJoystick.getRawButton(1) : rightJoystick.getRawButton(1);
    }

    public double getLeftDrive() {
        return leftJoystick.getY();
    }

    public double getRightDrive() {
        return rightJoystick.getY();
    }

    public boolean getAutoAlign(Target target) {
        return (target == Target.GEAR) ? leftJoystick.getRawButton(2) : leftJoystick.getRawButton(3);
    }

    // XBOX
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

    // ------------------------------ UPDATE VALUES -----------------------------
    public boolean getIndexerIn() {
        return xboxController.getBackButton();
    }

    public boolean getGearOut() {
        return xboxController.getBackButton();
    }

    public boolean getShoot() {
        return xboxController.getBackButton();
    }

    public boolean getToggleCamera() { 
	    return rightJoystick.getRawButton(1);
    }

}
