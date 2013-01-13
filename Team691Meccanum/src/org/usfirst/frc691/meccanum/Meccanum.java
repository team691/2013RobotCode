package org.usfirst.frc691.meccanum;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;


public class Meccanum {
	
	//Victors
	int[] vicSlot;
	int[] vicChannel;
	Victor frVic;
	Victor flVic;
	Victor brVic;
	Victor blVic;
	
	//Encoders
	int[] encSlot;
	int[][] encPort;
	int[] encCount;
	Encoder frEnc;
	Encoder flEnc;
	Encoder brEnc;
	Encoder blEnc;
	
	//PIDMotor
	double[] kp;
	double[] ki;
	double[] kd;
	PIDMotor fr;
	PIDMotor fl;
	PIDMotor br;
	PIDMotor bl;
	
	//Meccanum control
	boolean enabled = true;
	double forward = 0.0;
	double right = 0.0;
	double clockwise = 0.0;
	double frVel = 0.0;
	double flVel = 0.0;
	double brVel = 0.0;
	double blVel = 0.0;
	double maxVel = 0.0;
	
	//Fallbacks in case of meccanum code failure
	boolean useEncoders = true;
	boolean rdCreated = false;
	RobotDrive rd;
	
	public Meccanum(int[] vicSlot, int[] vicChannel, int[] encSlot, int[][] encPort, int[] encCount, double[][] pid) {
		this.vicSlot = vicSlot;
		this.vicChannel = vicChannel;
		this.encSlot = encSlot;
		this.encPort = encPort;
		this.encCount = encCount;
		this.kp = pid[0];
		this.ki = pid[1];
		this.kd = pid[2];
		useEncoders = true;
		init();
		initEnc();
		initPID();
	}
	
	public Meccanum(int[] vicSlot, int[] vicChannel) {
		this.vicSlot = vicSlot;
		this.vicChannel = vicChannel;
		useEncoders = false;
		init();
	}
	
	public void init() {
		frVic = new Victor(vicSlot[0], vicChannel[0]);
		flVic = new Victor(vicSlot[1], vicChannel[1]);
		brVic = new Victor(vicSlot[2], vicChannel[2]);
		blVic = new Victor(vicSlot[3], vicChannel[3]);
	}
	
	public void initEnc() {
		frEnc = new Encoder(encSlot[0], encPort[0][0], encSlot[0], encPort[0][1]);
		flEnc = new Encoder(encSlot[1], encPort[1][0], encSlot[1], encPort[1][1]);
		brEnc = new Encoder(encSlot[2], encPort[2][0], encSlot[2], encPort[2][1]);
		blEnc = new Encoder(encSlot[3], encPort[3][0], encSlot[3], encPort[3][1]);		
		frEnc.setDistancePerPulse(360/encCount[0]);
		flEnc.setDistancePerPulse(360/encCount[1]);
		brEnc.setDistancePerPulse(360/encCount[2]);
		blEnc.setDistancePerPulse(360/encCount[3]);
	}
	
	public void initPID() {
		fr = new PIDMotor(frVic, frEnc, kp[0], ki[0], kd[0]);
		fl = new PIDMotor(flVic, flEnc, kp[1], ki[1], kd[1]);
		br = new PIDMotor(brVic, brEnc, kp[2], ki[2], kd[2]);
		bl = new PIDMotor(blVic, blEnc, kp[3], ki[3], kd[3]);
	}
	
	public void move(Joystick rjoy, Joystick ljoy) {
		forward = rjoy.getRawAxis(2);
		clockwise = rjoy.getRawAxis(1);
		right = ljoy.getRawAxis(1);
		update(forward, right, clockwise);
	}
	
	public void update(double forward, double right, double clockwise) {
		if(enabled) {
			//Figure out motor speeds
			frVel = (forward - right - clockwise);
			flVel = (forward + right + clockwise);
			brVel = (forward - right + clockwise);
			blVel = (forward + right - clockwise);
			
			//Find the maximum speed
			if(maxVel < Math.abs(frVel)) {maxVel = frVel;}
			else if(maxVel < Math.abs(flVel)) {maxVel = flVel;}
			else if(maxVel < Math.abs(brVel)) {maxVel = brVel;}
			else if(maxVel < Math.abs(blVel)) {maxVel = blVel;}
			
			//Scale motor power if it is above 100%
			if(maxVel >= 1) {
				frVel /= maxVel;
				flVel /= maxVel;
				brVel /= maxVel;
				blVel /= maxVel;
			}
			
			//Set the motor PIDMotor objects to the output value for PIDMotor control and additional scaling
			if(useEncoders) {
				fr.set(frVel);
				fl.set(flVel);
				br.set(brVel);
				bl.set(blVel);
			} else {
				frVic.set(frVel);
				flVic.set(flVel);
				brVic.set(brVel);
				blVic.set(blVel);
			}
		}
	}
	
	public void arcade(Joystick joy) {
		if(rdCreated == false) {
			rd = new RobotDrive(flVic, blVic, frVic, brVic);
			rdCreated = true;
		}
		
		if(enabled) {rd.arcadeDrive(joy);}
	}
	
	public void tank(Joystick rjoy, Joystick ljoy) {
		if(rdCreated == false) {
			rd = new RobotDrive(flVic, blVic, frVic, brVic);
			rdCreated = true;
		}
		
		if(enabled) {rd.tankDrive(ljoy, rjoy);}
	}
	
	public void stop() {
		fr.set(0.0);
		fl.set(0.0);
		br.set(0.0);
		bl.set(0.0);
	}

	public void enable() {
		enabled = true;
	}
	
	public void disable() {
		stop();
		enabled = false;
	}
}