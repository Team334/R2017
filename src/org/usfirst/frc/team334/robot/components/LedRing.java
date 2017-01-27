package org.usfirst.frc.team334.robot.components;
import java.nio.ByteBuffer;
import org.usfirst.frc.team334.robot.comms.I2C;

public class LedRing {
	I2C ringWriter = new I2C(I2C.Port.kOnboard, 8); //hook up pins A4 to SCL on RoboRio, A5 to SDA on RoboRio, and GND to ground on RoboRio
	//SendRGB method takes a led start position, led stop position, red value, green value, and blue value
    public void sendrgb(int start, int stop,int r, int g, int b) {
		ByteBuffer finalbytes = ByteBuffer.allocateDirect(5); //ByteBuffer needed to send values to RioDunio
		int[] intList = {start,stop,r,g,b}; //list in decimal
		for (int i = 0; i < intList.length; i++) { //converts int to byte and puts it in the bytelist to be sent
			finalbytes.put((byte) intList[i]);
		}
		ringWriter.writeBulk(finalbytes, 5); //Transfers bytes to RioDuino
	}
}
