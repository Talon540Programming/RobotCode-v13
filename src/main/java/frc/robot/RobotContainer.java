package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotConfig;
import frc.robot.drivetrain.DrivetrainBase;
import frc.robot.drivetrain.commands.control.AttackJoystickDriveControl;
import frc.robot.drivetrain.commands.control.XboxControllerDriveControl;
import frc.robot.intake.IntakeBase;
import frc.robot.intake.commands.control.AttackJoystickIntakeControl;
import frc.robot.intake.commands.control.XboxControllerIntakeControl;
import frc.robot.shooter.ShooterBase;
import frc.robot.shooter.commands.control.AttackJoystickShooterControl;
import frc.robot.shooter.commands.control.XboxControllerShooterControl;

public class RobotContainer {
  private final CommandJoystick leftJoystick = new CommandJoystick(RobotConfig.kLeftJoystickPort);
  private final CommandJoystick rightJoystick = new CommandJoystick(RobotConfig.kRightJoystickPort);
  private final CommandXboxController xboxController = new CommandXboxController(RobotConfig.kXboxControllerPort);

  // Subsystems
  private final DrivetrainBase drivetrainBase = new DrivetrainBase();
  private final ShooterBase shooterBase = new ShooterBase();
  private final IntakeBase intakeBase = new IntakeBase();

  public RobotContainer() {
    DriverStation.silenceJoystickConnectionWarning(true);

    configureBindings(RobotConfig.controlMode);
  }

  private void configureBindings(Constants.ControlMode operatorMode) {
    switch(operatorMode) {
        case JOYSTICKS:
            drivetrainBase.setDefaultCommand(
                    new AttackJoystickDriveControl(drivetrainBase, leftJoystick, rightJoystick));
            shooterBase.setDefaultCommand(
                    new AttackJoystickShooterControl(shooterBase, leftJoystick, rightJoystick));
            intakeBase.setDefaultCommand(
                    new AttackJoystickIntakeControl(intakeBase, leftJoystick, rightJoystick));
            break;
        case XBOX:
            drivetrainBase.setDefaultCommand(
                    new XboxControllerDriveControl(drivetrainBase, xboxController));
            shooterBase.setDefaultCommand(
                    new XboxControllerShooterControl(shooterBase, xboxController));
            intakeBase.setDefaultCommand(
                    new XboxControllerIntakeControl(intakeBase, xboxController));
            break;
    }
  }
}
