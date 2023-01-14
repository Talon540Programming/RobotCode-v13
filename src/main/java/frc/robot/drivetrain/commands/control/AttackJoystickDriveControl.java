package frc.robot.drivetrain.commands.control;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
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
    super.kLeftDrivePercent = MathUtil.applyDeadband(leftJoystick.getY(), 0.2);
    super.kRightDrivePercent = MathUtil.applyDeadband(rightJoystick.getY(), 0.2);
    super.execute();
  }
}
