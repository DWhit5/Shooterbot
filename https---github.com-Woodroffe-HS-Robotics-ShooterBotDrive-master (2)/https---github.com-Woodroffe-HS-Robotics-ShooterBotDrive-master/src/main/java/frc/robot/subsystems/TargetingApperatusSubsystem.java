package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Constants.NerfConstants;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class TargetingApperatusSubsystem extends SubsystemBase {
    
    private final WPI_TalonSRX motorShoot = new WPI_TalonSRX(NerfConstants.TargetingApperatusrID);


	private double ShootSpeed = 1;
	private double AimSpeed = 0.5;
	private boolean Rev = false;
	private DigitalOutput Output;
	


	public void enable() {
		Rev = true;
	}
	
	public void disable() {
		Rev = false;
	}

	@Override
	public void periodic() {
		if(Rev) {
			motorShoot.set(ShootSpeed);
		} else {
			motorShoot.set(0);
		}
	}





    public TargetingApperatusSubsystem() {
		motorShoot.setNeutralMode(NeutralMode.Brake);
    }

    public void setUP() {
		motorShoot.set(AimSpeed);
	}
	
	public void setDown() {
		motorShoot.set(-AimSpeed);
	}

	public void setStop() {
		motorShoot.set(0);
	}


    public void Firing() {
        Output = new DigitalOutput(0);
        Output.set(false);
      }
  

	
}