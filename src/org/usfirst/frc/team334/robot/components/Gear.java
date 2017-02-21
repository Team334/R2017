package org.usfirst.frc.team334.robot.components;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team334.robot.controls.Constants;

public class Gear extends Subsystem {

    private Servo left;
    private Servo right;

    public Gear() {
        left = new Servo(Constants.GEAR_LEFT);
        right = new Servo(Constants.GEAR_RIGHT);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void pushOutGear() {
        left.setAngle(Constants.lEFT_SERVO_OUT);
        right.setAngle(Constants.RIGHT_SERVO_OUT);
    }

    public void gripGear() {
        left.setAngle(Constants.LEFT_SERVO_GRIP);
        right.setAngle(Constants.RIGHT_SERVO_GRIP);
    }

    public void resetServos() {
        left.setAngle(Constants.LEFT_SERVO_RESET);
        right.setAngle(Constants.RIGHT_SERVO_RESET);
    }

}
