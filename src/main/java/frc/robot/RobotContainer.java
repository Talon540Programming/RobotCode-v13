// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.constants.Constants;
import frc.robot.constants.HardwareDevices;
import frc.robot.drivetrain.DriveBase;
import frc.robot.drivetrain.DriveIO;
import frc.robot.drivetrain.DriveIOTalonFX;
import frc.robot.drivetrain.commands.DriveControl;
import frc.robot.intake.IntakeBase;
import frc.robot.intake.IntakeIO;
import frc.robot.intake.IntakeIOTalonSRX;
import frc.robot.intake.commands.IntakeControl;
import frc.robot.oi.DualAttackJoystick;
import frc.robot.shooter.ShooterBase;
import frc.robot.shooter.ShooterIO;
import frc.robot.shooter.ShooterIOTalonSRX;
import frc.robot.shooter.commands.ShooterControl;

public class RobotContainer {
  // OI
  private final DualAttackJoystick m_joysticks = new DualAttackJoystick(1, 2);

  // Subsystems
  private DriveBase m_driveBase;
  private ShooterBase m_shooterBase;
  private IntakeBase m_intakeBase;

  public RobotContainer() {

    if (Constants.getRobotMode() != Constants.RobotMode.REPLAY) {
      switch (Constants.getRobotType()) {
        case ROBOBT_2020C -> {
          m_driveBase =
              new DriveBase(
                  new DriveIOTalonFX(
                      HardwareDevices.kDrivetrainFrontLeft,
                      HardwareDevices.kDrivetrainBackLeft,
                      HardwareDevices.kDrivetrainFrontRight,
                      HardwareDevices.kDrivetrainBackRight,
                      Constants.Drivetrain.kDrivetrainGearing,
                      Constants.Drivetrain.kWheelRadiusMeters,
                      Constants.Drivetrain.kLeftSideInverted,
                      Constants.Drivetrain.kLeftEncoderInverted,
                      Constants.Drivetrain.kRightSideInverted,
                      Constants.Drivetrain.kRightEncoderInverted));
          m_shooterBase =
              new ShooterBase(
                  new ShooterIOTalonSRX(
                      HardwareDevices.kShooterLeft,
                      HardwareDevices.kShooterRight,
                      HardwareDevices.kShooterKickup,
                      Constants.Shooter.kShooterInverted,
                      Constants.Shooter.kIndexerInverted));
          m_intakeBase =
              new IntakeBase(
                  new IntakeIOTalonSRX(
                      HardwareDevices.kIntakeRollers,
                      HardwareDevices.kIntakeIndexer,
                      Constants.Intake.kRollersInverted,
                      Constants.Intake.kIndexerInverted));
        }
        case ROBOT_SIMBOT -> {}
      }
    }

    m_driveBase = m_driveBase != null ? m_driveBase : new DriveBase(new DriveIO() {});
    m_shooterBase = m_shooterBase != null ? m_shooterBase : new ShooterBase(new ShooterIO() {});
    m_intakeBase = m_intakeBase != null ? m_intakeBase : new IntakeBase(new IntakeIO() {});

    configureBindings();
  }

  private void configureBindings() {
    m_driveBase.setDefaultCommand(new DriveControl(m_driveBase, m_joysticks));
    m_shooterBase.setDefaultCommand(new ShooterControl(m_shooterBase, m_joysticks));
    m_intakeBase.setDefaultCommand(new IntakeControl(m_intakeBase, m_joysticks));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
