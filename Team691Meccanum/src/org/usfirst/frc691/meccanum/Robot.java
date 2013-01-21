package org.usfirst.frc691.meccanum;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;


public class Robot extends SimpleRobot {
	
    //Joysticks
    Joystick joy = new Joystick(Values.DRIVE_JOYSTICK);

    //Meccanum drive
    Meccanum drive;

    public void robotInit() {
        drive = new Meccanum(Values.DRIVE_VICTOR_SIDECARS, Values.DRIVE_VICTORS); //No encoders
        /*drive = new Meccanum(
                        Values.DRIVE_VICTOR_SIDECARS,
                        Values.DRIVE_VICTORS,
                        Values.DRIVE_ENCODER_SIDECARS,
                        Values.DRIVE_ENCODERS,
                        Values.DRIVE_ENCODER_COUNTS,
                        Values.DRIVE_PID
        ); */	//With encoders
        drive.stop();
    }
	
    public void operatorControl() {
        while(isEnabled() && isOperatorControl()) {
            drive.update(joy.getRawAxis(2), joy.getRawAxis(1), joy.getRawAxis(3));
                       //Forward            Right              Clockwise
        }
    }
}

