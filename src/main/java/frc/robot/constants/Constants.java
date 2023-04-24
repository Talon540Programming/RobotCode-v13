package frc.robot.constants;

public class Constants {
  public enum ControlMode {
    XBOX,
    JOYSTICKS
  }

  public static class HardwareIDs {
    public static final int kDrivetrainFrontRight = 11;
    public static final int kDrivetrainBackRight = 10;
    public static final int kDrivetrainFrontLeft = 8;
    public static final int kDrivetrainBackLeft = 9;

    public static final int kShooterFlyLeader = 3;
    public static final int kShooterFlyFollower = 6;
    public static final int kKickupWheel = 2;

    public static final int kIntakeWrist = 1;
    public static final int kIntakeRollers = 5;

    public static final int kIndexer = 4;
  }
}
