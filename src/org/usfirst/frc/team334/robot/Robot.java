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
import org.usfirst.frc.team334.robot.controls.Constants;
import org.usfirst.frc.team334.robot.controls.Controls;
import org.usfirst.frc.team334.robot.util.ManualAutonSelect;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class Robot extends IterativeRobot {
    // COMPONENTS
    private DriveTrain driveTrain;
    private double stickCalLeft;
    private double stickCalRight;

    private Controls controls;
    private Intake intake;
    private Indexer indexer;
    private Climber climber;
    private Gear gear;
    private Shooter shooter;
    private VisionAutoAlign visionAutoAlign;
    private CameraSet cameraSet;

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

    @Override
    public void robotInit() {
        // INIT VISION
        VisionData.init();

        // INIT COMPONENETS
        driveTrain = new DriveTrain();
        controls = new Controls();

        // INIT PIDS
        gyroPID = new GyroPID();
        areaPID = new VisionAreaPID();
        offsetPID = new VisionOffsetPID();

//        // UPDATE PORTS AND VALUES
//        intake = new Intake();
//        indexer = new Indexer();
//        climber = new Climber();
//        gear = new Gear();
//        shooter = new Shooter();
        visionAutoAlign = new VisionAutoAlign(driveTrain, gyroPID, areaPID, offsetPID);

        cameraSet = new CameraSet(controls, Constants.VIDEO_1, Constants.VIDEO_2);
        cameraSet.enable();
//
//        fastRamp = new Ramp(10);
//        slowRamp = new Ramp(50);
//
        // AUTON COMMAND
        turnLeft = new Turn(-Constants.ANGLE_TO_PEG, driveTrain, gyroPID);
        turnRight = new Turn(Constants.ANGLE_TO_PEG, driveTrain, gyroPID);
        straight = new Straight(Constants.DISTANCE_TO_BASELINE, driveTrain, gyroPID);
        visionGear = new VisionAuton(visionAutoAlign, Target.GEAR);
        visionBoiler = new VisionAuton(visionAutoAlign, Target.BOILER);
        // manualAutonSelect = new ManualAutonSelect();

        // ADD OBJECTS TO SENDABLE CHOOSER
        autoChoose = new SendableChooser<>();
        autoChoose.addObject("Turn Left", AutonScenario.LEFT_SIDE);
        autoChoose.addObject("Turn Right", AutonScenario.RIGHT_SIDE);
        autoChoose.addDefault("Go Straight", AutonScenario.MIDDLE);
        // autoChoose.addDefault("Default", AutonScenario.MANUAL);
        SmartDashboard.putData("Choose Auton Mode", autoChoose);
    }

    @Override
    public void robotPeriodic() {

    }

    @Override
    public void autonomousInit() {
        // Vision code will update to true if it initialized successfully
        VisionData.getNt().putBoolean("running", false);

        // reset gyro
        GyroSource.imu.resetHeading();

        // clear old commands
        Scheduler.getInstance().removeAll();

        autonScenario = autoChoose.getSelected();

//        if (autonScenario == AutonScenario.MANUAL) {
//            autonScenario = manualAutonSelect.getScenario();
//        }

        System.out.println("Auton Scenario " + autonScenario);
        switch (autonScenario) {
            case LEFT_SIDE:
                break;
            case RIGHT_SIDE:
                break;
            case MIDDLE:
                break;
        }
        System.out.println("AUTON INIT");
    }

    @Override
    public void autonomousPeriodic() {
        // RUN COMMANDS IF ANY
        Scheduler.getInstance().run();

        updateSmartDashboard();
    }

    @Override
    public void teleopInit() {
        // Vision code will update to true if it initialized successfully
        VisionData.getNt().putBoolean("running", false);

        stickCalLeft = controls.getLeftDrive();
        stickCalRight = controls.getRightDrive();
    }

    @Override
    public void teleopPeriodic() {
//        // CLIMBER LISTENER
//        if (controls.getClimbUp() && !controls.getClimbDown()) {
//            climber.climbUp();
//        } else if (controls.getClimbDown() && !controls.getClimbUp()) {
//            climber.climbDown();
//        } else {
//            climber.stop();
//        }
//
//        // INTAKE LISTENER
//        if (controls.getIntakeIn()) {
//            intake.pull_in();
//        } else {
//            intake.stop();
//        }
//
//        // INTAKE LISTENER
//        if (controls.getIndexerIn()) {
//            indexer.pushIntoShooter();
//        } else {
//            indexer.stop();
//        }
//
//        // SHOOTER LISTENER
//        if (controls.getShoot()) {
//            shooter.setShooterSpeed(0.6);
//        } else {
//            shooter.setShooterSpeed(0);
//        }
//
//        // GEAR LISTENER
//        if (controls.getGearOut()) {
//            gear.pushOutGear();
//        } else {
//            gear.resetServos();
//        }

        // DRIVETRAIN LISTENER
        if (controls.getAutoAlign(Target.GEAR)) {
            visionAutoAlign.setTarget(Target.GEAR);
            visionAutoAlign.autoAlign();
        } else if (controls.getAutoAlign(Target.BOILER)) {
            visionAutoAlign.setTarget(Target.BOILER);
            visionAutoAlign.autoAlign();
        } else {
            // joystick controlled
            double leftSpeed = controls.getLeftDrive() - stickCalLeft;
            double rightSpeed = controls.getRightDrive() - stickCalRight;
            // slow down when turning
            if (controls.getSlowRamp(Ramp.SIDE.LEFT) && controls.getSlowRamp(Ramp.SIDE.RIGHT)) {
                double sens = 1 + Math.abs(controls.getLeftDrive() - controls.getRightDrive());
                leftSpeed /= sens * Constants.SLOW_FACTOR;
                rightSpeed /= sens * Constants.SLOW_FACTOR;
            }
            driveTrain.setLeftMotors(leftSpeed);
            driveTrain.setRightMotors(rightSpeed);
        }

        updateSmartDashboard();

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

    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Area", areaPID.getInput());
        SmartDashboard.putNumber("Offset", VisionData.getOffset());
        SmartDashboard.putNumber("Vision angle", VisionData.getAngle());

        SmartDashboard.putBoolean("Running", VisionData.visionRunning());
        SmartDashboard.putBoolean("Found Target", VisionData.foundTarget());

        SmartDashboard.putNumber("Gyro Angle", gyroPID.getInput());
    }
}
