package org.usfirst.frc.team334.robot.auton.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team334.robot.auton.commands.Straight;
import org.usfirst.frc.team334.robot.auton.commands.Turn;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;
import org.usfirst.frc.team334.robot.controls.Constants;

public class LeftNoGear extends CommandGroup{

    /**
     * Perform tasks in order
     *
     * Steps:
     *      1) Go straight to baseline
     *      2) Turn right to peg
     */
    public LeftNoGear(DriveTrain driveTrain, GyroPID gyroPID) {
        addSequential(new Straight(Constants.DISTANCE_TO_BASELINE, Constants.STRAIGHT_TIME_BASELINE, driveTrain, gyroPID));
        addSequential(new Turn(-Constants.ANGLE_TO_PEG, Constants.TURN_TIME, driveTrain, gyroPID));
        addSequential(new Straight(Constants.DISTANCE_TO_BASELINE, 0.75, driveTrain, gyroPID));
    }
}
