package org.team691.discshooter;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
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
    private DigitalInput topLimit;
    private DigitalInput bottomLimit;
    private Counter tachometer;
    private double tachTarget = 0.0;
    private long tachTime = 0;
    private double lastTiltPos = 0.0;
    private int tiltResetCounter = 0;
    private boolean useEncoders = true;
    private boolean useLimits = true;

    public Shooter(int[] shooterSlot, int[] shooterChannel, int[][] shooterEnc, double[] shooterPID, int tiltSlot, int tiltChannel, int[] tiltEnc, double[] tiltPosPID, double[] tiltPID, int[] tiltLimits) {
        useEncoders = true;
        useLimits = true;
        
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
        
        topLimit = new DigitalInput(tiltLimits[0], tiltLimits[1]);
        bottomLimit = new DigitalInput(tiltLimits[0], tiltLimits[2]);
    }
    
    public Shooter(int[] shooterSlot, int[] shooterChannel, int tiltSlot, int tiltChannel, int[] tiltLimits, int tachSlot, int tachChannel, double targetRPM) {
        useEncoders = false;
        useLimits = true;
        shooterVic = new Victor(shooterSlot[0], shooterChannel[0]);
        shooterAccelVic = new Victor(shooterSlot[1], shooterChannel[1]);
        tiltVic = new Victor(tiltSlot, tiltChannel);
        topLimit = new DigitalInput(tiltLimits[0], tiltLimits[1]);
        bottomLimit = new DigitalInput(tiltLimits[0], tiltLimits[2]);
        tachometer = new Counter(tachSlot, tachChannel);
        tachTarget = targetRPM;
    }
    
    public Shooter(int[] shooterSlot, int[] shooterChannel, int tiltSlot, int tiltChannel, int[] tiltLimits) {
        useEncoders = false;
        useLimits = true;
        shooterVic = new Victor(shooterSlot[0], shooterChannel[0]);
        shooterAccelVic = new Victor(shooterSlot[1], shooterChannel[1]);
        tiltVic = new Victor(tiltSlot, tiltChannel);
        topLimit = new DigitalInput(tiltLimits[0], tiltLimits[1]);
        bottomLimit = new DigitalInput(tiltLimits[0], tiltLimits[2]);
    }
    
    public Shooter(int[] shooterSlot, int[] shooterChannel, int tiltSlot, int tiltChannel) {
        useEncoders = false;
        useLimits = false;
        shooterVic = new Victor(shooterSlot[0], shooterChannel[0]);
        shooterAccelVic = new Victor(shooterSlot[1], shooterChannel[1]);
        tiltVic = new Victor(tiltSlot, tiltChannel);
    }
    
    public void update(double shooterSpeed,double accelSpeed, double tiltPos) {
        if(useEncoders) {
            shooterMotor.run(shooterSpeed);
            shooterAccelMotor.run(accelSpeed);
            tiltMotor.run(tiltPos);
        } else {
            shooterVic.set(shooterSpeed);
            shooterAccelVic.set(accelSpeed);
            tiltVic.set(tiltPos);
        }
    }
    
    public void shoot(double shooterSpeed, double accelSpeed) {
        if(useEncoders) {
            shooterMotor.run(shooterSpeed);
            shooterAccelMotor.run(accelSpeed);
        } else {
            shooterVic.set(shooterSpeed);
            shooterAccelVic.set(accelSpeed);
        }
    }
    
    public void tilt(double tiltPos, boolean force){
        if(useEncoders) {
            if(useLimits) {
                if(tiltPos >= 0 && !topLimit.get()) {
                    tiltMotor.run(tiltPos);
                } else if(tiltPos < 0 && !bottomLimit.get()) {
                    tiltMotor.run(tiltPos);
                } else if(force) {
                    tiltMotor.run(tiltPos);
                }
            } else {
                tiltMotor.run(tiltPos);
            }
        } else {
            if(useLimits) {
                if(tiltPos >= 0 && !topLimit.get()) {
                    tiltVic.set(tiltPos);
                } else if(tiltPos < 0 && !bottomLimit.get()) {
                    tiltVic.set(tiltPos);
                } else if(force) {
                    tiltVic.set(tiltPos);
                }
            } else {
                tiltVic.set(tiltPos);
            }
        }
    }
    
    public boolean isReady() {
        if(useEncoders && shooterMotor.atTarget() && shooterAccelMotor.atTarget()) {
            return true;
        } else if(useEncoders) {
            return false;
        } else if(tachometer != null && Math.abs((tachometer.get() / (System.currentTimeMillis() - tachTime)) - tachTarget) <= 0.05) {
            tachTime = System.currentTimeMillis();
            tachometer.reset();
            return true;
        } else if (tachometer != null) {
            tachTime = System.currentTimeMillis();
            tachometer.reset();
            return false;
        } else {
            return true;
        }
    }
    
    public boolean resetTilt() {
        if(useEncoders) {
            tiltMotor.run(tiltEncoder.getDistance());
            tiltVic.set(0.1);

            if(Math.abs(tiltEncoder.getDistance() - lastTiltPos) <= 5) {
                tiltResetCounter++;
            } else {
                tiltResetCounter = 0;
            }        
            lastTiltPos = tiltEncoder.getDistance();

            if(tiltResetCounter >= 5) {
                tiltVic.set(0.0);
                tiltEncoder.reset();
                tiltResetCounter = 0;
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public void stop() {
        if(useEncoders) {
            shooterMotor.run(0.0);
            shooterAccelMotor.run(0.0);
        } else {
            shooterVic.set(0.0);
            shooterAccelVic.set(0.0);
        }
    }
}