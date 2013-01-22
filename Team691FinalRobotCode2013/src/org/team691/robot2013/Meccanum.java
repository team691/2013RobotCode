package org.team691.robot2013;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;


public class Meccanum {
	
    //Speed Controllers
    private int[] motorSlot;
    private int[] motorChannel;
    private SpeedController frVic;
    private SpeedController flVic;
    private SpeedController brVic;
    private SpeedController blVic;

    //Encoders
    private int[] encSlot;
    private int[][] encPort;
    private int[] encCount;
    private Encoder frEnc;
    private Encoder flEnc;
    private Encoder brEnc;
    private Encoder blEnc;

    //PIDMotor
    private double[] kp;
    private double[] ki;
    private double[] kd;
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

    public Meccanum(int[] vicSlot, int[] vicChannel, int[] encSlot, int[][] encPort, int[] encCount, double[][] pid) {
        this.motorSlot = vicSlot;
        this.motorChannel = vicChannel;
        this.encSlot = encSlot;
        this.encPort = encPort;
        this.encCount = encCount;
        this.kp = pid[0];
        this.ki = pid[1];
        this.kd = pid[2];
        useEncoders = true;
        init();
        initEnc();
        initPID();
    }

    public Meccanum(int[] vicSlot, int[] vicChannel) {
        this.motorSlot = vicSlot;
        this.motorChannel = vicChannel;
        useEncoders = false;
        init();
    }

    private void init() {
        frVic = new Victor(motorSlot[0], motorChannel[0]);
        flVic = new Victor(motorSlot[1], motorChannel[1]);
        brVic = new Jaguar(motorSlot[2], motorChannel[2]);
        blVic = new Jaguar(motorSlot[3], motorChannel[3]);
    }

    private void initEnc() {
        frEnc = new Encoder(encSlot[0], encPort[0][0], encSlot[0], encPort[0][1]);
        flEnc = new Encoder(encSlot[1], encPort[1][0], encSlot[1], encPort[1][1]);
        brEnc = new Encoder(encSlot[2], encPort[2][0], encSlot[2], encPort[2][1]);
        blEnc = new Encoder(encSlot[3], encPort[3][0], encSlot[3], encPort[3][1]);		
        frEnc.setDistancePerPulse(360/encCount[0]);
        flEnc.setDistancePerPulse(360/encCount[1]);
        brEnc.setDistancePerPulse(360/encCount[2]);
        blEnc.setDistancePerPulse(360/encCount[3]);
        frEnc.start();
        flEnc.start();
        brEnc.start();
        blEnc.start();
    }

    private void initPID() {
        fr = new PIDMotor(frVic, frEnc, kp[0], ki[0], kd[0]);
        fl = new PIDMotor(flVic, flEnc, kp[1], ki[1], kd[1]);
        br = new PIDMotor(brVic, brEnc, kp[2], ki[2], kd[2]);
        bl = new PIDMotor(blVic, blEnc, kp[3], ki[3], kd[3]);
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
        if(maxVel >= 1) {
            frVel /= maxVel;
            flVel /= maxVel;
            brVel /= maxVel;
            blVel /= maxVel;
        }

        if(useEncoders) {
            fr.set(frVel);
            fl.set(flVel);
            br.set(brVel);
            bl.set(blVel);
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