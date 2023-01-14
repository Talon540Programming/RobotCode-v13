package frc.robot.shooter.commands.control;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.ShooterBase;

public abstract class ShooterControl extends CommandBase {
  protected ShooterBase shooterSubsystem;

  protected double kFlywheelPercent, kKickupPercent;

  public ShooterControl(ShooterBase shooter) {
    this.shooterSubsystem = shooter;
    addRequirements(shooter);
  }

  @Override
  public void execute() {
    shooterSubsystem.setFlywheelPercent(kFlywheelPercent);
    shooterSubsystem.setKickupPercent(kKickupPercent);
  }
}
