package frc.robot.constants;

/**
 * "Hot-Swappable" constants file that controls the output percent of motors. Useful for when robots
 * are retired to outreach.
 */
public class RobotConfig {
  public static final Constants.ControlMode controlMode = Constants.ControlMode.JOYSTICKS;

  public static final int kLeftJoystickPort = 0;
  public static final int kRightJoystickPort = 1;
  public static final int kXboxControllerPort = 2;

  public static final double kDrivetrainJoystickDeadband = 0.05;
  public static final double kShooterPercent = -0.8;
  public static final double kKickupPercent = -0.5;
  public static final double kIndexPercent = 0.5;
  public static final double kIntakeForwardPercent = 0.5;
  public static final double kIntakeReversePercent = -0.5;
  public static final double kMaxDrivetrainPercent = 1.0;
}
