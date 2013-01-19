package org.usfirst.frc691.discshooter;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {

    Joystick joy = new Joystick(Values.SHOOTER_JOYSTICK);
    DriverStationLCD dslcd = DriverStationLCD.getInstance();
    Shooter shooter;

    public void robotInit() {
        shooter = new Shooter(
                Values.SHOOTER_VICTOR,
                Values.SHOOTER_ENCODER,
                Values.SHOOTER_TILT_VICTOR);
        shooter.stop();
    }

    public void operatorControl() {
        while (isEnabled() && isOperatorControl()) {
            shooter.shoot(joy);
            
            dslcd.println(DriverStationLCD.Line.kMain6, 1, Double.toString(shooter.getSpeed()));
            dslcd.updateLCD();
        }
    }
}
