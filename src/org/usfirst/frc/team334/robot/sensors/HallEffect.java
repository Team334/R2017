package org.usfirst.frc.team334.robot.sensors;

import edu.wpi.first.wpilibj.Counter;

public class HallEffect {

    private Counter hallEffect;

    public HallEffect(int port) {
        hallEffect = new Counter(port);
    }

    public double getRPM() {
        return (1 / hallEffect.getPeriod()) * 60;
    }

}
