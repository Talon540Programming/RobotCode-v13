package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
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

  // Subsystems
  private final DrivetrainBase drivetrainBase = new DrivetrainBase();
  private final ShooterBase shooterBase = new ShooterBase();
  private final IntakeBase intakeBase = new IntakeBase();

  public RobotContainer() {
    DriverStation.silenceJoystickConnectionWarning(true);

    configureBindings();
  }

  private void configureBindings() {
    drivetrainBase.setDefaultCommand(
        new AttackJoystickDriveControl(drivetrainBase, leftJoystick, rightJoystick));
    shooterBase.setDefaultCommand(
        new AttackJoystickShooterControl(shooterBase, leftJoystick, rightJoystick));
    intakeBase.setDefaultCommand(
        new AttackJoystickIntakeControl(intakeBase, leftJoystick, rightJoystick));
  }
}
