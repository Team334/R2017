package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Shooter extends Subsystem {

    private VictorSP shooterMotor;

    private Counter hallEffect;

    public Shooter() {
        shooterMotor = new VictorSP(Constants.SHOOTER);

        hallEffect = new Counter(Constants.HALL_EFFECT);
        hallEffect.setDistancePerPulse(1);
    }

    @Override
    protected void initDefaultCommand() {}

//    public void setShooterSpeed() {
//        shooterMotor.set(Constants.SHOOTER_SPEED);
//    }

    public void setShooterSpeed(double speed) {
        shooterMotor.set(speed);
    }

    public double getHallEffectRate() {
        return hallEffect.getRate();
    }

}
