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

    public void pullIn() {
        intakeMotor.set(0.5);
    }

    public void pushOut() {
        intakeMotor.set(-0.5);
    }

    public void stop() {
        intakeMotor.set(0);
    }

}
