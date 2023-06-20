package frc.robot.commands.TargetingApperatus;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TargetingApperatusSubsystem;

    

public class Aiming extends CommandBase {
    private final TargetingApperatusSubsystem sShoot;
	private final RobotContainer robotContainer;

    public Aiming(RobotContainer robotcontainer, TargetingApperatusSubsystem sShoot) {
		addRequirements(sShoot);
		
		this.sShoot = sShoot;
		this.robotContainer = robotcontainer;
	}

//    @Override
//	public void execute() {
//		if(robotContainer.getButtonAimingMotorUP()) {
//			sShoot.setUP();
//		} else if(robotContainer.getButtonAimingMotorDOWN()) {
//			sShoot.setDown();
//		} else {
//			sShoot.setStop();
//		}
//	}

}