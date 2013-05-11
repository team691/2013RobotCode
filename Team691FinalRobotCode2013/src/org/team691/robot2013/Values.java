package org.team691.robot2013;

public class Values {
    
    public static final int SIDECAR_2 = 1;
    public static final int SIDECAR_4 = 2;
    
    public static final int DRIVE_JOYSTICK      = 1;
    public static final int SHOOTER_JOYSTICK    = 2;
    
    public static final int FR_DRIVE_VICTOR     = 9;
    public static final int FL_DRIVE_VICTOR     = 10;
    public static final int BR_DRIVE_VICTOR     = 10;
    public static final int BL_DRIVE_VICTOR     = 2; 
    public static final int[] DRIVE_VICTOR_SIDECARS  = {SIDECAR_2, SIDECAR_4, SIDECAR_2, SIDECAR_4};
    												  //FR FL BR BL
    public static final int[] DRIVE_VICTORS          = {FR_DRIVE_VICTOR, FL_DRIVE_VICTOR, BR_DRIVE_VICTOR, BL_DRIVE_VICTOR};
    
    public static final int FR_DRIVE_ENCODER_A  	 = 3;
    public static final int FR_DRIVE_ENCODER_B  	 = 4;
    public static final int FR_DRIVE_ENCODER_SIDECAR = SIDECAR_2;
    public static final int FR_DRIVE_ENCODER_DISTANCE_PER_PULSE = 1;
    public static final boolean FR_DRIVE_ENCODER_REVERSE = false;
    
    public static final int FL_DRIVE_ENCODER_A  	 = 3;
    public static final int FL_DRIVE_ENCODER_B  	 = 4;
    public static final int FL_DRIVE_ENCODER_SIDECAR = SIDECAR_4;
    public static final int FL_DRIVE_ENCODER_DISTANCE_PER_PULSE = 1;
    public static final boolean FL_DRIVE_ENCODER_REVERSE = false;
    
    public static final int BR_DRIVE_ENCODER_A  	 = 1;
    public static final int BR_DRIVE_ENCODER_B  	 = 2;
    public static final int BR_DRIVE_ENCODER_SIDECAR = SIDECAR_2;
    public static final int BR_DRIVE_ENCODER_DISTANCE_PER_PULSE = 1;
    public static final boolean BR_DRIVE_ENCODER_REVERSE = false;
    
    public static final int BL_DRIVE_ENCODER_A  	 = 1;
    public static final int BL_DRIVE_ENCODER_B  	 = 2;
    public static final int BL_DRIVE_ENCODER_SIDECAR = SIDECAR_4;
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
    public static final double FR_DRIVE_PID_SCALAR = 500.0;
    public static final double FL_DRIVE_PID_SCALAR = 500.0;
    public static final double BR_DRIVE_PID_SCALAR = 500.0;
    public static final double BL_DRIVE_PID_SCALAR = 500.0;
    public static final double[] FR_DRIVE_PID  = {FR_DRIVE_PID_KP, FR_DRIVE_PID_KI, FR_DRIVE_PID_KD, FR_DRIVE_PID_SCALAR};
    public static final double[] FL_DRIVE_PID  = {FL_DRIVE_PID_KP, FL_DRIVE_PID_KI, FL_DRIVE_PID_KD, FL_DRIVE_PID_SCALAR};
    public static final double[] BR_DRIVE_PID  = {BR_DRIVE_PID_KP, BR_DRIVE_PID_KI, BR_DRIVE_PID_KD, BR_DRIVE_PID_SCALAR};
    public static final double[] BL_DRIVE_PID  = {BL_DRIVE_PID_KP, BL_DRIVE_PID_KI, BL_DRIVE_PID_KD, BL_DRIVE_PID_SCALAR};
    public static final double[][] DRIVE_PID   = {FR_DRIVE_PID, FL_DRIVE_PID, BR_DRIVE_PID, BL_DRIVE_PID};
    
    public static final double SHOOTER_RPM              = 0.6;
    public static final double SHOOTER_ACCEL_RPM        = 0.9;
    public static final double SHOOTER_RPM_IDLE         = SHOOTER_RPM / 2.0;
    public static final double SHOOTER_ACCEL_RPM_IDLE   = SHOOTER_ACCEL_RPM / 2.0;
    public static final double SHOOTER_TACHOMETER_RPM   = 2000.0;
    public static final double FLIP_SPEED               = 0.5;
    
    public static final int SHOOTER_VICTOR               = 8;
    public static final int SHOOTER_ACCEL_VICTOR         = 6;
    public static final int[] SHOOTER_VICTORS            = {SHOOTER_VICTOR, SHOOTER_ACCEL_VICTOR};
    public static final int[] SHOOTER_VICTOR_SIDECARS    = {SIDECAR_4, SIDECAR_2};
    
