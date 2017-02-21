package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

import java.nio.ByteBuffer;

public class LedRing extends Subsystem {

    // Hook up pins A4 to SCL on RoboRio, A5 to SDA on RoboRio, and GND to ground on RoboRio
    private I2C ringWriter = new I2C(I2C.Port.kOnboard, Constants.LED_RINGS);

    // sendRGB method requires led start and stop positions, red, green, blue and values
    public void sendRGB(int start, int stop, int r, int g, int b) {
        // ByteBuffer needed to send values to RioDuino
        int bufSize = 5;
        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);

        int[] message = {start, stop, r, g, b};

        // Converts int to byte and puts it in the buffer to be sent
        LedControl.writeBytesToBuffer(buf, message);

        // Transfers bytes to RioDuino
        ringWriter.writeBulk(buf, bufSize);
    }

    @Override
    protected void initDefaultCommand() {

    }

}
