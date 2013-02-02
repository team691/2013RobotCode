package org.team691.robot2013;

public class Values {
    
    public static final int SIDECAR_1 = 1;
    public static final int SIDECAR_2 = 2;
    
    public static final int DRIVE_JOYSTICK      = 2;
    public static final int SHOOTER_JOYSTICK    = 1;
    
    public static final double SHOOTER_RPM      = 0.0; //TODO: Change this to best value after prototype! Final because value doesn't change.
    public static final double SHOOTER_RPM_IDLE = SHOOTER_RPM / 2;
    public static final double UPTAKE_SPEED     = 0.0; //TODO: Change this to best value after prototype! Final because value doesn't change.
    
    public static final int FR_DRIVE_VICTOR     = 1;
    public static final int FL_DRIVE_VICTOR     = 10;
    public static final int BR_DRIVE_VICTOR     = 2;
    public static final int BL_DRIVE_VICTOR     = 8; 
    public static final int[] DRIVE_VICTOR_SIDECARS  = {1, 1, 1, 1};
    												  //FR FL BR BL
    public static final int[] DRIVE_VICTORS          = {FR_DRIVE_VICTOR, FL_DRIVE_VICTOR, BR_DRIVE_VICTOR, BL_DRIVE_VICTOR};
    
    public static final int FR_DRIVE_ENCODER_A  	 = 7;
    public static final int FR_DRIVE_ENCODER_B  	 = 8;
    public static final int FR_DRIVE_ENCODER_SIDECAR = 1;
    public static final int FR_DRIVE_ENCODER_DISTANCE_PER_PULSE = 1;
    public static final boolean FR_DRIVE_ENCODER_REVERSE = false;
    
    public static final int FL_DRIVE_ENCODER_A  	 = 5;
    public static final int FL_DRIVE_ENCODER_B  	 = 6;
    public static final int FL_DRIVE_ENCODER_SIDECAR = 1;
    public static final int FL_DRIVE_ENCODER_DISTANCE_PER_PULSE = 1;
    public static final boolean FL_DRIVE_ENCODER_REVERSE = false;
    
    public static final int BR_DRIVE_ENCODER_A  	 = 1;
    public static final int BR_DRIVE_ENCODER_B  	 = 2;
    public static final int BR_DRIVE_ENCODER_SIDECAR = 1;
    public static final int BR_DRIVE_ENCODER_DISTANCE_PER_PULSE = 1;
    public static final boolean BR_DRIVE_ENCODER_REVERSE = false;
    
    public static final int BL_DRIVE_ENCODER_A  	 = 3;
    public static final int BL_DRIVE_ENCODER_B  	 = 4;
    public static final int BL_DRIVE_ENCODER_SIDECAR = 1;
    public static final int BL_DRIVE_ENCODER_DISTANCE_PER_PULSE = 1;
    public static final boolean BL_DRIVE_ENCODER_REVERSE = false;
    
    public static final int[][] DRIVE_ENCODERS 		 = {
                                                            {FR_DRIVE_ENCODER_A, FR_DRIVE_ENCODER_B},
                                                            {FL_DRIVE_ENCODER_A, FL_DRIVE_ENCODER_B},
                                                            {BR_DRIVE_ENCODER_A, BR_DRIVE_ENCODER_B},
                                                            {BL_DRIVE_ENCODER_A, BL_DRIVE_ENCODER_B},
                                                       };
    public static final int[] DRIVE_ENCODER_SIDECARS = {FR_DRIVE_ENCODER_SIDECAR, FL_DRIVE_ENCODER_SIDECAR, BR_DRIVE_ENCODER_SIDECAR, BL_DRIVE_ENCODER_SIDECAR};
    public static final int[] DRIVE_ENCODER_DISTANCES_PER_PULSE = {FR_DRIVE_ENCODER_DISTANCE_PER_PULSE, FL_DRIVE_ENCODER_DISTANCE_PER_PULSE, BR_DRIVE_ENCODER_DISTANCE_PER_PULSE, BL_DRIVE_ENCODER_DISTANCE_PER_PULSE};
    public static final boolean[] DRIVE_ENCODER_REVERSES = {FR_DRIVE_ENCODER_REVERSE, FL_DRIVE_ENCODER_REVERSE, BR_DRIVE_ENCODER_REVERSE, BL_DRIVE_ENCODER_REVERSE};
    
