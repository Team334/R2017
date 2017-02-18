package org.usfirst.frc.team334.robot.auton.sources;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team334.robot.sensors.BNO055;

public class GyroSource implements PIDSource {

    public static BNO055 imu;

    public GyroSource () {
        imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER);
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
        double angle = imu.getHeading();
        SmartDashboard.putNumber("Gyro Angle", angle);
        return angle;
    }

}
