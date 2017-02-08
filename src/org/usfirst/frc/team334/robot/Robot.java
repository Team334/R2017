package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.components.*;
import org.usfirst.frc.team334.robot.controls.Controls;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class Robot extends IterativeRobot {
    private DriveTrain driveTrain;
    private Controls controls;
    private Intake intake;
    private Indexer indexer;
    private Climber climber;
    private Gear gear;
    private Shooter shooter;

    private Ramp fastRamp;
    private Ramp slowRamp;

    private PowerDistributionPanel powerDistributionPanel = new PowerDistributionPanel();

    private double stickCalLeft;
    private double stickCalRight;

    @Override
    public void robotInit() {
        driveTrain = new DriveTrain(0, 1);
        controls = new Controls(0, 1, 2);

        // UPDATE PORTS
        intake = new Intake(0);
        indexer = new Indexer(0);
        climber = new Climber(0);
        gear = new Gear(0, 1);
        shooter = new Shooter(0);

        VisionData.init();

        fastRamp = new Ramp(10);
        slowRamp = new Ramp(50);
    }

    @Override
    public void robotPeriodic() {

    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        stickCalLeft = controls.getLeftDrive();
        stickCalRight = controls.getRightDrive();
    }

    double leftSpeed;
    double rightSpeed;

    @Override
    public void teleopPeriodic() {
        leftSpeed = controls.getLeftDrive();
        rightSpeed = controls.getRightDrive();

        if (controls.getSlowRampButton(Ramp.SIDE.LEFT) && controls.getSlowRampButton(Ramp.SIDE.RIGHT)) {
            double sens = 1 + Math.abs(controls.getLeftDrive() - controls.getRightDrive());
            leftSpeed = controls.getLeftDrive() / sens;
            rightSpeed = controls.getRightDrive() / sens;
        }
//        fastRamp.addJoystickValues(controls.getLeftDrive(), Ramp.SIDE.LEFT);
//        fastRamp.addJoystickValues(controls.getRightDrive(), Ramp.SIDE.RIGHT);
//
//        slowRamp.addJoystickValues(controls.getLeftDrive(), Ramp.SIDE.LEFT);
//        slowRamp.addJoystickValues(controls.getRightDrive(), Ramp.SIDE.RIGHT);
//
//        leftSpeed = ((controls.getLeftDrive() - stickCalLeft) * fastRamp.getRamp(Ramp.SIDE.LEFT));
//        rightSpeed = ((controls.getRightDrive() - stickCalRight) * fastRamp.getRamp(Ramp.SIDE.RIGHT));
//
//        if (controls.getSlowRampButton(Ramp.SIDE.LEFT))
//            leftSpeed = ((controls.getLeftDrive() - stickCalLeft) * slowRamp.getRamp(Ramp.SIDE.LEFT));
//        else
//            slowRamp.reset(Ramp.SIDE.LEFT);
//
//        if (controls.getSlowRampButton(Ramp.SIDE.RIGHT))
//":$pl.            rightSpeed = ((controls.getRightDrive() - stickCalRight) * slowRamp.getRamp(Ramp.SIDE.RIGHT));
//        else
//            slowRamp.reset(Ramp.SIDE.LEFT);
//
//
//        SmartDashboard.putNumber("fastL ramp", fastRamp.getRamp(Ramp.SIDE.LEFT));
//        SmartDashboard.putNumber("fastR ramp", fastRamp.getRamp(Ramp.SIDE.RIGHT));
//        SmartDashboard.putNumber("slowL ramp", slowRamp.getRamp(Ramp.SIDE.LEFT));
//        SmartDashboard.putNumber("slowR ramp", slowRamp.getRamp(Ramp.SIDE.RIGHT));

        SmartDashboard.putNumber("Left speed", leftSpeed);
        SmartDashboard.putNumber("Right speed", rightSpeed);

        SmartDashboard.putNumber("LeftJoy", controls.getLeftDrive());
        SmartDashboard.putNumber("Right Joy", controls.getRightDrive());

        driveTrain.setLeftMotors(leftSpeed);
        driveTrain.setRightMotors(rightSpeed);
    }

    @Override
    public void testInit() {

    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {

    }
}
