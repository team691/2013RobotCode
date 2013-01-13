package org.usfirst.frc691.discshooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class PIDMotor {

	//Init data
	Victor v;
	Encoder e;
	//PIDMotor input
	double targetRPM = 0.0;
	double currentRPM = 0.0;
	double error = 0.0;
	//PIDMotor scale
	double kp = 0.0;
	double ki = 0.0;
	double kd = 0.0;
	//PIDMotor out
	double pout = 0.0;
	double iout = 0.0;
	double dout = 0.0;
	double out = 0.0;
	//PIDMotor loop
	double lasterror = 0.0;
	long lastTime = 0;
	
	public PIDMotor(Victor vic, Encoder enc, double kp, double ki, double kd) {
		v = vic;
		e = enc;
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}
	
	//PIDMotor control
	public void run() {
		currentRPM = e.getRate() * 60;
		error = targetRPM - currentRPM;		
			
		pout = error;
		iout += error;
		dout = (error - lasterror)/(System.currentTimeMillis() - lastTime);
		out = (kp * pout) + (ki * iout) + (kd * dout);
		v.set(out);
		
		lasterror = error;
		lastTime = System.currentTimeMillis();
	}
    
    public void set(double rpm) {
    	targetRPM = rpm;
    }
}