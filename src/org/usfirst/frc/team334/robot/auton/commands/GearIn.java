package org.usfirst.frc.team334.robot.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team334.robot.components.Gear;

public class GearIn extends Command {

    private Gear gear;

    public GearIn(Gear gear) {
        requires(gear);
        this.gear = gear;
    }

    @Override
    public void initialize() {
        setTimeout(1);
        gear.resetServos();
    }

    public void execute() {}

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
