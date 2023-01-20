package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.constants.RobotMap;
import frc.robot.drivetrain.DrivetrainBase;
import frc.robot.drivetrain.commands.control.AttackJoystickDriveControl;
import frc.robot.intake.IntakeBase;
import frc.robot.intake.commands.control.AttackJoystickIntakeControl;
import frc.robot.shooter.ShooterBase;
import frc.robot.shooter.commands.control.AttackJoystickShooterControl;

public class RobotContainer {
  private final CommandJoystick leftJoystick = new CommandJoystick(RobotMap.kLeftJoystickPort);
  private final CommandJoystick rightJoystick = new CommandJoystick(RobotMap.kRightJoystickPort);
  private final TalonXboxController xboxController = new TalonXboxController(2);

  // Subsystems
  private final DrivetrainBase drivetrainBase = new DrivetrainBase();
  private final ShooterBase shooterBase = new ShooterBase();
  private final IntakeBase intakeBase = new IntakeBase();

  public RobotContainer() {
    DriverStation.silenceJoystickConnectionWarning(true);

    configureBindings();
  }

  private void configureBindings() {
    switch(operatorMode) {
      case ATTACK_ONLY:
        drivetrainBase.setDefaultCommand(
            new AttackJoystickDriveControl(drivetrainBase, leftJoystick, rightJoystick));
        shooterBase.setDefaultCommand(
            new AttackJoystickShooterControl(shooterBase, leftJoystick, rightJoystick));
        intakeBase.setDefaultCommand(
            new AttackJoystickIntakeControl(intakeBase, leftJoystick, rightJoystick));
        break;
      case XBOX_ONLY: 
        drivetrainBase.setDefaultCommand(new XboxJoystickDriveControl(drivetrainbase, xboxController))
        break;
      case XBOX_AND_ATTACK:
        drivetrainBase.setDefaultCommand(new XboxJoystickDriveControl(drivetrainbase, xboxController))
        break;
    }
  }
  
  private void configureButtonBindings() {
        boolean joystickOneConnected = DriverStation.isJoystickConnected(0);
        boolean joystickTwoConnected = DriverStation.isJoystickConnected(1);
        boolean xboxcontrollerConnected = DriverStation.isJoystickConnected(2);

        if((joystickOneConnected && joystickTwoConnected)) {
            if(xboxcontrollerConnected) {
                configureButtonBindings(OperatorModes.XBOX_AND_ATTACK);
            } else {
                configureButtonBindings(OperatorModes.ATTACK_ONLY);
            }
        } else {
            configureButtonBindings(OperatorModes.XBOX_ONLY);
        }

    }
}
