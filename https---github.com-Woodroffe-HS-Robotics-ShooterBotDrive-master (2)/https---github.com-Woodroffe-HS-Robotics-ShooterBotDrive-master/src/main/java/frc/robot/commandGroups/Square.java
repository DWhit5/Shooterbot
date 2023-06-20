// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file contains code for the autonomous patrol for going in a square shaped route. 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drivetrain.DriveStraightPID;
import frc.robot.commands.Drivetrain.DriveTurnPID;
import frc.robot.subsystems.DrivetrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Square extends SequentialCommandGroup {
  /** Creates a new Square. */
  // Creates instance of drivetrainsubsystem class
  public Square(DrivetrainSubsystem drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    // Commands for driving straight and turning to go in a square
      addCommands(new DriveStraightPID(drivetrain, 20),
                  new DriveTurnPID(drivetrain, 90),
                  new DriveStraightPID(drivetrain, 20),
                  new DriveTurnPID(drivetrain, 90),
                  new DriveStraightPID(drivetrain, 20),
                  new DriveTurnPID(drivetrain, 90),
                  new DriveStraightPID(drivetrain, 20),
                  new DriveTurnPID(drivetrain, 90));
    }
  }

