package frc.robot.intake.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.intake.IntakeBase;
import frc.robot.oi.IntakeInterface;
import org.littletonrobotics.junction.networktables.LoggedDashboardNumber;

public class IntakeControl extends CommandBase {
  private final IntakeBase m_intakeBase;

  private final IntakeInterface m_intakeInterface;

  private final LoggedDashboardNumber m_rollerMultiplier =
      new LoggedDashboardNumber("Intake/RollerMultiplier", 0.5);
  private final LoggedDashboardNumber m_indexerMultiplier =
      new LoggedDashboardNumber("Intake/IndexerMultiplier", 0.5);

  public IntakeControl(IntakeBase intakeBase, IntakeInterface intakeInterface) {
    m_intakeBase = intakeBase;
    m_intakeInterface = intakeInterface;

    addRequirements(intakeBase);
  }

  @Override
  public void execute() {
    m_intakeBase.setRollerPercent(
        m_intakeInterface.getRollerPercent() * MathUtil.clamp(m_rollerMultiplier.get(), 0, 1));
    m_intakeBase.setIndexerPercent(
        m_intakeInterface.getIndexerPercent() * MathUtil.clamp(m_indexerMultiplier.get(), 0, 1));
  }
}
