package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.components.DriveTrain;
import org.usfirst.frc.team334.robot.controls.Controls;

public class Robot extends IterativeRobot {
    private DriveTrain driveTrain = new DriveTrain(0, 1, 2, 7, 8, 9);
    private Controls controls = new Controls(0, 1, 2);
    private PowerDistributionPanel powerDistributionPanel = new PowerDistributionPanel();

    private double stickCalLeft;
    private double stickCalRight;

    @Override
    public void robotInit() {
        stickCalLeft = controls.getLeftDrive();
        stickCalRight = controls.getRightDrive();
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
    }

    @Override
    public void teleopPeriodic() {
        driveTrain.setLeftMotors(controls.getLeftDrive() - stickCalLeft);
        driveTrain.setRightMotors(controls.getRightDrive() - stickCalRight);
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
