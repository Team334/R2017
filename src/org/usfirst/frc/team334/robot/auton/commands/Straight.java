package org.usfirst.frc.team334.robot.auton.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Straight extends Command {

    private double distance;
    private boolean straightDone;

    private GyroPID gyroPID;
    private DriveTrain driveTrain;

    public Straight(double distance, DriveTrain driveTrain, GyroPID gyroPID) {
        requires(driveTrain);

        this.distance = distance;

        this.gyroPID = gyroPID;
        this.driveTrain = driveTrain;

        setTimeout(2.5);
        straightDone = false;
    }

    // Called once at start of command
    public void initialize() {
        gyroPID.getController().setSetpoint(0);
    }

    /**
     * Continues looping until isFinished returns true(non-Javadoc)
     * Go straight until you reach distance or time
     */
    public void execute() {
        SmartDashboard.putString("Mode", "STRAIGHT");
        System.out.println("STRAIGHT");

        if (driveTrain.getDistanceTraveled() >= distance) {
            straightDone = true;
            return;
        }

        double speed = 0.4;

        double leftSpeed = speed + gyroPID.getOutput();
        double rightSpeed = speed - gyroPID.getOutput();

        driveTrain.setLeftMotors(leftSpeed);
        driveTrain.setRightMotors(rightSpeed);
    }

    // Stops program when returns true
    @Override
    protected boolean isFinished() {
        return isTimedOut() || straightDone;
    }

    protected void end() {
        driveTrain.stop();
    }
}
