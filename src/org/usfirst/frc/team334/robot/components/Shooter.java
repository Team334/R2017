package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;

public class Shooter {

    private VictorSP shooterMotor;

    public Shooter(int port) {
        shooterMotor = new VictorSP(port);
    }

    public void setShooterSpeed(double speed) {
        shooterMotor.set(speed);
    }
}
