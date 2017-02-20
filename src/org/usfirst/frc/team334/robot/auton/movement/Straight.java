package org.usfirst.frc.team334.robot.auton.movement;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;
import org.usfirst.frc.team334.robot.controls.Constants;
import edu.wpi.first.wpilibj.Timer;

public class Straight extends Command {
    
    private double distance;
    private boolean straightDone;

    private Timer time;
    int straightTime = 3;

    private Encoder enc;

    private GyroPID gyroPID;
    private DriveTrain driveTrain;

    public Straight(double distance, DriveTrain driveTrain, GyroPID gyroPID) {
        this.distance = distance;
        this.gyroPID = gyroPID;
        this.driveTrain = driveTrain;

        enc = new Encoder(Constants.ENCODER_1, Constants.ENCODER_2);
    }

    // Called once at start of command
    public void initialize() {
        straightDone = false;

        gyroPID.getController().setSetpoint(0);

        enc.setDistancePerPulse(Constants.DISTANCE_PER_PULSE);
        enc.reset();

        time = new Timer();
    }

    /**
     * Continues looping until isFinished returns true(non-Javadoc)
     * Go straight until you reach distance
     */
    public void execute() {
        SmartDashboard.putString("Mode", "STRAIGHT");
        if (time.get() > straightTime) {
            straightDone = true;
        }
        System.out.println("STRAIGHT");
//        double speed = 0.4;
//        double leftSpeed = 0, rightSpeed = 0;
//        if (enc.getDistance() <= distance) {
//            driveTrain.stop();
//            straightDone = true;
//        } else {
//            leftSpeed = speed + gyroPID.getOutput();
//            rightSpeed = speed - gyroPID.getOutput();
//        }

//        driveTrain.setLeftMotors(leftSpeed);
//        driveTrain.setRightMotors(rightSpeed);
    }

    // Stops program when returns true
    @Override
    protected boolean isFinished() {
            return straightDone;
        }

}
