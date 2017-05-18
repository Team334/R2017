package org.usfirst.frc.team334.robot.controls;

public class Constants {

//------------------------CONTROLS PORTS---------------------------------------------

    public final static int JOYSTICK_LEFT = 0,
                            JOYSTICK_RIGHT = 1,
                            XBOX = 2;

//-----------------------COMPONENT PORTS---------------------------------------------

    public final static int DRIVETRAIN_LEFT = 0,
                            DRIVETRAIN_RIGHT = 1;

    public final static int INDEXER_BELT = 3;
    public final static int INDEXER2_ROLLER = 2;

    public final static int GEAR_LEFT = 4,
                            GEAR_RIGHT = 5;

    public final static int SHOOTER = 6;
    public final static int SHOOTER_2 = 7;

    public final static int CLIMBER_INTAKE = 8;
    public final static int CLIMBER_INTAKE_2 = 9;

//    public final static int ANGLER = 7;

    public final static int HALL_EFFECT = 5;

    public final static int ENCODER_LEFT_1 = 6,
                            ENCODER_LEFT_2 = 7,
                            ENCODER_RIGHT_1 = 8,
                            ENCODER_RIGHT_2 = 9;

//-------------------------------LEDS PORTS---------------------------------------------------

    public final static int LED_RINGS = 8;

    public final static int RGB_STRIPS = 0;

//---------0-----------------SENSORS PORTS-------------------------------------------------

    public final static int GYRO_PORT = 0;
//    public final static int HALL_EFFECT = 5;

    // MANUAL AUTON SELECT
    public final static int DI_1_CHANNEL = 0, // a
                            DI_2_CHANNEL = 1, // b
                            DI_3_CHANNEL = 2; // c

//----------------------------PID VALUES----------------------------------------------

    public static final double GYRO_CAP = 1,
                               GYRO_P = 0.01,
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

    //--------------------FIELD MEASUREMENTS (IN FEET)-----------------------------------

    public static final double DISTANCE_TO_BASELINE = 93.3 / 12;
    //    public static final double DISTANCE_TO_BASELINE = 9.4;
    public static final double DISTANCE_FROM_PEG_TO_KEY = -6.0;

    public static final double ANGLE_TO_PEG = 60;

    //-----------------------VISION TARGETS SPECS----------------------------------------

    public static final double GEAR_TARGET = 300;
    public static final double GEAR_TOLERANCE = GEAR_TARGET * 0.05;
    public static final double GEAR_AREA_CAP = 350;

    public static final double BOILER_TARGET = 300;
    public static final double BOILER_TOLERANCE = BOILER_TARGET * 0.05;
    public static final double BOILER_AREA_CAP = 350;

    //-------------------------COMPONENT SPEEDS-----------------------------------------

    public final static double INTAKE_SPEED = 0.5;
    public final static double CLIMB_SPEED = 1;
    public final static double INDEXER_BELT_SPEED = -.7;
    public final static double INDEXER_ROLLER_SPEED = -0.4;
    public final static double SHOOTER_SPEED = 0.74;

    public final static double ANGLER_ANGLE = 90;

    // PRACTICE ROBOT
//    public final static int LEFT_SERVO_OUT = 100,
//                            RIGHT_SERVO_OUT = 60;
//    public final static int LEFT_SERVO_RESET = 175,
//                            RIGHT_SERVO_RESET = 0;

    // REAL ROBOT
    public final static int LEFT_SERVO_OUT = 120, // 150
                            RIGHT_SERVO_OUT = 40; // 40
    public final static int LEFT_SERVO_RESET = 160,
                            RIGHT_SERVO_RESET = 10;

    public static final double DRIVE_SLOW_FACTOR = 0.6; // UPDATE

//-------------------------COMPONENT DETAILS----------------------------------------

    public final static double ROBOT_WIDTH = 2.9; // feet

    public final static double DRIVEWHEEL_CIRCUMFERENCE = 4.1 * Math.PI / 12; // in feet
    public final static int DRIVE_PULSES_PER_REVOLUTION = 76;

//-----------------------------AUTON DRIVE TIMES--------------------------------------

    public static final double STRAIGHT_TIME_GEAR = 3.75;
    public static final double STRAIGHT_TIME_BASELINE = 2;
    public static final double STRAIGHT_TIME_BACK = 3;

    public static final double STRAIGHT_SPEED = 0.3;
    public static final double TURN_TIME = 1;

}
