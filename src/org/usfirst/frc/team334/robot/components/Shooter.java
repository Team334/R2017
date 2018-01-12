package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Shooter extends Subsystem {

    private VictorSP shooterMotor, shooterMotor2;
    private boolean shooter1Inverted = true, shooter2Inverted = true;

    private Counter hallEffect;

    public Shooter() {
        shooterMotor = new VictorSP(Constants.SHOOTER);
        // shooterMotor2 = new VictorSP(Constants.SHOOTER_2);

        shooterMotor.setInverted(true);
        // shooterMotor2.setInverted(true);

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
        // shooterMotor2.set(speed);
    }

    public double getHallEffectRate() {
        return hallEffect.getRate();
    }

}
