package org.team691.meccanum;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;


public class Robot extends SimpleRobot {
	
    //Joysticks
    Joystick driveJoy = new Joystick(Values.DRIVE_JOYSTICK);

    //Meccanum drive
    Meccanum drive;
    double forward = 0.0;
    double right = 0.0;
    double clockwise = 0.0;
    //double scalar = 0.0;
    
    //Autonomous
    long autoStart = 0;

    public void robotInit() {
        drive = new Meccanum(Values.DRIVE_VICTOR_SIDECARS, Values.DRIVE_VICTORS); //No encoders
        /*drive = new Meccanum(
                        Values.DRIVE_VICTOR_SIDECARS,
                        Values.DRIVE_VICTORS,
                        Values.DRIVE_ENCODER_SIDECARS,
                        Values.DRIVE_ENCODERS,
                        Values.DRIVE_ENCODER_DISTANCES_PER_PULSE,
                        Values.DRIVE_ENCODER_REVERSES,
                        Values.DRIVE_PID
        );*/  //With encoders
        drive.stop();
        //scalar = (Values.FR_DRIVE_PID_SCALAR + Values.FL_DRIVE_PID_SCALAR + Values.BR_DRIVE_PID_SCALAR + Values.BL_DRIVE_PID_SCALAR) / 4;
    }
    
    public void autonomous() {
        autoStart = System.currentTimeMillis();
        while(isEnabled() && isAutonomous()) {            
            /*if(((System.currentTimeMillis() - autoStart) > 12000) && ((System.currentTimeMillis() - autoStart) < 14000)) {
                drive.update(-0.25, 0.0, 0.0);  //Drive Forward
            } else {
                drive.update(0.0, 0.0, 0.0);    //Stop
            }*/
        }
    }
	
    public void operatorControl() {
        while(isEnabled() && isOperatorControl()) {
            if(Math.abs(driveJoy.getRawAxis(2)) < 0.2) {
                forward = 0.0;
            } else {
                forward = driveJoy.getRawAxis(2);
                forward *= Math.abs(forward);
                //forward *= scalar;
            }
            if(Math.abs(driveJoy.getRawAxis(1)) < 0.2) {
                right = 0.0;
            } else {
                right = driveJoy.getRawAxis(1);
                right *= Math.abs(right);
                //right *= scalar;
            }
            if(Math.abs(driveJoy.getRawAxis(3)) < 0.2) {
                clockwise = 0.0;
            } else {
                clockwise = driveJoy.getRawAxis(3);
                if(clockwise <= 0.5) {
                    clockwise *= 0.5;
                } else {
                    clockwise *= Math.abs(clockwise);
                }
                //clockwise *= scalar;
            }
            drive.update(forward, right, -clockwise);
                       //Forward  Right  Clockwise
            
            System.out.println("Joystick:" + forward + " " + right + " " + clockwise);
            
            /*if(driveJoy.getRawButton(1)) {
                drive.update(1.0, 0.0, 0.0);
            } else if(driveJoy.getRawButton(3)) {
                drive.update(0.75, 0.0, 0.0);
            } else if(driveJoy.getRawButton(2)) {
                drive.update(0.5, 0.0, 0.0);
            } else {
                drive.update(0.0, 0.0, 0.0);
            }*/
        }
    }
}