package org.usfirst.frc.team334.robot.components;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;
import org.usfirst.frc.team334.robot.controls.Controls;

public class Climber extends Subsystem {

    private VictorSP climberMotor;
    private Encoder climberEnc;

    private Controls controls;

    public Climber(Controls controls) {
        this.controls = controls;

        climberMotor = new VictorSP(Constants.CLIMBER_1);
        climberEnc = new Encoder(Constants.CLIMBER_ENC_1, Constants.CLIMBER_ENC_2);

        climberEnc.setDistancePerPulse(1);
    }

    @Override
    protected void initDefaultCommand() {

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
        climberMotor.set(-0.5);
    }

    public void stop() {
        climberMotor.set(0);
    }
}
