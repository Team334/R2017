package org.usfirst.frc.team334.robot.components;

import java.nio.ByteBuffer;
import edu.wpi.first.wpilibj.I2C;

public class LedRing {
    // hook up pins A4 to SCL on RoboRio, A5 to SDA on RoboRio, and GND to ground on RoboRio
    I2C ringWriter = new I2C(I2C.Port.kOnboard, 8);

    // SendRGB method takes a led start position, led stop position, red value, green value, and blue value
    public void sendrgb(int start, int stop, int r, int g, int b) {
        // ByteBuffer needed to send values to RioDunio
        ByteBuffer finalbytes = ByteBuffer.allocateDirect(5);
        int[] intList = {start, stop, r, g, b}; // list in decimal

        // converts int to byte and puts it in the bytelist to be sent
        for (int i = 0; i < intList.length; i++) {
            finalbytes.put((byte) intList[i]);
        }

        // Transfers bytes to RioDuino
        ringWriter.writeBulk(finalbytes, 5);
    }
}
