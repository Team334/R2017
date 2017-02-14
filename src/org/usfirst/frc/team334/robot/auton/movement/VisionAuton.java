package org.usfirst.frc.team334.robot.auton.movement;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionAreaPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionOffsetPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class VisionAuton extends Command {

    public enum Target {
        GEAR, BOILER
    }
    Target target;

    private boolean visionDone;

    private GyroPID gyroPID;
    private VisionAreaPID areaPID;
    private VisionOffsetPID offsetPID;

    private DriveTrain driveTrain;

    private final double GEAR_TARGET = 450;
    private final double GEAR_TOLERANCE = GEAR_TARGET * 0.05;

    private final double BOILER_TARGET = 300;
    private final double BOILER_TOLERANCE = BOILER_TARGET * 0.05;

    public VisionAuton(Target target, DriveTrain driveTrain) {
        this.target = target;
        this.driveTrain = driveTrain;

        this.gyroPID = new GyroPID();
        this.areaPID = new VisionAreaPID();
        this.offsetPID = new VisionOffsetPID();
    }

    // Called once at start of command
    public void initialize() {
        if (target == Target.GEAR) {
            areaPID.getController().setSetpoint(GEAR_TARGET);
            areaPID.getController().setAbsoluteTolerance(GEAR_TOLERANCE); // 5% tolerance
        } else if (target == Target.BOILER) {
            areaPID.getController().setSetpoint(BOILER_TARGET);
            areaPID.getController().setAbsoluteTolerance(BOILER_TOLERANCE);
        }

        visionDone = false;
    }

    /*
     * Continues looping until isFinished returns true(non-Javadoc)
     *
     *  Steps:
     *      1) Finds target
     *      2) Move towards target until close enough
     *      3) Stop
     */
    public void execute() {
        System.out.println("VISION");
        double speedLeft = 0;
        double speedRight = 0;
        final double AREA_CAP = 80_000;

        if (areaPID.getInput() < AREA_CAP) { // Area increases as we get closer
//            double div = 0.85 * (1 + Math.abs(offsetPID.getOutput())/30.0);
//            speedLeft = -offsetPID.getOutput() + areaPID.getOutput() / div;
//            speedRight = offsetPID.getOutput() + areaPID.getOutput() / div;
            double angle = VisionData.getAngle();
            gyroPID.getController().setPID(0.005, 0.0, 0.0);
            gyroPID.getController().setSetpoint(gyroPID.getInput() + angle);

            double div = (1 + Math.abs(gyroPID.getOutput()));
            speedLeft = +gyroPID.getOutput() + areaPID.getOutput() / div;
            speedRight = -gyroPID.getOutput() + areaPID.getOutput() / div;
        } else {
            visionDone = true;
        }

        driveTrain.setLeftMotors(speedLeft);
        driveTrain.setRightMotors(speedRight);
    }

    // Stops command when returns true
    @Override
    protected boolean isFinished() {
        return visionDone;
    }
}
