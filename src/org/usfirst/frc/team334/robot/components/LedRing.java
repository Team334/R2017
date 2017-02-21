package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

import java.nio.ByteBuffer;

public class LedRing extends Subsystem {

    // hook up pins A4 to SCL on RoboRio, A5 to SDA on RoboRio, and GND to ground on RoboRio
    private I2C ringWriter = new I2C(I2C.Port.kOnboard, Constants.LED_RINGS);

    // SendRGB method takes a led start position, led stop position,
    // red value, green value, and blue value
    public void sendrgb(int start, int stop, int r, int g, int b) {
        // ByteBuffer needed to send values to RioDunio
        int bufSize = 5;
        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);

        int[] message = {start, stop, r, g, b};

        // converts int to byte and puts it in the buffer to be sent
        for (int n : message) {
            buf.put((byte) n);
        }

        // Transfers bytes to RioDuino
        ringWriter.writeBulk(buf, bufSize);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
