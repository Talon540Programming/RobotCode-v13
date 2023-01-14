package frc.robot.drivetrain.commands.control;

import frc.robot.drivetrain.DrivetrainBase;
import org.talon540.control.XboxController.TalonXboxController;

public class XboxControllerDriveControl extends DriveControl {
  private final TalonXboxController controller;

  public XboxControllerDriveControl(
      DrivetrainBase drivetrainBase, TalonXboxController cTalonXboxController) {
    super(drivetrainBase);
    this.controller = cTalonXboxController;
  }

  @Override
  public void execute() {
    super.kLeftDrivePercent = controller.getLeftDeadbandY();
    super.kRightDrivePercent = controller.getRightDeadbandY();
    super.execute();
  }
}
