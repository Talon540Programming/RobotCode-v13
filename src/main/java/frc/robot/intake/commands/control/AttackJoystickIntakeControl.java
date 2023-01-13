package frc.robot.intake.commands.control;

import frc.robot.constants.RobotConfig;
import frc.robot.intake.IntakeBase;
import org.talon540.control.AttackJoystick.TalonJoystick;

public class AttackJoystickIntakeControl extends IntakeControl {
    private final TalonJoystick leftJoystick, rightJoystick;

    public AttackJoystickIntakeControl(IntakeBase intakeBase, TalonJoystick leftJoystick, TalonJoystick rightJoystick) {
        super(intakeBase);

        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;
    }

    @Override
    public void execute() {
        this.kIndexPercent = rightJoystick.buttons.TOP_MIDDLE.get() ? RobotConfig.kIndexPercent : 0;
        this.kRollersPercent = rightJoystick.buttons.TOP_LEFT.get() ? RobotConfig.kIntakeForwardPercent : rightJoystick.buttons.TOP_RIGHT.get() ? RobotConfig.kIntakeReversePercent : 0;

        super.execute();
    }
}
