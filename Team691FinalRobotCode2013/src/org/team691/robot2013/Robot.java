package org.team691.robot2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {
    
    //Joysticks
    Joystick driveJoy;
    Joystick shooterJoy;
    
    //Meccanum drive
    Meccanum drive;
    
    //Shooter
    Shooter shooter;
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void robotInit() {
        driveJoy = new Joystick(Values.DRIVE_JOYSTICK);
        driveJoy = new Joystick(Values.SHOOTER_JOYSTICK);
        
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
        
        shooter = new Shooter(
                Values.SHOOTER_VICTOR,
                Values.SHOOTER_ENCODER,
                Values.SHOOTER_TILT_VICTOR);
        shooter.stop();
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        //TODO: Do stuff!
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while(isEnabled() && isOperatorControl()) {
            drive.move(driveJoy);      
            shooter.shoot(shooterJoy);
        }
    }
}