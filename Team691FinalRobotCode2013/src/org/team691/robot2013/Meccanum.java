package org.team691.robot2013;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;


public class Meccanum {
	
    //Speed Controllers
    private SpeedController frVic;
    private SpeedController flVic;
    private SpeedController brVic;
    private SpeedController blVic;

    //Encoders
    private Encoder frEnc;
    private Encoder flEnc;
    private Encoder brEnc;
    private Encoder blEnc;

    //PIDMotor
    private PIDVelocityMotor fr;
    private PIDVelocityMotor fl;
    private PIDVelocityMotor br;
    private PIDVelocityMotor bl;

    //Meccanum control
    private double frVel = 0.0;
    private double flVel = 0.0;
    private double brVel = 0.0;
    private double blVel = 0.0;
    private double max = 0.0;
    private double maxVel = 0.0;

    //Fallbacks in case of meccanum code failure
    private boolean useEncoders = true;

    public Meccanum(int[] vicSlot, int[] vicChannel, int[] encSlot, int[][] encPort, int[] encDistPerPulse, boolean[] encRev, double[][] pid) {
        useEncoders = true;
        init(vicSlot, vicChannel);
        initEnc(encSlot, encPort, encDistPerPulse, encRev);
        initPID(pid);
    }

    public Meccanum(int[] vicSlot, int[] vicChannel) {
        useEncoders = false;
        init(vicSlot, vicChannel);
    }
    
    private void init(int[] vicSlot, int[] vicChannel) {
        frVic = new Victor(vicSlot[0], vicChannel[0]);
        flVic = new Victor(vicSlot[1], vicChannel[1]);
        brVic = new Jaguar(vicSlot[2], vicChannel[2]);
        blVic = new Jaguar(vicSlot[3], vicChannel[3]);
        System.out.println("Victors initialized...");
    }

    private void initEnc(int[] encSlot, int[][] encPort, int[] encDistPerPulse, boolean[] encRev) {
        frEnc = new Encoder(encSlot[0], encPort[0][0], encSlot[0], encPort[0][1], encRev[0]);
        flEnc = new Encoder(encSlot[1], encPort[1][0], encSlot[1], encPort[1][1], encRev[1]);
        brEnc = new Encoder(encSlot[2], encPort[2][0], encSlot[2], encPort[2][1], encRev[2]);
        blEnc = new Encoder(encSlot[3], encPort[3][0], encSlot[3], encPort[3][1], encRev[3]);		
        frEnc.setDistancePerPulse(encDistPerPulse[0]);
        flEnc.setDistancePerPulse(encDistPerPulse[1]);
        brEnc.setDistancePerPulse(encDistPerPulse[2]);
        blEnc.setDistancePerPulse(encDistPerPulse[3]);
        System.out.println("Encoders initialized...");
        frEnc.start();
        flEnc.start();
        brEnc.start();
        blEnc.start();
        System.out.println("Encoders started...");
    }

    private void initPID(double[][] pid) {
        max = (pid[0][3] + pid[1][3] + pid[2][3] + pid[3][3]) / 4;
        fr = new PIDVelocityMotor("FR", frVic, frEnc, pid[0]);
        fl = new PIDVelocityMotor("FL", flVic, flEnc, pid[1]);
        br = new PIDVelocityMotor("BR", brVic, brEnc, pid[2]);
        bl = new PIDVelocityMotor("BL", blVic, blEnc, pid[3]);
        System.out.println("PIDMotors initialized...");
    }

    public void moveDual(Joystick rjoy, Joystick ljoy) {
        update(ljoy.getRawAxis(2), ljoy.getRawAxis(1), rjoy.getRawAxis(1));
    }

    public void move(Joystick joy) {
        update(joy.getRawAxis(2), joy.getRawAxis(1), joy.getRawAxis(3));
    }

    public void update(double forward, double right, double clockwise) {
        //Figure out motor speeds
        frVel = -(forward + right - clockwise); //0--
        flVel = (forward - right + clockwise);  //0++
        brVel = -(forward - right - clockwise); //0+-
        blVel = (forward + right + clockwise);  //0-+

        //Set the motor objects to the output value for motor control and additional scaling
        //Find the maximum speed
        if(maxVel < Math.abs(frVel)) {maxVel = frVel;}
        else if(maxVel < Math.abs(flVel)) {maxVel = flVel;}
        else if(maxVel < Math.abs(brVel)) {maxVel = brVel;}
        else if(maxVel < Math.abs(blVel)) {maxVel = blVel;}

        if(useEncoders) {   
            //Scale motor power if it is above 100%
            if(Math.abs(maxVel) > max) {
                frVel /= maxVel;
                flVel /= maxVel;
                brVel /= maxVel;
                blVel /= maxVel;
                frVel *= max;
                flVel *= max;
                brVel *= max;
                blVel *= max;
            }
            fr.run(frVel);
            fl.run(flVel);
            br.run(brVel);
            bl.run(blVel);
        } else {
            //Scale motor power if it is above 100%
            if(Math.abs(maxVel) > 1.0) {
                frVel /= maxVel;
                flVel /= maxVel;
                brVel /= maxVel;
                blVel /= maxVel;
            }
            frVic.set(frVel);
            flVic.set(flVel);
            brVic.set(brVel);
            blVic.set(blVel);
        }
    }

    public void stop() {
        if(useEncoders) {
            fr.run(0.0);
            fl.run(0.0);
            br.run(0.0);
            bl.run(0.0);
        } else {
            frVic.set(0.0);
            flVic.set(0.0);
            brVic.set(0.0);
            blVic.set(0.0);
        }
    }
}