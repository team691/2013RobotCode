package org.team691.discshooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public class PIDPositionMotor {

    //Init data
    private String name;
    private PIDVelocityMotor motor;
    private Encoder enc;
    //PIDMotor input
    private double position = 0.0;
    private double target = 0.0;
    private double error = 0.0;
    private double deltaTime = 0.0;
    //PIDMotor scale
    private double kp = 0.0;
    private double ki = 0.0;
    private double kd = 0.0;
    private double max = 0.0;
    //PIDMotor out
    private double integral = 0.0;
    private double derivative = 0.0;
    private double out = 0.0;
    //PIDMotor loop
    private double lastError = 0.0;
    private long lastTime = 0;

    public PIDPositionMotor(String name, SpeedController motor, Encoder enc, double[] posPID, double[] velPID) {
        this.name = name;
        this.motor = new PIDVelocityMotor(name, motor, enc, velPID);
        this.enc = enc;
        kp = posPID[0];
        ki = posPID[1];
        kd = posPID[2];
        max = velPID[3];
    }

    //PIDPositionMotor control
    public void run() {
        if(System.currentTimeMillis() - 10 > lastTime) {
            position = enc.getDistance();
            while(position > 360 || position < -360) {
                position /= 360;
            }
            error = target - enc.getDistance();
            if(target == enc.getDistance()) {
                integral = 0.0;
            }
            deltaTime = System.currentTimeMillis() - lastTime;

            integral += error * deltaTime;
            derivative = (error - lastError) / deltaTime;
            out = (kp * error) + (ki * integral) + (kd * derivative);
            out *= max;
            if(out >= max) {
                motor.run(max);
            } else if(out <= -max) {
                motor.run(-max);
            } else {
                motor.run(out);
            }
            System.out.println("Name: " + name + " KP: " + kp + " Target: " + target + " CurrentPos: " + enc.getDistance() + " Error: " + error + " Get(): " + enc.get() + " Out: " + out + "\n");

            lastError = error;
            lastTime = System.currentTimeMillis();
        }
    }
    
    //PIDPositionMotor control
    public void run(double pos) {
        target = pos;
        run();
    }
}
