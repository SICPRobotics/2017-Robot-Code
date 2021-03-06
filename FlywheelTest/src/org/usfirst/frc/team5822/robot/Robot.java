package org.usfirst.frc.team5822.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	
	CANTalon armR;
	Timer tele;
	boolean flag; 
	
	@Override
	public void robotInit() 
	{
		armR = new CANTalon(1); 
		armR.setFeedbackDevice(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
		armR.setPID(1.3, .001, 35, 0.001, 0, 0, 0);
		armR.reverseSensor(true); 
		tele = new Timer();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	@Override
	public void teleopInit()
	{
		armR.changeControlMode(TalonControlMode.PercentVbus);
		tele.start();
	}

	
	
		
	/**
	 * This function is called periodically during operator control
	 */
	@Override
		public void teleopPeriodic() 
	{
		if (tele.get()<2)
			armR.set(.75);
		else 
			flag = true; 
		if (flag)
		{
			armR.changeControlMode(TalonControlMode.Speed);
			armR.set(8850); 
			armR.reverseSensor(true); 
			armR.enableControl(); //Enable PID control on the talon
			flag = false;
			SmartDashboard.putNumber("Speed", armR.getSpeed());
		}
			
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

