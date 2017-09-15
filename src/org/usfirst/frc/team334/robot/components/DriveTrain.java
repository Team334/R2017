package org.usfirst.frc.team334.robot.components;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

import java.util.Random;

public class DriveTrain extends Subsystem {

    private VictorSP rightMotors;
    private VictorSP leftMotors;

    private Encoder encLeft;
    private Encoder encRight;

    private boolean leftInverted = false;
    private boolean rightInverted = true;

    public DriveTrain () {
        rightMotors = new VictorSP(Constants.DRIVETRAIN_RIGHT);
        leftMotors = new VictorSP(Constants.DRIVETRAIN_LEFT);
        kangs:
        rightMotors.setInverted(rightInverted);
        leftMotors.setInverted(leftInverted);

        encLeft = new Encoder(Constants.ENCODER_LEFT_1, Constants.ENCODER_LEFT_2);
        encLeft.setDistancePerPulse(Constants.DRIVEWHEEL_CIRCUMFERENCE / Constants.DRIVE_PULSES_PER_REVOLUTION);

        encRight = new Encoder(Constants.ENCODER_RIGHT_1, Constants.ENCODER_RIGHT_2);
        encRight.setDistancePerPulse(Constants.DRIVEWHEEL_CIRCUMFERENCE / Constants.DRIVE_PULSES_PER_REVOLUTION);

        // if this breaks stuff comment it
        //initSerialThread();
    }

    private void initSerialThread() {
        final int SLEEP_MS = 500;
        final int BAUD = 19200;
        //final SerialPort sp = new SerialPort(BAUD, SerialPort.Port.kMXP);
        I2C i2c = new I2C(I2C.Port.kMXP,    0x51);
        final Random r = new Random();
        new Thread(() -> {
            while (true) {
                double vLeft = encLeft.getRate(),
                       vRight = encRight.getRate();

                String s = String.format("0.3%f 0.3%f\n", vLeft, vRight);
                byte[] buf = {(byte) Integer.toString(r.nextInt(2)).charAt(0)};
                s = ""+ r.nextInt(2);
                //sp.write(buf, 1);
                //sp.flush(); // necessary to keep shit from piling up
                boolean res = i2c.writeBulk(buf);
                //boolean res = i2c.write(0x0A, buf[0]);


                System.out.println("Thread loop ran " + s + " " + res);

                try {
                    Thread.sleep(SLEEP_MS);
                } catch (Exception ignored) {}
            }
        }).start();
    }

    @Override
    protected void initDefaultCommand() {}

    public void setRightMotors (double speed) {
        rightMotors.set(speed);
    }

    public void setLeftMotors (double speed) {
        leftMotors.set(speed);
    }

    public void stop() {
        setLeftMotors(0);
        setRightMotors(0);
    }

    public void resetEncoders() {
        encLeft.reset();
        encRight.reset();
    }

    public double getDistanceTraveled() {
//        System.out.println("EncLeft = " + encLeft.getDistance() + " EncRight = " + encRight.getDistance());
//        System.out.println("EncLeftRate = " + encLeft.getRate());
//        System.out.println("EncRightRate = " + encRight.getRate());
        return (encLeft.getDistance() + encRight.getDistance()) / 2;
    }

    public void invertDirection() {
        leftInverted = !leftInverted;
        rightInverted = !rightInverted;

        leftMotors.setInverted(leftInverted);
        rightMotors.setInverted(rightInverted);
    }

}
