package org.usfirst.frc.team334.robot.auton.pids;

import edu.wpi.first.wpilibj.PIDController;
import org.usfirst.frc.team334.robot.auton.sources.GyroSource;
import org.usfirst.frc.team334.robot.auton.sources.VisionOffsetSource;

public class VisionOffsetPID {

    private PIDController offsetPID;
    private VisionOffsetSource visionOffsetSource;
    private DefaultOutput defaultOutput;

    public VisionOffsetPID() {
        visionOffsetSource = new VisionOffsetSource();
        defaultOutput = new DefaultOutput();
        double offsetCap = 0.3;
        double offsetP = 0.0008;
        double offsetI = 0.0;
        double offsetD = 0.0001;

        offsetPID = new PIDController(offsetP, offsetI, offsetD, visionOffsetSource, defaultOutput);
        offsetPID.setContinuous();
        offsetPID.setOutputRange(-offsetCap, offsetCap);
        offsetPID.enable();
    }

}
