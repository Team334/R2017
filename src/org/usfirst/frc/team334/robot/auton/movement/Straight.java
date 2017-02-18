package org.usfirst.frc.team334.robot.auton.movement;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;

public class Straight extends Command {
    
    private double distance;
    private Encoder enc;

    private boolean straightDone;

    private GyroPID gyroPID;
    private DriveTrain driveTrain;

    public Straight(double distance, DriveTrain driveTrain, GyroPID gyroPID) {
        this.distance = distance;
        this.gyroPID = gyroPID;
        this.driveTrain = driveTrain;

        // update
        enc = new Encoder(0,1);
    }

    // Called once at start of command
    public void initialize() {
        straightDone = false;

        gyroPID.getController().setSetpoint(0);

        final double DOUBLE_PER_PULSE = 0.1; // in feet
        enc.setDistancePerPulse(DOUBLE_PER_PULSE);
        enc.reset();
    }

    /**
     * Continues looping until isFinished returns true(non-Javadoc)
     * Go straight until you reach distance
     */
    public void execute() {
        SmartDashboard.putString("Mode", "STRAIGHT");

        double speed = 0.4;
        double leftSpeed = 0, rightSpeed = 0;
        if (distance >= enc.getDistance()) {
            straightDone = true;
        } else {
            leftSpeed = speed + gyroPID.getOutput();
            rightSpeed = speed - gyroPID.getOutput();
        }
//        driveTrain.setLeftMotors(leftSpeed);
//        driveTrain.setRightMotors(rightSpeed);
    }

    // Stops program when returns true
    @Override
    protected boolean isFinished() {
            return straightDone;
        }

}
