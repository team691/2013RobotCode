package org.team691.discshooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public class PIDMotor {

    //Init data
    private String name;
    private SpeedController motor;
    private Encoder enc;
    private boolean velocity;
    //PIDMotor input
    private double target = 0.0;
    private double error = 0.0;
    private double deltaTime = 0.0;
    //PIDMotor scale
    private double kp = 0.0;
    private double ki = 0.0;
    private double kd = 0.0;
    private double scalar = 0.0;
    //PIDMotor out
    private double integral = 0.0;
    private double derivative = 0.0;
    private double out = 0.0;
    //PIDMotor loop
    private double lastError = 0.0;
    private long lastTime = 0;

    public PIDMotor(String name, boolean velocity, SpeedController motor, Encoder enc, double kp, double ki, double kd, double scalar) {
        this.name = name;
        this.velocity = velocity;
        this.motor = motor;
        this.enc = enc;
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.scalar = scalar;
    }

    //PIDMotor control
    public void run() {
        if(System.currentTimeMillis() - 10 > lastTime) {
            if(velocity) {
                error = (target * scalar) - (enc.getRate() / 60);
            } else {
                error = target - enc.get();
            }
            if(target == 0.0) {
                integral = 0.0;
            }
            deltaTime = System.currentTimeMillis() - lastTime;

            integral += error * deltaTime;
            derivative = (error - lastError) / deltaTime;
            out = (kp * error) + (ki * integral) + (kd * derivative);
            motor.set(out / scalar);
            System.out.println("Name: " + name + " KP: " + kp + " Target: " + target + " CurrentRPM: " + (enc.getRate() / 60) + " Error: " + error + " Get(): " + enc.get() + " Out: " + out + "\n");

            lastError = error;
            lastTime = System.currentTimeMillis();
        }
    }
    
    //PIDMotor control
    public void run(double speed) {
        set(speed);
        run();
    }
    
    public void set(double rpm) {
    	target = rpm;
    }
}