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
    //Intake intake;

    public void robotInit() {
        shooter = new Shooter(
                Values.SHOOTER_VICTOR_SIDECAR,
                Values.SHOOTER_VICTOR,
                Values.SHOOTER_ENCODER,
                Values.SHOOTER_PID,
                Values.SHOOTER_TILT_VICTOR_SIDECAR,
                Values.SHOOTER_TILT_VICTOR, 
                Values.SHOOTER_TILT_ENCODER,
                Values.SHOOTER_TILT_PID);
        shooter.stop();
        
        uptake = new Uptake(Values.UPTAKE_VICTOR_SIDECAR, Values.UPTAKE_VICTOR);
        uptake.stop();
        
        /*intake = new Intake(
                Values.INTAKE_ARM_VICTOR,
                Values.INTAKE_ARM_VICTOR_SIDECAR,
                Values.INTAKE_ARM_ENCODER,
                Values.INTAKE_ARM_PID,
                Values.INTAKE_WRIST_RELAY,
                Values.INTAKE_WRIST_RELAY_SIDECAR,
                Values.INTAKE_WRIST_LIMIT,
                Values.INTAKE_GRABBER_RELAY,
                Values.INTAKE_GRABBER_RELAY_SIDECAR);
        intake.stop();*/
    }

    public void operatorControl() {
        while (isEnabled() && isOperatorControl()) {
            /*if (joy.getRawButton(1)) {
                shooter.shoot(Values.SHOOTER_RPM);      //Full Speed
            } else if(joy.getRawButton(4) || joy.getRawButton(5)){
                shooter.stop();                         //Stop
            } else {
                shooter.shoot(Values.SHOOTER_RPM_IDLE); //Idle
            }*/
            shooter.shoot(0.0);   //TODO: Debug statement, remove for final code!
            shooter.tilt(joy.getRawAxis(3));
            System.out.println(shooter.get());  //TODO: Debug function, remove for final code!
            
            if(joy.getRawButton(6)) {
                uptake.feed(Values.UPTAKE_SPEED);       //Forward
            } else if(joy.getRawButton(7)){
                uptake.feed(-Values.UPTAKE_SPEED);      //Backward
            } else {
                uptake.stop();                          //Stop
            }
            
            /*intake.reach(joy.getRawAxis(2));
            if(joy.getRawButton(8)) {
                intake.flip(true);                      //Flip upside up
            } else if(joy.getRawButton(9)) {
                intake.flip(false);                     //Flip upside down
            } else {
                intake.flip(intake.isFlipped());        //Finish flipping/don't flip
            }
            if(joy.getRawButton(11)) {
                intake.grab(Relay.Value.kForward);      //Run intake forward
            } else if(joy.getRawButton(10)) {
                intake.grab(Relay.Value.kReverse);      //Run intake backward
            } else {
                intake.grab(Relay.Value.kOff);          //Turn off intake
            }*/
        }
    }
}
