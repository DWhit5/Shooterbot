package frc.robot.commands.TargetingApperatus;

import edu.wpi.first.wpilibj.DigitalOutput;
import frc.robot.subsystems.TargetingApperatusSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class Shooting extends CommandBase{
    public boolean RevMotors = false;

    DigitalOutput output = new DigitalOutput(0);


    public Shooting(TargetingApperatusSubsystem apperatus) {
        output.set(true);
    }

}