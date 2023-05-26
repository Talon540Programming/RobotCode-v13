package frc.lib;

import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import edu.wpi.first.math.util.Units;

public class TalonFXMechanism {
  public static final double IntegratedEncoderResolution = 2048.0;

  private final TalonFXSensorCollection m_collection;

  private final double m_gearing;
  private final double m_radius;

  private boolean m_inverted;

  public TalonFXMechanism(
      TalonFXSensorCollection collection, double gearing, double radius, boolean inverted) {
    m_collection = collection;
    m_gearing = gearing;
    m_radius = radius;
    m_inverted = inverted;
  }

  public TalonFXMechanism(TalonFXSensorCollection collection, double gearing, double radius) {
    this(collection, gearing, radius, false);
  }

  public void setInverted(boolean inverted) {
    m_inverted = inverted;
  }

  public double getVelocityRadPerSecond() {
    return Units.rotationsPerMinuteToRadiansPerSecond(getMotorRotations()) / m_gearing;
  }

  public double getVelocityMetersPerSecond() {
    return getVelocityRadPerSecond() * m_radius;
  }

  public double getPositionRadians() {
    return Units.rotationsToRadians(getMotorRotations()) / m_gearing;
  }

  public double getPositionMeters() {
    return getPositionRadians() * m_radius;
  }

  public double getMotorRotations() {
    return getRawPosition() / IntegratedEncoderResolution;
  }

  public double getMotorRotationsPerMinute() {
    return getRawVelocity() * 600.0 / IntegratedEncoderResolution;
  }

  public double getRawVelocity() {
    return m_collection.getIntegratedSensorVelocity() * (m_inverted ? -1 : 1);
  }

  public double getRawPosition() {
    return m_collection.getIntegratedSensorPosition() * (m_inverted ? -1 : 1);
  }

  public double getRawAbsolutePosition() {
    return m_collection.getIntegratedSensorAbsolutePosition();
  }

  public TalonFXSensorCollection getCollection() {
    return m_collection;
  }

  public void resetPosition(double positionTalonFXUnits) {
    m_collection.setIntegratedSensorPosition(positionTalonFXUnits, 0);
  }
}
