// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file encapsulates various objects, methods related to distance, and everything related to the Ultrasonic distance sensor.
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package used to group related classes
package frc.robot.subsystems;

// Imports from wpi library 
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Creating the DistanceSubsystem class which is a child class of the SubsytemBase class
public class DistanceSubsystem extends SubsystemBase {

  // Creates an instance of the Ultrasonic class using ports 5 and 6 for signal 
  public Ultrasonic distanceSensor = new Ultrasonic(5, 6);

  boolean bumperStatus = true;
  
  // Method to get distance 
  public double getDistance(){
    return distanceSensor.getRangeInches();
  }

  // Method to return a boolean that tells the user if bumper is still on based on the distance that the distance sensor is measuring
  public boolean bumperStatus() {
    // An if statement using shorthand known as the ternary operator
    bumperStatus = (getDistance() > 2)
    ? false
    : true;

    return bumperStatus;
  }
}
