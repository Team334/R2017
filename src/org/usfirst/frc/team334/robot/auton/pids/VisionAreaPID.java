package org.usfirst.frc.team334.robot.auton.pids;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.sources.VisionAreaSource;
import org.usfirst.frc.team334.robot.controls.Constants;

public class VisionAreaPID {

    private PIDController areaPID;
    private VisionAreaSource visionAreaSource;
    private DefaultOutput defaultOutput;

    public VisionAreaPID() {
        visionAreaSource = new VisionAreaSource();
        defaultOutput = new DefaultOutput();

        areaPID = new PIDController(Constants.areaP, Constants.areaI, Constants.areaD, visionAreaSource, defaultOutput);
        areaPID.setContinuous();
        areaPID.setOutputRange(-Constants.areaCap, Constants.areaCap);
        areaPID.enable();

        SmartDashboard.putData("AreaPID", areaPID);
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
