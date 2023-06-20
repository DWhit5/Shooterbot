// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package to group related classes 
package frc.robot;

// Import from constants folder 
import frc.robot.Constants.OperatorConstants;

// Imports for commandGroups 
import frc.robot.commandGroups.BackAndForth;
import frc.robot.commandGroups.PlusPatrol;
import frc.robot.commandGroups.OctagonPatrol;
import frc.robot.commandGroups.Square;

// Imports for subsystems 
import frc.robot.subsystems.DistanceSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.TargetingApperatusSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// Imports for commands
import frc.robot.commands.CancelAutomation;
import frc.robot.commands.Drivetrain.CameraLineUpGyroPID;
import frc.robot.commands.Drivetrain.CameraLineUpStrafePID;
import frc.robot.commands.Drivetrain.DriveSkew1;
import frc.robot.commands.Drivetrain.DriveSkew2;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.commands.Drivetrain.ResetEncoders;
import frc.robot.commands.TargetingApperatus.Aiming;
import frc.robot.commands.TargetingApperatus.Shooting;
import frc.robot.commands.TargetingApperatus.RevMotor;

import javax.management.remote.TargetedNotification;

// Imports from wpi library 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  final DistanceSubsystem distance =  new DistanceSubsystem();
  final VisionSubsystem vision = new VisionSubsystem();
  final TargetingApperatusSubsystem Apperatus = new TargetingApperatusSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final Joystick m_driverController =
      new Joystick(OperatorConstants.kDriverControllerPort);

  // public JoystickButton straightButton = new JoystickButton(m_driverController, OperatorConstants.forwardOnlyButton);

  // public JoystickButton button = new JoystickButton(m_driverController, 2);

  // Initializes various commands for different autonomous routes 
  public Command BackAndForthCommand = new BackAndForth(drivetrain);
  public Command OctagonPatrolCommand = new OctagonPatrol(drivetrain);
  public Command LineUpStrafeCommand = new CameraLineUpStrafePID(drivetrain, vision, 0);
  public Command LineUpGyroCommand = new CameraLineUpGyroPID(drivetrain, vision, 0);
  public Command PlusPatrolCommand = new PlusPatrol(drivetrain);
  public Command SquareCommand = new Square(drivetrain);
  public Command DriveSkew1Command = new DriveSkew1(drivetrain, 10);
  public Command DriveSkew2Command = new DriveSkew2(drivetrain, 10);
  public Command ShootingCommand = new Shooting(Apperatus);
  public Command RevCommand = new RevMotor(Apperatus);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    drivetrain.setDefaultCommand(
      new MecanumDriveCmd(
        drivetrain,
        () -> m_driverController.getRawAxis(OperatorConstants.forwardAxis), 
        () -> m_driverController.getRawAxis(OperatorConstants.sideAxis),
        () -> m_driverController.getRawAxis(OperatorConstants.rotationAxis))
    );
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

    // Line up with a target by moving side to side on its own
    new JoystickButton(m_driverController, 1).onTrue(BackAndForthCommand);  

    new JoystickButton(m_driverController, 3).onTrue(LineUpStrafeCommand);

    new JoystickButton(m_driverController, 4).onTrue(new ResetEncoders(drivetrain));

    // Button to cancel any current automated sequences that are running
    new JoystickButton(m_driverController, 2).
              onTrue(new CancelAutomation(drivetrain, 
                                          vision, 
                                          BackAndForthCommand,
                                          OctagonPatrolCommand,
                                          PlusPatrolCommand,
                                          SquareCommand,
                                          LineUpStrafeCommand,
                                          LineUpGyroCommand,
                                          DriveSkew1Command,
                                          DriveSkew2Command));

    new JoystickButton(m_driverController, 5).onTrue(RevCommand);

    new JoystickButton(m_driverController, 6).onTrue(ShootingCommand);

    


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
