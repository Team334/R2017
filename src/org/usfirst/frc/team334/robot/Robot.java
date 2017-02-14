package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.movement.Straight;
import org.usfirst.frc.team334.robot.auton.movement.Turn;
import org.usfirst.frc.team334.robot.components.*;
import org.usfirst.frc.team334.robot.controls.Controls;
import org.usfirst.frc.team334.robot.sensors.BNO055;
import org.usfirst.frc.team334.robot.sensors.HallEffect;
import org.usfirst.frc.team334.robot.util.ManualAutonSelect;
import org.usfirst.frc.team334.robot.vision.VisionData;

class Robot extends IterativeRobot {
    private DriveTrain driveTrain;
    private Controls controls;
    private Intake intake;
    private Indexer indexer;
    private Climber climber;
    private Gear gear;
    private Shooter shooter;

    private HallEffect hallFX;
    private BNO055 imu;

    private Ramp fastRamp;
    private Ramp slowRamp;

    private Turn turnLeft;
    private Turn turnRight;
    private Straight straight;
    private ManualAutonSelect manualAutonSelect;

    private SendableChooser<String> autoChoose;
    private String autonScenario;
    private final String LEFT_SIDE = "LEFT",
                         RIGHT_SIDE = "RIGHT",
                         MIDDLE = "MIDDLE",
                         MANUAL = "MANUAL";

    private double stickCalLeft;
    private double stickCalRight;

    @Override
    public void robotInit() {
        // INIT SENSORS
        hallFX = new HallEffect(0);
        imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER);

        // INIT SUBSYSTEMS
        driveTrain = new DriveTrain(0, 1);
        controls = new Controls(0, 1, 2);

        // UPDATE PORTS AND VALUES
        intake = new Intake(0);
        indexer = new Indexer(0);
        climber = new Climber(0);
        gear = new Gear(0, 1);
        shooter = new Shooter(0);

        fastRamp = new Ramp(10);
        slowRamp = new Ramp(50);

        // INIT VISION
        VisionData.init();

        // AUTON COMMAND
        double distanceToBaseLine = 9.4;
        double angleToPeg = 60;
        turnLeft = new Turn(-angleToPeg, driveTrain);
        turnRight = new Turn(angleToPeg, driveTrain);
        straight = new Straight(distanceToBaseLine, driveTrain);
        manualAutonSelect = new ManualAutonSelect();

        // ADD OBJECTS TO SENDABLE CHOOSER
        autoChoose = new SendableChooser<String>();
        autoChoose.addObject("Turn Left", LEFT_SIDE);
        autoChoose.addObject("Turn Right", RIGHT_SIDE);
        autoChoose.addObject("Go Straight", MIDDLE);
        autoChoose.addDefault("Default", MANUAL);
        SmartDashboard.putData("Choose Auton Mode", autoChoose);
    }

    @Override
    public void robotPeriodic() {

    }

    @Override
    public void autonomousInit() {
        Scheduler.getInstance().removeAll(); // clear old command

        autonScenario = autoChoose.getSelected();
        if (autonScenario.equals(MANUAL)) {
            autonScenario = manualAutonSelect.getSelection();
        }

        switch (autonScenario) {
            case LEFT_SIDE:
                Scheduler.getInstance().add(straight);
                Scheduler.getInstance().add(turnLeft);
                break;
            case RIGHT_SIDE:
                Scheduler.getInstance().add(straight);
                Scheduler.getInstance().add(turnRight);
                break;
            case MIDDLE:
                Scheduler.getInstance().add(straight);
                break;
            default:
                break;
        }
    }

    @Override
    public void autonomousPeriodic() {
        // RUN COMMANDS IF ANY
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        stickCalLeft = controls.getLeftDrive();
        stickCalRight = controls.getRightDrive();
    }

    @Override
    public void teleopPeriodic() {
        // CLIMBER LISTENER
        if (controls.getClimbUp() && !controls.getClimbDown()) {
            climber.climbUp();
        } else if (controls.getClimbDown()) {
            climber.climbDown();
        } else {
            climber.stop();
        }

        // INTAKE LISTENER
        if (controls.getIntakeIn() && !controls.getIntakeOut()) {
            intake.pull_in();
        } else if (controls.getIntakeOut()) {
            intake.push_out();
        } else {
            intake.stop();
        }

        // INTAKE LISTENER
        if (controls.getIndexerIn() && !controls.getIndexerOut()) {
            indexer.pushIntoShooter();
        } else if (controls.getIndexerOut()) {
            indexer.pushOutOfShooter();
        } else {
            indexer.stop();
        }

        // SHOOTER LISTENER
        if (controls.getShooterButton()) {
            shooter.setShooterSpeed(0.6);
        } else {
            shooter.setShooterSpeed(0);
        }

        // GEAR LISTENER
        if (controls.getGearOut()) {
            gear.pushOutGear();
        } else {
            gear.resetServos();
        }

        // DRIVETRAIN LISTENER
        double leftSpeed = controls.getLeftDrive();
        double rightSpeed = controls.getRightDrive();

        if (controls.getSlowRampButton(Ramp.SIDE.LEFT) && controls.getSlowRampButton(Ramp.SIDE.RIGHT)) {
            double sens = 1 + Math.abs(controls.getLeftDrive() - controls.getRightDrive());
            leftSpeed = controls.getLeftDrive() / sens;
            rightSpeed = controls.getRightDrive() / sens;
        }

        driveTrain.setLeftMotors(leftSpeed);
        driveTrain.setRightMotors(rightSpeed);

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
//            rightSpeed = ((controls.getRightDrive() - stickCalRight) * slowRamp.getRamp(Ramp.SIDE.RIGHT));
//        else
//            slowRamp.reset(Ramp.SIDE.LEFT);

        SmartDashboard.putNumber("Left speed", leftSpeed);
        SmartDashboard.putNumber("Right speed", rightSpeed);

        SmartDashboard.putNumber("LeftJoy", controls.getLeftDrive());
        SmartDashboard.putNumber("Right Joy", controls.getRightDrive());
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
