package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.I2C;

import java.nio.ByteBuffer;

public class LedControl {

    private static final int ADDRESS = 8;

    // hook up pins A4 to SCL on RoboRio, A5 to SDA on RoboRio, and GND to ground on RoboRio
    private I2C ledWriter = new I2C(I2C.Port.kOnboard, ADDRESS);

    // BAD LED STRIP
    public void badStripColor(int r, int g, int b) {
        // ByteBuffer required to send values to RioDuino
        int bufSize = 5;

        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);
        int[] message = {2, 1, r, g, b};

        // Converts int to byte and places in the buffer to be sent
        writeBytesToBuffer(buf, message);

        // Transfers bytes to RioDuino
        ledWriter.writeBulk(buf, bufSize);
    }

    public void badStripPulse(int delay, int r, int g, int b) {
        int bufSize = 6;

        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);
        int[] message = {2, 0, r, g, b, delay};

        writeBytesToBuffer(buf, message);
        ledWriter.writeBulk(buf, bufSize);
    }

    // NEOPIXEL LED STRIP
    // The strip number (0, 1), the number of streaks that chase, the length of each streak,
    // the space in between each streak, delay(ms), and the rgb values
    public void neoPixelChase(int strip, int numStreaks, int trail, int space, int delay, int r, int g, int b) {
        int bufSize = 10;

        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);
        int[] message = {1 , 0, strip, numStreaks, trail, space, delay, r, g, b};

        writeBytesToBuffer(buf, message);
        ledWriter.writeBulk(buf, bufSize);
    }

    public void neoPixelMarquee(int strip, int delay, int r, int g, int b) {
        int bufSize = 7;

        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);
        int[] message = {1, 1, strip, delay, r, g, b};

        writeBytesToBuffer(buf, message);
        ledWriter.writeBulk(buf, bufSize);
    }

    // Neopixel Ring
    // Start led number (0-23), stop led number, delay(ms), and rgb values
    public void neoPixelRing(int start, int stop, int delay, int r, int g, int b) {
        int bufSize = 7;

        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);
        int[] message = {0, 1, start, stop, r, g, b, delay};

        writeBytesToBuffer(buf, message);
        ledWriter.writeBulk(buf, bufSize);
    }

    public static void writeBytesToBuffer(ByteBuffer buf, int... bytes) {
        for (int b : bytes) {
            buf.put((byte) b);
        }
    }

}
