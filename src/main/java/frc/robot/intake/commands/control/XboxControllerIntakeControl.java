package frc.robot.intake.commands.control;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.constants.RobotConfig;
import frc.robot.drivetrain.DrivetrainBase;
import frc.robot.intake.IntakeBase;

public class XboxControllerIntakeControl extends IntakeControl {
    private final CommandXboxController controller;

    public XboxControllerIntakeControl(IntakeBase intakeBase, CommandXboxController controller) {
        super(intakeBase);

        this.controller = controller;
    }

    @Override
    public void execute() {
        this.kIndexPercent = controller.getHID().getXButton() ? RobotConfig.kIndexPercent : 0;
        this.kRollersPercent =
                controller.getHID().getYButton()
                        ? RobotConfig.kIntakeForwardPercent
                        : controller.getHID().getAButton() ? RobotConfig.kIntakeReversePercent : 0;

        super.execute();
    }
}
