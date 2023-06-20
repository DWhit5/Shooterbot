// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023/
// This file contains code that allows the robot to move at a 45 degree diagonal direction forwards left and backwards right using the PID and drivetrain. 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package groups related classes for Drivetrain commands for the robot
package frc.robot.commands.Drivetrain;

// Imports from wpi library 
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

// Import from Constants folder 
import frc.robot.Constants.DrivetrainConstants;

// Import for DrivetrainSubsystem 
import frc.robot.subsystems.DrivetrainSubsystem;

// Creates DriveSkew1 class which is a child class of the PIDCommand class 
public class DriveSkew1 extends PIDCommand {
  /** Creates a new DriveSkew1. */
  public DriveSkew1(DrivetrainSubsystem drivetrain, double setpoint) {
    super(
        // The controller that the command will use
        new PIDController(DrivetrainConstants.driveSkew1KI, 
                          DrivetrainConstants.driveSkew1KP, 
                          DrivetrainConstants.driveSkew1KD),
        // This should return the measurement for the distance traveled
        () -> drivetrain.getFrontLeftDistance(),
        // This returns the setpoint
        () -> setpoint,
        // This uses the output
        output -> {
          // Uses the output here and is only applied to two wheels so that the robot can travel at a 45 degree angle 
          drivetrain.frontLeft.set(output);
          drivetrain.backRight.set(output);
        });
    // States that we need the drivetrain to run 
    addRequirements(drivetrain);
    // Sets the tolerance (allowance for inaccuracy of target) here 
    getController().setTolerance(DrivetrainConstants.driveSkewTolerance);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
