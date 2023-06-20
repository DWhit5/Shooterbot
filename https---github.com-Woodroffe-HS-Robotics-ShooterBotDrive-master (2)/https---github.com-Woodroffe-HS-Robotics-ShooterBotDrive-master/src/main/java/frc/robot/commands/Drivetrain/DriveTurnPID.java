// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file contains code that allows the robot to turn in place using the gyro, PID, and drivetrain. 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package groups related classes for Drivetrain robot commands 
package frc.robot.commands.Drivetrain;

// Imports from wpi library 
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

// Import from Constants folder 
import frc.robot.Constants.DrivetrainConstants;

// Import for DrivetrainSubsystem 
import frc.robot.subsystems.DrivetrainSubsystem;

// Creates the DriveTurnPID class which is a child class of the PIDCommand class 
public class DriveTurnPID extends PIDCommand {
  /** Creates a new Turn. */
  public DriveTurnPID(DrivetrainSubsystem drivetrain, double setpoint) {
    super(
        // The controller that the command will use
        new PIDController(DrivetrainConstants.driveTurnKP, 
                          DrivetrainConstants.driveTurnKI, 
                          DrivetrainConstants.driveTurnKD),
        // This should return the measurement for the angle being turned 
        () -> drivetrain.getGryoAngle(),
        // This returns the setpoint 
        () -> setpoint,
        // This uses the output
        output -> {
          // Uses the output for the rotational axis (rx) in order to turn 
          drivetrain.setMecanum(0, 0, output);
        });
    // States that the code requires the drivetrain 
    addRequirements(drivetrain);
    // Sets the tolerance here which is the allowed inaccuracy from the target 
    getController().setTolerance(DrivetrainConstants.gyroTurnTolerance);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
