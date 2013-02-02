package org.team691.meccanum;

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
    private PIDMotor fr;
    private PIDMotor fl;
    private PIDMotor br;
    private PIDMotor bl;

    //Meccanum control
    private double frVel = 0.0;
    private double flVel = 0.0;
    private double brVel = 0.0;
    private double blVel = 0.0;
    private double maxVel = 0.0;

    //Fallbacks in case of meccanum code failure
    private boolean useEncoders = true;

    public Meccanum(int[] vicSlot, int[] vicChannel, int[] encSlot, int[][] encPort, int[] encDistPerPulse, boolean[] encRev, double[][] pid) {
        useEncoders = true;
        init(vicSlot, vicChannel);
        initEnc(encSlot, encPort, encDistPerPulse, encRev);
        initPID(pid[0], pid[1], pid[2], pid[3]);
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

    private void initPID(double[] kp, double[] ki, double[] kd, double[] scalar) {
        fr = new PIDMotor("FR", true, frVic, frEnc, kp[0], ki[0], kd[0], scalar[0]);
        fl = new PIDMotor("FL", true, flVic, flEnc, kp[1], ki[1], kd[1], scalar[1]);
        br = new PIDMotor("BR", true, brVic, brEnc, kp[2], ki[2], kd[2], scalar[2]);
        bl = new PIDMotor("BL", true, blVic, blEnc, kp[3], ki[3], kd[3], scalar[3]);
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
        frVel = -(forward + right - clockwise); //0--   /TODO: Check negatives for final robot!
        flVel = (forward - right + clockwise);  //0++
        brVel = -(forward - right - clockwise); //0+-
        blVel = (forward + right + clockwise);  //0-+

        //Set the motor objects to the output value for motor control and additional scaling
        //Find the maximum speed
        if(maxVel < Math.abs(frVel)) {maxVel = frVel;}
        else if(maxVel < Math.abs(flVel)) {maxVel = flVel;}
        else if(maxVel < Math.abs(brVel)) {maxVel = brVel;}
        else if(maxVel < Math.abs(blVel)) {maxVel = blVel;}

        //Scale motor power if it is above 100%
        if(maxVel > 1.0) {
            frVel /= maxVel;
            flVel /= maxVel;
            brVel /= maxVel;
            blVel /= maxVel;
        }

        if(useEncoders) {
            fr.run(frVel);
            fl.run(flVel);
            br.run(brVel);
            bl.run(blVel);
        } else {
            frVic.set(frVel);
            flVic.set(flVel);
            brVic.set(brVel);
            blVic.set(blVel);
        }
    }

    public void stop() {
        if(useEncoders) {
            fr.set(0.0);
            fl.set(0.0);
            br.set(0.0);
            bl.set(0.0);
        } else {
            frVic.set(0.0);
            flVic.set(0.0);
            brVic.set(0.0);
            blVic.set(0.0);
        }
    }
}