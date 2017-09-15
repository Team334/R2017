package org.usfirst.frc.team334.robot.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;
import org.usfirst.frc.team334.robot.controls.Constants;

public class
Straight extends Command {

    private double distance;
    private double direction;
    private boolean straightDone;

    private GyroPID gyroPID;
    private DriveTrain driveTrain;

    public Straight(double distance, double time, DriveTrain driveTrain, GyroPID gyroPID) {
        requires(driveTrain);

        this.distance = distance;
        this.direction = (distance < 0) ? -1 : 1; // -1 is backwards, 1 is forward
        this.gyroPID = gyroPID;
        this.driveTrain = driveTrain;

        setTimeout(time);
        straightDone = false;
    }

    // Called once at start of command
    public void initialize() {
        gyroPID.getController().setSetpoint(0);
        gyroPID.resetGyro();
        driveTrain.resetEncoders();
    }

    /**
     * Continues looping until isFinished returns true(non-Javadoc)
     * Go straight until you reach distance or time
     */
    public void execute() {
        SmartDashboard.putString("Mode", "STRAIGHT");
//        System.out.println("STRAIGHT");

        double speed = Constants.STRAIGHT_SPEED * direction;

//        double leftSpeed = speed - gyroPID.getOutput();
        double leftSpeed = speed;
        double rightSpeed = speed + gyroPID.getOutput();

        driveTrain.setLeftMotors(leftSpeed);
        driveTrain.setRightMotors(rightSpeed);

        // stop if traveled distance
        straightDone = Math.abs(driveTrain.getDistanceTraveled()) >= Math.abs(distance);
        SmartDashboard.putBoolean("EncoderStoppedStraight", straightDone);
        SmartDashboard.putBoolean("TimeStoppedStraight", isTimedOut());
    }

    // Stops program when returns true
    @Override
    protected boolean isFinished() {
        return isTimedOut(); // || straightDone;
    }

    protected void end() {
        driveTrain.stop();
    }
}
