// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file contains all constants needed throughout every other file. 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Imports robot package 
package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  // Constants to be used for anything related to the drivetrain 
  public static class DrivetrainConstants{

    // Motor controller IDs
    public static final int frontLeftTalonID = 1;
    public static final int backLeftTalonID = 2;
    public static final int frontRightTalonID = 4;
    public static final int backRightTalonID = 3;

    // Limits maximum speeds 
    public static double maxDriveSpeed = 0.5;
    public static double maxTurnSpeed = 0.2;

    // PID tuning values for driveStraightPID
    public static final double driveStraightKP = 0.05;
    public static final double driveStraightKI = 0.1;
    public static final double driveStraightKD = 0.5;

    // PID tuning values for driveSidePID
    public static final double driveSideKP = 0.05;
    public static final double driveSideKI = 0;
    public static final double driveSideKD = 0;

    //PID tuning values for driveTurnPID
    public static final double driveTurnKP = 0.05;
    public static final double driveTurnKI = 0;
    public static final double driveTurnKD = 0;

    //PID tuning values for driveSkew1PID
    public static final double driveSkew1KP = 0.05;
    public static final double driveSkew1KI = 0;
    public static final double driveSkew1KD = 0;

    // PID tuning values for driveSkew2PID
    public static final double driveSkew2KP = 0.5;
    public static final double driveSkew2KI= 0;
    public static final double driveSkew2KD = 0; 

    // PID tolerances for PID commands
    public static final int driveStraightTolerance = 1; 
    public static final int driveSideTolerance = 1; 
    public static final int gyroTurnTolerance = 1; 
    public static final int driveSkewTolerance = 1;

  }

  // Constants to be used for anything related to driving the robot and the controller
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int forwardAxis = 1;
    public static final int sideAxis = 0;
    public static final int rotationAxis = 4;

  }

  // Constants to be used for anything related to limelight
  public static class VisionConstants {
    public static final double cameraAngle = -1;
    public static final double tragetHeight = -1;
    public static final double cameraHeight = -1;
  }

  public static class NerfConstants  {
    public static final int TargetingApperatusrID = 5;
    public static final int powergunID = 0;
    public static final int shootgunID = 1;

  }
  
}
