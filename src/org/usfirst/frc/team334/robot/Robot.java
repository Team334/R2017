package org.usfirst.frc.team334.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.AutonScenario;
import org.usfirst.frc.team334.robot.auton.commandgroups.*;

import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionAreaPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionOffsetPID;
import org.usfirst.frc.team334.robot.components.*;
import org.usfirst.frc.team334.robot.controls.Constants;
import org.usfirst.frc.team334.robot.controls.Controls;
import org.usfirst.frc.team334.robot.util.ManualAutonSelect;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class Robot extends IterativeRobot {

    private DriveTrain driveTrain;
    private double stickCalLeft;
    private double stickCalRight;

    private Controls controls;

    private BumperLedStrip bumper;

    private UsbCamera cam;

    private VisionAutoAlign visionAutoAlign;

    private GyroPID gyroPID;
    private VisionAreaPID areaPID;
    private VisionOffsetPID offsetPID;

    private Indexer indexer;
    private ClimberAndIntake climberIntake;
    private Gear gear;
    private Shooter shooter;
    private ManualAutonSelect manualAutonSelect;

    private GoToLeftPeg goToLeftPeg;
    private GoToRightPeg goToRightPeg;
    private GoToMiddlePeg goToMiddlePeg;
    private LeftNoGear leftNoGear;
    private RightNoGear rightNoGear;

    private SendableChooser<AutonScenario> autoChoose;
    private AutonScenario autonScenario;

    @Override
    public void robotInit() {
        // INIT COMPONENTS
        driveTrain = new DriveTrain();
        controls = new Controls();

        // INIT TEAM LEDS
        bumper = new BumperLedStrip(DriverStation.getInstance().getAlliance());

        // INIT VISION
        VisionData.init();

        // INIT CAMERA
//        this.cam = CameraServer.getInstance().startAutomaticCapture("cam", "/dev/video0");
//        this.cam.setResolution(320, 240);
//        this.cam.setFPS(24);

        // INIT PIDS
        gyroPID = new GyroPID();
        areaPID = new VisionAreaPID();
        offsetPID = new VisionOffsetPID();

        climberIntake = new ClimberAndIntake(controls);
        indexer = new Indexer();
        gear = new Gear();
        shooter = new Shooter();
//        visionAutoAlign = new VisionAutoAlign(driveTrain, gyroPID, areaPID, offsetPID);
//        manualAutonSelect = new ManualAutonSelect();
//
//        // AUTON COMMAND GROUPS
//        goToLeftPeg = new GoToLeftPeg(driveTrain, gyroPID, visionAutoAlign, gear);
//        goToRightPeg = new GoToRightPeg(driveTrain, gyroPID, visionAutoAlign, gear);
//        goToMiddlePeg = new GoToMiddlePeg(driveTrain, gyroPID, visionAutoAlign, gear);
//        leftNoGear = new LeftNoGear(driveTrain, gyroPID);
//        rightNoGear = new RightNoGear(driveTrain, gyroPID);
//
//        // ADD OBJECTS TO SENDABLE CHOOSER
//        autoChoose = new SendableChooser<>();
//        autoChoose.addDefault("Manual", AutonScenario.MANUAL);
//        autoChoose.addObject("Turn Left", AutonScenario.LEFT_SIDE);
//        autoChoose.addObject("Turn Right", AutonScenario.RIGHT_SIDE);
//        autoChoose.addObject("Go Straight", AutonScenario.MIDDLE);
//        autoChoose.addObject("LeftNoGear", AutonScenario.LEFT_NO_GEAR);
//        autoChoose.addObject("RightNoGear", AutonScenario.RIGHT_NO_GEAR);
//        autoChoose.addObject("Nothing", AutonScenario.NOTHING);
//        SmartDashboard.putData("Choose Auton Mode", autoChoose);
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void autonomousInit() {
        // Assume Vision is not initialized
        // Will update to true if initialized successfully
        VisionData.getNt().putBoolean("running", false);

        bumper.setTeam();

        // Clear old commands
        Scheduler.getInstance().removeAll();

//        autonScenario = autoChoose.getSelected();
////        autonScenario = AutonScenario.MIDDLE;
//
//        if (autonScenario == AutonScenario.MANUAL) {
//            autonScenario = manualAutonSelect.getScenario();
//        }
//
//        switch (autonScenario) {
//            case LEFT_SIDE:
//                System.out.println("LEFT AUTON");
//                Scheduler.getInstance().add(goToLeftPeg);
//                break;
//            case RIGHT_SIDE:
//                System.out.println("RIGHT AUTON");
//                Scheduler.getInstance().add(goToRightPeg);
//                break;
//            case MIDDLE:
//                System.out.println("MIDDLE AUTON");
//                Scheduler.getInstance().add(goToMiddlePeg);
//                break;
//            case LEFT_NO_GEAR:
//                System.out.println("LEFT STRAIGHT, TURN ONLY");
//                Scheduler.getInstance().add(leftNoGear);
//                break;
//            case RIGHT_NO_GEAR:
//                System.out.println("RIGHT STRAIGHT, TURN ONLY");
//                Scheduler.getInstance().add(rightNoGear);
//                break;
//        }
    }

    @Override
    public void autonomousPeriodic() {
        // RUN COMMANDS IF ANY
        Scheduler.getInstance().run();

        updateSmartDashboard();
    }

    private double shooterSpeed = 0.7;
    private double indexerSpeed = 0.8;
    private double intakeSpeed = 0.55;
    private double indexerRollerSpeed = 0.5;

    @Override
    public void teleopInit() {
        VisionData.getNt().putBoolean("running", false);

        bumper.setTeam();

        gyroPID.resetGyro();

        // Zeroes joysticks at the beginning
        stickCalLeft = controls.getLeftDrive();
        stickCalRight = controls.getRightDrive();

        // Test component speeds with SmartDashboard
        SmartDashboard.putNumber("Shooter Speed", shooterSpeed);
        SmartDashboard.putNumber("Indexer Speed", indexerSpeed);
        SmartDashboard.putNumber("Intake Speed", intakeSpeed);
    }

    @Override
    public void teleopPeriodic() {
       subsystemsListener();

//        // DRIVETRAIN LISTENER
//        if (controls.getAutoAlign(Target.GEAR)) {
//            visionAutoAlign.setTarget(Target.GEAR);
//            visionAutoAlign.autoAlign();
//        }
//        else if (controls.getAutoAlign(Target.BOILER)) {
//            visionAutoAlign.setTarget(Target.BOILER);
//            visionAutoAlign.autoAlign();
//        }
//        else {
            // JOYSTICK LISTENER
            double leftSpeed = controls.getLeftDrive() - stickCalLeft;
            double rightSpeed = controls.getRightDrive() - stickCalRight;

            if (controls.getSlowRamp()) {
                double sens = 1 + Math.abs(controls.getLeftDrive() - controls.getRightDrive());
                leftSpeed /= sens * Constants.DRIVE_SLOW_FACTOR;
                rightSpeed /= sens * Constants.DRIVE_SLOW_FACTOR;
            }

            double slow = 1;
            driveTrain.setLeftMotors(leftSpeed * slow);
            driveTrain.setRightMotors(rightSpeed * slow);
//        }

        updateSmartDashboard();
    }

    @Override
    public void testInit() {

    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void disabledInit() {
        bumper.setBrown();
    }

    @Override
    public void disabledPeriodic() {

    }

    private void subsystemsListener() {
        shooterSpeed = SmartDashboard.getNumber("Shooter Speed", 0);
        indexerSpeed = SmartDashboard.getNumber("Indexer Speed", 0);
        intakeSpeed = SmartDashboard.getNumber("Intake Speed", 0);
        indexerRollerSpeed = SmartDashboard.getNumber("IntakeRoller Speed", 0);

        // CLIMBER LISTENER
        if (controls.getClimbUp() && !controls.getClimbDown()) {
            System.out.println("CLIMBING UP");
            climberIntake.climbUp();
        } else if (controls.getClimbDown() && !controls.getClimbUp()) {
            System.out.println("CLIMBING DOWN");
            climberIntake.climbDown();
        } else {
            climberIntake.stop();
        }

        // INTAKE LISTENER
        if (controls.getIntakeIn() && !controls.getIntakeOut()) {
            System.out.println("INTAKE");
            climberIntake.intakeIn();
        } else if (controls.getIntakeOut() && !controls.getIntakeIn()) {
            climberIntake.intakeOut();
        }

        // INDEXER LISTENER
        if (controls.getIndexerIn()) {
            System.out.println("INDEXING");
//            indexer.pushIntoShooter(indexerSpeed, indexerRollerSpeed);
            indexer.pushIntoShooter(Constants.INDEXER_BELT_SPEED, Constants.INDEXER_ROLLER_SPEED);
        } else {
            indexer.stop();
        }

        // SHOOTER LISTENER
        if (controls.getShoot()) {
            System.out.println("SHOOTING");
//            shooter.setShooterSpeed(shooterSpeed);
            shooter.setShooterSpeed(Constants.SHOOTER_SPEED + controls.getShooterSpeedAdjustment());
        } else {
            shooter.setShooterSpeed(0);
        }
//
//        // GEAR LISTENER TOGGLE
////        if (controls.getMoveGear()) {
////            System.out.println("GEAR");
////            if (gear.isGearOut()) {
////                gear.resetServos();
////            } else {
////                gear.pushOutGear();
////            }
////        }

        // GEAR LISTENER HOLD
        if (controls.getOutGear()) {
            System.out.println("GEAR");
            gear.pushOutGear();
        } else {
            gear.resetServos();
        }
    }

    private void updateSmartDashboard() {
        SmartDashboard.putNumber("Gyro Angle", gyroPID.getInput());
        SmartDashboard.putNumber("Hall Effect Rate", shooter.getHallEffectRate());
        SmartDashboard.putNumber("Distance Traveled", driveTrain.getDistanceTraveled());

        VisionData.displayData();
    }
}
