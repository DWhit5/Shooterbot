// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file contains code that allows the robot to move forwards and backwards using the PID and drivetrain.
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package groups related classes for Drivetrain robot commands 
package frc.robot.commands.Drivetrain;

// Imports from wpi library 
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

// Import from constants folder 
import frc.robot.Constants.DrivetrainConstants;

// Import for DrivetrainSubsystem 
import frc.robot.subsystems.DrivetrainSubsystem;

// Creates DriveStraightPID class which is a child class of the PIDCommand class 
public class DriveStraightPID extends PIDCommand {
  DrivetrainSubsystem drivetrain;

  /** Creates a new DriveStraight. */
  public DriveStraightPID(DrivetrainSubsystem drivetrain, double setpoint) {
    super(
        // The controller that the command will use
        new PIDController(DrivetrainConstants.driveStraightKP, 
                          DrivetrainConstants.driveStraightKI, 
                          DrivetrainConstants.driveStraightKD),
        // This should return the measurement for distance traveling 
        () -> drivetrain.getFrontLeftDistance(),
        // This returns the setpoint 
        () -> setpoint,
        // This uses the output
        output -> {
          // Uses the output here for the x axis, making the robot move back and forth 
          drivetrain.setMecanum(-output,0,0);
        });
    // States that we need the drivetrain for the code to run 
        addRequirements(drivetrain);
    // Sets the tolerance here which is the allowance for inaccuracy from target 
        getController().setTolerance(DrivetrainConstants.driveStraightTolerance);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
