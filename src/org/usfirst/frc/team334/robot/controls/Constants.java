package org.usfirst.frc.team334.robot.controls;

public class Constants {

    public final static int JOYSTICK_LEFT = 0,
                            JOYSTICK_RIGHT = 1,
                            XBOX = 2;

    public final static int CLIMBER_1 = 0;

    public final static int DRIVETRAIN_LEFT = 1,
                            DRIVETRAIN_RIGHT = 2;

    public final static int ENCODER_1 = 0,
                            ENCODER_2 = 1;

    public final static int CLIMBER_ENC_1 = 4,
                            CLIMBER_ENC_2 = 5;

    public final static double DISTANCE_PER_PULSE = 0.1; // in feet

    public final static int GEAR_LEFT = 0,
                            GEAR_RIGHT = 1;

    public final static int INDEXER = 0;

    public final static int INTAKE = 0;

    public final static int LED_RINGS = 8;

    public final static int RGB_STRIPS = 0;

    public final static int SHOOTER = 0;

    public final static int HALL_EFFECT = 0;

    // channels for the inputs
    public final static int DI_1_CHANNEL = 0,
                            DI_2_CHANNEL = 1,
                            DI_3_CHANNEL = 2;

    public final static String VIDEO_1 = "/dev/video0",
                               VIDEO_2 = "/dev/video1";

    public static final double DISTANCE_TO_BASELINE = 9.4;
    public static final double ANGLE_TO_PEG = 60;
    public static final double SLOW_FACTOR = 0.8;

    // Targets
    public static final double GEAR_TARGET = 300;
    public static final double GEAR_TOLERANCE = GEAR_TARGET * 0.05;
    public static final double GEAR_AREA_CAP = 350;

    public static final double BOILER_TARGET = 300;
    public static final double BOILER_TOLERANCE = BOILER_TARGET * 0.05;
    public static final double BOILER_AREA_CAP = 350;

    // ----------------------PIDS--------------------------------
    public static final double gyroCap = 0.3,
                               gyroP = 0.015,
                               gyroI = 0.0,
                               gyroD = 0.0;

    public static final double areaCap = 0.15,
                               areaP = 0.001,
                               areaI = 0.0,
                               areaD = 0.0;

    public static final double offsetCap = 0.3,
                               offsetP = 0.0008,
                               offsetI = 0.0,
                               offsetD = 0.0001;

}
