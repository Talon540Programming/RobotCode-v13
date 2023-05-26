package frc.robot.drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.lib.LoggedIO;
import org.littletonrobotics.junction.AutoLog;

public interface DriveIO extends LoggedIO<DriveIO.DriveInputs> {
  @AutoLog
  class DriveInputs {
    public double LeftPositionMeters;
    public double RightPositionMeters;
    public double LeftVelocityMetersPerSecond;
    public double RightVelocityMetersPerSecond;

    public double[] TemperatureCelcius = new double[] {};
    public double[] CurrentAmps = new double[] {};

    public boolean GyroConnected;

    public double YawPositionRad;
    public double YawRateRadPerSecond;
  }

  default void setVoltage(double leftVoltage, double rightVoltage) {}

  default void resetEncoders() {}

  default void resetHeading() {}

  default Rotation2d getHeading() {
    return new Rotation2d();
  }
}
