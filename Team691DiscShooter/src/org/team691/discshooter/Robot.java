package org.team691.discshooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {

    //Driver station I/O
    Joystick shooterJoy = new Joystick(Values.SHOOTER_JOYSTICK);
    
    //Shooter, Uptake, & Intake
    Shooter shooter;
    Uptake uptake;
    Intake intake;
    boolean running = false;
    
    //Autonomous
    long autoStart = 0;

    public void robotInit() {
        shooter = new Shooter(Values.SHOOTER_VICTOR_SIDECARS, Values.SHOOTER_VICTORS, Values.SHOOTER_TILT_VICTOR_SIDECAR, Values.SHOOTER_TILT_VICTOR);    //No Encoders
        //shooter = new Shooter(Values.SHOOTER_VICTOR_SIDECARS, Values.SHOOTER_VICTORS, Values.SHOOTER_TILT_VICTOR_SIDECAR, Values.SHOOTER_TILT_VICTOR, Values.SHOOTER_TACHOMETER_SIDECAR, Values.SHOOTER_TACHOMETER, Values.SHOOTER_TACHOMETER_RPM);    //With Tachometer
        /*shooter = new Shooter(
                Values.SHOOTER_VICTOR_SIDECARS,
                Values.SHOOTER_VICTORS,
                Values.SHOOTER_ENCODERS,
                Values.SHOOTER_PID,
                Values.SHOOTER_TILT_VICTOR_SIDECAR,
                Values.SHOOTER_TILT_VICTOR, 
                Values.SHOOTER_TILT_ENCODER,
                Values.SHOOTER_TILT_POSITION_PID,
                Values.SHOOTER_TILT_PID
        );*/ //With Encoders
        shooter.stop();
        
        uptake = new Uptake(Values.UPTAKE_VICTOR_SIDECAR, Values.UPTAKE_VICTOR, Values.UPTAKE_GATEKEEPER_SIDECAR, Values.UPTAKE_GATEKEEPER);
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
    
    public void autonomous() {
        autoStart = System.currentTimeMillis();
        while(isEnabled() && isAutonomous()) {
            //while(!shooter.resetTilt()) {}
            
            if (shooter.isReady() && (System.currentTimeMillis() - autoStart) < 10000) {
                shooter.shoot(Values.SHOOTER_RPM / 100);        //Full Speed
                uptake.feed(Values.UPTAKE_SPEED);               //Feed Disc
            } else if((System.currentTimeMillis() - autoStart) < 10000){
                shooter.shoot(Values.SHOOTER_RPM / 100);        //Full Speed
            }
        }
    }

    public void operatorControl() {
        while (isEnabled() && isOperatorControl()) {
            if (shooterJoy.getRawButton(1) && shooter.isReady()) {
                shooter.shoot(Values.SHOOTER_RPM / 100);        //Full Speed
                uptake.feed(Values.UPTAKE_SPEED);               //Feed Disc
            } else if(shooterJoy.getRawButton(1)) {
                shooter.shoot(Values.SHOOTER_RPM / 100);        //Full Speed
            } else if(shooterJoy.getRawButton(3)){
                shooter.stop();                                 //Stop
            } else {
                shooter.shoot(Values.SHOOTER_RPM_IDLE / 100);   //Idle
                uptake.close();
            }
            if(shooterJoy.getRawButton(2)) {
                uptake.feed(Values.UPTAKE_SPEED);
            }
            /*if(shooterJoy.getRawButton(11)) {
                while(!shooter.resetTilt()) {}
            }*/
            shooter.tilt(shooterJoy.getRawAxis(1) );//* Values.SHOOTER_TILT_POSITION_SCALAR);

            if(shooterJoy.getRawButton(4)) {
                uptake.run(Values.UPTAKE_SPEED);    //Forward
            } else if(shooterJoy.getRawButton(5)){
                uptake.run(-Values.UPTAKE_SPEED);   //Backward
            } else {
                uptake.stop();                      //Stop
            }
            
            if(shooterJoy.getRawAxis(2) <= -0.1) {
                intake.reach(shooterJoy.getRawAxis(2) );//* Values.INTAKE_ARM_POSITION_SCALAR);    //Up
            } else if(shooterJoy.getRawAxis(2) > 0) {
                intake.reach(0.1);                  //Down
            } else {
                intake.reach(-0.1);                 //Stop
            }
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
