package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.AutonScenario;
import org.usfirst.frc.team334.robot.auton.commandgroups.GoToLeftPeg;
import org.usfirst.frc.team334.robot.auton.commandgroups.GoToMiddlePeg;
import org.usfirst.frc.team334.robot.auton.commandgroups.GoToRightPeg;

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

    private ManualAutonSelect manualAutonSelect;

    private BumperLedStrip bumper;

    private VisionAutoAlign visionAutoAlign;
    private CameraSet cameraSet;

    // PIDS
    private GyroPID gyroPID;
    private VisionAreaPID areaPID;
    private VisionOffsetPID offsetPID;

    // AUTON COMMANDS
    private GoToLeftPeg goToLeftPeg;
    private GoToRightPeg goToRightPeg;
    private GoToMiddlePeg goToMiddlePeg;

    private SendableChooser<AutonScenario> autoChoose;
    private AutonScenario autonScenario;


    @Override
    public void robotInit() {
        // INIT COMPONENTS
        driveTrain = new DriveTrain();
        controls = new Controls();

        // INIT TEAM LEDS
        bumper = new BumperLedStrip(DriverStation.getInstance().getAlliance());

        // INIT LEDS
        // leds.neoPixelRing(0,23,10,0,255,0);

        // INIT VISION
        VisionData.init();

        // INIT CAMERA
//        cameraSet = new CameraSet(controls, Constants.VIDEO_1, Constants.VIDEO_2);
//        cameraSet.enable();

        // INIT PIDS
        gyroPID = new GyroPID();
        areaPID = new VisionAreaPID();
        offsetPID = new VisionOffsetPID();

        climber = new Climber(controls);
        intake = new Intake();
        indexer = new Indexer();
        gear = new Gear();
        shooter = new Shooter();
//        manualAutonSelect = new ManualAutonSelect();
        visionAutoAlign = new VisionAutoAlign(driveTrain, gyroPID, areaPID, offsetPID);

        // AUTON COMMAND GROUPS
        goToLeftPeg = new GoToLeftPeg(driveTrain, gyroPID, visionAutoAlign, gear);
        goToRightPeg = new GoToRightPeg(driveTrain, gyroPID, visionAutoAlign, gear);
        goToMiddlePeg = new GoToMiddlePeg(driveTrain, gyroPID, visionAutoAlign, gear);

        // ADD OBJECTS TO SENDABLE CHOOSER
        autoChoose = new SendableChooser<>();
        autoChoose.addObject("Turn Left", AutonScenario.LEFT_SIDE);
        autoChoose.addObject("Turn Right", AutonScenario.RIGHT_SIDE);
        autoChoose.addObject("Go Straight", AutonScenario.MIDDLE);
        autoChoose.addDefault("Nothing", AutonScenario.NOTHING);
        // autoChoose.addDefault("Default", AutonScenario.MANUAL);
        SmartDashboard.putData("Choose Auton Mode", autoChoose);

//        encLeft = new Encoder(Constants.ENCODER_LEFT_1, Constants.ENCODER_LEFT_2);
//        encRight = new Encoder(Constants.ENCODER_RIGHT_1, Constants.ENCODER_RIGHT_2);
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void autonomousInit() {
//        // Assume Vision is not running
//        VisionData.getNt().putBoolean("running", false);
//
        // Reset gyro
        GyroSource.imu.resetHeading();
        System.out.println("IMU AT " + GyroSource.imu.getHeading());
        for (int i = 0; i < 50; i++)
            System.out.println("started");
//
//        bumper.setTeam();
//
        // Clear old commands
        Scheduler.getInstance().removeAll();
//
//        autonScenario = autoChoose.getSelected();
//
////        if (autonScenario == AutonScenario.MANUAL) {
////            autonScenario = manualAutonSelect.getScenario();
////        }
//
//        switch (autonScenario) {
//            case LEFT_SIDE:
//                Scheduler.getInstance().add(goToLeftPeg);
//                break;
////            case RIGHT_SIDE:
////                Scheduler.getInstance().add(goToRightPeg);
////                break;
////            case MIDDLE:
////                Scheduler.getInstance().add(goToMiddlePeg);
////                break;
//        }
        goToLeftPeg.start();
//        goToRightPeg.start();
//        goToMiddlePeg.start();
    }

    @Override
    public void autonomousPeriodic() {
        // RUN COMMANDS IF ANY
        Scheduler.getInstance().run();

//        driveTrain.setLeftMotors(.5);
//        driveTrain.setRightMotors(.5);
        updateSmartDashboard();
    }

    double shooterSpeed = 0.7;
    double indexerSpeed = 0.8;
    double intakeSpeed = 0.55;

    @Override
    public void teleopInit() {
        // Vision code will update to true if it initialized successfully
        VisionData.getNt().putBoolean("running", false);

        bumper.setTeam();

        stickCalLeft = controls.getLeftDrive();
        stickCalRight = controls.getRightDrive();

        SmartDashboard.putNumber("Shooter Speed", shooterSpeed);
        SmartDashboard.putNumber("Indexer Speed", indexerSpeed);
        SmartDashboard.putNumber("Intake Speed", intakeSpeed);
    }

    @Override
    public void teleopPeriodic() {

        shooterSpeed = SmartDashboard.getNumber("Shooter Speed", 0);
        System.out.println("Shooter Speed = " + shooterSpeed);
        indexerSpeed = SmartDashboard.getNumber("Indexer Speed", 0);
        System.out.println("Indexer Speed = " + indexerSpeed);
        intakeSpeed = SmartDashboard.getNumber("Intake Speed", intakeSpeed);
        System.out.println("Intake Speed = " + intakeSpeed);

        // CLIMBER LISTENER
        if (controls.getClimbUp() && !controls.getClimbDown()) {
            System.out.println("CLIMBING UP");
            climber.climbUp();
        } else if (controls.getClimbDown() && !controls.getClimbUp()) {
            System.out.println("CLIMBING DOWN");
            climber.climbDown();
        } else {
            climber.stop();
        }

        // INTAKE LISTENER
        if (controls.getIntakeIn()) {
            System.out.println("INTAKING");
//            intake.pullIn(intakeSpeed);
            intake.pullIn(Constants.INTAKE_SPEED);
        } else {
            intake.stop();
        }

        // INDEXER LISTENER
        if (controls.getIndexerIn()) {
            System.out.println("INDEXING");
//            indexer.pushIntoShooter(indexerSpeed);
            indexer.pushIntoShooter(Constants.INDEXER_SPEED);
        } else {
            indexer.stop();
        }

        // SHOOTER LISTENER
        if (controls.getShoot()) {
            System.out.println("SHOOTING");
//            shooter.setShooterSpeed(shooterSpeed);
            shooter.setShooterSpeed(Constants.SHOOTER_SPEED);
        } else {
            shooter.setShooterSpeed(0);
        }

        // GEAR LISTENER
        if (controls.getMoveGear()) {
            System.out.println("GEAR");
            if (gear.isGearOut()) {
                gear.resetServos();
            } else {
                gear.pushOutGear();
            }
        }

        // DRIVETRAIN LISTENER
//        if (controls.getAutoAlign(Target.GEAR)) {
//            visionAutoAlign.setTarget(Target.GEAR);
//            visionAutoAlign.autoAlign();
//        }
//        else if (controls.getAutoAlign(Target.BOILER)) {
//            visionAutoAlign.setTarget(Target.BOILER);
//            visionAutoAlign.autoAlign();
//        }
//        else {
            // joystick controlled
        if (controls.getChangeDriveDirection()) {
            driveTrain.invertDirection();
        }

        double leftSpeed = controls.getLeftDrive() - stickCalLeft;
        double rightSpeed = controls.getRightDrive() - stickCalRight;
        // slow ramp
        if (controls.getSlowRamp(Ramp.SIDE.LEFT) && controls.getSlowRamp(Ramp.SIDE.RIGHT)) {
            double sens = 1 + Math.abs(controls.getLeftDrive() - controls.getRightDrive());
            leftSpeed /= sens * Constants.SLOW_FACTOR;
            rightSpeed /= sens * Constants.SLOW_FACTOR;
        }

        driveTrain.setLeftMotors(leftSpeed);
        driveTrain.setRightMotors(rightSpeed);
//        }

        updateSmartDashboard();
////        SmartDashboard.putNumber("EncLeft Pulses", encLeft.get());
////        SmartDashboard.putNumber("EncRight Pulses", encRight.get());
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

    private void updateSmartDashboard() {
        SmartDashboard.putNumber("Gyro Angle", gyroPID.getInput());
        SmartDashboard.putNumber("Hall Effect Rate", shooter.getHallEffectRate());
        SmartDashboard.putNumber("Distance Traveled", driveTrain.getDistanceTraveled());

        VisionData.displayData();
    }
}
