package frc.robot.constants;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;

public class Constants {
  private static RobotType kRobotType = RobotType.ROBOT_2020C;
  public static final double loopPeriodSecs = 0.02;

  public enum RobotMode {
    REAL,
    REPLAY,
    SIM
  }

  public enum RobotType {
    ROBOT_2020C,
    ROBOT_SIMBOT
  }

  public static RobotType getRobotType() {
    if (RobotBase.isReal() && kRobotType == RobotType.ROBOT_SIMBOT) {
      DriverStation.reportError(
          "Robot is set to SIM, but it is REAL, backing up to Competition Type", false);
      kRobotType = RobotType.ROBOT_2020C;
    }

    if (RobotBase.isSimulation() && kRobotType != RobotType.ROBOT_SIMBOT) {
      DriverStation.reportError(
          "Robot is set to REAL, but it is SIM, backing up to SIMBOT Type", false);
      kRobotType = RobotType.ROBOT_SIMBOT;
    }

    return kRobotType;
  }

  public static RobotMode getRobotMode() {
    return switch (getRobotType()) {
      case ROBOT_2020C -> RobotBase.isReal() ? RobotMode.REAL : RobotMode.REPLAY;
      case ROBOT_SIMBOT -> RobotMode.SIM;
    };
  }

  public static class Drivetrain {
    public static final boolean kLeftSideInverted = false;
    public static final boolean kLeftEncoderInverted = false;
    public static final boolean kRightSideInverted = true;
    public static final boolean kRightEncoderInverted = true;

    public static final double kDrivetrainGearing = (54.0 / 20.0) * (50.0 / 11.0);

    public static final double kWheelRadiusInches = 3;
    public static final double kWheelRadiusMeters = Units.inchesToMeters(kWheelRadiusInches);

    public static final double kTrackWidthInches = 20;
    public static final double kTrackWidthMeters = Units.inchesToMeters(kTrackWidthInches);

    public static final DifferentialDriveKinematics kKinematics =
        new DifferentialDriveKinematics(kTrackWidthMeters);
  }

  public static class Shooter {
    public static final boolean kShooterInverted = true;
    public static final boolean kIndexerInverted = true;
  }

  public static class Intake {
    public static final boolean kRollersInverted = false;
    public static final boolean kIndexerInverted = false;
  }
}
