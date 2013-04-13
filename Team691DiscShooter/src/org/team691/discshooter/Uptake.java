package org.team691.discshooter;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Uptake {
    
    private SpeedController spiral;
    private Servo gatekeeper;
    
    public Uptake(int spiralSlot, int spiralChannel, int gatelot, int gateChannel) {
        spiral = new Victor(spiralSlot, spiralChannel);
        gatekeeper = new Servo(gatelot, gateChannel);
    }
    
    public void update(double speed, boolean open) {
        spiral.set(speed);
        if(open) {
            gatekeeper.setAngle(0.0);
        } else {
            gatekeeper.setAngle(45.0);
        }
    }
    
    public void feed(double speed) {
        gatekeeper.setAngle(0.0);
        spiral.set(speed);
    }
    
    public void run(double speed) {
        spiral.set(speed);
    }
    
    public void open() {
        gatekeeper.setAngle(0.0);
    }
    
    public void close() {
        gatekeeper.setAngle(45.0);
    }
    
    public void stop() {
        spiral.set(0.0);
        gatekeeper.setAngle(45.0);
    }
}