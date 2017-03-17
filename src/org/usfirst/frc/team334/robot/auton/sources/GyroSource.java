package org.usfirst.frc.team334.robot.auton.sources;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import org.usfirst.frc.team334.robot.controls.Constants;
import org.usfirst.frc.team334.robot.sensors.BNO055;

public class GyroSource implements PIDSource {

    public static BNO055 imu;
    public AnalogGyro gyro;

    public GyroSource () {
        imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER);
        gyro = new AnalogGyro(Constants.GYRO_PORT);
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
//        return imu.getHeading();
        return gyro.pidGet();
    }

//    public BNO055 getGyro() {
//        return imu;
//    }

    public AnalogGyro getGyro() {
        return gyro;
    }

}
