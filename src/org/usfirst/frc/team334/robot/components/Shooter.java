package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Shooter extends Subsystem {

    private VictorSP shooterMotor;

    public Shooter() {
        shooterMotor = new VictorSP(Constants.SHOOTER);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void setShooterSpeed(double speed) {
        shooterMotor.set(speed);
    }

}