    public static final double FR_DRIVE_PID_KP = 0.5;
    public static final double FL_DRIVE_PID_KP = 0.5;
    public static final double BR_DRIVE_PID_KP = 0.5;
    public static final double BL_DRIVE_PID_KP = 0.5;
    public static final double FR_DRIVE_PID_KI = 0.005;
    public static final double FL_DRIVE_PID_KI = 0.005;
    public static final double BR_DRIVE_PID_KI = 0.005;
    public static final double BL_DRIVE_PID_KI = 0.005;
    public static final double FR_DRIVE_PID_KD = 0.5;
    public static final double FL_DRIVE_PID_KD = 0.5;
    public static final double BR_DRIVE_PID_KD = 0.5;
    public static final double BL_DRIVE_PID_KD = 0.5;
    public static final double FR_DRIVE_PID_SCALAR = 100.0;
    public static final double FL_DRIVE_PID_SCALAR = 100.0;
    public static final double BR_DRIVE_PID_SCALAR = 100.0;
    public static final double BL_DRIVE_PID_SCALAR = 100.0;
    public static final double[] DRIVE_PID_KP  = {FR_DRIVE_PID_KP, FL_DRIVE_PID_KP, BR_DRIVE_PID_KP, BL_DRIVE_PID_KP};
    public static final double[] DRIVE_PID_KI  = {FR_DRIVE_PID_KI, FL_DRIVE_PID_KI, BR_DRIVE_PID_KI, BL_DRIVE_PID_KI};
    public static final double[] DRIVE_PID_KD  = {FR_DRIVE_PID_KD, FL_DRIVE_PID_KD, BR_DRIVE_PID_KD, BL_DRIVE_PID_KD};
    public static final double[] DRIVE_PID_SCALAR  = {FR_DRIVE_PID_SCALAR, FL_DRIVE_PID_SCALAR, BR_DRIVE_PID_SCALAR, BL_DRIVE_PID_SCALAR};
    public static final double[][] DRIVE_PID   = {DRIVE_PID_KP, DRIVE_PID_KI, DRIVE_PID_KD, DRIVE_PID_SCALAR};
    
    public static final double SHOOTER_KP    = 0.0;
    public static final double SHOOTER_KI    = 0.0;
    public static final double SHOOTER_KD    = 0.0;
    public static final double SHOOTER_SCALAR = 0.0;
    public static final double[] SHOOTER_PID = {SHOOTER_KP, SHOOTER_KI, SHOOTER_KD, SHOOTER_SCALAR};    
    
    public static final double SHOOTER_TILT_KP    = 0.0;
    public static final double SHOOTER_TILT_KI    = 0.0;
    public static final double SHOOTER_TILT_KD    = 0.0;
    public static final double SHOOTER_TILT_SCALAR = 0.0;
    public static final double[] SHOOTER_TILT_PID = {SHOOTER_TILT_KP, SHOOTER_TILT_KI, SHOOTER_TILT_KD, SHOOTER_TILT_SCALAR};    
    
    public static final int SHOOTER_VICTOR          = 0;
    public static final int SHOOTER_VICTOR_SIDECAR  = 0;
    
    public static final int SHOOTER_ENCODER_A       = 0;
    public static final int SHOOTER_ENCODER_B       = 0;
    public static final int SHOOTER_ENCODER_SIDECAR = 0;
    public static final int SHOOTER_ENCODER_COUNT   = 0;
    public static final int[] SHOOTER_ENCODER       = {SHOOTER_ENCODER_SIDECAR, SHOOTER_ENCODER_A, SHOOTER_ENCODER_B, SHOOTER_ENCODER_COUNT};
    
    public static final int SHOOTER_TILT_VICTOR          = 0;
    public static final int SHOOTER_TILT_VICTOR_SIDECAR  = 0;
    
    public static final int SHOOTER_TILT_ENCODER_A       = 0;
    public static final int SHOOTER_TILT_ENCODER_B       = 0;
    public static final int SHOOTER_TILT_ENCODER_SIDECAR = 0;
    public static final int SHOOTER_TILT_ENCODER_COUNT   = 0;
    public static final int[] SHOOTER_TILT_ENCODER       = {SHOOTER_ENCODER_SIDECAR, SHOOTER_ENCODER_A, SHOOTER_ENCODER_B, SHOOTER_ENCODER_COUNT};
    
    public static final int UPTAKE_VICTOR           = 0;
    public static final int UPTAKE_VICTOR_SIDECAR   = 0;
    
    public static final int INTAKE_ARM_VICTOR          = 0;
    public static final int INTAKE_ARM_VICTOR_SIDECAR  = 0;
    
    public static final int INTAKE_ARM_ENCODER_A       = 0;
    public static final int INTAKE_ARM_ENCODER_B       = 0;
    public static final int INTAKE_ARM_ENCODER_SIDECAR = 0;
    public static final int INTAKE_ARM_ENCODER_COUNT   = 0;
    public static final int[] INTAKE_ARM_ENCODER       = {INTAKE_ARM_ENCODER_SIDECAR, INTAKE_ARM_ENCODER_A, INTAKE_ARM_ENCODER_B, INTAKE_ARM_ENCODER_COUNT};
    
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