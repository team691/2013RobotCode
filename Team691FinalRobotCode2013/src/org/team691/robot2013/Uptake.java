package org.team691.robot2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Uptake {
    
    private SpeedController uptake;
    
    public Uptake(int sidecar, int port) {
        uptake = new Victor(sidecar, port);
    }
    
    public void feed(Joystick joy) {
        if(joy.getRawButton(6)) {
            uptake.set(0.5);    //Forward
        } else if(joy.getRawButton(7)){
            uptake.set(-0.5);   //Backward
        } else {
            stop(); //Stop
        }
    }
    
    public void stop() {
        uptake.set(0.0);
    }
}
