package frc.lib;

import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.constants.BuildConstants;
import frc.robot.constants.Constants;
import java.nio.file.Path;
import java.util.Optional;
import org.littletonrobotics.junction.Logger;

public class LoggerUtil {
  private LoggerUtil() {
    // utility class
  }

  public static void initalizeLoggerMetadata(Logger logger) {
    logger.recordMetadata("ROBOT_NAME", Constants.getRobotType().toString());
    logger.recordMetadata("RUNTIME_ENVIORMENT", RobotBase.getRuntimeType().toString());
    logger.recordMetadata("PROJECT_NAME", BuildConstants.MAVEN_NAME);
    logger.recordMetadata("BUILD_DATE", BuildConstants.BUILD_DATE);
    logger.recordMetadata("GIT_SHA", BuildConstants.GIT_SHA);
    logger.recordMetadata("GIT_DATE", BuildConstants.GIT_DATE);
    logger.recordMetadata("GIT_BRANCH", BuildConstants.GIT_BRANCH);

    switch (BuildConstants.DIRTY) {
      case 0 -> logger.recordMetadata("GIT_STATUS", "All changes are commited");
      case 1 -> logger.recordMetadata("GIT_STATUS", "There are uncommited changes");
      default -> logger.recordMetadata("GIT_STATUS", "Unknown Status");
    }
  }

  public static Optional<Path> getUSBPath() {
    try {
      return Optional.of(Path.of("/u").toRealPath(null));
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
