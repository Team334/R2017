package org.usfirst.frc.team334.robot.auton.movement;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;

public class Turn extends Command {

    private double angle;
    private boolean turnDone;

    private GyroPID gyroPID;
    private DriveTrain driveTrain;

    public Turn(double angle, DriveTrain driveTrain) {
        this.angle = angle;
        this.gyroPID = new GyroPID();
        this.driveTrain = driveTrain;
    }

    // Called once at start of command
    public void initialize() {
        turnDone = false;

        gyroPID.getController().setSetpoint(angle);
        gyroPID.getController().setAbsoluteTolerance(angle * .05); // 5% tolerance
    }

    /*
     *  Continues looping until isFinished returns true(non-Javadoc)
     *
     *  Steps:
     *      1) Set degrees to turn
     *      2) Turn to degrees
     *      3) Done
     */
    public void execute() {
        System.out.println("TURN");
        double speed = 0.1;
        double leftSpeed = 0, rightSpeed = 0;
        if (gyroPID.getController().onTarget()) {
            turnDone = true;
        } else {
            leftSpeed = speed + gyroPID.getOutput();
            rightSpeed = speed - gyroPID.getOutput();
        }
        driveTrain.setLeftMotors(leftSpeed);
        driveTrain.setRightMotors(rightSpeed);
    }

    // Stops program when returns true
    @Override
    protected boolean isFinished() {
        return turnDone;
    }

}

