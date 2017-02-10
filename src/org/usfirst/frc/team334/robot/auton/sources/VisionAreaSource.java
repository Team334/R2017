package org.usfirst.frc.team334.robot.auton.sources;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class VisionAreaSource implements PIDSource {

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
        return Math.sqrt(VisionData.getArea());
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {}


}
