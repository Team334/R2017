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
    protected void initDefaultCommand() {}

    public void pullIn(double speed) {
//        intakeMotor.set(-Constants.INTAKE_SPEED);
        intakeMotor.set(-speed);
    }

    public void pushOut() {
        intakeMotor.set(Constants.INTAKE_SPEED);
    }

    public void stop() {
        intakeMotor.set(0);
    }

}
