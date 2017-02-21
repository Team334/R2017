package org.usfirst.frc.team334.robot.auton.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;

public class Straight extends Command {

    private Encoder enc;

    private boolean straightDone;

    private GyroPID gyroPID;
    private DriveTrain driveTrain;

    public Straight(DriveTrain driveTrain, GyroPID gyroPID) {
        requires(driveTrain);

        this.gyroPID = gyroPID;
        this.driveTrain = driveTrain;

//        enc = new Encoder(Constants.ENCODER_1, Constants.ENCODER_2);
//        enc.setDistancePerPulse(Constants.DISTANCE_PER_PULSE);
//        enc.reset();

        setTimeout(1);
        straightDone = false;
    }

    // Called once at start of command
    public void initialize() {
        gyroPID.getController().setSetpoint(0);
    }

    /**
     * Continues looping until isFinished returns true(non-Javadoc)
     * Go straight until you reach distance
     */
    public void execute() {
        SmartDashboard.putString("Mode", "STRAIGHT");

        System.out.println("STRAIGHT");
        double speed = 0.4;
        double leftSpeed = 0, rightSpeed = 0;
//        if (enc.getDistance() <= Constants.DISTANCE_TO_BASELINE) {
//            straightDone = true;
//        } else {

        leftSpeed = speed + gyroPID.getOutput();
        rightSpeed = speed - gyroPID.getOutput();

        System.out.println("gyro pid" + gyroPID.getOutput());
        //}

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
