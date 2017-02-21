package org.usfirst.frc.team334.robot.vision;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class VisionData {

    private static final String VISION_RUNNING_KEY = "running";
    private static final String FOUND_TARGET_KEY = "found";

    private static final String AREA_KEY = "area";
    private static final String OFFSET_KEY = "x_offset";
    private static final String SKEW_KEY = "skew";
    private static final String ANGLE_KEY = "angle";

    private static boolean visionRunning;
    private static boolean foundTarget;
    private static double area;
    private static double offset;
    private static double skew;
    private static double angle;

    private static NetworkTable nt;

    // call once!!!
    public static void init() {
        nt = NetworkTable.getTable("vision");
        nt.addTableListener(new ITableListener() {
            @Override
            public void valueChanged(ITable source, String key, Object value, boolean isNew) {
                switch (key) {
                    case FOUND_TARGET_KEY:
                        foundTarget = (Boolean) value;
                        break;
                    case AREA_KEY:
                        area = (Double) value;
                        break;
                    case OFFSET_KEY:
                        offset = (Double) value;
                        break;
                    case SKEW_KEY:
                        skew = (Double) value;
                        break;
                    case ANGLE_KEY:
                        angle = (Double) value;
                        break;
                }
            }
        });
    }

    public static NetworkTable getNt() {
        return nt;
    }

    // Can't use listener because networktables doesn't listen for server side changes
    public static boolean visionRunning() {
        return VisionData.getNt().getBoolean(VISION_RUNNING_KEY, false);
    }

    public static boolean foundTarget() {
        return foundTarget;
    }

    public static double getArea() {
        return area;
    }

    public static double getOffset() {
        return offset;
    }

    public static double getAngle() {
        return angle;
    }

    public static double getSkew() {
        return skew;
    }

    public static void displayData() {
        SmartDashboard.putNumber("Area", area);
        SmartDashboard.putNumber("Offset", offset);
        SmartDashboard.putNumber("Angle", angle);
        SmartDashboard.putBoolean("FoundTarget", foundTarget);
        SmartDashboard.putBoolean("Vision Running", visionRunning());
    }
}
