package frc.robot.drivetrain.commands.control;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drivetrain.DrivetrainBase;

public abstract class DriveControl extends CommandBase {
    private final DrivetrainBase drivetrainBase;
    protected double kLeftDrivePercent, kRightDrivePercent;

    public DriveControl(DrivetrainBase drivetrainBase) {
        addRequirements(drivetrainBase);

        this.drivetrainBase = drivetrainBase;
    }

    @Override
    public void execute() {
        drivetrainBase.setPercent(kLeftDrivePercent,
                kRightDrivePercent
        );
    }
}
