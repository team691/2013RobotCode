package org.usfirst.frc691.discshooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {

    private PIDMotor shooter;
    private Victor tilt;
    private static final double shooterRPM = 0.0; //TODO: Change this to best value after prototype! Final because value doesn't change.
    private double targetRPM = 0.0;	//TODO: Remove for final robot! Prototype only!
    
    //Shooter PID
    private static final double kp = 0.0;
    private static final double ki = 0.0;
    private static final double kd = 0.0;

    public Shooter(int shooterVic, int[] shooterEnc, int tiltVic) {
        Encoder shooterEncoder = new Encoder(shooterEnc[0], shooterEnc[1], shooterEnc[0], shooterEnc[2]);
        shooterEncoder.setDistancePerPulse(360 / shooterEnc[3]);
        shooter = new PIDMotor(new Victor(shooterVic), shooterEncoder, kp, ki, kd);

        tilt = new Victor(tiltVic);
    }
    
    public void shoot(Joystick joy) {
        if (joy.getRawButton(3)) {
            setSpeed(true);     //Add 50 RPM
        } else if (joy.getRawButton(2)) {
            setSpeed(false);    //Subtract 50 RPM
        } else if (joy.getRawButton(1)) {
            shooter.set(0.0);   //Stop
        } else if(joy.getRawButton(4)){
            shooter.set(shooterRPM);    //Full Speed
        } else {
            shooter.set(shooterRPM / 2);    //Idle
        }
        
        shooter.run();
        tilt.set(joy.getRawAxis(2));
    }

    public void setSpeed(boolean speedUp) { //TODO: Remove for final robot! Prototype only!
        if (speedUp) {
            targetRPM += 50;
            shooter.set(targetRPM);
        } else {
            targetRPM -= 50;
            shooter.set(targetRPM);
        }
    }

    public double getSpeed() {  //TODO: Remove for final robot! Prototype only!
        return targetRPM;
    }

    public void stop() {
        shooter.set(0.0);
    }
}