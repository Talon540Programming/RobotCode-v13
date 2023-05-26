package frc.robot.oi;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class XboxDriver implements DriverInterface {
  private final CommandXboxController m_controller;

  public XboxDriver(int port) {
    m_controller = new CommandXboxController(port);
  }

  @Override
  public double getLeftPercent(DriveMode mode) {
    return MathUtil.applyDeadband(-m_controller.getLeftY(), 0.15);
  }

  @Override
  public double getRightPercent(DriveMode mode) {
    return switch (mode) {
      case Arcade -> MathUtil.applyDeadband(-m_controller.getRightX(), 0.15);
      case Tank -> MathUtil.applyDeadband(-m_controller.getRightY(), 0.15);
    };
  }
}
