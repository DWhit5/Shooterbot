// Hans Martin, Lucas Watts, Roy Hoang, Molly Prevost | ICS4U-01 | June 15, 2023
// This file encapsulates various objects and methods related to the drivetrain such as getting the wheels to turn; making them turn the right way; 
// converting back and forth between ticks and inches; getting x, y, and rotational axis units displayed on the Smart Dashboard; and getting the angle from the gyro.
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Package used to group related classes
package frc.robot.subsystems;

// Importing from the ctre library 
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// Importing from the wpi library
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Importing from the constants folder
import frc.robot.Constants.DrivetrainConstants;


/** Add your docs here. */
// Creating the DrivetrainSubsystem class which is a child class of SubsystemBase class
public class DrivetrainSubsystem extends SubsystemBase{
    
    // Creating instances of the WPI_TalonSRX class for each of the motor controllers using IDs assigned in phoenix tuner
    public WPI_TalonSRX frontLeft = new WPI_TalonSRX(DrivetrainConstants.frontLeftTalonID);
    private WPI_TalonSRX backLeft = new WPI_TalonSRX(DrivetrainConstants.backLeftTalonID);
    private WPI_TalonSRX frontRight = new WPI_TalonSRX(DrivetrainConstants.frontRightTalonID);
    public WPI_TalonSRX backRight = new WPI_TalonSRX(DrivetrainConstants.backRightTalonID);

    // Creating an instance of the MecanumDrive class to be able to use the motor controller objects
    public MecanumDrive mecDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    // Creating an instance of the ADXRS450_Gyro class
    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    // Constructor for the DrivetrainSubsystem class 
    public DrivetrainSubsystem() {

        // When not doing anything, brake wheels 
        frontLeft.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);

        // Makes wheels on the right side spin the right way
        frontRight.setInverted(true);
        backRight.setInverted(true);

        // Resets the encoders on each of the wheels so when the robot starts up, the encoders read zero
        frontLeft.setSelectedSensorPosition(0);
        backLeft.setSelectedSensorPosition(0);
        frontRight.setSelectedSensorPosition(0);
        backRight.setSelectedSensorPosition(0);

    }

    // Method to reset encoders
    public void resetEncoders() {

        // Use a built in motor controller method to set sensor position on every motor controller
        frontLeft.setSelectedSensorPosition(0);
        backLeft.setSelectedSensorPosition(0);
        frontRight.setSelectedSensorPosition(0);
        backRight.setSelectedSensorPosition(0);
    }

    public double distanceToTicks(double distanceInches) { // This method takes in a distance in inches
        double ticks = (distanceInches / (6 * Math.PI)) * 4096; // Take the input distance and divide by the circumference of the wheels (diameter 6 inches) then multiply by total ticks per rotation (4069 ticks)
        return ticks; // returns the distance in ticks
    }

    public double getFrontLeftDistance() { // This method returns distance in inches from ticks on the front left encoder
        double distance = (frontLeft.getSelectedSensorPosition() / 4096) * (6 * Math.PI) * -1; // Take the ticks and divide by the number of ticks in one rotation to get number of rotations, then multiply by circumference of wheels to get distance traveled
        return distance;
    }

    // Same as getFrontLeftDistance but for FrontRight motor controller
    public double getFrontRightDistance(){
        double distance = (frontRight.getSelectedSensorPosition() / 4096)* (6*Math.PI) * -1;
        return distance;
    }

    // This is a method that tells the robot what direction to move in by taking in x, y, and rotation axis directions as arguments
    public void setMecanum(double x, double y, double rx) {

        // A built in method of the MecanumDrive class and uses cartesian plane and directions to move around
        mecDrive.driveCartesian(x, y, rx);

        // Makes x, y, and rotational axis measurements visible on SmartDashboard, primarily from joysticks
        SmartDashboard.putNumber("x", x);
        SmartDashboard.putNumber("y", y);
        SmartDashboard.putNumber("rx", rx);
    }

    // A method for getting the angle from the gyro
    public double getGryoAngle(){
        return gyro.getAngle();
    }

}
