package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team334.robot.controls.Constants;
import org.usfirst.frc.team334.robot.controls.Controls;

public class Climber {

    private VictorSP climberMotor1;

    public Climber() {
        climberMotor1 = new VictorSP(Constants.CLIMBER_1);
    }

    public void climbUp() {
        climberMotor1.set(0.5);
    }

    public void climbDown() {
        climberMotor1.set(-0.5);
    }

    public void stop() {
        climberMotor1.set(0);
    }
}
