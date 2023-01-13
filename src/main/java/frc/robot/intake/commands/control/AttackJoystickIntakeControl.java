package frc.robot.intake.commands.control;

import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.constants.RobotConfig;
import frc.robot.intake.IntakeBase;

public class AttackJoystickIntakeControl extends IntakeControl {
    private final CommandJoystick leftJoystick, rightJoystick;

    public AttackJoystickIntakeControl(IntakeBase intakeBase, CommandJoystick leftJoystick, CommandJoystick rightJoystick) {
        super(intakeBase);

        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;
    }

    @Override
    public void execute() {
        this.kIndexPercent = rightJoystick.getHID().getRawButton(3) ? RobotConfig.kIndexPercent : 0;
        this.kRollersPercent = rightJoystick.getHID().getRawButton(4) ? RobotConfig.kIntakeForwardPercent : rightJoystick.getHID().getRawButton(5) ? RobotConfig.kIntakeReversePercent : 0;

        super.execute();
    }
}
