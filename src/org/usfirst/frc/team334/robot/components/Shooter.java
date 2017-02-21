package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Shooter extends Subsystem {

    private VictorSP shooterMotor1, shooterMotor2;

    public Shooter() {
        shooterMotor1 = new VictorSP(Constants.SHOOTER_1);
        shooterMotor2 = new VictorSP(Constants.SHHOTER_2);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void setShooterSpeed(double speed) {
        shooterMotor1.set(speed);
        shooterMotor2.set(speed);
    }

}
