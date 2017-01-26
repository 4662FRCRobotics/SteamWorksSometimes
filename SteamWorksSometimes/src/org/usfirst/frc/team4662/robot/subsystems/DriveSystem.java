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
	
	
	RobotDrive steamDrive = new RobotDrive(ControllerLeft1, ControllerRight1); //makes the drive
	 
	public DriveSystem(){
		
		ControllerLeft1 = new CANTalon(RobotMap.leftMotor1);
		ControllerLeft2 = new CANTalon(RobotMap.leftMotor2);
		//ControllerLeft1.setInverted(true);
		ControllerRight1 = new CANTalon(RobotMap.rightMotor1);
		ControllerRight2 = new CANTalon(RobotMap.rightMotor2);
		
		ControllerLeft2.changeControlMode(CANTalon.TalonControlMode.Follower); //makes the controllers followers
		ControllerRight2.changeControlMode(CANTalon.TalonControlMode.Follower);
		
		ControllerLeft2.set(ControllerLeft1.getDeviceID()); //tells them who to follow
		ControllerRight2.set(ControllerRight1.getDeviceID());
		
		ControllerLeft1.enable();
		ControllerRight1.enable();
	
		
		
		SmartDashboard.putString("DriveSytem", "ConstructorMethod");
		
		
	}
	
	public void ArcadeDrive(double stickX, double stickY){
		
		steamDrive.arcadeDrive(stickX, stickY);		
		//ControllerLeft1.set(stickY);
		logDashboard(stickY, stickX);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArcadeDrive());
    	
    	 	
   	    	
    }
    
    public void logDashboard (double Y, double X){
    	
    	SmartDashboard.putNumber("Left1Temp", ControllerLeft1.getTemperature());
    	SmartDashboard.putNumber("Left1Amps", ControllerLeft1.getOutputCurrent());
    	
    	SmartDashboard.putNumber("Right1Temp", ControllerRight1.getTemperature());
    	SmartDashboard.putNumber("Right1Amps", ControllerRight1.getOutputCurrent());
    			
    	
    }
   
    
}

