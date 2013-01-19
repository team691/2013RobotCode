package org.usfirst.frc691.discshooter;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {

    Joystick joy = new Joystick(2);
    DriverStationLCD dslcd = DriverStationLCD.getInstance();
    Shooter shooter;

    public void robotInit() {
        shooter = new Shooter(
                Values.SHOOTER_VICTOR,
                Values.SHOOTER_ENCODER,
                Values.TILT_VICTOR);
        shooter.stop();
    }

    public void operatorControl() {
        while (isEnabled() && isOperatorControl()) {
            shooter.run();
            if (joy.getRawButton(3)) {
                shooter.setSpeed(true);
            } else if (joy.getRawButton(2)) {
                shooter.setSpeed(false);
            } else if (joy.getRawButton(1)) {
                shooter.stop();
            }
            dslcd.println(DriverStationLCD.Line.kMain6, 1, Double.toString(shooter.getSpeed()));
            dslcd.updateLCD();
        }
    }
}
