package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Intake extends Subsystem {

    private VictorSP intakeMotor;

    public Intake() {
        intakeMotor = new VictorSP(Constants.INTAKE);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void pull_in() {
        intakeMotor.set(.5);
    }

    public void push_out() {
        intakeMotor.set(-.5);
    }

    public void stop() {
        intakeMotor.set(0);
    }

}
