package org.team691.robot2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {
    
    //Joysticks
    Joystick driveJoy = new Joystick(Values.DRIVE_JOYSTICK);
    Joystick shooterJoy = new Joystick(Values.SHOOTER_JOYSTICK);
    
    //Meccanum drive
    Meccanum drive;
    double forward = 0.0;
    double right = 0.0;
    double clockwise = 0.0;
    double scalar = 0.0;
    
    //Shooter, Uptake, & Intake
    //Shooter shooter;
    //Uptake uptake;
    Intake intake;
    
    //Autonomous
    long autoStart = 0;
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void robotInit() {
        driveJoy = new Joystick(Values.DRIVE_JOYSTICK);
        shooterJoy = new Joystick(Values.SHOOTER_JOYSTICK);
        
        drive = new Meccanum(Values.DRIVE_VICTOR_SIDECARS, Values.DRIVE_VICTORS); //No encoders
        /*drive = new Meccanum(
                        Values.DRIVE_VICTOR_SIDECARS,
                        Values.DRIVE_VICTORS,
                        Values.DRIVE_ENCODER_SIDECARS,
                        Values.DRIVE_ENCODERS,
                        Values.DRIVE_ENCODER_DISTANCES_PER_PULSE,
                        Values.DRIVE_ENCODER_REVERSES,
                        Values.DRIVE_PID
        );  //With encoders
        drive.stop();*/
        //scalar = (Values.FR_DRIVE_PID_SCALAR + Values.FL_DRIVE_PID_SCALAR + Values.BR_DRIVE_PID_SCALAR + Values.BL_DRIVE_PID_SCALAR) / 4;
        
        //shooter = new Shooter(Values.SHOOTER_VICTOR_SIDECARS, Values.SHOOTER_VICTORS, Values.SHOOTER_TILT_VICTOR_SIDECAR, Values.SHOOTER_TILT_VICTOR);  //No Encoders
        /*shooter = new Shooter(
                Values.SHOOTER_VICTOR_SIDECARS,
                Values.SHOOTER_VICTORS,
                Values.SHOOTER_ENCODERS,
                Values.SHOOTER_PID,
                Values.SHOOTER_TILT_VICTOR_SIDECAR,
                Values.SHOOTER_TILT_VICTOR, 
                Values.SHOOTER_TILT_ENCODER,
                Values.SHOOTER_TILT_POSITION_PID,
                Values.SHOOTER_TILT_PID);*/ //With Encoders
        //shooter.stop();
        
        //uptake = new Uptake(Values.UPTAKE_VICTOR_SIDECAR, Values.UPTAKE_VICTOR);
        //uptake.stop();
        
        intake = new Intake(
                Values.INTAKE_ARM_VICTOR_SIDECAR,
                Values.INTAKE_ARM_VICTOR,
                Values.INTAKE_WRIST_RELAY_SIDECAR,
                Values.INTAKE_WRIST_RELAY,
                Values.INTAKE_WRIST_LIMIT,
                Values.INTAKE_GRABBER_RELAY_SIDECAR,
                Values.INTAKE_GRABBER_RELAY);   //No Encoders
        /*intake = new Intake(
                Values.INTAKE_ARM_VICTOR_SIDECAR,
                Values.INTAKE_ARM_VICTOR,
                Values.INTAKE_ARM_ENCODER,
                Values.INTAKE_ARM_PID,
                Values.INTAKE_WRIST_RELAY_SIDECAR,
                Values.INTAKE_WRIST_RELAY,
                Values.INTAKE_WRIST_LIMIT,
                Values.INTAKE_GRABBER_RELAY_SIDECAR,
                Values.INTAKE_GRABBER_RELAY); */    //With Encoders
        intake.stop();
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
        
    public void autonomous() {
        autoStart = System.currentTimeMillis();
        while(isEnabled() && isAutonomous()) {
            //while(!shooter.resetTilt()) {}   
            if((System.currentTimeMillis() - autoStart) < 2000) {
                drive.update(-0.25, 0.0, 0.0);
            } else{
                drive.update(0.0, 0.0, 0.0);
            }
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while(isEnabled() && isOperatorControl()) {
            if(Math.abs(driveJoy.getRawAxis(2)) < 0.2) {
                forward = 0.0;
            } else {
                forward = driveJoy.getRawAxis(2) ;//* scalar;
            }
            if(Math.abs(driveJoy.getRawAxis(1)) < 0.2) {
                right = 0.0;
            } else {
                right = driveJoy.getRawAxis(1) ;//* scalar;
            }
            if(Math.abs(driveJoy.getRawAxis(3)) < 0.2) {
                clockwise = 0.0;
            } else {    // if(Math.abs(joy.getRawAxis(3)) < 0.75) {
                clockwise = driveJoy.getRawAxis(3) * 0.75 ;//* scalar;
            }/* else {
                clockwise = joy.getRawAxis(3);
            }*/
            drive.update(forward, right, -clockwise);
                       //Forward  Right  Clockwise
            
            /*if (shooterJoy.getRawButton(1) && shooter.isReady()) {
                shooter.shoot(Values.SHOOTER_RPM / 100);      //Full Speed
                uptake.feed(Values.UPTAKE_SPEED);
            } else if(shooterJoy.getRawButton(1)) {
                shooter.shoot(Values.SHOOTER_RPM / 100);      //Full Speed
            } else if(shooterJoy.getRawButton(3)){
                shooter.stop();                         //Stop
            } else {
                shooter.shoot(Values.SHOOTER_RPM_IDLE / 100); //Idle
            }
            if(shooterJoy.getRawButton(11)) {
                while(!shooter.resetTilt()) {}
            }
            shooter.tilt(shooterJoy.getRawAxis(3) );//* Values.SHOOTER_TILT_POSITION_SCALAR);*/

            /*if(shooterJoy.getRawButton(2) || shooterJoy.getRawButton(4)) {
                uptake.feed(Values.UPTAKE_SPEED);       //Forward
            } else if(shooterJoy.getRawButton(5)){
                uptake.feed(-Values.UPTAKE_SPEED);      //Backward
            } else {
                uptake.stop();                          //Stop
            }*/
            
            if(shooterJoy.getRawAxis(2) <= -0.1) {
                intake.reach(shooterJoy.getRawAxis(2) );//* Values.INTAKE_ARM_POSITION_SCALAR);
            } else if(shooterJoy.getRawAxis(2) > 0) {
                intake.reach(0.1);
            } else {
                intake.reach(-0.1);
            }
            /*if(shooterJoy.getRawButton(8)) {
                intake.flip(true);                      //Flip upside up
            } else if(shooterJoy.getRawButton(9)) {
                intake.flip(false);                     //Flip upside down
            } else {
                intake.flip(intake.isFlipped());        //Finish flipping/don't flip
            }*/
            if(shooterJoy.getRawButton(6)) {
                intake.grab(Relay.Value.kForward);      //Run intake forward
            } else if(shooterJoy.getRawButton(7)) {
                intake.grab(Relay.Value.kReverse);      //Run intake backward
            } else {
                intake.grab(Relay.Value.kOff);          //Turn off intake
            }
        }
    }
}