package org.usfirst.frc.team5518.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// joysticks
	public static int JOYSTICK_ZERO = 0;
	public static int JOYSTICK_ONE = 1;
	
	// Xbox 360/One controller mapping
	public static int XBOX_LSTICKX = 0;
	public static int XBOX_LSTICKY = 1; 
	public static int XBOX_RSTICKX = 4;
	public static int XBOX_RSTICKY = 5;
	public static int XBOX_LTRIGGER = 2;
	public static int XBOX_RTRIGGER = 3;
	
	public static int XBOX_YBUTTON = 4;
	public static int XBOX_XBUTTON = 3;
	public static int XBOX_ABUTTON = 1;
	public static int XBOX_BBUTTON = 2; 
	public static int XBOX_RBUMBER = 6;
	public static int XBOX_LBUMBER = 5;
	public static int XBOX_LSTICK = 9;
	public static int XBOX_RSTICK = 10;
	public static int XBOX_START = 8;
	public static int XBOX_BACK = 7;
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    public static int FRONT_LEFT_MTR = 5;
	public static int REAR_LEFT_MTR = 1;
    public static int FRONT_RIGHT_MTR = 2;
    public static int REAR_RIGHT_MTR = 3;
    
    public static int BTM_SHOOT_MTR = 4;
    public static int TOP_SHOOT_MTR = 0;
    public static int INTAKE_MTR = 7;
    
    public static int ARM_LEFT_MTR = 8;
    public static int ARM_RIGHT_MTR = 6;
    public static int ANALOG_LEFT_POT = 1;
    public static int ANALOG_RIGHT_POT = 0;
    
    public static int COMPRESSOR = 0;
    public static int SOLENOID_FORWARD = 4;
    public static int SOLENOID_REVERSE = 5;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    
    public static int FORWARD_ULTRASONIC_TRIG = 0;
    public static int FORWARD_ULTRASONIC_ECHO = 1;
    public static int BACKWARD_ULTRASONIC_TRIG = 2;
    public static int BACKWARD_ULTRASONIC_ECHO = 3;
    
    // Analog Output Def
    public static int ANALOG_OUTPUT_0 = 0;
    public static int ANALOG_OUTPUT_1 = 1;
}