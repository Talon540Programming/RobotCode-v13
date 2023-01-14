package frc.robot.shooter.commands.control;

import frc.robot.shooter.ShooterBase;
import org.talon540.control.XboxController.TalonXboxController;

// Abstract because bindings need to be made, and I don't want people to try and instantiate.
public abstract class XboxControllerShooterControl extends ShooterControl {
  private final TalonXboxController controller;

  public XboxControllerShooterControl(ShooterBase shooter, TalonXboxController controller) {
    super(shooter);
    this.controller = controller;
  }
}
