package org.usfirst.frc.team334.robot.auton.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team334.robot.auton.commands.GearOut;
import org.usfirst.frc.team334.robot.auton.commands.Straight;
import org.usfirst.frc.team334.robot.auton.commands.Turn;
import org.usfirst.frc.team334.robot.auton.commands.VisionAuton;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;
import org.usfirst.frc.team334.robot.components.Gear;
import org.usfirst.frc.team334.robot.components.Target;
import org.usfirst.frc.team334.robot.components.VisionAutoAlign;
import org.usfirst.frc.team334.robot.controls.Constants;

public class GoToRightPeg extends CommandGroup {

    /**
     * Perform tasks in order
     *
     * Steps:
     *      1) Go straight to baseline
     *      2) Turn right to peg
     *      3) Vision to get close
     */
    public GoToRightPeg(DriveTrain driveTrain, GyroPID gyroPID, VisionAutoAlign visionAutoAlign, Gear gear) {
        addSequential(new Straight(Constants.DISTANCE_TO_BASELINE, driveTrain, gyroPID));
        addSequential(new Turn(-Constants.ANGLE_TO_PEG, driveTrain, gyroPID));
        addSequential(new VisionAuton(visionAutoAlign, Target.GEAR));
        addSequential(new GearOut(gear));
        addSequential(new Straight(-3, driveTrain, gyroPID));
    }

}
