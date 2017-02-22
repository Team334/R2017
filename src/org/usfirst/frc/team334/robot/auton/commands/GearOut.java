package org.usfirst.frc.team334.robot.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team334.robot.components.Gear;

public class GearOut extends Command {

    private Gear gear;

    public GearOut(Gear gear) {
        requires(gear);
        this.gear = gear;

        setTimeout(1);
    }

    public void initialize() {
        gear.pushOutGear();
    }

    public void execute() {}

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
