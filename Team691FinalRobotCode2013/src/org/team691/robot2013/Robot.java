package org.team691.robot2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {
    
    //Joysticks
    Joystick driveJoy;
    Joystick shooterJoy;
    
    //Meccanum drive
    Meccanum drive;
    
    //Shooter, Uptake, & Intake
    Shooter shooter;
    Uptake uptake;
    Intake intake;
    
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
                Values.SHOOTER_VICTOR_SIDECAR,
                Values.SHOOTER_ENCODER,
                Values.SHOOTER_PID,
                Values.SHOOTER_TILT_VICTOR, 
                Values.SHOOTER_TILT_VICTOR_SIDECAR,
                Values.SHOOTER_TILT_ENCODER,
                Values.SHOOTER_TILT_PID);
        shooter.stop();
        
        uptake = new Uptake(Values.UPTAKE_VICTOR_SIDECAR, Values.UPTAKE_VICTOR);
        uptake.stop();
        
        intake = new Intake(
                Values.INTAKE_ARM_VICTOR,
                Values.INTAKE_ARM_VICTOR_SIDECAR,
                Values.INTAKE_ARM_ENCODER,
                Values.INTAKE_ARM_PID,
                Values.INTAKE_WRIST_RELAY,
                Values.INTAKE_WRIST_RELAY_SIDECAR,
                Values.INTAKE_WRIST_LIMIT,
                Values.INTAKE_GRABBER_RELAY,
                Values.INTAKE_GRABBER_RELAY_SIDECAR);
        intake.stop();
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
            drive.update(driveJoy.getRawAxis(2), driveJoy.getRawAxis(1), driveJoy.getRawAxis(3));
                       //Forward            Right              Clockwise
            
            if (shooterJoy.getRawButton(1)) {
                shooter.shoot(Values.SHOOTER_RPM);      //Full Speed
            } else if(shooterJoy.getRawButton(4) || shooterJoy.getRawButton(5)){
                shooter.stop();                         //Stop
            } else {
                shooter.shoot(Values.SHOOTER_RPM_IDLE); //Idle
            }
            shooter.tilt(shooterJoy.getRawAxis(2));
            
            if(shooterJoy.getRawButton(6)) {
                uptake.feed(Values.UPTAKE_SPEED);       //Forward
            } else if(shooterJoy.getRawButton(7)){
                uptake.feed(-Values.UPTAKE_SPEED);      //Backward
            } else {
                uptake.stop();                          //Stop
            }
            
            intake.reach(shooterJoy.getRawAxis(2));
            if(shooterJoy.getRawButton(8)) {
                intake.flip(true);                      //Flip upside up
            } else if(shooterJoy.getRawButton(9)) {
                intake.flip(false);                     //Flip upside down
            } else {
                intake.flip(intake.isFlipped());        //Finish flipping/don't flip
            }
            if(shooterJoy.getRawButton(11)) {
                intake.grab(Relay.Value.kForward);      //Run intake forward
            } else if(shooterJoy.getRawButton(10)) {
                intake.grab(Relay.Value.kReverse);      //Run intake backward
            } else {
                intake.grab(Relay.Value.kOff);          //Turn off intake
            }
        }
    }
}