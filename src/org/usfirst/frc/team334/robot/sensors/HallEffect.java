package org.usfirst.frc.team334.robot.sensors;

import edu.wpi.first.wpilibj.Counter;

public class HallEffect {
    Counter hallEffect;

    public HallEffect(int port) {
        hallEffect = new Counter(port);
        hallEffect.setSemiPeriodMode(true);
    }

    public double getRPM() {
        return (1/hallEffect.getPeriod()) * 60;
    }
}
