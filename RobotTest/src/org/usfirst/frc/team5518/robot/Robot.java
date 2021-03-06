/** The MIT License (MIT)
*
*
* Copyright (c) 2016 Techno Wolves
*
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

package org.usfirst.frc.team5518.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	private final NetworkTable grip = NetworkTable.getTable("grip");
	
	Joystick stick = new Joystick(0); // init input device 0
	Victor mVictor; // variable for Victor
	
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    NetworkTable mTable; // variable for NetworkTables
    RobotDrive mDrive; // variable for driving
    
    Ultrasonic mUltra; // global variable for SR04 ultrasonic
    Encoder mEncoder; // variable for encoder
    
    ADXRS450_Gyro mGyro; // ADXRS450450 SPI gyroscope
    ADXL362 mAccel; // ADXL362 SPI accelorometer
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        mVictor = new Victor(4);  // init new Victor at PWM Port 4
        mVictor.enableDeadbandElimination(true); // eliminate deadband
        mVictor.setExpiration(Victor.DEFAULT_SAFETY_EXPIRATION); // set PWM timeout of Victor
        
        mUltra = new Ultrasonic (0,1); // construct Ultrasonic object with DIO port #0 (trig) & port #1 (echo)
        mUltra.setAutomaticMode(true); // set so ultrasonic sensors fire round robin (?)
        
        // construct Encoder with DIO port #8 (channel A) & port #9 (channel B)
        mEncoder = new Encoder(8, 9, false, EncodingType.k4X); //Encoder object created
        
        /* front left motor 0
         * rear left motor 1
         * front right motor 2
         * rear right motor 3
         */
        mDrive = new RobotDrive(0,1,2,3);
        
        // instantiate gyroscope & accelerometer
    	mGyro = new ADXRS450_Gyro();
    	mAccel = new ADXL362(Range.k8G);
        
        // add sensors and other components to the LiveWindow
        LiveWindow.addActuator("DriveTrain", "victor", mVictor);
        LiveWindow.addSensor("Sensor", "ultrasonic", mUltra);
        LiveWindow.addSensor("Sensor", "encoder", mEncoder);
        LiveWindow.addSensor("Sensor", "gyro", mGyro);
        LiveWindow.addSensor("Sensor", "accel", mAccel);
        
        /*Encoder sampleEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        //Setting encoder parameters
        sampleEncoder.setMaxPeriod(.1);
        sampleEncoder.setMinRate(10);
        sampleEncoder.setDistancePerPulse(5);
        sampleEncoder.setReverseDirection(true);
        sampleEncoder.setSamplesToAverage(7);*/
        
        //Starting and stopping a compressor
        /*Compressor c = new Compressor(0);
        c.setClosedLoopControl(true);
        c.setClosedLoopControl(false);
        //Compressor status
        boolean enabled = c.enabled();
        boolean pressureSwitch = c.getPressureSwitchValue();
        float current = c.getCompressorCurrent();*/

        // Run GRIP in a new process
        try {
        	new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    	
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    	// run LiveWindow (outputs values for test purposes)
    	LiveWindow.run();
    	
    	// get the value of the ultrasonic sensor in inches and mm
    	String inches = Double.toString(mUltra.getRangeInches());
    	String mm = Double.toString(mUltra.getRangeMM());
    	
    	// set Victor's values to match input axis 0
    	mVictor.set(stick.getRawAxis(0));
    	
    	/* mecanum drive based on joystick 0 values
    	 * xbox control with left & right thumbsticks
    	 */
    	mDrive.mecanumDrive_Cartesian(stick.getRawAxis(1), stick.getRawAxis(0), 
    			stick.getRawAxis(5), mGyro.getAngle());
    	
    	// log the values
    	SmartDashboard.putString("Ultrasonic SR04 in: ", inches);
    	SmartDashboard.putString("Ultrasonic SR04 mm: ", mm);
    	SmartDashboard.putString("Accel X: ", Double.toString(mAccel.getX()));
    	SmartDashboard.putString("Accel Y: ", Double.toString(mAccel.getY()));
    	SmartDashboard.putString("Accel Z: ", Double.toString(mAccel.getZ()));
    	SmartDashboard.putString("Gyro Angle: ", Double.toString(mGyro.getAngle()));
    	SmartDashboard.putString("Gyro Rate: ", Double.toString(mGyro.getRate()));
    	
    	// log GRIP vision tracking values
    	getGripValues();
    	
    	//Starting, Stopping and Resetting Encoders
    	Encoder sampleEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    	sampleEncoder.reset(); 
    	
    }
    
    /**
     * This function gets the values from NetworkTable published by GRIP (tool for Vision Tracking)
     * and gets the location of the specified contour(s) to output to the Riolog (Eclipse -> Window -> Show View)
     */
    private void getGripValues() {
    	
    	/* Get published values from GRIP using NetworkTables */
        for (double area : grip.getNumberArray("targets/area", new double[0])) {
            System.out.println("Got contour with area = " + area);
        }
    	
        /*double[] defaultValue = new double[0]; // create empty array to store values in
    	double[] areas = mTable.getNumberArray("area", defaultValue); // get the location array of specified contour(s)
    	System.out.println("areas: "); // output to Riolog
   
    	// iterate over location of specified contour(s)
    	for (double area : areas) {
    		System.out.print("area: " + area); 
    	}
    	
    	System.out.println(); // output empty line to Riolog*/
    	
    }
 
}
