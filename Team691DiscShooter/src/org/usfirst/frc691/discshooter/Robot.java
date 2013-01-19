package org.usfirst.frc691.discshooter;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {

    //Driver station I/O
    Joystick joy = new Joystick(Values.SHOOTER_JOYSTICK);
    DriverStationLCD dslcd = DriverStationLCD.getInstance();
    
    //Shooter, Uptake, & Intake
    Shooter shooter;
    Uptake uptake;

    public void robotInit() {
        shooter = new Shooter(
                Values.SHOOTER_VICTOR,
                Values.SHOOTER_VICTOR_SIDECAR,
                Values.SHOOTER_ENCODER,
                Values.SHOOTER_TILT_VICTOR, 
                Values.SHOOTER_TILT_VICTOR_SIDECAR);
        shooter.stop();
        
        uptake = new Uptake(Values.UPTAKE_VICTOR_SIDECAR, Values.UPTAKE_VICTOR);
        uptake.stop();
    }

    public void operatorControl() {
        while (isEnabled() && isOperatorControl()) {
            shooter.shoot(joy);
            uptake.feed(joy);
            
            dslcd.println(DriverStationLCD.Line.kMain6, 1, Double.toString(shooter.getSpeed()));
            dslcd.updateLCD();
        }
    }
}
