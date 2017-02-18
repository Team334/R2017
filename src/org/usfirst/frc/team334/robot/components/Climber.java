package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team334.robot.controls.Controls;

public class Climber {

    private VictorSP climberMotor1, climberMotor2;

    public Climber(int port1, int port2) {
        climberMotor1 = new VictorSP(port1);
        climberMotor2 = new VictorSP(port2);
    }

    public void climbUp() {
        climberMotor1.set(0.5);
        climberMotor2.set(0.5);
    }

    public void climbDown() {
        climberMotor1.set(-0.5);
        climberMotor2.set(-0.5);
    }

    public void stop() {
        climberMotor1.set(0);
        climberMotor2.set(0);
    }
}
