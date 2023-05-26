package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.MathUtil;

public class ShooterIOTalonSRX implements ShooterIO {
  private final WPI_TalonSRX m_leader;
  private final WPI_TalonSRX m_follower;
  private final WPI_TalonSRX m_kickup;

  public ShooterIOTalonSRX(
      int shooterLeaderId,
      int shooterFollowerId,
      int kickupId,
      boolean shooterInverted,
      boolean kickupInverted) {
    m_leader = new WPI_TalonSRX(shooterLeaderId);
    m_follower = new WPI_TalonSRX(shooterFollowerId);
    m_kickup = new WPI_TalonSRX(kickupId);

    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.continuousCurrentLimit = 40;

    m_leader.configAllSettings(config);
    m_follower.configAllSettings(config);
    m_kickup.configAllSettings(config);

    m_follower.follow(m_leader);

    m_leader.setInverted(shooterInverted);
    m_follower.setInverted(InvertType.FollowMaster);

    m_kickup.setInverted(kickupInverted);
  }

  @Override
  public void updateInputs(ShooterInputs inputs) {
    inputs.TemperatureCelcius =
        new double[] {
          m_leader.getTemperature(), m_follower.getTemperature(), m_kickup.getTemperature()
        };
    inputs.CurrentAmps =
        new double[] {
          m_leader.getSupplyCurrent(), m_follower.getSupplyCurrent(), m_kickup.getSupplyCurrent()
        };
  }

  @Override
  public void setShooterVoltage(double voltage) {
    voltage = MathUtil.clamp(voltage, -12.0, 12.0);

    m_leader.setVoltage(voltage);
  }

  @Override
  public void setKickupVoltage(double voltage) {
    voltage = MathUtil.clamp(voltage, -12.0, 12.0);

    m_kickup.setVoltage(voltage);
  }
}
