package org.usfirst.frc.team334.robot.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;

public class Turn extends Command {

    private double angle;

    private GyroPID gyroPID;
    private DriveTrain driveTrain;

    public Turn(double angle, DriveTrain driveTrain, GyroPID gyroPID) {
        requires(driveTrain);

        this.angle = angle;
        this.gyroPID = gyroPID;
        this.driveTrain = driveTrain;

        setTimeout(2);
    }

    // Called once at start of command
    public void initialize() {
        gyroPID.getController().setSetpoint(angle);
        gyroPID.getController().setAbsoluteTolerance(angle * .05); // 5% tolerance
    }

    /**
     *  Continues looping until isFinished returns true(non-Javadoc)
     *
     *  Steps:
     *      1) Set degrees to turn
     *      2) Turn to degrees
     *      3) Done
     */
    public void execute() {
        SmartDashboard.putString("Mode", "TURN");

        System.out.println("TURN" + gyroPID.getOutput() + "setpoint " + gyroPID.getController().getSetpoint());
        double speed = 0.0;

        double leftSpeed = speed + gyroPID.getOutput();
        double rightSpeed = speed - gyroPID.getOutput();

        driveTrain.setLeftMotors(leftSpeed);
        driveTrain.setRightMotors(rightSpeed);
    }

    // Stops program when returns true
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        driveTrain.stop();
    }

}

