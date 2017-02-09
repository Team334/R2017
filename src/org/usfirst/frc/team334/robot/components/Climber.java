package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team334.robot.controls.Controls;

public class Climber {

    private VictorSP climberMotor;

    private Controls controls;

    public Climber(int port) {
        climberMotor = new VictorSP(port);
    }

    public void climbUp() {
        climberMotor.set(.5);
    }

    public void climbDown() {
        climberMotor.set(-.5);
    }

    public void stop() {
        climberMotor.set(0);
    }
}
