// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DistanceSubsystem;

public class BumperStatus extends CommandBase {
  /** Creates a new BumperStatus. */
  DistanceSubsystem distance;
  public BumperStatus(DistanceSubsystem distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(distance);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (distance.getDistance() < 2) {
      SmartDashboard.putString("Bumper Status", "ON");
    } else {
      SmartDashboard.putString("Bumper Status", "OFF");
    }
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
