package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Indexer extends Subsystem {

    private VictorSP indexerMotorBelt;
    private VictorSP indexerMotorRoller;

    public Indexer() {
        indexerMotorBelt = new VictorSP(Constants.INDEXER_BELT);
        indexerMotorRoller = new VictorSP(Constants.INDEXER2_ROLLER);
    }

    @Override
    protected void initDefaultCommand() {}

//    public void pushIntoShooter(double speed, double indexerRoller) {
//        indexerMotor.set(-speed);
//        indexerMotor2.set(-indexerRoller);
//    }

    public void pushIntoShooter() {
        indexerMotorBelt.set(Constants.INDEXER_BELT_SPEED);
        indexerMotorRoller.set(Constants.INDEXER_ROLLER_SPEED);
    }

    public void pushOutOfShooter() {
        indexerMotorBelt.set(-Constants.INDEXER_BELT_SPEED);
        indexerMotorRoller.set(Constants.INDEXER_ROLLER_SPEED);
    }

    public void stop() {
        indexerMotorBelt.set(0);
        indexerMotorRoller.set(0);
    }

}
