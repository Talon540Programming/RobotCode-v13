package frc.robot.drivetrain.commands.control;

import frc.robot.constants.RobotConfig;
import frc.robot.drivetrain.DrivetrainBase;
import org.talon540.control.AttackJoystick.TalonJoystick;

public class AttackJoystickDriveControl extends DriveControl {
    private final TalonJoystick leftJoystick, rightJoystick;

    public AttackJoystickDriveControl(DrivetrainBase drivetrainBase, TalonJoystick left, TalonJoystick right) {
        super(drivetrainBase);

        this.leftJoystick = left;
        this.rightJoystick = right;
    }

    @Override
    public void execute() {
        super.kLeftDrivePercent = leftJoystick.getDeadbandY();
        super.kRightDrivePercent = rightJoystick.getDeadbandY();
        super.execute();
    }
}
