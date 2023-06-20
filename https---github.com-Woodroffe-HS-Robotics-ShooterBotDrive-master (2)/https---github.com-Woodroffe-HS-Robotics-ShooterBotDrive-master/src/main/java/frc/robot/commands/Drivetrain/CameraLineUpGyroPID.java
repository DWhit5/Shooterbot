// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// Uses the drivetrain subsystem and vision subsystem to rotate the bot using the gyro to lineup with targets which are seen by limelight. 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package to group related classes for robot Drivetrain commands
package frc.robot.commands.Drivetrain;

// Imports from wpi library 
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

// Import from constants folder 
import frc.robot.Constants.DrivetrainConstants;

// Imports for different robot subsystems
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// Creates CameraLineUpGyroPID class which is a child class of the PIDCommand class
public class CameraLineUpGyroPID extends PIDCommand {

  /** Creates a new CameraLineUpGyroPID. */
  public CameraLineUpGyroPID(DrivetrainSubsystem drivetrain, VisionSubsystem vision, double setpoint) {
    super(
        // The controller that the command will use
        new PIDController(DrivetrainConstants.driveTurnKP, 
                          DrivetrainConstants.driveTurnKI, 
                          DrivetrainConstants.driveTurnKD),
        // This should return the measurement of TX (measurement of how offset the crosshair is from the target)
        () -> vision.getTX(),
        // This return the setpoint
        () -> setpoint,
        // This uses the output
        output -> {
          // Output is the amount of degrees the camera is turning by 
          drivetrain.setMecanum(0, 0, output);
        });
    // States we need vision and the drivetrain to run
        addRequirements(drivetrain, vision);
    // Tolerance is the allowance for innaccuracy which is set to a constant variabele
        getController().setTolerance(DrivetrainConstants.gyroTurnTolerance);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
