package frc.robot.shooter.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.oi.ShooterInterface;
import frc.robot.shooter.ShooterBase;
import org.littletonrobotics.junction.networktables.LoggedDashboardNumber;

public class ShooterControl extends CommandBase {
  private final ShooterBase m_shooterBase;

  private final ShooterInterface m_shooterInterface;

  private final LoggedDashboardNumber m_shooterMultiplier =
      new LoggedDashboardNumber("Shooter/ShooterMultiplier", 0.8);
  private final LoggedDashboardNumber m_kickupMultiplier =
      new LoggedDashboardNumber("Shooter/KickupMultiplier", 0.5);

  public ShooterControl(ShooterBase shooterBase, ShooterInterface shooterInterface) {
    m_shooterBase = shooterBase;
    m_shooterInterface = shooterInterface;

    addRequirements(shooterBase);
  }

  @Override
  public void execute() {
    m_shooterBase.setShooterPercent(
        m_shooterInterface.getShooterPercent() * MathUtil.clamp(m_shooterMultiplier.get(), 0, 1));
    m_shooterBase.setKickupPercent(
        m_shooterInterface.getKickupPercent() * MathUtil.clamp(m_kickupMultiplier.get(), 0, 1));
  }
}
