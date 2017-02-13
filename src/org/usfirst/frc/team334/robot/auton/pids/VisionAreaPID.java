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
        double areaCap = 0.15;
        double areaP = 0.01;
        double areaI = 0.0;
        double areaD = 0.0;

        areaPID = new PIDController(areaP, areaI, areaD, visionAreaSource, defaultOutput);
        areaPID.setContinuous();
        areaPID.setOutputRange(-areaCap, areaCap);
        areaPID.enable();
    }

    public double getInput() {
        return visionAreaSource.pidGet();
    }

    public double getOutput() {
        return areaPID.get();
    }

    public PIDController getController() {
        return areaPID;
    }

}
