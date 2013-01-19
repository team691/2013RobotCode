package org.usfirst.frc691.meccanum;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;

public class PIDMotor {

    //Init data
    private Victor v;
    private Jaguar j;
    private Encoder e;
    private boolean victor = true;
    //PIDMotor input
    private double targetRPM = 0.0;
    private double currentRPM = 0.0;
    private double error = 0.0;
    //PIDMotor scale
    private double kp = 0.0;
    private double ki = 0.0;
    private double kd = 0.0;
    //PIDMotor out
    private double pout = 0.0;
    private double iout = 0.0;
    private double dout = 0.0;
    private double out = 0.0;
    //PIDMotor loop
    private double lasterror = 0.0;
    private long lastTime = 0;

    public PIDMotor(Victor vic, Encoder enc, double kp, double ki, double kd) {
        v = vic;
        e = enc;
        victor = true;
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    public PIDMotor(Jaguar jag, Encoder enc, double kp, double ki, double kd) {
        j = jag;
        e = enc;
        victor = false;
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
        if(victor){
            v.set(out);
        } else {
            j.set(out);
        }

        lasterror = error;
        lastTime = System.currentTimeMillis();
    }

    public void set(double rpm) {
    	targetRPM = rpm;
    }
}