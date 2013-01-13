/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc691.discshooter;


import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {

	public static final int SHOOTER_VICTOR = 0;
	public static final int SHOOTER_ENCODER_A = 0;
	public static final int SHOOTER_ENCODER_B= 0;
	public static final int SHOOTER_ENCODER_SIDECAR = 0;
	public static final int SHOOTER_ENCODER_COUNT = 0;
	public static final int[] SHOOTER_ENCODER = {SHOOTER_ENCODER_SIDECAR, SHOOTER_ENCODER_A, SHOOTER_ENCODER_B, SHOOTER_ENCODER_COUNT};
	public static final double SHOOTER_KP = 0.0;
	public static final double SHOOTER_KI = 0.0;
	public static final double SHOOTER_KD = 0.0;
	public static final double[] SHOOTER_PID = {SHOOTER_KP, SHOOTER_KI, SHOOTER_KD};
	
	public static final int TURN_VICTOR = 0;
	public static final int TILT_VICTOR = 0;
	
	public static final int OUTPUT_SPIKE = 0;
	public static final int OUTPUT_LIMIT_FRONT = 0;
	public static final int OUTPUT_LIMIT_BACK = 0;
	public static final int OUTPUT_LIMIT_SIDECAR = 0;
	public static final int[] OUTPUT_LIMIT = {OUTPUT_LIMIT_SIDECAR, OUTPUT_LIMIT_FRONT, OUTPUT_LIMIT_BACK};
	
	Joystick joy = new Joystick(2);
	DriverStationLCD dslcd = DriverStationLCD.getInstance();
	Shooter shooter;
	
	public void robotInit() {
		shooter = new Shooter(
				SHOOTER_VICTOR,
				SHOOTER_ENCODER,
				SHOOTER_PID,
				TURN_VICTOR,
				TILT_VICTOR,
				OUTPUT_SPIKE,
				OUTPUT_LIMIT
		);
		shooter.stop();
	}
	
    public void operatorControl() {
    	while(isEnabled() && isOperatorControl()) {
    		shooter.run();
    		if(joy.getRawButton(3)) {
    			shooter.setSpeed(true);
    		} else if(joy.getRawButton(2)) {
    			shooter.setSpeed(false);
    		} else if(joy.getRawButton(1)) {
    			shooter.stop();
    		}
    		dslcd.println(DriverStationLCD.Line.kMain6, 1, Double.toString(shooter.getSpeed()));
    		dslcd.updateLCD();
    	}
    }
}
