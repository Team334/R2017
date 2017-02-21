package org.usfirst.frc.team334.robot.sensors;

import edu.wpi.first.wpilibj.Counter;
import org.usfirst.frc.team334.robot.controls.Constants;

public class HallEffect {

    private Counter hallEffect;

    public HallEffect() {
        hallEffect = new Counter(Constants.HALL_EFFECT);
    }

    public double getRPM() {
        return (1 / hallEffect.getPeriod()) * 60;
    }

}
