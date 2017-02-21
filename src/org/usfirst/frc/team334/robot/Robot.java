package org.usfirst.frc.team334.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.AutonScenario;
import org.usfirst.frc.team334.robot.auton.command_groups.GoToLeftPeg;
import org.usfirst.frc.team334.robot.auton.command_groups.GoToMiddlePeg;
import org.usfirst.frc.team334.robot.auton.command_groups.GoToRightPeg;
import org.usfirst.frc.team334.robot.components.VisionAutoAlign;
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
        // INIT COMPONENETS
        driveTrain = new DriveTrain();
        controls = new Controls();

        // INIT VISION
        VisionData.init();

        // INIT CAMERA
//        cameraSet = new CameraSet(controls, Constants.VIDEO_1, Constants.VIDEO_2);
//        cameraSet.enable();

        // INIT PIDS
        gyroPID = new GyroPID();
        areaPID = new VisionAreaPID();
        offsetPID = new VisionOffsetPID();

//        intake = new Intake();
//        indexer = new Indexer();
//        climber = new Climber();
        gear = new Gear();
//        shooter = new Shooter();
//        manualAutonSelect = new ManualAutonSelect();
        visionAutoAlign = new VisionAutoAlign(driveTrain, gyroPID, areaPID, offsetPID);

        // AUTON COMMAND GROUPS
        /**
         * @param drivetrain = For moving the robot
         * @param gyroPID = used to keep robot straight or turn
         * @paran visionAutoAlign = For vision
         */
        goToLeftPeg = new GoToLeftPeg(driveTrain, gyroPID, visionAutoAlign);
        goToRightPeg = new GoToRightPeg(driveTrain, gyroPID, visionAutoAlign);
        goToMiddlePeg = new GoToMiddlePeg(driveTrain, gyroPID, visionAutoAlign);

        // ADD OBJECTS TO SENDABLE CHOOSER
        autoChoose = new SendableChooser<>();
        autoChoose.addObject("Turn Left", AutonScenario.LEFT_SIDE);
        autoChoose.addObject("Turn Right", AutonScenario.RIGHT_SIDE);
        autoChoose.addObject("Go Straight", AutonScenario.MIDDLE);
        autoChoose.addDefault("Nothing", AutonScenario.NOTHING);
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

        switch (autonScenario) {
            case LEFT_SIDE:
                Scheduler.getInstance().add(goToLeftPeg);
                break;
            case RIGHT_SIDE:
                Scheduler.getInstance().add(goToRightPeg);
                break;
            case MIDDLE:
                Scheduler.getInstance().add(goToMiddlePeg);
                break;
        }
    }

    @Override
    public void autonomousPeriodic() {
        // RUN COMMANDS IF ANY
//        Scheduler.getInstance().run();

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
//        } else if (controls.getHoldGear()) {
//            gear.gripGear();
//        } else if (controls.getResetGear()) {
//            gear.resetServos();
//        }

        // DRIVETRAIN LISTENER
        if (controls.getAutoAlign(Target.GEAR)) {
            visionAutoAlign.setTarget(Target.GEAR);
            visionAutoAlign.autoAlign();
        }
        else if (controls.getAutoAlign(Target.BOILER)) {
            visionAutoAlign.setTarget(Target.BOILER);
            visionAutoAlign.autoAlign();
        }
        else {
            // joystick controlled
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
        }

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

    }

    @Override
    public void disabledPeriodic() {

    }

    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Gyro Angle", gyroPID.getInput());

        VisionData.displayData();
    }
}
