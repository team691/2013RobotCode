package org.team691.discshooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Intake {
    
    //Arm and Wrist
    private SpeedController armVic;
    private Encoder armEncoder;
    private PIDPositionMotor arm;
    private Victor wrist;
    private boolean useEncoders = true;
    
    public Intake(int armSlot, int armChannel, int[] armEnc, double[] armPosPID, double[] armPID, int wristSlot, int wristChannel){
        useEncoders = true;
        armVic = new Victor(armSlot, armChannel);
        armEncoder = new Encoder(armEnc[0], armEnc[1], armEnc[0], armEnc[2]);
        armEncoder.setDistancePerPulse(360 / armEnc[3]);
        armEncoder.start();
        arm = new PIDPositionMotor("Arm", armVic, armEncoder, armPosPID, armPID);        
        wrist = new Victor(wristSlot, wristChannel);
    }
    
    public Intake(int armSlot, int armChannel, int wristSlot, int wristChannel){
        useEncoders = false;
        armVic = new Victor(armSlot, armChannel);
        wrist = new Victor(wristSlot, wristChannel);
    }
    
    public void update(double reach, double flip) {
        if(useEncoders) {
            arm.run(reach);            
        } else {
            armVic.set(0.0);
        }
        wrist.set(flip);
    }
    
    public void reach(double pos) {
        if(useEncoders) {
            arm.run(pos);            
        } else {
            armVic.set(pos);
        }
    }
    
    public void flip(double speed) {
        wrist.set(speed);
    }
    
    public void stop() {
        if(useEncoders) {
            wrist.set(0.0);
        } else {
            armVic.set(0.0);
            wrist.set(0.0);
        }
    }
}