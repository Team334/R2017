package org.usfirst.frc.team334.robot.controls;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team334.robot.components.Ramp;

public class Controls {

    private Joystick leftJoystick;
    private Joystick rightJoystick;
    private XboxController xboxController;

    public Controls(int leftJoy, int rightJoy, int xbox) {
        leftJoystick = new Joystick(leftJoy);
        rightJoystick = new Joystick(rightJoy);
        xboxController = new XboxController(xbox);
    }

    public boolean getSlowRampButton(Ramp.SIDE side) {
        return (side == Ramp.SIDE.LEFT) ? leftJoystick.getRawButton(1) : rightJoystick.getRawButton(1);
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

    // ------------------------------ UPDATE VALUES -----------------------------

    public boolean getIndexerIn() {
        return xboxController.getBackButton();
    }

    public boolean getGearOut() {
        return xboxController.getBackButton();
    }

    public boolean getShooterButton() {
        return xboxController.getBackButton();
    }

    public boolean getToggleCamera() { 
	return leftJoystick.getRawButton(1);
    }

    public void xboxRumble() {
        xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
        xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
    }

    public void xboxUnRumble() {
        xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
    }

}
