package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.AutonScenario;
import org.usfirst.frc.team334.robot.auton.movement.Straight;
import org.usfirst.frc.team334.robot.auton.movement.Turn;
import org.usfirst.frc.team334.robot.auton.movement.VisionAuton;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionAreaPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionOffsetPID;
import org.usfirst.frc.team334.robot.auton.sources.GyroSource;
import org.usfirst.frc.team334.robot.components.*;
import org.usfirst.frc.team334.robot.controls.Controls;
import org.usfirst.frc.team334.robot.util.ManualAutonSelect;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class Robot extends IterativeRobot {
    // COMPONENTS
    private DriveTrain driveTrain;
    private Controls controls;
    private Intake intake;
    private Indexer indexer;
    private Climber climber;
    private Gear gear;
    private Shooter shooter;
    private VisionAutoAlign visionAutoAlign;

    private Ramp fastRamp;
    private Ramp slowRamp;

    // PIDS
    private GyroPID gyroPID;
    private VisionAreaPID areaPID;
    private VisionOffsetPID offsetPID;

    // AUTON COMMANDS
    private Turn turnLeft;
    private Turn turnRight;
    private Straight straight;
    private VisionAuton visionGear;
    private VisionAuton visionBoiler;
    private ManualAutonSelect manualAutonSelect;

    private SendableChooser<AutonScenario> autoChoose;

    private AutonScenario autonScenario;

    private double stickCalLeft;
    private double stickCalRight;

    @Override
    public void robotInit() {
        // INIT VISION
        VisionData.init();

        // INIT COMPONENETS
        driveTrain = new DriveTrain(0, 1);
        controls = new Controls(0, 1, 2);

        // INIT PIDS
        gyroPID = new GyroPID();
        areaPID = new VisionAreaPID();
        offsetPID = new VisionOffsetPID();

        // UPDATE PORTS AND VALUES
        intake = new Intake(0);
        indexer = new Indexer(0);
        climber = new Climber(0);
        gear = new Gear(0, 1);
        shooter = new Shooter(0);
        visionAutoAlign = new VisionAutoAlign(driveTrain, gyroPID, areaPID, offsetPID);

        fastRamp = new Ramp(10);
        slowRamp = new Ramp(50);

        // AUTON COMMAND
        final double DISTANCE_TO_BASELINE = 9.4;
        final double ANGLE_TO_PEG = 60;
        turnLeft = new Turn(-ANGLE_TO_PEG, driveTrain, gyroPID);
        turnRight = new Turn(ANGLE_TO_PEG, driveTrain, gyroPID);
        straight = new Straight(DISTANCE_TO_BASELINE, driveTrain, gyroPID);
        visionGear = new VisionAuton(visionAutoAlign, Target.GEAR, driveTrain);
        visionBoiler = new VisionAuton(visionAutoAlign, Target.BOILER, driveTrain);
        manualAutonSelect = new ManualAutonSelect();

        // ADD OBJECTS TO SENDABLE CHOOSER
        autoChoose = new SendableChooser<>();
        autoChoose.addObject("Turn Left", AutonScenario.LEFT_SIDE);
        autoChoose.addObject("Turn Right", AutonScenario.RIGHT_SIDE);
        autoChoose.addObject("Go Straight", AutonScenario.MIDDLE);
        autoChoose.addDefault("Default", AutonScenario.MANUAL);
        SmartDashboard.putData("Choose Auton Mode", autoChoose);
    }

    @Override
    public void robotPeriodic() {

    }

    @Override
    public void autonomousInit() {
        // Vision code will update to true if it initialized successfully
        VisionData.getNt().putBoolean("init", false);

        // reset gyro
        GyroSource.imu.resetHeading();

        // clear old commands
        Scheduler.getInstance().removeAll();

        autonScenario = autoChoose.getSelected();
        if (autonScenario == AutonScenario.MANUAL) {
            autonScenario = manualAutonSelect.getScenario();
        }

        switch (autonScenario) {
            case LEFT_SIDE:
                Scheduler.getInstance().add(straight);
                Scheduler.getInstance().add(turnLeft);
                Scheduler.getInstance().add(visionGear);
                break;
            case RIGHT_SIDE:
                Scheduler.getInstance().add(straight);
                Scheduler.getInstance().add(turnRight);
                Scheduler.getInstance().add(visionGear);
                break;
            case MIDDLE:
                Scheduler.getInstance().add(straight);
                Scheduler.getInstance().add(visionGear);
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
        } else if (controls.getClimbDown() && !controls.getClimbUp()) {
            climber.climbDown();
        } else {
            climber.stop();
        }

        // INTAKE LISTENER
        if (controls.getIntakeIn() && !controls.getIntakeOut()) {
            intake.pull_in();
        } else if (controls.getIntakeOut() && !controls.getIntakeIn()) {
            intake.push_out();
        } else {
            intake.stop();
        }

        // INTAKE LISTENER
        if (controls.getIndexerIn()) {
            indexer.pushIntoShooter();
        } else {
            indexer.stop();
        }

        // SHOOTER LISTENER
        if (controls.getShoot()) {
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
        if (controls.getAutoAlign(Target.GEAR)) {
            visionAutoAlign.setTarget(Target.GEAR);
            visionAutoAlign.autoAlign();
        } else if (controls.getAutoAlign(Target.BOILER)) {
            visionAutoAlign.setTarget(Target.BOILER);
            visionAutoAlign.autoAlign();
        } else {
            // joystick controlled
            double leftSpeed = controls.getLeftDrive();
            double rightSpeed = controls.getRightDrive();
            if (controls.getSlowRamp(Ramp.SIDE.LEFT) && controls.getSlowRamp(Ramp.SIDE.RIGHT)) {
                double sens = 1 + Math.abs(controls.getLeftDrive() - controls.getRightDrive());
                leftSpeed /= sens;
                rightSpeed /= sens;
            }
            driveTrain.setLeftMotors(leftSpeed);
            driveTrain.setRightMotors(rightSpeed);

            SmartDashboard.putNumber("Left speed", leftSpeed);
            SmartDashboard.putNumber("Right speed", rightSpeed);
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
//            rightSpeed = ((controls.getRightDrive() - stickCalRight) * slowRamp.getRamp(Ramp.SIDE.RIGHT));
//        else
//            slowRamp.reset(Ramp.SIDE.LEFT);

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
