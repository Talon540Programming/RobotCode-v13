package frc.robot.oi;

public interface DriverInterface {
  double getLeftPercent(DriverInterface.DriveMode mode);

  double getRightPercent(DriverInterface.DriveMode mode);

  public enum DriveMode {
    Tank,
    Arcade
  }
}
