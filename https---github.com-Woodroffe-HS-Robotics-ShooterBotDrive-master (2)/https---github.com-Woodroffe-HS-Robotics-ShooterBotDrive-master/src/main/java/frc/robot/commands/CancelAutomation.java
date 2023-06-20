// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file contains code that ensures that the robot is not doing anything when it isn't supposed to.
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package groups related classes for robot commands 
package frc.robot.commands;

// Imports from wpi library 
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

// Imports different robot subsystems 
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// Creates CancelAutomation class which is a childclass of the CommandBase class 
public class CancelAutomation extends CommandBase {
  /** Creates a new CancelAutomationInstant. */
  DrivetrainSubsystem drivetrain;
  VisionSubsystem vision;

  public Command BackAndForth, OctagonPatrol, PlusPatrol, Square, CameraLineUpStrafe, CameraLineUpGyro, DriveSkew1, DriveSkew2;
  
  public CancelAutomation(
          DrivetrainSubsystem drivetrain, 
          VisionSubsystem vision, 
          Command BackAndForth, 
          Command OctagonPatrol, 
          Command PlusPatrol, 
          Command Square, 
          Command CameraLineUpStrafe, 
          Command CameraLineUpGyro,
          Command DriveSkew1,
          Command DriveSkew2) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.BackAndForth = BackAndForth;
    this.OctagonPatrol = OctagonPatrol;
    this.PlusPatrol = PlusPatrol;
    this.Square = Square;
    this.CameraLineUpStrafe = CameraLineUpStrafe;
    this.CameraLineUpGyro = CameraLineUpGyro;
    this.DriveSkew1 = DriveSkew1;
    this.DriveSkew2 = DriveSkew2;

    addRequirements(drivetrain, vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    BackAndForth.cancel();
    OctagonPatrol.cancel();
    PlusPatrol.cancel();
    Square.cancel();
    CameraLineUpStrafe.cancel();
    CameraLineUpGyro.cancel();
    DriveSkew1.cancel();
    DriveSkew2.cancel();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
