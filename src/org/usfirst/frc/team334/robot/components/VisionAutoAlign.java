package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionAreaPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionOffsetPID;
import org.usfirst.frc.team334.robot.util.TrackTarget;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class VisionAutoAlign {

    private Target target;

    private DriveTrain driveTrain;

    private GyroPID gyroPID;
    private VisionAreaPID areaPID;
    private VisionOffsetPID offsetPID;

    private TrackTarget tracker;

    // chechk if target was found in last # of frames
    private final int N_FRAMES = 10;

    private double areaCap;

    private final double GEAR_TARGET = 450;
    private final double GEAR_TOLERANCE = GEAR_TARGET * 0.05;
    private final double GEAR_AREA_CAP = 80_000;

    private final double BOILER_TARGET = 300;
    private final double BOILER_TOLERANCE = BOILER_TARGET * 0.05;
    private final double BOILER_AREA_CAP = 40_000;

    public VisionAutoAlign(DriveTrain driveTrain, GyroPID gyroPID, VisionAreaPID areaPID, VisionOffsetPID offsetPID) {
        this.driveTrain = driveTrain;
        this.gyroPID = gyroPID;
        this.areaPID = areaPID;
        this.offsetPID = offsetPID;

        this.tracker = new TrackTarget(N_FRAMES);
    }

    /**
     * Sets target to auto align with
     * Sets all specifications (setpoint, tolerance, areaCap)
     *
     * @param target: target to align with
     *
     */
    public void setTarget(Target target) {
        this.target = target;

        if (target == Target.GEAR) {
            areaPID.getController().setSetpoint(GEAR_TARGET);
            areaPID.getController().setAbsoluteTolerance(GEAR_TOLERANCE); // 5% tolerance
            areaCap = GEAR_AREA_CAP;
        } else if (target == Target.BOILER) {
            areaPID.getController().setSetpoint(BOILER_TARGET);
            areaPID.getController().setAbsoluteTolerance(BOILER_TOLERANCE);
            areaCap = BOILER_AREA_CAP;
        }
    }

    /**
     * calculates amount to turn and move forward to align to target
     * STOPS ROBOT IF TARGET LOST OR IN RANGE
     *
     * @return true if pids ran successfully, false if vision done or interrupted
     */
    public boolean autoAlign() {
        // stop if no target or too close or at destination
        tracker.addTargetFound(VisionData.foundTarget());
        if (tracker.lostTarget() || !VisionData.visionRunning() || areaPID.getInput() > areaCap || areaPID.getController().onTarget()) {
            driveTrain.stop();
            return false;
        }

        System.out.println("AUTO-ALIGNING");

//        double div = 0.85 * (1 + Math.abs(offsetPID.getOutput())/30.0);
//        speedLeft = -offsetPID.getOutput() + areaPID.getOutput() / div;
//        speedRight = offsetPID.getOutput() + areaPID.getOutput() / div;
        double angle = VisionData.getAngle();
        gyroPID.getController().setPID(0.005, 0.0, 0.0);
        gyroPID.getController().setSetpoint(gyroPID.getInput() + angle);

        // slow down when turning
        double div = (1 + Math.abs(gyroPID.getOutput()));
        double speedLeft = gyroPID.getOutput() + areaPID.getOutput() / div;
        double speedRight = -gyroPID.getOutput() + areaPID.getOutput() / div;
//        driveTrain.setLeftMotors(speedLeft);
//        driveTrain.setRightMotors(speedRight);

        return true;
    }
}
