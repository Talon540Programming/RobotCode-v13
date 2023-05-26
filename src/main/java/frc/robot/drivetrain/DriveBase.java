package frc.robot.drivetrain;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class DriveBase extends SubsystemBase {
  private final DriveIO m_driveIO;
  private final DriveInputsAutoLogged m_driveInputs = new DriveInputsAutoLogged();

  private final DifferentialDriveOdometry m_odometry;

  public DriveBase(DriveIO driveIO) {
    m_driveIO = driveIO;

    m_driveIO.resetEncoders();
    m_driveIO.resetHeading();

    m_odometry = new DifferentialDriveOdometry(m_driveIO.getHeading(), 0, 0);
  }

  @Override
  public void periodic() {
    m_driveIO.updateInputs(m_driveInputs);
    Logger.getInstance().processInputs("Drive", m_driveInputs);

    m_odometry.update(
        m_driveIO.getHeading(),
        m_driveInputs.LeftPositionMeters,
        m_driveInputs.RightPositionMeters);
    Logger.getInstance().recordOutput("Odometry", m_odometry.getPoseMeters());
  }

  public Pose2d getEstimatedPose() {
    return m_odometry.getPoseMeters();
  }

  public void tankDrivePercent(double leftPercent, double rightPercent) {
    leftPercent = MathUtil.clamp(leftPercent, -1, 1);
    rightPercent = MathUtil.clamp(rightPercent, -1, 1);

    leftPercent = Math.copySign(Math.pow(leftPercent, 2), leftPercent);
    rightPercent = Math.copySign(Math.pow(rightPercent, 2), rightPercent);

    setVoltage(leftPercent * 12.0, rightPercent * 12.0);
  }

  public void arcadeDrivePercent(double forwardPercent, double rotationPercent) {
    forwardPercent = MathUtil.clamp(forwardPercent, -1, 1);
    rotationPercent = MathUtil.clamp(rotationPercent, -1, 1);

    forwardPercent = Math.copySign(Math.pow(forwardPercent, 2), forwardPercent);
    rotationPercent = Math.copySign(Math.pow(rotationPercent, 2), rotationPercent);

    double leftSpeed = forwardPercent - rotationPercent;
    double rightSpeed = forwardPercent + rotationPercent;

    double greaterInput = Math.max(Math.abs(forwardPercent), Math.abs(rotationPercent));
    double minimumInput = Math.min(Math.abs(forwardPercent), Math.abs(rotationPercent));

    if (greaterInput == 0.0) {
      setVoltage(0, 0);
    }

    double saturatedInput = (greaterInput + minimumInput) / greaterInput;
    leftSpeed /= saturatedInput;
    rightSpeed /= saturatedInput;

    setVoltage(leftSpeed * 12.0, rightSpeed * 12.0);
  }

  public void setVoltage(double leftVoltage, double rightVoltage) {
    leftVoltage = MathUtil.clamp(leftVoltage, -12.0, 12.0);
    rightVoltage = MathUtil.clamp(rightVoltage, -12.0, 12.0);

    m_driveIO.setVoltage(leftVoltage, rightVoltage);
  }
}
