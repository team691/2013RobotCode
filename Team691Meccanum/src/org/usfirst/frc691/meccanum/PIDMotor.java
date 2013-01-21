package org.usfirst.frc691.meccanum;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public class PIDMotor {

    //Init data
    private SpeedController motor;
    private Encoder enc;
    //PIDMotor input
    private double targetRPM = 0.0;
    private double currentRPM = 0.0;
    private double error = 0.0;
    private double deltaTime = 0.0;
    //PIDMotor scale
    private double kp = 0.0;
    private double ki = 0.0;
    private double kd = 0.0;
    //PIDMotor out
    private double integral = 0.0;
    private double derivative = 0.0;
    private double out = 0.0;
    //PIDMotor loop
    private double lastError = 0.0;
    private long lastTime = 0;

    public PIDMotor(SpeedController motor, Encoder enc, double kp, double ki, double kd) {
        this.motor = motor;
        this.enc = enc;
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    //PIDMotor control
    public void run() {
        currentRPM = enc.getRate() * 60;
        error = targetRPM - currentRPM;
        deltaTime = System.currentTimeMillis() - lastTime;

        integral += error * deltaTime;
        derivative = (error - lastError)/deltaTime;
        out = (kp * error) + (ki * integral) + (kd * derivative);
        motor.set(out);

        lastError = error;
        lastTime = System.currentTimeMillis();
    }
    
    //PIDMotor control
    public void run(double speed) {
        set(speed);
        run();
    }
    
    public void set(double rpm) {
    	targetRPM = rpm;
    }
}