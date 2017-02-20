package org.usfirst.frc.team334.robot.auton.movement;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.components.Target;
import org.usfirst.frc.team334.robot.components.VisionAutoAlign;

public class VisionAuton extends Command {

    private Target target;

    private boolean visionDone;

    private VisionAutoAlign visionAutoAlign;

    public VisionAuton(VisionAutoAlign visionAutoAlign, Target target) {
        this.visionAutoAlign = visionAutoAlign;
        this.target = target;
    }

    // Called once at start of command
    public void initialize() {
        visionDone = false;

        visionAutoAlign.setTarget(target);
    }

    /**
     * Continues looping until isFinished returns true(non-Javadoc)
     * Move using vision until vision lost or at destination
     */
    public void execute() {
        SmartDashboard.putString("Mode", "VISION");

        boolean running = visionAutoAlign.autoAlign();
        visionDone = !running;
        System.out.println("Vision Done = " + visionDone);
    }

    // Stops command when returns true
    @Override
    protected boolean isFinished() {
        return visionDone;
    }
}
