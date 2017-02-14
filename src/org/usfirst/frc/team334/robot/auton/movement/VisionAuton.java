package org.usfirst.frc.team334.robot.auton.movement;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionAreaPID;
import org.usfirst.frc.team334.robot.auton.pids.VisionOffsetPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class VisionAuton extends Command {

    enum TARGET {
        GEAR, BOILER
    }
    TARGET target;

    private boolean visionDone;

    private GyroPID gyroPID;
    private VisionAreaPID areaPID;
    private VisionOffsetPID offsetPID;

    private DriveTrain driveTrain;

    public VisionAuton(TARGET target, DriveTrain driveTrain) {
        this.target = target;
        this.driveTrain = driveTrain;

        this.gyroPID = new GyroPID();
        this.areaPID = new VisionAreaPID();
        this.offsetPID = new VisionOffsetPID();
    }

    public void initialize() {
        visionDone = false;
    }

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

    @Override
    protected boolean isFinished() {
        return visionDone;
    }
}
