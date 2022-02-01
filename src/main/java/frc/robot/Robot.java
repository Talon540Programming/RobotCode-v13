/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String startLeft = "Left Start";
  private static final String startMiddle = "Middle Start";
  private static final String startRight = "Right Start";
  private static final String testAuto = "Test Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  // Motors
  private TalonFX frontLeft, backLeft, frontRight, backRight;
  private TalonSRX wrist, rollers, shooterFly1, shooterFly2, shooterTransition, index, climb, wench;

  // Controllers
  private Joystick leftJoy, rightJoy; // Drive Station Joysticks
  private XboxController xbox; // Drive Station Xbox Controller
  
  // Sensors
  private Encoder enc1, enc2, enc3, enc4, enc5;
  //private ADXRS450_Gyro gyro;
  //private LiDARSensor liDAR;

  // Sensor Values
  private double dist, pulse, dist2, pulse2, dist3, pulse3, dist4, pulse4, dist5, pulse5, angle;
  
   // Autonomous counter
  private int counter;

  // Unknown Constants
  private static final double PULSES_PER_INCH = 1282.3333;
  private static final double DRIVE_CONSTANT = 0.4;
  private static final double WHEEL_CIRCUMFERENCE = 100.53;
  private static final int TIME = 100; // Change time for index

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("Left Start", startLeft);
    m_chooser.addOption("Middle Start", startMiddle);
    m_chooser.addOption("Right Start", startRight);
    m_chooser.addOption("Test Auto", testAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //Drivetrain motors
    frontRight = new TalonFX(RobotMap.DRIVETRAIN_FRONTRIGHT);
    backRight = new TalonFX(RobotMap.DRIVETRAIN_BACKRIGHT);
    frontLeft = new TalonFX(RobotMap.DRIVETRAIN_BACKLEFT);
    backLeft = new TalonFX(RobotMap.DRIVETRAIN_BACKLEFT);

    //Shooter motors
    shooterFly1 = new TalonSRX(RobotMap.SHOOTER_FLY1);
    shooterFly2 = new TalonSRX(RobotMap.SHOOTER_FLY2);
    shooterTransition = new TalonSRX(RobotMap.SHOOTER_TRANSITION);

    //Intake
    wrist = new TalonSRX(RobotMap.INTAKE_WRIST);
    rollers = new TalonSRX(RobotMap.INTAKE_ROLLERS);
    
    //indexer
    index = new TalonSRX(RobotMap.INDEXER);
    
    //Climb
    climb = new TalonSRX(RobotMap.CLIMB);
    //wench = new TalonSRX(RobotMap.WENCH);

    //Drivetrain enc1 TODO: setMaxPeriod, setMinRate, setDistancePulse, setSamplesToAverage
    enc1 = new Encoder(0, 1, false);
    
    //Rotation Control Encoder
    enc2 = new Encoder(4, 5, false);
    
    //Shooter Encoder
    enc3 = new Encoder(6, 7, false);

    frontLeft.getSensorCollection().setIntegratedSensorPosition(0, 10);
    frontRight.getSensorCollection().setIntegratedSensorPosition(0, 10);

    // Control Initializations
    leftJoy = new Joystick(0);
    rightJoy = new Joystick(1);
    xbox = new XboxController(2);

    // Gyro initialization

    //xbox.setRumble(RumbleType.kLeftRumble, 0.5);
    //xbox.setRumble(RumbleType.kRightRumble, 0.5);
  }
  
  /**
   * This function sets the speed of the motors based on the parameters
   * @param l The speed of the left motors
   * @param r The speed of the right motors
   */
  public void setMotors(double l, double r) {  
    frontLeft.set(ControlMode.PercentOutput, l);
    backLeft.set(ControlMode.PercentOutput, l);
    frontRight.set(ControlMode.PercentOutput, -r);
    backRight.set(ControlMode.PercentOutput, -r);
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //angle = gyro.getAngle();
    pulse = frontLeft.getSelectedSensorPosition(); // Drivetrain
    //pulse2 = enc2.get(); // Drivetrain
    //pulse3 = enc3.get(); // Shooter
    //pulse4 = enc4.get(); // Control Panel
    //pulse5 = enc5.get();
    dist = frontLeft.getSelectedSensorPosition() / PULSES_PER_INCH;
    //dist2 = enc2.getDistance();
    //dist3 = enc3.getDistance();
    //dist4 = enc4.getDistance();
    //dist5 = enc5.getDistance();

    SmartDashboard.putNumber("Angle: ", angle);
    SmartDashboard.putNumber("Distance: ", dist);
    SmartDashboard.putNumber("Distance2: ", dist2);
    SmartDashboard.putNumber("Distance3: ", dist3);
    SmartDashboard.putNumber("Distance4: ", dist4);
    SmartDashboard.putNumber("Distance5: ", dist5);
    SmartDashboard.putNumber("Pulse: ", pulse);
    SmartDashboard.putNumber("Pulse2: ", pulse2);
    SmartDashboard.putNumber("Pulse3: ", pulse3);
    SmartDashboard.putNumber("Pulse4: ", pulse4);
    SmartDashboard.putNumber("Pulse5: ", pulse5);
      
    switch (m_autoSelected) {
      case startLeft:
        // TODO: code for starting on left hand side of field
        /*
        Steps:
        1. Move across line
        2. Shoot 3 balls
        3. Move towards trench to collect balls during telop
        */
        if(counter == 0) {
          shooterAuto();
        }
        if(counter == 1) {
          turnRight(180);
        }
        if(counter == 2){
          moveForward(4);
        }
      break;
      case startMiddle:
        // TODO: code for starting on center of field
        /*
        Steps:
        1. Move across line
        2. Shoot 3 balls
        3. Move to the loading bay to collect balls during telop
        */
        if(counter == 0) {
          shooterAuto();
        }
        if(counter == 1) {
          turnRight(180);
        }
        if(counter == 2){
          moveForward(8);
        }
      break;
      case startRight:
        // TODO: code for starting on right hand side of field; to be programmed when strategy is finalized
        /*
        Steps:
        1. Move across line
        2. Shoot 3 balls
        3. Move towards rendezvous point to collect balls during telop
        */
        if(counter == 0) {
          shooterAuto();
        }
        if(counter == 1) {
          turnRight(180);
        }
        if(counter == 2){
          moveForward(3);
        }
      break;
      case testAuto:
        if (counter == 0) {
          //moveForward(24);
          turnLeft(90);
        }
        break;
      case kDefaultAuto:
      default:
        setMotors(0,0);
      break;
    }
  }
  
  /**
   * Get the proportion of moter speed based on the distance to the target value.
   * Used in auto.
   * 
   * @param target
   *            the target value
   * @param currentEnc
   *            the current encoder value
   * @return the proportion of the motor speed constant set
   */
  public static double prop(double target, double currentEnc){
    if (((target - currentEnc) / target ) < 0.3) {
      return 0.1;
    } else{
      return (((target - currentEnc) / target) * DRIVE_CONSTANT);
    }
  }
  
  /**
   * Gets the proportion to be used with motor speed during gyro turns. Used in
   * auto.
   * Target always positive
   * 
   * @param target
   *            the target value
   * @param currentGyro
   *            the current gyro value
   * @return the proportion of the motor speed constant to set
   */ 
  public static double propGyro(double target, double currentGyro) {
    if ((1 - (currentGyro / target)) <= 0.5) {
      return 1 - (currentGyro / target);
    }
    return 0.3;
  }

  /**
   * Moves the robot forward in autonomous a specified amount
   * 
   * @param targetDistance
   *        the distance to travel in inches
   */ 
  private void moveForward(double targetDistance) {
    if (dist >= targetDistance) {
      setMotors(0, 0);
      //frontLeft.getSensorCollection().setIntegratedSensorPosition(0, 10);
      counter++;
    } else {
      setMotors(prop(targetDistance, dist), prop(targetDistance, dist));
    }
  }

  /**
   * Turns the robot to the left a specified amount. Used in auto.
   * 
   * @param targetAngle
   *            the angle to turn to (degrees)
   */ 
  private void turnLeft(double targetAngle) {
    //targetAngle = -targetAngle;
    if (angle > targetAngle) {
      setMotors(0,0);
      frontLeft.getSensorCollection().setIntegratedSensorPosition(0, 10);
      counter++;
    }
    else {
      setMotors(-propGyro(targetAngle, angle), propGyro(targetAngle, angle));
    }
  }

  /**
   * Turns the robot to the right a specified amount. Used in auto.
   * 
   * @param targetAngle
   *            the angle to turn to (degrees)
   */ 
  private void turnRight(double targetAngle) {
    if (angle > targetAngle) {
      setMotors(0,0);
      enc1.reset();
      counter++;
    }
    else {
      setMotors(propGyro(targetAngle, angle), -propGyro(targetAngle, angle));
    }
  }

  /**
   * The shooter method for auto mode
   */
  public void shooterAuto() {
    // TODO: insert alignment code
    if (dist3 >= 4) { // find perfect distance for the five balls shot
      shooterFly1.set(ControlMode.PercentOutput, 0);
      shooterFly2.set(ControlMode.PercentOutput, 0);
      shooterTransition.set(ControlMode.PercentOutput, 0);
      enc3.reset();
      counter++;
    } else {
      shooterFly1.set(ControlMode.PercentOutput, 1);
      shooterFly2.set(ControlMode.PercentOutput, 1);
      shooterTransition.set(ControlMode.PercentOutput, 0.25);
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    //angle = gyro.getAngle();
    pulse = frontLeft.getSelectedSensorPosition(); // Drivetrain - left
    pulse2 = frontRight.getSelectedSensorPosition(); // Drivetrain - right
    pulse3 = enc1.get(); // Shooter
    //pulse4 = enc4.get(); // Control Panel
    //pulse5 = enc5.get();
    dist = frontLeft.getSelectedSensorPosition() / PULSES_PER_INCH;
    dist2 = frontRight.getSelectedSensorPosition() / PULSES_PER_INCH;
    dist3 = enc1.getDistance();
    //dist4 = enc4.getDistance();
    //dist5 = enc5.getDistance();

    SmartDashboard.putNumber("Angle: ", angle);
    SmartDashboard.putNumber("Distance: ", dist);
    SmartDashboard.putNumber("Distance2: ", dist2);
    SmartDashboard.putNumber("Distance3: ", dist3);
    SmartDashboard.putNumber("Distance4: ", dist4);
    SmartDashboard.putNumber("Distance5: ", dist5);
    SmartDashboard.putNumber("Pulse: ", pulse);
    SmartDashboard.putNumber("Pulse2: ", pulse2);
    SmartDashboard.putNumber("Pulse3: ", pulse3);
    SmartDashboard.putNumber("Pulse4: ", pulse4);
    SmartDashboard.putNumber("Pulse5: ", pulse5);

    //xbox.setRumble(RumbleType.kLeftRumble, 0.5);
    //xbox.setRumble(RumbleType.kRightRumble, 0.5);

    // reset button
    if (leftJoy.getTrigger()) {
      frontLeft.getSensorCollection().setIntegratedSensorPosition(0, 10);
      frontRight.getSensorCollection().setIntegratedSensorPosition(0, 10);
    }

    drive();
    shooter();
    index();
    climb();
    wrist();
    intake();
  }

  /**
   * This method takes inputs from joysticks and translates it to motor speeds for the drive train
   */
  public void drive() {
     // Joysticks return inverted inputs (because they are made for flight simulators)
    double right = -rightJoy.getY();
    double left = -leftJoy.getY();

    // Deadzone for joystick
    if (Math.abs(right) < 0.2) {
      right = 0;
    }
    if (Math.abs(left) < 0.2) {
      left = 0;
    }
    setMotors(left, right);
  }

  /**
   * The method for shooting in teleop
   */
  public void shooter() {
    if(xbox.getLeftBumper()) { // LB Button
       shooterTransition.set(ControlMode.PercentOutput, -0.5);
    } else {
      shooterTransition.set(ControlMode.PercentOutput, 0);
    }

    if (xbox.getRightBumper()) { // RB Button
      shooterFly1.set(ControlMode.PercentOutput, -0.8);
      shooterFly2.set(ControlMode.PercentOutput, -0.8);
    } else {
      shooterFly1.set(ControlMode.PercentOutput, 0);
      shooterFly2.set(ControlMode.PercentOutput, 0);
    }
  }

  /**
   * Operates the index motors
   */
  private void index() {
    if(Math.abs(xbox.getRightY()) > 0.2) { 
      index.set(ControlMode.PercentOutput, xbox.getRightY());
    } else {
      index.set(ControlMode.PercentOutput, 0);
    }
  }
  
  /**
   * Operates the climber motor
   */
  public void climb() {
    if (xbox.getLeftTriggerAxis() > 0.2) { // Left Trigger
      climb.set(ControlMode.PercentOutput, -xbox.getLeftTriggerAxis());
    } else if (xbox.getRightTriggerAxis() > 0.2) { // Right Trigger
      climb.set(ControlMode.PercentOutput, (xbox.getRightTriggerAxis()/2)+0.5);
    } else {
      climb.set(ControlMode.PercentOutput, 0);
    }

    if (xbox.getAButton()) {
      //wench.set(ControlMode.PercentOutput, 0.75);
    } else {
      //wench.set(ControlMode.PercentOutput, 0);
    }
  }
  
   /**
    * Used to change the position of the wrist
    */
  public void wrist() {
		if (xbox.getYButton()) { // Raise wrist
      wrist.set(ControlMode.PercentOutput, 0.75);
		} else if (xbox.getBButton()) { // Lower wrist
			wrist.set(ControlMode.PercentOutput, -0.4);
		}	else {
			wrist.set(ControlMode.PercentOutput, 0);
    }
  }

  /**
   * Operates the intake motors using the y-axis of the right joystick
   */
  private void intake() {
	  if (Math.abs(xbox.getLeftY()) > 0.2) { // deadzone
		  rollers.set(ControlMode.PercentOutput, xbox.getLeftY());
	  } else {
	    rollers.set(ControlMode.PercentOutput, 0);
	  }
  }
}

