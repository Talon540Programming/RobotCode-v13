package frc.robot.oi;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

public class DualAttackJoystick implements DriverInterface, ShooterInterface, IntakeInterface {
  private final CommandJoystick m_leftJoystick;
  private final CommandJoystick m_rightJoystick;

  public DualAttackJoystick(int leftPort, int rightPort) {
    m_leftJoystick = new CommandJoystick(leftPort);
    m_rightJoystick = new CommandJoystick(rightPort);
  }

  @Override
  public double getLeftPercent(DriveMode mode) {
    return MathUtil.applyDeadband(-m_leftJoystick.getY(), 0.15);
  }

  @Override
  public double getRightPercent(DriveMode mode) {
    return switch (mode) {
      case Arcade -> MathUtil.applyDeadband(-m_rightJoystick.getX(), 0.15);
      case Tank -> MathUtil.applyDeadband(-m_rightJoystick.getY(), 0.15);
    };
  }

  @Override
  public double getShooterPercent() {
    return m_rightJoystick.trigger().getAsBoolean() ? 1 : 0;
  }

  @Override
  public double getKickupPercent() {
    return m_leftJoystick.trigger().getAsBoolean() ? 1 : 0;
  }

  @Override
  public double getRollerPercent() {
    if (m_rightJoystick.button(4).getAsBoolean()) {
      return 1;
    } else if (m_rightJoystick.button(5).getAsBoolean()) {
      return -1;
    } else {
      return 0;
    }
  }

  @Override
  public double getIndexerPercent() {
    return m_rightJoystick.button(3).getAsBoolean() ? 1 : 0;
  }
}
