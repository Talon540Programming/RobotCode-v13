package frc.robot.drivetrain;

import frc.lib.LoggedIO;
import org.littletonrobotics.junction.AutoLog;

public interface DriveIO extends LoggedIO<DriveIO.DriveInputs> {
  @AutoLog
  class DriveInputs {
    public double LeftPositionMeters;
    public double RightPositionMeters;
    public double LeftVelocityMetersPerSecond;
    public double RightVelocityMetersPerSecond;

    public double[] TemperatureCelsius = new double[] {};
    public double[] CurrentAmps = new double[] {};
  }

  default void setVoltage(double leftVoltage, double rightVoltage) {}

  default void resetEncoders() {}
}
