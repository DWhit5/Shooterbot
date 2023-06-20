// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file encapsulates various objects and methods for vision such as the limelight and calculating distance through trigonometry. 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package used to group related classes
package frc.robot.subsystems;

// Imports from wpi library
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Import from constants folder 
import frc.robot.Constants.VisionConstants;

// Creating VisionSubsystem class which is a child class of SubsystemBase
public class VisionSubsystem extends SubsystemBase {

  // Creates a variable for current limelight pipeline
  public int currentPipeline = 0;

  // Creates an instance of the NetworkTable class using the limelight key to specify the kind of network table
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  // Creating variables for tx, ty, tv values in the network table
  NetworkTableEntry txEntry = table.getEntry("tx");
  NetworkTableEntry tyEntry = table.getEntry("ty");
  NetworkTableEntry tvEntry = table.getEntry("tv");
  // Gets the current pipeline from the network table
  NetworkTableEntry pipelineEntry = table.getEntry("pipeline");

  // setPipeline is a method that sets the pipeline and updates the current pipeline variable 
  public void setPipeline(int pipeline) {
    pipelineEntry.setNumber(pipeline);
    currentPipeline = pipeline;
  }

  // Getter method for getting ty
  public double getTY() {
    return tyEntry.getDouble(-10000);
  }

  // Getter method for getting tx
  public double getTX(){
    return txEntry.getDouble(-10000);
  }

  // Getter method for getting tv
  public double getTV(){
    return tvEntry.getDouble(-10000);
  }

  // Getter method for getting currentPipeline
  public int getPipeline(){
    return currentPipeline;
  }

// Method for turning limelight LED off
public void limelightLEDOff() {
  table.getEntry("ledMode").setNumber(1);
}

// Method for turning limelightLED on
public void limelightLEDOn(){
  table.getEntry("ledMode").setNumber(3);
}

// Method for getting distance to target using limelight and trig (pythagorean theorum, and tangent)
public double detDistance() {

  // totalAngle adds the angle of the camera and the angle from the crosshair in the y direction and converts it to radians
  double totalAngle = Math.toRadians((tyEntry.getDouble(-1000)) + VisionConstants.cameraAngle);
  //targetDistance finds height from the camera to the target an uses the angle and tangent to calculate distance in the x direction
  double targetDistance = (VisionConstants.tragetHeight - VisionConstants.cameraHeight) / Math.tan(totalAngle);
  
  return targetDistance;
}
}
