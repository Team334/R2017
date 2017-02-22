package org.usfirst.frc.team334.robot.controls;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team334.robot.components.Ramp;
import org.usfirst.frc.team334.robot.components.Target;

public class Controls {

    private Joystick leftJoystick;
    private Joystick rightJoystick;
    private XboxController xboxController;

    private boolean gearButtonHeld = false;
    private boolean changeDriveButtonHeld = false;

    public Controls() {
        leftJoystick = new Joystick(Constants.JOYSTICK_LEFT);
        rightJoystick = new Joystick(Constants.JOYSTICK_RIGHT);
        xboxController = new XboxController(Constants.XBOX);
    }

    // ----------------------JOYSTICKS------------------------------------
    public boolean getSlowRamp(Ramp.SIDE side) {
        return (side == Ramp.SIDE.LEFT) ? leftJoystick.getRawButton(1) : rightJoystick.getRawButton(1);
    }

    public boolean getAutoAlign(Target target) {
        return (target == Target.GEAR) ? leftJoystick.getRawButton(2) : leftJoystick.getRawButton(3);
    }

    public double getLeftDrive() {
        return leftJoystick.getY();
    }

    public double getRightDrive() {
        return rightJoystick.getY();
    }

    // -------------------------XBOX-------------------------------------

    public boolean getMoveGear() {
        boolean gearToggled =  rightJoystick.getRawButton(1) && !gearButtonHeld;
        gearButtonHeld = rightJoystick.getRawButton(1);
        return gearToggled;
    }

    public boolean getHoldGear() {
        return rightJoystick.getRawButton(2);
    }

    public boolean getClimbUp() {
        return rightJoystick.getRawButton(3);
    }

    public boolean getClimbDown() {
        return rightJoystick.getRawButton(4);
    }

    public boolean getIntakeIn() {
        return rightJoystick.getRawButton(5);
    }

    public boolean getChangeDriveDirection() {
        boolean driveDirectionToggled = rightJoystick.getRawButton(10) && !changeDriveButtonHeld;
        changeDriveButtonHeld = rightJoystick.getRawButton(10);
        return driveDirectionToggled;
    }

    // ------------------------------ UPDATE VALUES -----------------------------

    public boolean getIndexerIn() {
        return rightJoystick.getRawButton(6);
    }

    public boolean getShoot() {
        return rightJoystick.getRawButton(7);
    }

    public boolean getToggleCamera() {
	    return rightJoystick.getRawButton(1);
    }

    public void xboxRumble() {
        xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
        xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
    }

    public void xboxStopRumble() {
        xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
    }

}
