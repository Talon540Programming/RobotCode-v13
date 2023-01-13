package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.constants.RobotMap;
import frc.robot.drivetrain.DrivetrainBase;
import frc.robot.drivetrain.commands.control.AttackJoystickDriveControl;
import frc.robot.intake.IntakeBase;
import frc.robot.intake.commands.control.AttackJoystickIntakeControl;
import frc.robot.shooter.ShooterBase;
import frc.robot.shooter.commands.control.AttackJoystickShooterControl;
import org.talon540.control.AttackJoystick.TalonJoystick;
import org.talon540.control.XboxController.TalonXboxController;


public class RobotContainer {
    private final TalonJoystick leftJoystick = new TalonJoystick(RobotMap.kLeftJoystickPort);
    private final TalonJoystick rightJoystick = new TalonJoystick(RobotMap.kRightJoystickPort);
    private final TalonXboxController xboxController = new TalonXboxController(RobotMap.kXboxControllerPort);

    // Subsystems
    private final DrivetrainBase drivetrainBase = new DrivetrainBase();
    private final ShooterBase shooterBase = new ShooterBase();
    private final IntakeBase intakeBase = new IntakeBase();


    public RobotContainer() {
        DriverStation.silenceJoystickConnectionWarning(true);

        configureBindings();
    }

    private void configureBindings() {
        drivetrainBase.setDefaultCommand(new AttackJoystickDriveControl(drivetrainBase, leftJoystick, rightJoystick));
        shooterBase.setDefaultCommand(new AttackJoystickShooterControl(shooterBase, leftJoystick, rightJoystick));
        intakeBase.setDefaultCommand(new AttackJoystickIntakeControl(intakeBase, leftJoystick, rightJoystick));
    }
}
