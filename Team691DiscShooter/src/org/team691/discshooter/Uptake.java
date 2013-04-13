package org.team691.discshooter;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;

public class Uptake {
    
    private Relay spiral;
    private Servo gatekeeper;
    
    public Uptake(int spiralSlot, int spiralChannel, int gateSlot, int gateChannel) {
        spiral = new Relay(spiralSlot, spiralChannel);
        gatekeeper = new Servo(gateSlot, gateChannel);
    }
    
    public void update(Relay.Value direction, boolean open) {
        spiral.set(direction);
        if(open) {
            gatekeeper.setAngle(70.0);
        } else {
            gatekeeper.setAngle(180.0);
        }
    }
    
    public void feed() {
        gatekeeper.setAngle(70.0);
        spiral.set(Relay.Value.kForward);
    }
    
    public void run(Relay.Value direction) {
        spiral.set(direction);
    }
    
    public void open() {
        gatekeeper.setAngle(70.0);
    }
    
    public void close() {
        gatekeeper.setAngle(180.0);
    }
    
    public void stop() {
        spiral.set(Relay.Value.kOff);
        gatekeeper.setAngle(180.0);
    }
}