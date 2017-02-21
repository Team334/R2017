package org.usfirst.frc.team334.robot.components;

import java.nio.ByteBuffer;
import edu.wpi.first.wpilibj.I2C;

public class LedControl {

    private static final int ADDRESS = 8;

    // hook up pins A4 to SCL on RoboRio, A5 to SDA on RoboRio, and GND to ground on RoboRio
    private I2C ledwriter = new I2C(I2C.Port.kOnboard, ADDRESS);

    // Bad LED strip methods
    public void badStripColor(int r, int g, int b) {
        // ByteBuffer needed to send values to RioDunio
        int bufSize = 5;
        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);
        int[] message = {2,1, r, g, b};
        // converts int to byte and puts it in the buffer to be sent
        for (int n : message) {
            buf.put((byte) n);
        }
        // Transfers bytes to RioDuino
        ledwriter.writeBulk(buf, bufSize);
    }
    public void badStripPulse(int r, int g, int b, int dly) {
        int bufSize = 6;
        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);
        int[] message = {2,0,r,g,b,dly};
        for (int n : message) {
            buf.put((byte) n);
        }
        ledwriter.writeBulk(buf, bufSize);
    }
    // NeoPixel LED strips methods
    //The strip number (0,1), the numebr of streaks that chase, the length of each streak, the space in between each streak, r, g, b, and delay in ms
    public void neoPixelChase(int strip, int numstreaks, int trail, int space, int r, int g, int b, int dly) {
        int bufSize = 10;
        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);
        int[] message = {1,0,strip,numstreaks,trail,space,dly,r,g,b};
        for (int n : message) {
            buf.put((byte) n);
        }
        ledwriter.writeBulk(buf, bufSize);
    }
    public void neoPixelMarquee(int strip, int r, int g, int b, int dly) {
        int bufSize = 7;
        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);
        int[] message = {1,1,strip,dly,r,g,b};
        for (int n : message) {
            buf.put((byte) n);
        }
        ledwriter.writeBulk(buf, bufSize);
    }
    //Neopixel Ring
    //Start led number (0-23), stop led number, r, g, b, and delay
    public void neoPixelRing(int strt, int stp, int r, int g, int b, int dly) {
        int bufSize = 7;
        ByteBuffer buf = ByteBuffer.allocateDirect(bufSize);
        int[] message = {0,1,strt,stp,r,g,b,dly};
        for (int n : message) {
            buf.put((byte) n);
        }
        ledwriter.writeBulk(buf, bufSize);
    }
}
