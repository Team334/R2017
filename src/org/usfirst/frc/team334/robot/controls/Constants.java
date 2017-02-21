package org.usfirst.frc.team334.robot.controls;

public class Constants {

    // --------------------CONTROLS--------------------------
    public final static int JOYSTICK_LEFT = 0,
                            JOYSTICK_RIGHT = 1,
                            XBOX = 2;

    // -------------------SUBSYSTEMS--------------------------
    public final static int DRIVETRAIN_LEFT = 1,
                            DRIVETRAIN_RIGHT = 2;
    public static final double SLOW_FACTOR = 1;

    public final static int CLIMBER_1 = 0;

    public final static int GEAR_RIGHT = 3,
                            GEAR_LEFT = 4;
    // GEAR SPECS
    public final static int lEFT_SERVO_OUT = 125,
                            RIGHT_SERVO_OUT = 45;
    public final static int LEFT_SERVO_GRIP = 180,
                            RIGHT_SERVO_GRIP = 0;
    public final static int LEFT_SERVO_RESET = 170,
                            RIGHT_SERVO_RESET = 10;

    public final static int ENCODER_RIGHT_1 = 0,
                            ENCODER_RIGHT_2 = 1,
                            ENCODER_LEFT_1 = 2,
                            ENCODER_LEFT_2 = 3;

    public final static double DISTANCE_PER_PULSE = 0.1; // in feet

    public final static int CLIMBER_ENC_1 = 4,
                            CLIMBER_ENC_2 = 5;
    public final static double CLIMB_SPEED = 0.5;

    public final static int INDEXER = 5;

    public final static int SHOOTER_1 = 6,
                            SHHOTER_2 = 7;

    public final static int INTAKE = 8;

    public final static int LED_RINGS = 8;

    public final static int RGB_STRIPS = 0;

    public static final double DISTANCE_TO_BASELINE = 9.4;
    public static final double ANGLE_TO_PEG = 60;

    // --------------------SENSORS--------------------------------
    public final static int HALL_EFFECT = 0;

    // MANUAL AUTON SELECT
    public final static int DI_1_CHANNEL = 0,
                            DI_2_CHANNEL = 1,
                            DI_3_CHANNEL = 2;

    // --------------------CAMERAS--------------------------------
    public final static String VIDEO_1 = "/dev/video0",
                               VIDEO_2 = "/dev/video1";


    // -----------------VISION TARGETS SPECS----------------------
    public static final double GEAR_TARGET = 300;
    public static final double GEAR_TOLERANCE = GEAR_TARGET * 0.05;
    public static final double GEAR_AREA_CAP = 350;

    public static final double BOILER_TARGET = 300;
    public static final double BOILER_TOLERANCE = BOILER_TARGET * 0.05;
    public static final double BOILER_AREA_CAP = 350;

    // ----------------------PIDS--------------------------------
    public static final double GYRO_CAP = 0.3,
                               GYRO_P = 0.015,
                               GYRO_I = 0.0,
                               GYRO_D = 0.0;

    public static final double AREA_CAP = 0.15,
                               AREA_P = 0.001,
                               AREA_I = 0.0,
                               AREA_D = 0.0;

    public static final double OFFSET_CAP = 0.3,
                               OFFSET_P = 0.0008,
                               OFFSET_I = 0.0,
                               OFFSET_D = 0.0001;

}
