package frc.robot.intake;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class IntakeBase extends SubsystemBase {
  private final IntakeIO m_intakeIO;
  private final IntakeInputsAutoLogged m_intakeInputs = new IntakeInputsAutoLogged();

  public IntakeBase(IntakeIO intakeIO) {
    m_intakeIO = intakeIO;
  }

  @Override
  public void periodic() {
    m_intakeIO.updateInputs(m_intakeInputs);
    Logger.getInstance().processInputs("Intake", m_intakeInputs);
  }

  public void setRollerPercent(double percent) {
    percent = MathUtil.clamp(percent, -1.0, 1.0);

    setRollerVoltage(percent * 12.0);
  }

  public void setRollerVoltage(double voltage) {
    voltage = MathUtil.clamp(voltage, -12.0, 12.0);

    m_intakeIO.setRollerVoltage(voltage);
  }

  public void setIndexerPercent(double percent) {
    percent = MathUtil.clamp(percent, -1.0, 1.0);

    setIndexerVoltage(percent * 12.0);
  }

  public void setIndexerVoltage(double voltage) {
    voltage = MathUtil.clamp(voltage, -12.0, 12.0);

    m_intakeIO.setIndexerVoltage(voltage);
  }
}
