package frc.robot.shooter.commands.control;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.shooter.ShooterBase;

public class XboxControllerShooterControl extends ShooterControl {
    private final CommandXboxController controller;

    public XboxControllerShooterControl(ShooterBase shooterBase, CommandXboxController controller) {
        super(shooterBase);

        this.controller = controller;
    }

    @Override
    public void execute() {
        this.kFlywheelPercent = controller.getLeftTriggerAxis() > 0.2 ? 1 : 0;
        this.kKickupPercent = controller.getRightTriggerAxis() > 0.2 ? 1 : 0;
        super.execute();
    }
}
