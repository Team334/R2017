package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;

public class Intake {

    private VictorSP intakeMotor;

    public Intake(int port) {
        intakeMotor = new VictorSP(port);
    }

    public void pull_in() {
        intakeMotor.set(.5);
    }

    public void push_out() {
        intakeMotor.set(-.5);
    }
}
