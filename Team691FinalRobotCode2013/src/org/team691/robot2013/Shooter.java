package org.team691.robot2013;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {

    private PIDMotor shooterMotor;
    private PIDMotor tiltMotor;

    public Shooter(int shooterVic, int shooterSidecar, int[] shooterEnc, double[] shooterPID, int tiltVic, int tiltVicSidecar, int[] tiltEnc, double[] tiltPID) {
        Encoder shooterEncoder = new Encoder(shooterEnc[0], shooterEnc[1], shooterEnc[0], shooterEnc[2]);
        shooterEncoder.setDistancePerPulse(360 / shooterEnc[3]);
        shooterMotor = new PIDMotor("Shooter", true, new Victor(shooterVic, shooterSidecar), shooterEncoder, shooterPID[0], shooterPID[1], shooterPID[2], shooterPID[3]);

        Encoder tiltEncoder = new Encoder(tiltEnc[0], tiltEnc[1], tiltEnc[0], tiltEnc[2]);
        tiltEncoder.setDistancePerPulse(360 / tiltEnc[3]);
        //TODO: Make PID position, not velocity!
        tiltMotor = new PIDMotor("Tilt", false, new Victor(tiltVic, tiltVicSidecar), tiltEncoder, tiltPID[0], tiltPID[1], tiltPID[2], tiltPID[3]);
    }
    
    public void update(double shooterSpeed, double tiltPos) {
        shooterMotor.run(shooterSpeed);
        tiltMotor.run(tiltPos);
    }
    
    public void shoot(double shooterSpeed) {
        shooterMotor.run(shooterSpeed);
    }
    
    public void tilt(double tiltPos){
        tiltMotor.run(tiltPos);
    }

    public void stop() {
        shooterMotor.set(0.0);
    }
}