    public static final int SHOOTER_TACHOMETER           = 14;
    public static final int SHOOTER_TACHOMETER_SIDECAR   = SIDECAR_4;
    
    public static final int SHOOTER_TILT_VICTOR          = 5;
    public static final int SHOOTER_TILT_VICTOR_SIDECAR  = SIDECAR_2;
    public static final int SHOOTER_TILT_LIMIT_TOP       = 0;
    public static final int SHOOTER_TILT_LIMIT_BOTTOM    = 0;
    public static final int SHOOTER_TILT_LIMIT_SIDECAR   = 0;
    public static final int[] SHOOTER_TILT_LIMITS        = {SHOOTER_TILT_LIMIT_SIDECAR, SHOOTER_TILT_LIMIT_TOP, SHOOTER_TILT_LIMIT_BOTTOM};
    
    public static final int SHOOTER_TILT_ENCODER_A       = 0;
    public static final int SHOOTER_TILT_ENCODER_B       = 0;
    public static final int SHOOTER_TILT_ENCODER_SIDECAR = 0;
    public static final int SHOOTER_TILT_ENCODER_DISTANCE_PER_PULSE = 1;
    public static final boolean SHOOTER_TILT_ENCODER_REVERSE = false;
    public static final int[] SHOOTER_TILT_ENCODER           = {SHOOTER_TILT_ENCODER_SIDECAR, SHOOTER_TILT_ENCODER_A, SHOOTER_TILT_ENCODER_B, SHOOTER_TILT_ENCODER_DISTANCE_PER_PULSE, (SHOOTER_TILT_ENCODER_REVERSE ? 1 : 0)};
    
    public static final double SHOOTER_TILT_POSITION_KP     = 0.005;
    public static final double SHOOTER_TILT_POSITION_KI     = 0.0;
    public static final double SHOOTER_TILT_POSITION_KD     = 0.0;
    public static final double SHOOTER_TILT_POSITION_SCALAR = 180.0;
    public static final double[] SHOOTER_TILT_POSITION_PID  = {SHOOTER_TILT_POSITION_KP, SHOOTER_TILT_POSITION_KI, SHOOTER_TILT_POSITION_KD};  
    
    public static final double SHOOTER_TILT_KP      = 0.05;
    public static final double SHOOTER_TILT_KI      = 0.005;
    public static final double SHOOTER_TILT_KD      = 0.05;
    public static final double SHOOTER_TILT_SCALAR  = 20.0;
    public static final double[] SHOOTER_TILT_PID   = {SHOOTER_TILT_KP, SHOOTER_TILT_KI, SHOOTER_TILT_KD, SHOOTER_TILT_SCALAR};  
    
    public static final int UPTAKE_RELAY           = 8;
    public static final int UPTAKE_RELAY_SIDECAR   = SIDECAR_4;
    
    public static final int UPTAKE_GATEKEEPER           = 7;
    public static final int UPTAKE_GATEKEEPER_SIDECAR   = SIDECAR_4;
    
    public static final int INTAKE_ARM_VICTOR          = 6;
    public static final int INTAKE_ARM_VICTOR_SIDECAR  = SIDECAR_4;
    
    public static final int INTAKE_ARM_ENCODER_A       = 0;
    public static final int INTAKE_ARM_ENCODER_B       = 0;
    public static final int INTAKE_ARM_ENCODER_SIDECAR = 0;
    public static final int INTAKE_ARM_ENCODER_DISTANCE_PER_PULSE = 0;
    public static final int[] INTAKE_ARM_ENCODER       = {INTAKE_ARM_ENCODER_SIDECAR, INTAKE_ARM_ENCODER_A, INTAKE_ARM_ENCODER_B, INTAKE_ARM_ENCODER_DISTANCE_PER_PULSE};
    
    public static final double INTAKE_ARM_POSITION_KP       = 0.0;
    public static final double INTAKE_ARM_POSITION_KI       = 0.0;
    public static final double INTAKE_ARM_POSITION_KD       = 0.0;
    public static final double INTAKE_ARM_POSITION_SCALAR   = 0.0;
    public static final double[] INTAKE_ARM_POSITION_PID    = {SHOOTER_TILT_POSITION_KP, SHOOTER_TILT_POSITION_KI, SHOOTER_TILT_POSITION_KD};  
    
    public static final double INTAKE_ARM_KP        = 0.0;
    public static final double INTAKE_ARM_KI        = 0.0;
    public static final double INTAKE_ARM_KD        = 0.0;
    public static final double INTAKE_ARM_SCALAR    = 0.0;
    public static final double[] INTAKE_ARM_PID     = {INTAKE_ARM_KP, INTAKE_ARM_KI, INTAKE_ARM_KD, INTAKE_ARM_SCALAR};
    
    public static final int INTAKE_WRIST_VICTOR          = 3;
    public static final int INTAKE_WRIST_VICTOR_SIDECAR  = SIDECAR_4;
}