package org.usfirst.frc.team334.robot.auton.pids;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.sources.GyroSource;
import org.usfirst.frc.team334.robot.controls.Constants;

public class GyroPID {

    private PIDController gyroPID;
    private GyroSource gyroSource;
    private DefaultOutput defaultOutput;

    public GyroPID() {
        gyroSource = new GyroSource();
        defaultOutput = new DefaultOutput();

        gyroPID = new PIDController(Constants.GYRO_P, Constants.GYRO_I, Constants.GYRO_D, gyroSource, defaultOutput);
        gyroPID.setContinuous();
        gyroPID.setOutputRange(-Constants.GYRO_CAP, Constants.GYRO_CAP);
        gyroPID.enable();

        SmartDashboard.putData("GryoPID", gyroPID);
    }

    /**
     * @return Gyro angle
     */
    public double getInput() {
        return gyroSource.pidGet();
    }

    /**
     * @return Values to set motors
     */
    public double getOutput() {
        return gyroPID.get();
    }

    public PIDController getController() {
        return gyroPID;
    }

    public void resetGyro() {
        gyroSource.getGyro().resetHeading();
//        gyroSource.getGyro().reset();
    }

}
