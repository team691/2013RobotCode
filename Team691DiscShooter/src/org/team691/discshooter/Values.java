package org.team691.discshooter;

public class Values {
    
    public static final double SHOOTER_RPM      = 120.0; //TODO: Change this to best value after prototype! Final because value doesn't change.
    public static final double SHOOTER_RPM_IDLE = SHOOTER_RPM / 2;
    public static final double UPTAKE_SPEED     = 0.5; //TODO: Change this to best value after prototype! Final because value doesn't change.  
    
    public static final int SHOOTER_JOYSTICK        = 2;
    
    public static final int SHOOTER_VICTOR          = 2;
    public static final int SHOOTER_VICTOR_SIDECAR  = 1;
    
    public static final int SHOOTER_ENCODER_A       = 1;
    public static final int SHOOTER_ENCODER_B       = 2;
    public static final int SHOOTER_ENCODER_SIDECAR = 1;
    public static final int SHOOTER_ENCODER_DISTANCE_PER_PULSE   = 1;
    public static final boolean SHOOTER_ENCODER_REVERSE = false;
    public static final int[] SHOOTER_ENCODER       = {SHOOTER_ENCODER_SIDECAR, SHOOTER_ENCODER_A, SHOOTER_ENCODER_B, SHOOTER_ENCODER_DISTANCE_PER_PULSE, (SHOOTER_ENCODER_REVERSE ? 1 : 0)};
    
    public static final double SHOOTER_KP    = 0.005;
    public static final double SHOOTER_KI    = 0.0;
    public static final double SHOOTER_KD    = 0.0;
    public static final double SHOOTER_SCALAR = 1.0;
    public static final double[] SHOOTER_PID = {SHOOTER_KP, SHOOTER_KI, SHOOTER_KD, SHOOTER_SCALAR};    
    
    public static final int SHOOTER_TILT_VICTOR          = 1;
    public static final int SHOOTER_TILT_VICTOR_SIDECAR  = 1;
    
    public static final int SHOOTER_TILT_ENCODER_A       = 7;
    public static final int SHOOTER_TILT_ENCODER_B       = 8;
    public static final int SHOOTER_TILT_ENCODER_SIDECAR = 1;
    public static final int SHOOTER_TILT_ENCODER_DISTANCE_PER_PULSE   = 1;
    public static final boolean SHOOTER_TILT_ENCODER_REVERSE = false;
    public static final int[] SHOOTER_TILT_ENCODER       = {SHOOTER_TILT_ENCODER_SIDECAR, SHOOTER_TILT_ENCODER_A, SHOOTER_TILT_ENCODER_B, SHOOTER_TILT_ENCODER_DISTANCE_PER_PULSE, (SHOOTER_TILT_ENCODER_REVERSE ? 1 : 0)};
    
    public static final double SHOOTER_TILT_POSITION_KP    = 0.005;
    public static final double SHOOTER_TILT_POSITION_KI    = 0.0;
    public static final double SHOOTER_TILT_POSITION_KD    = 0.0;
    public static final double SHOOTER_TILT_POSITION_SCALAR = 180.0;
    public static final double[] SHOOTER_TILT_POSITION_PID = {SHOOTER_TILT_POSITION_KP, SHOOTER_TILT_POSITION_KI, SHOOTER_TILT_POSITION_KD, SHOOTER_TILT_POSITION_SCALAR};  
    
    public static final double SHOOTER_TILT_KP    = 0.05;
    public static final double SHOOTER_TILT_KI    = 0.005;
    public static final double SHOOTER_TILT_KD    = 0.05;
    public static final double SHOOTER_TILT_SCALAR = 20.0;
    public static final double[] SHOOTER_TILT_PID = {SHOOTER_TILT_KP, SHOOTER_TILT_KI, SHOOTER_TILT_KD, SHOOTER_TILT_SCALAR};  
    
    public static final int UPTAKE_VICTOR           = 10;
    public static final int UPTAKE_VICTOR_SIDECAR   = 1;
    
    public static final int INTAKE_ARM_VICTOR          = 0;
    public static final int INTAKE_ARM_VICTOR_SIDECAR  = 0;
    
    public static final int INTAKE_ARM_ENCODER_A       = 0;
    public static final int INTAKE_ARM_ENCODER_B       = 0;
    public static final int INTAKE_ARM_ENCODER_SIDECAR = 0;
    public static final int INTAKE_ARM_ENCODER_DISTANCE_PER_PULSE   = 0;
    public static final int[] INTAKE_ARM_ENCODER       = {INTAKE_ARM_ENCODER_SIDECAR, INTAKE_ARM_ENCODER_A, INTAKE_ARM_ENCODER_B, INTAKE_ARM_ENCODER_DISTANCE_PER_PULSE};
    
    public static final double INTAKE_ARM_KP    = 0.0;
    public static final double INTAKE_ARM_KI    = 0.0;
    public static final double INTAKE_ARM_KD    = 0.0;
    public static final double INTAKE_ARM_SCALAR = 0.0;
    public static final double[] INTAKE_ARM_PID = {INTAKE_ARM_KP, INTAKE_ARM_KI, INTAKE_ARM_KD, INTAKE_ARM_SCALAR};
    
    public static final int INTAKE_WRIST_RELAY          = 0;
    public static final int INTAKE_WRIST_RELAY_SIDECAR  = 0;
    public static final int INTAKE_WRIST_LIMIT          = 0;
    
    public static final int INTAKE_GRABBER_RELAY         = 0;
    public static final int INTAKE_GRABBER_RELAY_SIDECAR = 0;
}