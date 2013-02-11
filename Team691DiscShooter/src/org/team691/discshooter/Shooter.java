package org.team691.discshooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {

    private SpeedController shooterVic;
    private SpeedController shooterAccelVic;
    private SpeedController tiltVic;
    private Encoder shooterEncoder;
    private Encoder shooterAccelEncoder;
    private Encoder tiltEncoder;
    private PIDVelocityMotor shooterMotor;
    private PIDVelocityMotor shooterAccelMotor;
    private PIDPositionMotor tiltMotor;

    public Shooter(int[] shooterSlot, int[] shooterChannel, int[][] shooterEnc, double[] shooterPID, int tiltSlot, int tiltChannel, int[] tiltEnc, double[] tiltPosPID, double[] tiltPID) {
        shooterVic = new Victor(shooterSlot[0], shooterChannel[0]);
        shooterEncoder = new Encoder(shooterEnc[0][0], shooterEnc[0][1], shooterEnc[0][0], shooterEnc[0][2], (shooterEnc[0][4] == 1 ? true : false));
        shooterEncoder.setDistancePerPulse(shooterEnc[0][3]);
        shooterEncoder.start();
        shooterMotor = new PIDVelocityMotor("Shooter", shooterVic, shooterEncoder, shooterPID);
        
        shooterAccelVic = new Victor(shooterSlot[1], shooterChannel[1]);
        shooterAccelEncoder = new Encoder(shooterEnc[1][0], shooterEnc[1][1], shooterEnc[1][0], shooterEnc[1][2], (shooterEnc[1][4] == 1 ? true : false));
        shooterAccelEncoder.setDistancePerPulse(shooterEnc[1][3]);
        shooterAccelEncoder.start();
        shooterAccelMotor = new PIDVelocityMotor("ShooterAccel", shooterAccelVic, shooterAccelEncoder, shooterPID);

        tiltVic = new Victor(tiltSlot, tiltChannel);
        tiltEncoder = new Encoder(tiltEnc[0], tiltEnc[1], tiltEnc[0], tiltEnc[2], (tiltEnc[4] == 1 ? true : false));
        tiltEncoder.setDistancePerPulse(tiltEnc[3]);
        tiltEncoder.start();
        tiltMotor = new PIDPositionMotor("Tilt", tiltVic, tiltEncoder, tiltPosPID, tiltPID);
    }
    
    public void update(double shooterSpeed, double tiltPos) {
        shooterMotor.run(shooterSpeed);
        shooterAccelMotor.run(shooterSpeed);
        tiltMotor.run(tiltPos);
    }
    
    public void shoot(double shooterSpeed) {
        shooterMotor.run(shooterSpeed);
        shooterAccelMotor.run(shooterSpeed);
    }
    
    public void tilt(double tiltPos){
        tiltMotor.run(tiltPos);
    }
    
    public void resetTilt() {
        tiltEncoder.reset();
    }

    public void stop() {
        shooterMotor.run(0.0);
        shooterAccelMotor.run(0.0);
    }
}