package org.usfirst.frc.team334.robot.vision;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class VisionData {

    private static final String AREA_KEY = "area";
    private static final String OFFSET_KEY = "x_offset";
    private static final String SKEW_KEY = "skew";
    private static final String ANGLE_KEY = "angle";

    private static double area;
    private static double offset;
    private static double skew;
    private static double angle;

    // call once!!!
    public static void init() {
        NetworkTable nt = NetworkTable.getTable("vision");
        nt.addTableListener(new ITableListener() {
            @Override
            public void valueChanged(ITable source, String key, Object value, boolean isNew) {
                if (key.equals(AREA_KEY)) {
                    area = (Double) value;
                } else if (key.equals(OFFSET_KEY)) {
                    offset = (Double) value;
                } else if(key.equals(SKEW_KEY)) {
                    skew = (Double) value;
                } else if(key.equals(ANGLE_KEY)) {
                    angle = (Double) value;
                }
            }
        });
    }

    public static double getArea() {
        return area;
    }

    public static double getOffset() {
        return offset;
    }

    public static double getSkew() {
        return skew;
    }

    public static double getAngle() {
        return angle;
    }

}
