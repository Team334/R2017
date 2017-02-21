package org.usfirst.frc.team334.robot.auton.pids;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.sources.VisionOffsetSource;
import org.usfirst.frc.team334.robot.controls.Constants;

public class VisionOffsetPID {

    private PIDController offsetPID;
    private VisionOffsetSource visionOffsetSource;
    private DefaultOutput defaultOutput;

    public VisionOffsetPID() {
        visionOffsetSource = new VisionOffsetSource();
        defaultOutput = new DefaultOutput();

        offsetPID = new PIDController(Constants.OFFSET_P, Constants.OFFSET_I, Constants.OFFSET_D, visionOffsetSource, defaultOutput);
        offsetPID.setSetpoint(0);
        offsetPID.setContinuous();
        offsetPID.setOutputRange(-Constants.OFFSET_CAP, Constants.OFFSET_CAP);
        offsetPID.enable();
        SmartDashboard.putData("OffsetPID", offsetPID);
    }

    public double getInput() {
        return visionOffsetSource.pidGet();
    }

    public double getOutput() {
        return offsetPID.get();
    }

    public PIDController getController() {
        return offsetPID;
    }

}
