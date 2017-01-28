package org.usfirst.frc.team4662.robot.subsystems;

import org.usfirst.frc.team4662.robot.RobotMap;
import org.usfirst.frc.team4662.robot.commands.ArcadeDrive;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	
	
	private CANTalon ControllerLeft1;
	private CANTalon ControllerLeft2;
	private CANTalon ControllerRight1;
	private CANTalon ControllerRight2;
	
	
	
	private RobotDrive steamDrive; //makes the drive
	
	private double m_dThrottleDirection;
	
	public DriveSystem(){
		
		ControllerLeft1 = new CANTalon(RobotMap.leftMotor1);
		ControllerLeft2 = new CANTalon(RobotMap.leftMotor2);
		ControllerLeft1.setInverted(false);
		ControllerRight1 = new CANTalon(RobotMap.rightMotor1);
		ControllerRight2 = new CANTalon(RobotMap.rightMotor2);
		ControllerRight1.setInverted(true);
		
		ControllerLeft2.changeControlMode(CANTalon.TalonControlMode.Follower); //makes the controllers followers
		ControllerRight2.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		ControllerLeft2.set(ControllerLeft1.getDeviceID()); //tells them who to follow
		ControllerRight2.set(ControllerRight1.getDeviceID());
		
		ControllerLeft1.enable();
		ControllerRight1.enable();
		
		steamDrive = new RobotDrive(ControllerLeft1, ControllerRight1);
		
//		SmartDashboard.putString("DriveSytem", "ConstructorMethod");
		
		m_dThrottleDirection = 1;
		
	}
	
	public void ArcadeDrive(double stickX, double stickY){
		stickY = stickY * m_dThrottleDirection;
//		stickX = stickX * m_dThrottleDirection; tried this and it is backwards when reversed 
		steamDrive.arcadeDrive(stickX, stickY);		
		
		logDashboard(stickY, stickX);
	}
	
	public void ToggleEnds()  {
// this still needs better safety code 
// turning the drive off will take time to spin down
// how about putting a check for rotation speed by an encoder and only allownig the switch once it's below a threshold
// the command will execute until the direction is toggled
// need to add an indicator (boolean) that is false unless the toggle is done
// then a method to return the boolean to the toggle command
		ArcadeDrive(0,0);
		m_dThrottleDirection = m_dThrottleDirection * -1;
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArcadeDrive());
    }
    
    public void logDashboard (double Y, double X){
    	
    	SmartDashboard.putNumber("DriverStickY", Y);
    	SmartDashboard.putNumber("DriverStickX", X);
    	SmartDashboard.putNumber("Left1Temp", ControllerLeft1.getTemperature());
    	SmartDashboard.putNumber("Left1Amps", ControllerLeft1.getOutputCurrent());
    	SmartDashboard.putNumber("Left1VFactor", ControllerLeft1.getOutputVoltage()/ControllerLeft1.getBusVoltage());
    	SmartDashboard.putNumber("LeftVBus", ControllerLeft1.getBusVoltage());
    	SmartDashboard.putNumber("LeftVOut", ControllerLeft1.getOutputVoltage());
    	
    	
    	SmartDashboard.putNumber("Right1Temp", ControllerRight1.getTemperature());
    	SmartDashboard.putNumber("Right1Amps", ControllerRight1.getOutputCurrent());
    	
//    	SmartDashboard.putNumber("Right1Encoder, ControllerRight1.get)
    			
    	SmartDashboard.putNumber("DriveYToggle", m_dThrottleDirection);
    }
   
    
}

