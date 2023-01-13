package frc.robot.shooter.commands.control;

import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.constants.RobotConfig;
import frc.robot.shooter.ShooterBase;

public class AttackJoystickShooterControl extends ShooterControl {
    private final CommandJoystick leftJoystick, rightJoystick;

    public AttackJoystickShooterControl(ShooterBase shooterBase, CommandJoystick leftJoystick, CommandJoystick rightJoystick) {
        super(shooterBase);

        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;
    }

    @Override
    public void execute() {
        this.kFlywheelPercent = rightJoystick.getHID().getTrigger() ? RobotConfig.kShooterPercent : 0;
        this.kKickupPercent = leftJoystick.getHID().getTrigger() ? RobotConfig.kKickupPercent : 0;
        super.execute();
    }
}
