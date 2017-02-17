package org.usfirst.frc.team334.robot.auton.movement;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team334.robot.components.DriveTrain;
import org.usfirst.frc.team334.robot.components.Target;
import org.usfirst.frc.team334.robot.components.VisionAutoAlign;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class VisionAuton extends Command {

    private Target target;

    private boolean visionDone;

    private VisionAutoAlign visionAutoAlign;
    private DriveTrain driveTrain;

    public VisionAuton(VisionAutoAlign visionAutoAlign, Target target, DriveTrain driveTrain) {
        this.visionAutoAlign = visionAutoAlign;
        this.target = target;
        this.driveTrain = driveTrain;
    }

    // Called once at start of command
    public void initialize() {
        // MAKE SURE VISION IS ON!!!!
        if (!VisionData.isVisionInit()) {
            System.out.println("VISION DISABLED");
            cancel();
        }
        visionAutoAlign.setTarget(target);

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
        if (visionAutoAlign.isRunning()) {
            double[] speeds = visionAutoAlign.autoAlign();
            double speedL = speeds[0];
            double speedR = speeds[1];
            driveTrain.setLeftMotors(speedL);
            driveTrain.setRightMotors(speedR);
        } else {
            visionDone = true;
            driveTrain.stop();
        }
    }

    // If vision is canceled, stop the robot
    protected void interrupted() {
        System.out.println("CANCELED");
        driveTrain.stop();
    }

    // Stops command when returns true
    @Override
    protected boolean isFinished() {
        return visionDone;
    }
}
