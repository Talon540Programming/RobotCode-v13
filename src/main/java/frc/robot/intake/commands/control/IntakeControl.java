package frc.robot.intake.commands.control;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.intake.IntakeBase;

public abstract class IntakeControl extends CommandBase {
    private final IntakeBase intakeBase;

    protected double kRollersPercent, kIndexPercent;

    public IntakeControl(IntakeBase intakeBase) {
        addRequirements(intakeBase);

        this.intakeBase = intakeBase;
    }

    @Override
    public void execute() {
        intakeBase.setIntakeRollersPercent(kRollersPercent);
        intakeBase.setIndexPercent(kIndexPercent);
    }
}
