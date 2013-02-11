package org.team691.discshooter;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Intake {
    
    //Arm, Wrist, and Hand
    private SpeedController armVic;
    private Encoder armEncoder;
    private PIDPositionMotor arm;
    private Relay wrist;
    private Relay intake;
    
    //Wrist sensors
    private DigitalInput flipLimit;
    private boolean flipped = false;
    private boolean transition = false;
    
    public Intake(int armChannel, int armSlot, int[] armEnc, double[] armPosPID, double[] armPID, int wristPort, int wristSidecar, int wristLimit, int intakePort, int intakeSidecar){
        armVic = new Victor(armSlot, armChannel);
        armEncoder = new Encoder(armEnc[0], armEnc[1], armEnc[0], armEnc[2]);
        armEncoder.setDistancePerPulse(360 / armEnc[3]);
        armEncoder.start();
        arm = new PIDPositionMotor("Arm", armVic, armEncoder, armPosPID, armPID);
        
        wrist = new Relay(wristPort, wristSidecar);
        flipLimit = new DigitalInput(wristSidecar, wristLimit);
        
        intake = new Relay(intakePort, intakeSidecar);
    }
    
    public void update(double reach, boolean flip, Relay.Value grab) {
        arm.run(reach);
        
        if(flip != !flipped && transition == false) {
            if(!flipped) {
                wrist.set(Relay.Value.kForward);
            } else {
                wrist.set(Relay.Value.kReverse);
            }
            flipped = !flipped;
            transition = true;
        } else if(flip == !flipped && transition == true) {
            if(flipLimit.get()) {
                wrist.set(Relay.Value.kOff);
                transition = false;
            }
        }
        
        intake.set(grab);
    }
    
    public void reach(double pos) {
        arm.run(pos);
    }
    
    public void flip(boolean upward) {
        if(upward != !flipped && transition == false) {
            if(!flipped) {
                wrist.set(Relay.Value.kForward);
            } else {
                wrist.set(Relay.Value.kReverse);
            }
            flipped = !flipped;
            transition = true;
        } else if(upward == !flipped && transition == true) {
            if(flipLimit.get()) {
                wrist.set(Relay.Value.kOff);
                transition = false;
            }
        }
    }
    
    public boolean isFlipped() {
        return flipped;
    }
    
    public void grab(Relay.Value direction) {
        intake.set(direction);
    }
    
    public void stop() {
        wrist.set(Relay.Value.kOff);
        intake.set(Relay.Value.kOff);
    }
}