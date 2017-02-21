package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.DriverStation;

public class BumperLEDStrip {
    DriverStation.Alliance alliance;

    public BumperLEDStrip(DriverStation.Alliance alliance) {
        this.alliance = alliance;
    }

    public void setTeam() {
        switch (alliance) {
            case Blue:
                setBlue();
                break;
            case Red:
                setRed();
                break;
            case Invalid:
                setInv();
                break;
        }
    }

    private void setBlue() {

    }

    private void setRed() {

    }

    private void setInv() {

    }

    public void setBrown() {

    }
}
