package org.usfirst.frc.team334.robot.auton.pids;

import edu.wpi.first.wpilibj.PIDController;
import org.usfirst.frc.team334.robot.auton.sources.GyroSource;

public class GyroPID {

    private PIDController gyroPID;
    private GyroSource gyroSource;
    private DefaultOutput defaultOutput;

    public GyroPID() {
        gyroSource = new GyroSource();
        defaultOutput = new DefaultOutput();

        double gyroCap = 0.3;
        double gyroP = 0.015;
        double gyroI = 0.0;
        double gyroD = 0.0;

        gyroPID = new PIDController(gyroP, gyroI, gyroD, gyroSource, defaultOutput);
        gyroPID.setContinuous();
        gyroPID.setOutputRange(-gyroCap, gyroCap);
        gyroPID.enable();
    }

}
