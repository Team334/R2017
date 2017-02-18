package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Shooter {

    private VictorSP shooterMotor;

    public Shooter() {
        shooterMotor = new VictorSP(Constants.SHOOTER);
    }

    public void setShooterSpeed(double speed) {
        shooterMotor.set(speed);
    }

}
