package org.usfirst.frc691.discshooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;

public class Shooter {
	
	PIDMotor shooter;
	Victor turn;
	Victor tilt;
	Relay output;
	DigitalInput outputFront;
	DigitalInput outputBack;
	double targetRPM = 0.0;	//TODO: Change this to best value after prototype!
	boolean outputOut = false;
	
	public Shooter(int shooterVic, int[] shooterEnc, double[] shooterPID, int turnVic, int tiltVic, int outputSpike, int[] outputLimit) {
		Encoder shooterEncoder = new Encoder(shooterEnc[0], shooterEnc[1], shooterEnc[0], shooterEnc[2]);
		shooterEncoder.setDistancePerPulse(360/shooterEnc[3]);
		shooter = new PIDMotor(new Victor(shooterVic), shooterEncoder, shooterPID[0], shooterPID[1], shooterPID[2]);
		
		turn = new Victor(turnVic);
		tilt = new Victor(tiltVic);
		
		output = new Relay(outputSpike);
		outputFront = new DigitalInput(outputLimit[0], outputLimit[1]);
		outputBack = new DigitalInput(outputLimit[0], outputLimit[2]);
	}
	
	public void run() {
		shooter.set(targetRPM);
		if(outputOut && outputFront.get() == false) {
			output.set(Relay.Value.kForward);
		} else if(!outputOut && outputBack.get() == false){
			output.set(Relay.Value.kReverse);
		} else {
			output.set(Relay.Value.kOff);
		}
	}
	
	//TODO: Adjust this for prototype
	public void setSpeed(boolean speedUp) {
		if(speedUp) {
			targetRPM += 50;
		} else {
			targetRPM -= 50;
		}
	}
	
	public void turn(double speed) {
		turn.set(speed);
	}
	
	public void tilt(double speed) {
		tilt.set(speed);
	}
	
	public void setOutput(boolean out) {
		outputOut = out;
	}
	
	public double getSpeed() {
		return targetRPM;
	}
	
	public void stop() {
		targetRPM = 0.0;
	}
	
}