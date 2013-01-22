package org.usfirst.frc691.discshooter;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Uptake {
    
    private SpeedController uptake;
    
    public Uptake(int sidecar, int port) {
        uptake = new Victor(sidecar, port);
    }
    
    public void update(double speed) {
        uptake.set(speed);
    }
    
    public void feed(double speed) {
        uptake.set(speed);
    }
    
    public void stop() {
        uptake.set(0.0);
    }
}