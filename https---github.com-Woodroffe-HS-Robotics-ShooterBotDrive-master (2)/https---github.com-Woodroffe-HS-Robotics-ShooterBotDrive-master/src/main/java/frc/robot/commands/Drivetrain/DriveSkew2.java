// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file contains code that allows the robot to move at a 45 degree diagonal direction forwards right and backwards left using the PID and drivetrain. 
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

// Imports DrivetrainSubsystem 
import frc.robot.subsystems.DrivetrainSubsystem;

// Creates DriveSkew2 class which is a child class of the PIDCommand class 
public class DriveSkew2 extends PIDCommand {
  /** Creates a new DriveSkew2. */
  public DriveSkew2(DrivetrainSubsystem drivetrain, double setpoint) {
    super(
        // The controller that the command will use
        new PIDController(DrivetrainConstants.driveSkew2KP,
                          DrivetrainConstants.driveSkew2KI,
                          DrivetrainConstants.driveSkew2KD),
        // This should return the measurement for distance traveling 
        () -> drivetrain.getFrontRightDistance(),
        // This returns the setpoint 
        () -> setpoint,
        // This uses the output
        output -> {
          // Output is put at halfpower along both planes so that opposing vectors cancel out, moving robot at a 45 degree angle 
          drivetrain.setMecanum(output/2, -output/2, 0);
        });
    // States that we need the drivetrain to run 
    addRequirements(drivetrain);
    // Sets the tolerance here (allowance for inaccuracy from target)
    getController().setTolerance(DrivetrainConstants.driveSkewTolerance);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
