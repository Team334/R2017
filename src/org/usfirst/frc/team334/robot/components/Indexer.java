package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;

public class Indexer {

    private VictorSP indexerMotor;

    public Indexer(int port) {
        indexerMotor = new VictorSP(port);
    }

    public void pushIntoShooter() {
        indexerMotor.set(0.5);
    }

    public void pushOutOfShooter() {
        indexerMotor.set(-0.5);
    }

    public void stop() {
        indexerMotor.set(0);
    }

}
