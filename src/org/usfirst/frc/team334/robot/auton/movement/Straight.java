package org.usfirst.frc.team334.robot.auton.movement;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;

public class Straight extends Command {
    
    private double distance;
    private Encoder enc;

    private boolean straightDone;

    private GyroPID gyroPID;
    private DriveTrain driveTrain;

    public Straight(double distance, DriveTrain driveTrain) {
        this.distance = distance;
        this.gyroPID = new GyroPID();
        this.driveTrain = driveTrain;

        // update
        enc = new Encoder(0,1);
    }

    // Called once at start of command
    public void initialize() {
        straightDone = false;

        gyroPID.getController().setSetpoint(0);

        double distancePerPulse = 0.1; // in feet
        enc.setDistancePerPulse(distancePerPulse);
        enc.reset();

        System.out.println("Initialized");
    }

    /*
     * Continues looping until isFinished returns true(non-Javadoc)
     *
     *  Steps:
     *      1) Set degrees to turn
     *      2) Turn to degrees
     *      3) Done
     */
    public void execute() {
            double speed = 0.4;

            if (distance >= enc.getDistance()) {
                straightDone = true;
            } else {
                driveTrain.setLeftMotors(speed + gyroPID.getOutput());
                driveTrain.setLeftMotors(speed - gyroPID.getOutput());
            }
        }

    // Stops program when returns true
    @Override
    protected boolean isFinished() {
            return straightDone;
        }

}
