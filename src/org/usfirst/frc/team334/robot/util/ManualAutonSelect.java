package org.usfirst.frc.team334.robot.util;

import edu.wpi.first.wpilibj.DigitalInput;
import org.usfirst.frc.team334.robot.auton.AutonScenario;
import org.usfirst.frc.team334.robot.controls.Constants;

/**
 * Class for manually selecting auton mode via toggle
 * switches on the robot. This is good for acting as
 * a fallback in case there are issues communicating
 * with the driver station.
 */
public class ManualAutonSelect {

    private DigitalInput di1, di2, di3;

    public ManualAutonSelect() {
        this.di1 = new DigitalInput(Constants.DI_1_CHANNEL);
        this.di2 = new DigitalInput(Constants.DI_2_CHANNEL);
        this.di3 = new DigitalInput(Constants.DI_3_CHANNEL);
    }

    /**
     * Get the auton selection
     * @return an integer between 0 and 7
     */
    public int getSelection() {
        int bit1 = di1.get() ? 1 : 0;
        int bit2 = di2.get() ? 1 : 0;
        int bit3 = di3.get() ? 1 : 0;

        int selection = bit1 | (bit2 << 1) | (bit3 << 2);

        return selection;
    }

    /**
     * Select scenario based on selection
     * @return selected scenario
     */
    public AutonScenario getScenario() {
        int selection = getSelection();

        AutonScenario autonScenario;
        switch (selection) {
            case 1:
                autonScenario = AutonScenario.LEFT_SIDE;
                break;
            case 2:
                autonScenario = AutonScenario.RIGHT_SIDE;
                break;
            case 3:
                autonScenario = AutonScenario.MIDDLE;
                break;
            default:
                autonScenario = AutonScenario.NOTHING;
        }
        return autonScenario;
    }


}
