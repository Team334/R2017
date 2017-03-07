package org.usfirst.frc.team334.robot.controls;

public class Constants {

//------------------------CONTROLS PORTS---------------------------------------------

    public final static int JOYSTICK_LEFT = 0,
                            JOYSTICK_RIGHT = 1,
                            XBOX = 2;

//-----------------------COMPONENT PORTS---------------------------------------------

    public final static int CLIMBER_1 = 0;

    public final static int DRIVETRAIN_LEFT = 1,
                            DRIVETRAIN_RIGHT = 2;

    public final static int GEAR_RIGHT = 3,
                            GEAR_LEFT = 4;

    public final static int INDEXER = 5;

    public final static int SHOOTER_1 = 6,
                            SHOOTER_2 = 7;

    public final static int INTAKE = 8;

    public final static int ENCODER_RIGHT_1 = 0,
                            ENCODER_RIGHT_2 = 1,
                            ENCODER_LEFT_1 = 2,
                            ENCODER_LEFT_2 = 3;

    public final static int CLIMBER_ENC_1 = 4,
                            CLIMBER_ENC_2 = 5;

//-------------------------COMPONENT SPEEDS-----------------------------------------
    public final static double CLIMB_SPEED = 1;
    public final static double INDEXER_SPEED = 0.8;
    public final static double INTAKE_SPEED = 0.55;
    public final static double SHOOTER_SPEED = 0.8;

    public final static int lEFT_SERVO_OUT = 125,
                            RIGHT_SERVO_OUT = 45;
    public final static int LEFT_SERVO_GRIP = 180,
                            RIGHT_SERVO_GRIP = 0;
    public final static int LEFT_SERVO_RESET = 170,
                            RIGHT_SERVO_RESET = 10;

    public static final double DRIVE_SLOW_FACTOR = 0.6; // UPDATE

//-------------------------COMPONENT DETAILS----------------------------------------

    public final static double DRIVEWHEEL_CIRCUMFERENCE = 4 * Math.PI;
    public final static int DRIVE_PULSES_PER_REVOLUTION = 100; // UPDATE

    public final static double CLIMBWHEEL_CIRCUMFERENCE = 1 * Math.PI; // UPDATE
    public final static double CLIMB_PULSES_PER_REVOLUTION = 0.1; // UPDATE


//--------------------FIELD MEASUREMENTS (IN FEET)-----------------------------------

    public static final double DISTANCE_TO_BASELINE = 9.4;
    public static final double DISTANCE_FROM_PEG_TO_KEY = 6.0;

    public static final double ANGLE_TO_PEG = 90;

//-----------------------------LEDS---------------------------------------------------

    public final static int LED_RINGS = 8;

    public final static int RGB_STRIPS = 0;

//--------------------------SENSORS---------------------------------------------------

    public final static int HALL_EFFECT = 6;

    // MANUAL AUTON SELECT
    public final static int DI_1_CHANNEL = 0,
                            DI_2_CHANNEL = 1,
                            DI_3_CHANNEL = 2;

//------------------------CAMERA LOCATIONS-------------------------------------------

    public final static String VIDEO_1 = "/dev/video0",
                               VIDEO_2 = "/dev/video1";

//-----------------------VISION TARGETS SPECS----------------------------------------

    public static final double GEAR_TARGET = 300;
    public static final double GEAR_TOLERANCE = GEAR_TARGET * 0.05;
    public static final double GEAR_AREA_CAP = 350;

    public static final double BOILER_TARGET = 300;
    public static final double BOILER_TOLERANCE = BOILER_TARGET * 0.05;
    public static final double BOILER_AREA_CAP = 350;

//----------------------------PID VALUES----------------------------------------------

    public static final double GYRO_CAP = 1,
                               GYRO_P = 0.1,
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
