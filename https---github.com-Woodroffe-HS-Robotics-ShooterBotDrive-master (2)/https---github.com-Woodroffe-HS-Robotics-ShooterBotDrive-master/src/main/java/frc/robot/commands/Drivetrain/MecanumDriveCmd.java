// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file contains code related to the wheels of the robot such as rotating speed using the drivetrain. 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package used to group related classes
package frc.robot.commands.Drivetrain;

// Imports from wpi library
import edu.wpi.first.wpilibj2.command.CommandBase;

// Imports from constants folder
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

// Imports the supplier type 
import java.util.function.Supplier;

// Creates MecanumDriveCmd class which is a child class of the CommandBase class
public class MecanumDriveCmd extends CommandBase {

  // Creates instance of DrivetrainSubsytem class
  private DrivetrainSubsystem driveSubsystem;
  // Creates supplier variables to be used later in the code 
  private Supplier<Double> forwardFunction, sideFunction, rotateFunction;

  // MecanumDriveCmd class constructor that uses DrivetrainSubsystem and the supplier variables declared above as arguments 
  public MecanumDriveCmd(
    DrivetrainSubsystem driveSubsystem,
    Supplier<Double> ff,
    Supplier<Double> sf,
    Supplier<Double> rf) {
    
    this.driveSubsystem = driveSubsystem;
    this.forwardFunction = ff;
    this.sideFunction = sf;
    this.rotateFunction = rf;

     // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // Declaring speed variables
    double speedForward, speedSide, speedRotate;
    // Applying max speed to direction functions and ensuring that they go in the right direction
    // Multiply forward function by max drive speed constant to cap speed and multiply by -1 to go in the right direction 
    speedForward = -1 * DrivetrainConstants.maxDriveSpeed * forwardFunction.get();
    // Multiply side function by max drive speed constant to cap speed
    speedSide = DrivetrainConstants.maxDriveSpeed * sideFunction.get();
    // Multiply rotate function by max drive speed constant to cap speed
    speedRotate = DrivetrainConstants.maxTurnSpeed * rotateFunction.get();

    // Use the method we made in drive subsystem to tell the robot what direction to move in 
    driveSubsystem.setMecanum(speedForward, speedSide, speedRotate);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
