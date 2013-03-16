package org.team691.discshooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {

    //Driver station I/O
    Joystick joy = new Joystick(Values.SHOOTER_JOYSTICK);
    
    //Shooter, Uptake, & Intake
    Shooter shooter;
    Uptake uptake;
    Intake intake;

    public void robotInit() {
        shooter = new Shooter(Values.SHOOTER_VICTOR_SIDECARS, Values.SHOOTER_VICTORS, Values.SHOOTER_TILT_VICTOR_SIDECAR, Values.SHOOTER_TILT_VICTOR);  //No Encoders
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
        shooter.stop();
        
        uptake = new Uptake(Values.UPTAKE_VICTOR_SIDECAR, Values.UPTAKE_VICTOR);
        uptake.stop();
        
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
    
    public void autonomous() {
        while(isEnabled() && isAutonomous()) {
            while(!shooter.resetTilt()) {}   
        }
    }

    public void operatorControl() {
        while (isEnabled() && isOperatorControl()) {
            if (joy.getRawButton(1) && shooter.isReady()) {
                shooter.shoot(Values.SHOOTER_RPM / 100);      //Full Speed
                uptake.feed(Values.UPTAKE_SPEED);
            } else if(joy.getRawButton(1)) {
                shooter.shoot(Values.SHOOTER_RPM / 100);      //Full Speed
            } else if(joy.getRawButton(3)){
                shooter.stop();                         //Stop
            } else {
                shooter.shoot(Values.SHOOTER_RPM_IDLE / 100); //Idle
            }
            if(joy.getRawButton(11)) {
                while(!shooter.resetTilt()) {}
            }
            shooter.tilt(joy.getRawAxis(3) );//* Values.SHOOTER_TILT_POSITION_SCALAR);

            if(joy.getRawButton(2) || joy.getRawButton(4)) {
                uptake.feed(Values.UPTAKE_SPEED);       //Forward
            } else if(joy.getRawButton(5)){
                uptake.feed(-Values.UPTAKE_SPEED);      //Backward
            } else {
                uptake.stop();                          //Stop
            }
            
            if(joy.getRawAxis(2) <= -0.1) {
                intake.reach(joy.getRawAxis(2) );//* Values.INTAKE_ARM_POSITION_SCALAR);
            } else if(joy.getRawAxis(2) > 0) {
                intake.reach(0.1);
            } else {
                intake.reach(-0.1);
            }
            if(joy.getRawButton(8)) {
                intake.flip(true);                      //Flip upside up
            } else if(joy.getRawButton(9)) {
                intake.flip(false);                     //Flip upside down
            } else {
                intake.flip(intake.isFlipped());        //Finish flipping/don't flip
            }
            if(joy.getRawButton(6)) {
                intake.grab(Relay.Value.kForward);      //Run intake forward
            } else if(joy.getRawButton(7)) {
                intake.grab(Relay.Value.kReverse);      //Run intake backward
            } else {
                intake.grab(Relay.Value.kOff);          //Turn off intake
            }
        }
    }
}
