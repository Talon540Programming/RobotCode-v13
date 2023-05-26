package frc.robot.shooter;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class ShooterBase extends SubsystemBase {
  private final ShooterIO m_shooterIO;
  private final ShooterInputsAutoLogged m_shooterInputs = new ShooterInputsAutoLogged();

  public ShooterBase(ShooterIO shooterIO) {
    m_shooterIO = shooterIO;
  }

  @Override
  public void periodic() {
    m_shooterIO.updateInputs(m_shooterInputs);
    Logger.getInstance().processInputs("Shooter", m_shooterInputs);
  }

  public void setShooterPercent(double percent) {
    percent = MathUtil.clamp(percent, -1.0, 1.0);

    setShooterVoltage(percent * 12.0);
  }

  public void setShooterVoltage(double voltage) {
    voltage = MathUtil.clamp(voltage, -12.0, 12.0);

    m_shooterIO.setShooterVoltage(voltage);
  }

  public void setKickupPercent(double percent) {
    percent = MathUtil.clamp(percent, -1.0, 1.0);

    setKickupVoltage(percent * 12.0);
  }

  public void setKickupVoltage(double voltage) {
    voltage = MathUtil.clamp(voltage, -12.0, 12.0);

    m_shooterIO.setKickupVoltage(voltage);
  }
}
