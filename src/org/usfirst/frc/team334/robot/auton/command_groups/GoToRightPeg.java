package org.usfirst.frc.team334.robot.auton.command_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team334.robot.auton.commands.Straight;
import org.usfirst.frc.team334.robot.auton.commands.Turn;
import org.usfirst.frc.team334.robot.auton.commands.VisionAuton;
import org.usfirst.frc.team334.robot.auton.pids.GyroPID;
import org.usfirst.frc.team334.robot.components.DriveTrain;
import org.usfirst.frc.team334.robot.components.Target;
import org.usfirst.frc.team334.robot.components.VisionAutoAlign;
import org.usfirst.frc.team334.robot.controls.Constants;

public class GoToRightPeg extends CommandGroup {

    public GoToRightPeg(DriveTrain driveTrain, GyroPID gyroPID, VisionAutoAlign visionAutoAlign) {
        addSequential(new Straight(driveTrain, gyroPID));
        addSequential(new Turn(-Constants.ANGLE_TO_PEG, driveTrain, gyroPID));
        addSequential(new VisionAuton(visionAutoAlign, Target.GEAR));
    }

}
