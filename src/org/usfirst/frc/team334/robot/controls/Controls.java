package org.usfirst.frc.team334.robot.controls;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team334.robot.components.Ramp;
import org.usfirst.frc.team334.robot.components.Target;

public class Controls {

    private Joystick leftJoystick;
    private Joystick rightJoystick;
    private Joystick thirdJoy;
    private XboxController xboxController;

    // Only toggle once everytime a button is clicked
    private boolean gearButtonHeld = false;
    private boolean changeDriveButtonHeld = false;

    public Controls() {
        leftJoystick = new Joystick(Constants.JOYSTICK_LEFT);
        rightJoystick = new Joystick(Constants.JOYSTICK_RIGHT);
        thirdJoy = new Joystick(Constants.XBOX);
//        xboxController = new XboxController(Constants.XBOX);
    }

    // ----------------------JOYSTICKS------------------------------------------------------------------------

    public boolean getSlowRamp() {
        return leftJoystick.getRawButton(1);
    }

    // for toggling gear
    public boolean getMoveGear() {
        boolean gearToggled =  rightJoystick.getRawButton(1) && !gearButtonHeld;
        gearButtonHeld = rightJoystick.getRawButton(1);
        return gearToggled;
    }

    // for holding gear
    public boolean getOutGear() {
        return rightJoystick.getRawButton(1);
    }

    public boolean getAutoAlign(Target target) {
        return (target == Target.GEAR) ? leftJoystick.getRawButton(2) : rightJoystick.getRawButton(2);
    }

    public double getLeftDrive() {
        return leftJoystick.getY();
    }

    public double getRightDrive() {
        return rightJoystick.getY();
    }

    // -------------------------XBOX----------------------------------------------------------------------------

//    public boolean getClimbUp() {
//        return xboxController.getYButton();
//    }
//
//    public boolean getClimbDown() {
//        return xboxController.getAButton();
//    }
//
//    public boolean getIntakeIn() {
//        return xboxController.getXButton();
//    }
//
//    public boolean getIntakeOut() {
//        return xboxController.getBButton();
//    }
//
//    public boolean getIndexerIn() {
//        return xboxController.getRawAxis(3) > .75;
//    }
//
//    public boolean getShoot() {
//        return xboxController.getRawAxis(2) > .75;
//    }
//
//    public boolean getToggleCamera() {
//	    return rightJoystick.getRawButton(8);
//    }

    // BACKUP JOYSTICK
    public boolean getClimbUp() {
        return thirdJoy.getRawButton(3) || rightJoystick.getRawButton(3);
    }

    public boolean getClimbDown() {
        return thirdJoy.getRawButton(2);
    }

    public boolean getIntakeIn() {
        return thirdJoy.getRawButton(6);
    }

    public boolean getIntakeOut() {
        return thirdJoy.getRawButton(7);
    }

    public boolean getIndexerIn() {
        return thirdJoy.getRawButton(1);
    }

    public boolean getShoot() {
        return thirdJoy.getRawButton(4);
    }

    public boolean getToggleCamera() {
        return rightJoystick.getRawButton(8);
    }

    public double getShooterSpeedAdjustment() {
        return -thirdJoy.getY() * 0.1;
    }

}
