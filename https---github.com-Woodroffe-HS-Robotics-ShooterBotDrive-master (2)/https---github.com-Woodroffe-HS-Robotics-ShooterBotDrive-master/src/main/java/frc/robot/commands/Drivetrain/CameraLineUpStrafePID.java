// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// Uses the drivetrain subsystem and vision subsystem to strafe back in forth in order to track the target using the limelight. 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package to group related classes for robot Drivetrain commands
package frc.robot.commands.Drivetrain;

// Imports from wpi library
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

// Imports from constants folder
import frc.robot.Constants.DrivetrainConstants;

// Imports robot subsystems 
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// Creates CameraLineUpStrafePID class which is a child class of the PIDCommand class
public class CameraLineUpStrafePID extends PIDCommand {

  /** Creates a new CameraLineUpPID. */
  public CameraLineUpStrafePID(DrivetrainSubsystem drivetrain, VisionSubsystem vision, double setpoint) {
    super(
        // The controller that the command will use
        new PIDController(DrivetrainConstants.driveSideKP, DrivetrainConstants.driveSideKI, DrivetrainConstants.driveSideKD),
        // This should return the measurement of TX (measurement of how offset the crosshair is from the target)
        () -> vision.getTX(),
        // This returns the setpoint 
        () -> setpoint,
        // This uses the output
        output -> {
          // Use the output here
          drivetrain.setMecanum(0, output, 0);
        });
    // States we need drivetrain and vision to run 
    addRequirements(drivetrain, vision);
    // Applies the tolerance which is the measurement of allowed inaccuracy which is a saved constant variable 
    getController().setTolerance(DrivetrainConstants.driveSideTolerance);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
