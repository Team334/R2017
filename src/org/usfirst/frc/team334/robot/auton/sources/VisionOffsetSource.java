package org.usfirst.frc.team334.robot.auton.sources;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import org.usfirst.frc.team334.robot.vision.VisionData;

public class VisionOffsetSource implements PIDSource {

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {}

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
        return VisionData.getOffset();
    }

}
