package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Indexer extends Subsystem {

    private VictorSP indexerMotor;

    public Indexer() {
        indexerMotor = new VictorSP(Constants.INDEXER);
    }

    @Override
    protected void initDefaultCommand() {}

    public void pushIntoShooter(double speed) {
//        indexerMotor.set(Constants.INDEXER_SPEED);
        indexerMotor.set(speed);
    }

    public void pushOutOfShooter() {
        indexerMotor.set(-Constants.INDEXER_SPEED);
    }

    public void stop() {
        indexerMotor.set(0);
    }

}
