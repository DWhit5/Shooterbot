// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file contains code for the autonomous patrol for traveling an octogan-shaped route. 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drivetrain.DriveSidePID;
import frc.robot.commands.Drivetrain.DriveSkew1;
import frc.robot.commands.Drivetrain.DriveSkew2;
import frc.robot.commands.Drivetrain.DriveStraightPID;
import frc.robot.subsystems.DrivetrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class OctagonPatrol extends SequentialCommandGroup {
  /** Creates a new OctagonPatrol. */
  // Creates instance of drivetrainsubsystem class
  public OctagonPatrol(DrivetrainSubsystem drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    // Commands for driving straight, driving at 45 degree angles, and driving sideways
    addCommands(new DriveStraightPID(drivetrain, 10),
                new DriveSkew1(drivetrain, 10),
                new DriveSidePID(drivetrain, -10),
                new DriveSkew2(drivetrain, -10),
                new DriveStraightPID(drivetrain, -10),
                new DriveSkew2(drivetrain, -10),
                new DriveSidePID(drivetrain, 10),
                new DriveSkew1(drivetrain, 10));
  }
}
