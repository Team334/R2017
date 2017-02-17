package org.usfirst.frc.team334.robot.components;

import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionAreaPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionOffsetPID;
import org.usfirst.frc.team334.robot.util.TrackTarget;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class VisionAutoAlign {

    private Target target;

    private GyroPID gyroPID;
    private VisionAreaPID areaPID;
    private VisionOffsetPID offsetPID;

    private TrackTarget tracker;

    private double areaCap;
    private boolean isRunning = false;
    private final int N_FRAMES = 10;

    private final double GEAR_TARGET = 450;
    private final double GEAR_TOLERANCE = GEAR_TARGET * 0.05;
    private final double GEAR_AREA_CAP = 80_000;

    private final double BOILER_TARGET = 300;
    private final double BOILER_TOLERANCE = BOILER_TARGET * 0.05;
    private final double BOILER_AREA_CAP = 40_000;

    public VisionAutoAlign(GyroPID gyroPID, VisionAreaPID areaPID, VisionOffsetPID offsetPID) {
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
     *
     *  @return speeds for each side double[] = (speedLeft, speedRight)
     */
    public double[] autoAlign() {
        double speedLeft = 0;
        double speedRight = 0;

        tracker.addTargetFound(VisionData.foundTarget());
        if (tracker.lostTarget()) {
            isRunning = false;
            return new double[]{0,0};
        }

        isRunning = true;
        // go until we are in range or too close
        if (areaPID.getInput() < areaCap || areaPID.getController().onTarget()) {
            System.out.println("VISION ALIGN");
//            double div = 0.85 * (1 + Math.abs(offsetPID.getOutput())/30.0);
//            speedLeft = -offsetPID.getOutput() + areaPID.getOutput() / div;
//            speedRight = offsetPID.getOutput() + areaPID.getOutput() / div;
            double angle = VisionData.getAngle();
            gyroPID.getController().setPID(0.005, 0.0, 0.0);
            gyroPID.getController().setSetpoint(gyroPID.getInput() + angle);

            // slow down when turning
            double div = (1 + Math.abs(gyroPID.getOutput()));
            speedLeft = gyroPID.getOutput() + areaPID.getOutput() / div;
            speedRight = -gyroPID.getOutput() + areaPID.getOutput() / div;
        }

        double[] speeds = {speedLeft, speedRight};
        return speeds;
    }

    /**
     * @return if vision is auto-aligning or not
     */
    public boolean isRunning() {
        return isRunning;
    }
}
