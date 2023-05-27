package frc.robot.intake;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.MathUtil;

public class IntakeIOTalonSRX implements IntakeIO {
  private final WPI_TalonSRX m_rollers;
  private final WPI_TalonSRX m_indexer;

  public IntakeIOTalonSRX(
      int rollerId, int indexerId, boolean rollersInverted, boolean indexerInverted) {
    m_rollers = new WPI_TalonSRX(rollerId);
    m_indexer = new WPI_TalonSRX(indexerId);

    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.continuousCurrentLimit = 40;

    m_rollers.configAllSettings(config);
    m_indexer.configAllSettings(config);

    m_rollers.setInverted(rollersInverted);
    m_indexer.setInverted(indexerInverted);
  }

  @Override
  public void updateInputs(IntakeInputs inputs) {
    inputs.TemperatureCelsius =
        new double[] {m_rollers.getTemperature(), m_indexer.getTemperature()};
    inputs.CurrentAmps = new double[] {m_rollers.getSupplyCurrent(), m_indexer.getSupplyCurrent()};
  }

  @Override
  public void setIndexerVoltage(double voltage) {
    voltage = MathUtil.clamp(voltage, -12.0, 12.0);

    m_indexer.setVoltage(voltage);
  }

  @Override
  public void setRollerVoltage(double voltage) {
    voltage = MathUtil.clamp(voltage, -12.0, 12.0);

    m_rollers.setVoltage(voltage);
  }
}
