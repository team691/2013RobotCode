package org.team691.discshooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {

    private SpeedController shooterVic;
    private SpeedController tiltVic;
    private Encoder shooterEncoder;
    private Encoder tiltEncoder;
    private PIDMotor shooterMotor;
    private PIDPositionMotor tiltMotor;

    public Shooter(int shooterSlot, int shooterChannel, int[] shooterEnc, double[] shooterPID, int tiltSlot, int tiltChannel, int[] tiltEnc, double[] tiltPosPID, double[] tiltPID) {
        shooterVic = new Victor(shooterSlot, shooterChannel);
        shooterEncoder = new Encoder(shooterEnc[0], shooterEnc[1], shooterEnc[0], shooterEnc[2], (shooterEnc[4] == 1 ? true : false));
        shooterEncoder.setDistancePerPulse(shooterEnc[3]);
        shooterEncoder.start();
        shooterMotor = new PIDMotor("Shooter", true, shooterVic, shooterEncoder, shooterPID[0], shooterPID[1], shooterPID[2], shooterPID[3]);

        tiltVic = new Victor(tiltSlot, tiltChannel);
        tiltEncoder = new Encoder(tiltEnc[0], tiltEnc[1], tiltEnc[0], tiltEnc[2], (tiltEnc[4] == 1 ? true : false));
        tiltEncoder.setDistancePerPulse(tiltEnc[3]);
        tiltEncoder.start();
        tiltMotor = new PIDPositionMotor("Tilt", tiltVic, tiltEncoder, tiltPosPID, tiltPID);
    }
    
    public String get() { 
        return "Shooter: " + shooterEncoder.get() + " Tilt: " + tiltEncoder.get();  //TODO: Debug function, remove for final code!
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