package org.usfirst.frc.team334.robot.util;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Class for manually selecting auton mode via toggle
 * switches on the robot. This is good for acting as
 * a fallback in case there are issues communicating
 * with the driver station.
 */
public class ManualAutonSelect {

    // channels for the inputs
    private final static int DI_1_CHANNEL = 0,
                             DI_2_CHANNEL = 1,
                             DI_3_CHANNEL = 2;
    public final static String[] MODES = {"NONE", "LEFT", "RIGHT", "MIDDLE"};

    private DigitalInput di1, di2, di3;

    public ManualAutonSelect() {
        this.di1 = new DigitalInput(DI_1_CHANNEL);
        this.di2 = new DigitalInput(DI_2_CHANNEL);
        this.di3 = new DigitalInput(DI_3_CHANNEL);
    }

    /**
     * Get the auton selection
     * @return an integer between 0 and 7
     */
    public String getSelection() {
        int bit1 = di1.get() ? 1 : 0;
        int bit2 = di2.get() ? 1 : 0;
        int bit3 = di3.get() ? 1 : 0;

        int selection = bit1 | (bit2 << 1) | (bit3 << 2);

        String auton = MODES[selection];
        return auton;
    }
}
