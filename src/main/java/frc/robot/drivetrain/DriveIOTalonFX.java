package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SerialPort;
import frc.lib.TalonFXMechanism;

public class DriveIOTalonFX implements DriveIO {
  private final WPI_TalonFX m_leftLeader;
  private final WPI_TalonFX m_leftFollower;
  private final WPI_TalonFX m_rightLeader;
  private final WPI_TalonFX m_rightFollower;

  private final TalonFXMechanism m_leftEncoder;
  private final TalonFXMechanism m_rightEncoder;

  private final ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();

  public DriveIOTalonFX(
      int leftLeaderId,
      int leftFollowerId,
      int rightLeaderId,
      int rightFollowerId,
      double gearing,
      double wheelRadiusMeters,
      boolean leftSideInverted,
      boolean leftEncoderInverted,
      boolean rightSideInverted,
      boolean rightEncoderInverted) {
    m_leftLeader = new WPI_TalonFX(leftLeaderId);
    m_leftFollower = new WPI_TalonFX(leftFollowerId);
    m_rightLeader = new WPI_TalonFX(rightLeaderId);
    m_rightFollower = new WPI_TalonFX(rightFollowerId);

    m_leftEncoder =
        new TalonFXMechanism(
            m_leftLeader.getSensorCollection(), gearing, wheelRadiusMeters, leftEncoderInverted);

    m_rightEncoder =
        new TalonFXMechanism(
            m_rightLeader.getSensorCollection(), gearing, wheelRadiusMeters, rightEncoderInverted);

    TalonFXConfiguration config = new TalonFXConfiguration();
    config.voltageCompSaturation = 12.0;
    config.statorCurrLimit = new StatorCurrentLimitConfiguration(true, 40, 0, 0);

    m_leftLeader.configAllSettings(config);
    m_rightLeader.configAllSettings(config);
    m_leftFollower.configAllSettings(config);
    m_rightFollower.configAllSettings(config);

    m_leftFollower.follow(m_leftLeader);
    m_rightFollower.follow(m_rightLeader);

    m_leftLeader.setInverted(leftSideInverted);
    m_leftFollower.setInverted(InvertType.FollowMaster);
    m_rightLeader.setInverted(rightSideInverted);
    m_rightFollower.setInverted(InvertType.FollowMaster);

    m_gyro.calibrate();
  }

  @Override
  public void updateInputs(DriveInputs inputs) {
    inputs.LeftPositionMeters = m_leftEncoder.getPositionMeters();
    inputs.RightPositionMeters = m_rightEncoder.getPositionMeters();
    inputs.LeftVelocityMetersPerSecond = m_leftEncoder.getVelocityMetersPerSecond();
    inputs.RightVelocityMetersPerSecond = m_rightEncoder.getVelocityMetersPerSecond();

    inputs.TemperatureCelcius =
        new double[] {
          m_leftLeader.getTemperature(), m_leftFollower.getTemperature(),
          m_rightLeader.getTemperature(), m_rightFollower.getTemperature(),
        };

    inputs.CurrentAmps =
        new double[] {
          m_leftLeader.getSupplyCurrent(), m_leftFollower.getSupplyCurrent(),
          m_rightLeader.getSupplyCurrent(), m_rightFollower.getSupplyCurrent(),
        };

    inputs.GyroConnected = m_gyro.isConnected();

    inputs.YawPositionRad = -m_gyro.getAngle();
    inputs.YawRateRadPerSecond = m_gyro.getRate();
  }

  @Override
  public void setVoltage(double leftVoltage, double rightVoltage) {
    leftVoltage = MathUtil.clamp(leftVoltage, -12.0, 12.0);
    rightVoltage = MathUtil.clamp(rightVoltage, -12.0, 12.0);

    m_leftLeader.setVoltage(leftVoltage);
    m_rightLeader.setVoltage(rightVoltage);
  }

  @Override
  public void resetEncoders() {
    m_leftEncoder.resetPosition(0);
    m_rightEncoder.resetPosition(0);
  }

  @Override
  public void resetHeading() {
    m_gyro.reset();
  }

  @Override
  public Rotation2d getHeading() {
    return m_gyro.getRotation2d();
  }
}
