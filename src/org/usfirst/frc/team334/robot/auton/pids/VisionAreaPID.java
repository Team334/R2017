package org.usfirst.frc.team334.robot.auton.pids;

import edu.wpi.first.wpilibj.PIDController;
import org.usfirst.frc.team334.robot.auton.sources.VisionAreaSource;

public class VisionAreaPID {
    private PIDController areaPID;
    private VisionAreaSource visionAreaSource;
    private DefaultOutput defaultOutput;

    public VisionAreaPID() {
        visionAreaSource = new VisionAreaSource();
        defaultOutput = new DefaultOutput();
        double area_cap = 0.15;
        double areaP = 0.01;
        double areaI = 0.0;
        double areaD = 0.0;

        areaPID = new PIDController(areaP, areaI, areaD, visionAreaSource, defaultOutput);
        areaPID.setContinuous();
        areaPID.setOutputRange(-area_cap, area_cap);
        areaPID.enable();
    }
}
