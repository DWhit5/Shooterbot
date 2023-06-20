// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file contains code that allows the robot to move sideways using the PID and drivetrain. 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package to group related classes for robot Drivetrain commands 
package frc.robot.commands.Drivetrain;

// Imports from wpi library 
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

// Import from Constants folder 
import frc.robot.Constants.DrivetrainConstants;

// Import for DrivetraimSubsystem 
import frc.robot.subsystems.DrivetrainSubsystem;

// Creates DriveSidePID class which is a child class of the PIDCommand class
public class DriveSidePID extends PIDCommand {
  /** Creates a new DriveSide. */
  public DriveSidePID(DrivetrainSubsystem drivetrain, double setpoint) {
    super(
        // The controller that the command will use
        new PIDController(DrivetrainConstants.driveSideKP,
                          DrivetrainConstants.driveSideKI,
                          DrivetrainConstants.driveSideKD),
        // This should return the measurement of the distance traveled
        () -> drivetrain.getFrontLeftDistance(),
        // This returns the setpoint 
        () -> setpoint,
        // This uses the output
        output -> {
          // Uses the output here to move along the y plane (side to side)
          drivetrain.setMecanum(0, output, 0);
        });
    // States that we need the drivetrain to run 
    addRequirements(drivetrain);
    // Sets the tolerance, the allowance for inaccuracy, as a number assigned to a variable in the constants folder 
    getController().setTolerance(DrivetrainConstants.driveSideTolerance);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
