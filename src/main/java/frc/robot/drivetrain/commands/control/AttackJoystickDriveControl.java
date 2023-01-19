package frc.robot.drivetrain.commands.control;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.constants.RobotConfig;
import frc.robot.drivetrain.DrivetrainBase;

public class AttackJoystickDriveControl extends DriveControl {
  private final CommandJoystick leftJoystick, rightJoystick;

  public AttackJoystickDriveControl(
      DrivetrainBase drivetrainBase, CommandJoystick left, CommandJoystick right) {
    super(drivetrainBase);

    this.leftJoystick = left;
    this.rightJoystick = right;
  }

  @Override
  public void execute() {
    this.kLeftDrivePercent = MathUtil.applyDeadband(leftJoystick.getY(), RobotConfig.kDrivetrainJoystickDeadband);
    this.kRightDrivePercent = MathUtil.applyDeadband(rightJoystick.getY(), RobotConfig.kDrivetrainJoystickDeadband);

    super.execute();
  }
}
