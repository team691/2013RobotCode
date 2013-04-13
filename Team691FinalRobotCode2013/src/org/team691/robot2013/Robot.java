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
    //double scalar = 0.0;
    
    //Shooter, Uptake, & Intake
    Shooter shooter;
    Uptake uptake;
    Intake intake;
    
    //Autonomous
    long autoStart = 0;
    
    /**
     * This function is called once when the robot turns on.
     */
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
        
        shooter = new Shooter(Values.SHOOTER_VICTOR_SIDECARS, Values.SHOOTER_VICTORS, Values.SHOOTER_TILT_VICTOR_SIDECAR, Values.SHOOTER_TILT_VICTOR);    //No Encoders, No Limits
        //shooter = new Shooter(Values.SHOOTER_VICTOR_SIDECARS, Values.SHOOTER_VICTORS, Values.SHOOTER_TILT_VICTOR_SIDECAR, Values.SHOOTER_TILT_VICTOR, Values.SHOOTER_TILT_LIMITS);    //No Encoders, With Limits
        //shooter = new Shooter(Values.SHOOTER_VICTOR_SIDECARS, Values.SHOOTER_VICTORS, Values.SHOOTER_TILT_VICTOR_SIDECAR, Values.SHOOTER_TILT_VICTOR, Values.SHOOTER_TILT_LIMITS, Values.SHOOTER_TACHOMETER_SIDECAR, Values.SHOOTER_TACHOMETER, Values.SHOOTER_TACHOMETER_RPM);    //With Tachometer, With Limits
        /*shooter = new Shooter(
                Values.SHOOTER_VICTOR_SIDECARS,
                Values.SHOOTER_VICTORS,
                Values.SHOOTER_ENCODERS,
                Values.SHOOTER_PID,
                Values.SHOOTER_TILT_VICTOR_SIDECAR,
                Values.SHOOTER_TILT_VICTOR, 
                Values.SHOOTER_TILT_ENCODER,
                Values.SHOOTER_TILT_POSITION_PID,
                Values.SHOOTER_TILT_PID,
                Values.SHOOTER_TILT_LIMITS
        );*/ //With Encoders, With Limits
        shooter.stop();
        
        uptake = new Uptake(Values.UPTAKE_RELAY_SIDECAR, Values.UPTAKE_RELAY, Values.UPTAKE_GATEKEEPER_SIDECAR, Values.UPTAKE_GATEKEEPER);
        uptake.stop();
        
        intake = new Intake(
                Values.INTAKE_ARM_VICTOR_SIDECAR,
                Values.INTAKE_ARM_VICTOR,
                Values.INTAKE_WRIST_VICTOR_SIDECAR,
                Values.INTAKE_WRIST_VICTOR
        );    //No Encoders
        /*intake = new Intake(
                Values.INTAKE_ARM_VICTOR_SIDECAR,
                Values.INTAKE_ARM_VICTOR,
                Values.INTAKE_ARM_ENCODER,
                Values.INTAKE_ARM_PID,
                Values.INTAKE_WRIST_RELAY_SIDECAR,
                Values.INTAKE_WRIST_RELAY
        );*/   //With Encoders
        intake.stop();
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */        
    public void autonomous() {
        autoStart = System.currentTimeMillis();
        while(isEnabled() && isAutonomous()) {
            //while(!shooter.resetTilt()) {}
            
            if ((System.currentTimeMillis() - autoStart) >= 2000 && (System.currentTimeMillis() - autoStart) < 3000) {
                shooter.shoot(Values.SHOOTER_RPM, Values.SHOOTER_ACCEL_RPM);      //Full Speed
            } else if((System.currentTimeMillis() - autoStart) >= 3000) {
                uptake.feed();                          //Feed Disc
                shooter.shoot(Values.SHOOTER_RPM, Values.SHOOTER_ACCEL_RPM);      //Full Speed
            }
            
            /*if ((System.currentTimeMillis() - autoStart) < 1000) {
                intake.reach(-0.5);
            } else if ((System.currentTimeMillis() - autoStart) < 2000) {
                shooter.tilt(-0.25);
            } else if ((System.currentTimeMillis() - autoStart) < 10000) {
                shooter.shoot(Values.SHOOTER_RPM, Values.SHOOTER_ACCEL_RPM); //Full Speed
                uptake.feed();                                               //Feed Disc
            } else {
                shooter.shoot(Values.SHOOTER_RPM_IDLE, Values.SHOOTER_ACCEL_RPM_IDLE); //Idle
            }*/
            
            /*if(((System.currentTimeMillis() - autoStart) > 12000) && ((System.currentTimeMillis() - autoStart) < 14000)) {
                drive.update(-0.25, 0.0, 0.0);  //Drive Forward
            } else {
                drive.update(0.0, 0.0, 0.0);    //Stop
            }*/
        }
    }

    /**
     * This function is called once each time the robot enters operator control mode.
     */
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
            
            /*if(driveJoy.getRawButton(1)) {
                drive.update(1.0, 0.0, 0.0);
            } else if(driveJoy.getRawButton(3)) {
                drive.update(0.75, 0.0, 0.0);
            } else if(driveJoy.getRawButton(2)) {
                drive.update(0.5, 0.0, 0.0);
            } else {
                drive.update(0.0, 0.0, 0.0);
            }*/
            
            if(shooterJoy.getRawButton(1)) {
                shooter.shoot(Values.SHOOTER_RPM, Values.SHOOTER_ACCEL_RPM);            //Full Speed
            } else if(shooterJoy.getRawButton(2)) {
                shooter.stop();                                                         //Stop
            } else {
                shooter.shoot(Values.SHOOTER_RPM_IDLE, Values.SHOOTER_ACCEL_RPM_IDLE);  //Idle
            }
            /*if(shooterJoy.getRawButton(10)) {
                while(!shooter.resetTilt()) {}
            }*/
            shooter.tilt(shooterJoy.getRawAxis(2), shooterJoy.getRawButton(11));//* Values.SHOOTER_TILT_POSITION_SCALAR);
            
            if(shooterJoy.getRawButton(3)) {
                uptake.feed();                      //Feed Disc
            } else if(shooterJoy.getRawButton(4)) {
                uptake.run(Relay.Value.kForward);   //Forward
            } else if(shooterJoy.getRawButton(5)){
                uptake.run(Relay.Value.kReverse);   //Backward
            } else {
                uptake.stop();                      //Stop
            }
            
            /*if(shooterJoy.getRawAxis(2) <= -0.1) {
                intake.reach(shooterJoy.getRawAxis(2) );//* Values.INTAKE_ARM_POSITION_SCALAR);    //Up
            } else if(shooterJoy.getRawAxis(2) > 0) {
                intake.reach(0.1);                  //Down
            } else {
                intake.reach(-0.1);                 //Stop
            }*/
            if(shooterJoy.getRawButton(6)) {
                intake.flip(Values.FLIP_SPEED);     //Forward
            } else if(shooterJoy.getRawButton(7)) {
                intake.flip(-Values.FLIP_SPEED);    //Backward
            } else {
                intake.flip(0.0);                   //Stop
            }
        }
    }
}