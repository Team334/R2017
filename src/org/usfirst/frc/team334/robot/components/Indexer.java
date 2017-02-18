package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Indexer {

    private VictorSP indexerMotor;

    public Indexer() {
        indexerMotor = new VictorSP(Constants.INDEXER);
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
