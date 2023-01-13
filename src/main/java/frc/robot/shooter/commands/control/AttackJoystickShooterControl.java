package frc.robot.shooter.commands.control;

import frc.robot.constants.RobotConfig;
import frc.robot.shooter.ShooterBase;
import org.talon540.control.AttackJoystick.TalonJoystick;

public class AttackJoystickShooterControl extends ShooterControl {
    private final TalonJoystick leftJoystick, rightJoystick;

    public AttackJoystickShooterControl(ShooterBase shooterBase, TalonJoystick leftJoystick, TalonJoystick rightJoystick) {
        super(shooterBase);

        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;
    }

    @Override
    public void execute() {
        this.kFlywheelPercent = rightJoystick.buttons.TRIGGER.get() ? RobotConfig.kShooterPercent : 0;
        this.kKickupPercent = leftJoystick.buttons.TRIGGER.get() ? RobotConfig.kKickupPercent : 0;
        super.execute();
    }
}
