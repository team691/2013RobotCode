package org.team691.meccanum;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;


public class Robot extends SimpleRobot {
	
    //Joysticks
    Joystick joy = new Joystick(Values.DRIVE_JOYSTICK);
    double forward = 0.0;
    double right = 0.0;
    double clockwise = 0.0;
    double scalar = 0.0;

    //Meccanum drive
    Meccanum drive;

    public void robotInit() {
        //drive = new Meccanum(Values.DRIVE_VICTOR_SIDECARS, Values.DRIVE_VICTORS); //No encoders
        drive = new Meccanum(
                        Values.DRIVE_VICTOR_SIDECARS,
                        Values.DRIVE_VICTORS,
                        Values.DRIVE_ENCODER_SIDECARS,
                        Values.DRIVE_ENCODERS,
                        Values.DRIVE_ENCODER_DISTANCES_PER_PULSE,
                        Values.DRIVE_ENCODER_REVERSES,
                        Values.DRIVE_PID
        );  //With encoders
        drive.stop();
        scalar =  (Values.FR_DRIVE_PID_SCALAR + Values.FL_DRIVE_PID_SCALAR + Values.BR_DRIVE_PID_SCALAR + Values.BL_DRIVE_PID_SCALAR) / 4;
    }
	
    public void operatorControl() {
        while(isEnabled() && isOperatorControl()) {
            if(Math.abs(joy.getRawAxis(2)) < 0.2) {
                forward = 0.0;
            } else {
                forward = joy.getRawAxis(2) * scalar;
            }
            if(Math.abs(joy.getRawAxis(1)) < 0.2) {
                right = 0.0;
            } else {
                right = joy.getRawAxis(1) * scalar;
            }
            if(Math.abs(joy.getRawAxis(3)) < 0.2) {
                clockwise = 0.0;
            } else {    // if(Math.abs(joy.getRawAxis(3)) < 0.75) {
                clockwise = joy.getRawAxis(3) * 0.75 * scalar;
            }/* else {
                clockwise = joy.getRawAxis(3);
            }*/
            drive.update(forward, right, -clockwise);
                       //Forward  Right  Clockwise
            System.out.println("Joystick:" + forward + " " + right + " " + clockwise);
        }
    }
}

