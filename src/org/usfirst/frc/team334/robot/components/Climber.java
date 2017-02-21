package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team334.robot.controls.Controls;

public class Climber {

    private VictorSP climberMotor;
    private Encoder climberEnc;

    private Controls controls;

    public Climber(int port) {
        climberMotor = new VictorSP(port);
        climberEnc = new Encoder(4, 5);

        climberEnc.setDistancePerPulse(1);
    }

    public void climbUp() {
        climberMotor.set(.5);
        if(climberEnc.getRate() < 0.5 && climberMotor.get() >= .5) {
            controls.xboxRumble();
        } else {
            controls.xboxUnRumble();
        }
    }

    public void climbDown() {
        climberMotor.set(-.5);
    }

    public void stop() {
        climberMotor.set(0);
    }
}
