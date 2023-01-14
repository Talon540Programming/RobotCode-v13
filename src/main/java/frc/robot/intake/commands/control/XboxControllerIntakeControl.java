package frc.robot.intake.commands.control;

import frc.robot.intake.IntakeBase;
import org.talon540.control.XboxController.TalonXboxController;

// Abstract because bindings need to be made, and I don't want people to try and instantiate.
public abstract class XboxControllerIntakeControl extends IntakeControl {
  private final TalonXboxController controller;

  public XboxControllerIntakeControl(IntakeBase intakeBase, TalonXboxController controller) {
    super(intakeBase);

    this.controller = controller;
  }
}
