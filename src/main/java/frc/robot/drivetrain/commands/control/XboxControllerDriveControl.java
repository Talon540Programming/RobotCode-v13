package frc.robot.drivetrain.commands.control;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.drivetrain.DrivetrainBase;

public class XboxControllerDriveControl extends DriveControl {
    private CommandXboxController controller;

    public XboxControllerDriveControl(DrivetrainBase drivetrainBase, CommandXboxController controller) {
        super(drivetrainBase);
        this.controller = controller;
    }

    @Override
    public void execute() {
        this.kLeftDrivePercent = controller.getLeftY();
        this.kRightDrivePercent = controller.getRightY();

        super.execute();
    }
}
